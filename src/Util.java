
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Util {

//	public String command(String command) {
//		String output = runCommand(command);
//		if (output == null)
//			return "";
//		else
//			return output.trim();
//	}

	public String runCommand(String command) {
		String output = null;
		// ProcessBuilder does not read spaces hence need to do this
		String str[] = command.split(" ");
		List<String> commands = new ArrayList<>(Arrays.asList(str));
		try {
			ProcessBuilder pb = new ProcessBuilder(commands).redirectErrorStream(true);
			Process process = pb.start();
			output = new BufferedReader(new InputStreamReader(process.getInputStream())).lines()
					.collect(Collectors.joining("\r\n"));
			if (!process.waitFor(3, TimeUnit.SECONDS)) {
				// if the process has not exited yet, destroy it forcefully
				process.destroyForcibly();
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (output == null)
			return "";
		else
			return output.trim();
	}
	
	public String runCommand(List<String> command) {
		String output = null;
		// ProcessBuilder does not read spaces hence need to do this
		try {
			ProcessBuilder pb = new ProcessBuilder(command).redirectErrorStream(true);
			Process process = pb.start();
			output = new BufferedReader(new InputStreamReader(process.getInputStream())).lines()
					.collect(Collectors.joining("\r\n"));
			if (!process.waitFor(3, TimeUnit.SECONDS)) {
				// if the process has not exited yet, destroy it forcefully
				process.destroyForcibly();
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (output == null)
			return "";
		else
			return output.trim();
	}

	public ArrayList<String> getConnectedDevices() {
		ArrayList<String> devices = new ArrayList<>();
		String output = runCommand("adb devices");
		devices.addAll(Arrays.stream(output.split("\n")).map(String::trim).filter(line -> line.endsWith("device"))
				.map(line -> line.replace("device", "").trim()).collect(Collectors.toList()));
		return devices;

	}

	public String getWlanIp(String ID) {
		String ip = "";
		String output = runCommand("adb -s " + ID + " shell ip addr show wlan0");
		ip = Arrays.stream(output.split("\n")).map(String::trim)
				.filter(line -> line.startsWith("inet") && line.endsWith("wlan0"))
				.map(line -> line.replace("inet", "").trim()).map(line -> line.substring(0, line.indexOf("/")))
				.findFirst().orElse("");
		return ip;
	}

	public String getDeviceOSVersion(String ID) {
		String os_version;
		os_version = runCommand("adb -s " + ID + " shell getprop ro.build.version.release");
		return os_version.trim();
	}

	public String getDeviceName(String ID) {
		return runCommand("adb -s " + ID + " shell getprop ro.product.name");
	}

	public String getDeviceModel(String ID) {
		String output;
		output = runCommand("adb -s " + ID + " shell getprop ro.product.model");
		return output.trim();
	}

	public String getDeviceManufacturer(String ID) {
		return runCommand("adb -s " + ID + " shell getprop ro.product.manufacturer");
	}

	public void installApp(String ID, String path) {
		runCommand("adb -s " + ID + " install -r -d " + path);
	}

	public void saveLogs(String ID, String newFolder) {
		runCommand("adb -s " + ID + " pull " + "sdcard/Android/data/com.smithmicro.safepath.family/files/logs/ "
				+ newFolder);
		System.out.println("adb -s " + ID + " pull " + "sdcard/Android/data/com.smithmicro.safepath.family/files/logs/ "
				+ newFolder); 
	}

	public void startWifiDebugging(String ID, String IP) {
		runCommand("adb -s " + ID + " shell settings put global adb_wifi_enabled 1");
		runCommand("adb -s " + ID + " tcpip 5555");
		runCommand("adb -s " + ID + " connect " + IP);
	}

	public void stopWifiDebugging(String ID, String IP) {
		runCommand("adb -s " + ID + " disconnect " + IP);
	}

	public void enableAnalyticsDebug(String ID, String installedPackage) {
		runCommand("adb -s " + ID + " shell setprop debug.firebase.analytics.app " + installedPackage);
		runCommand("adb -s " + ID + " shell setprop log.tag.FA VERBOSE");
		System.out.println("adb -s " + ID + " shell setprop debug.firebase.analytics.app " + installedPackage);
	}

	public ArrayList<String> getInstalledPackages(String ID) {
		ArrayList<String> packages = new ArrayList<>();
		String[] output = runCommand("adb -s " + ID + " shell pm list packages").split("\n");
		for (String packagedID : output) {
			packages.add(packagedID.replace("package:", "").trim());
		}
		return packages;
	}
	
	public String getSafePathPackage(String ID) {
		String installedPackage = "";
		ArrayList<String> installedPackages = getInstalledPackages(ID);
		for (int i = 0; i < installedPackages.size(); i++) {
			if (installedPackages.get(i).contains("safepath.family")
					|| installedPackages.get(i).contains("securefamily")
					|| installedPackages.get(i).contains("familymode")) {
				installedPackage = installedPackages.get(i);
			}
		}
		return installedPackage;
	}
	
	public boolean checkIfInstalled(String ID) {
		String installedPackage = getSafePathPackage(ID);
		if (installedPackage.equals("com.smithmicro.tmobile.familymode.test")
				|| installedPackage.equals("com.smithmicro.att.securefamily")
				|| installedPackage.equals("com.att.securefamilycompanion")
				|| installedPackage.equals("com.smithmicro.safepath.family")
				|| installedPackage.equals("com.smithmicro.safepath.family.child")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean uninstallApp(String ID, String appPackage) {
		String output = runCommand("adb -s " + ID + " shell pm uninstall " + appPackage);
		if (output.contains("Success")) {
			return true;
		} else {
			return false;
		}
	}
	
	public void reboot(String ID) {
		runCommand("adb -s " + ID + " reboot");
	}
	
	public String takeScreenshot(String ID, String target, String fileName) {
		runCommand("adb -s " + ID + " shell screencap " + target + fileName);
		return runCommand("adb -s " + ID + " shell ls -t /sdcard/screenshot.png | grep " + fileName + " -m 1");		
	}
	
	public boolean pullFile(String ID, String source, String target) {
		String output = runCommand("adb -s " + ID + " pull " + source + " " + target);
		System.out.println("adb -s " + ID + " pull " + source + " " + target);
		return output.contains("file pulled");
	}

	public void deleteFile(String ID, String target) {
		runCommand("adb -s " + ID + " shell rm " + target);
	}
	
//	public ArrayList<String> getProcesses(String ID, String threadName) {
//		String[] output = runCommand("adb -s " + ID + " shell pidof '" + threadName + "'").split(" ");
//		ArrayList<String> processes = new ArrayList<>();
//		for (String line : output) {
//			processes.add(line.split(" ")[0]);
//			processes.removeAll(Arrays.asList("", null));
//		}
//		return processes;
//	}
//
//	public void clearLogBuffer(String ID) {
//		runCommand("adb -s " + ID + " shell logcat -c");
//	}
//
//	/*
//	* For windows, logID must be fileName
//	* For Mac, logID must be filePath + fileName
//	*/
//	public Object startLogcat(String ID, final String logID, final String grep) {
//		ArrayList<String> pidBefore = getProcesses(ID, "logcat");
//		Thread logcat = new Thread(() -> {
//				if (grep == null)
//					runCommand("adb -s " + ID + " shell logcat -v threadtime > /sdcard/" + logID + ".txt");
//				else
//					runCommand("adb -s " + ID + " shell logcat -v threadtime | grep - i '" + grep + "'> /sdcard/" + logID + ".txt");
//			
//		});
//		logcat.setName(logID);
//		logcat.start();
//		logcat.interrupt();
//
//		ArrayList<String> pidAfter = getProcesses(ID, "logcat");
//		Timer timer = new Timer();
//		timer.start();
//		while (!timer.expired(5)) {
//			if (pidBefore.size() > 0)
//				pidAfter.removeAll(pidBefore);
//			if (pidAfter.size() > 0)
//				break;
//			pidAfter = getProcesses(ID, "logcat");
//		}
//
//		if (pidAfter.size() >= 1)
//			return pidAfter.get(0);
//		else
//			throw new RuntimeException("Failed to start logcat process!");
//	}
//	
//	public void stopLogcat(String ID, Object PID) {
//			runCommand("adb -s " + ID + " shell kill -INT " + PID);
//		
//		ArrayList<String> pidAfter = getProcesses(ID, "logcat");
//		if (pidAfter.size() > 0) {
//			Timer timer = new Timer();
//			timer.start();
//			while (!timer.expired(30) && pidAfter.contains(PID)) {
//					runCommand("adb -s " + ID + " shell kill -INT " + PID);
//
////				Utility.waitFor(5); // give some time for resources to release
//				pidAfter = getProcesses(ID, "logcat");
//			}
//		}
//	}
	
}
