import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Util {    
	
	public String command(String command) {
		
		String output = runCommand(command);
		if (output == null)
			return "";
		else
			return output.trim();
}
	
	public String runCommand(String command) {
		
		String output = null;
		// ProcessBuilder does not read spaces hence need to do this
		String str[] = command.split(" ");
		List<String> commands = new ArrayList<>(Arrays.asList(str));
		try {
			ProcessBuilder pb = new ProcessBuilder(commands).redirectErrorStream(true);
			Process process = pb.start();
			output = new BufferedReader(new InputStreamReader(process.getInputStream())).lines().collect(Collectors.joining("\r\n"));
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
		return output;
	}
	
	public ArrayList<String> getConnectedDevices() {
		
		ArrayList<String> devices = new ArrayList<>();
		String output = command("adb devices");
		devices.addAll(Arrays.stream(output.split("\n"))
		        .map(String::trim)
		        .filter(line -> line.endsWith("device"))
		        .map(line -> line.replace("device", "").trim())
		        .collect(Collectors.toList()));
		return devices;
		
	}
	
	public String getWlanIp(String ID) {
		
		String ip = "";
		String output = command("adb -s " + ID + " shell ip addr show wlan0");
		ip = Arrays.stream(output.split("\n"))
		        .map(String::trim)
		        .filter(line -> line.startsWith("inet") && line.endsWith("wlan0"))
		        .map(line -> line.replace("inet", "").trim())
		        .map(line -> line.substring(0, line.indexOf("/")))
		        .findFirst()
		        .orElse("");
		return ip;
	}
	
	public String getDeviceOSVersion(String ID) {
		
		String os_version;
		os_version = command("adb -s " + ID + " shell getprop ro.build.version.release");
		return os_version.trim();
	}
	
	public String getDeviceName(String ID) {
		
		return command("adb -s " + ID + " shell getprop ro.product.name");
	}
	
	public String getDeviceModel(String ID) {
		
		String output;
		output = command("adb -s " + ID + " shell getprop ro.product.model");
		return output.trim();
	}

	public String getDeviceManufacturer(String ID) {
		
		return command("adb -s " + ID + " shell getprop ro.product.manufacturer");
	}
	
	public void installApp(String ID, String path) {
		
		command("adb -s " + ID + " install -r -d " + path);
	}
	
	public void saveLogs(String ID, String newFolder) {
		
		command("adb -s " + ID + " pull " + "sdcard/Android/data/com.smithmicro.safepath.family/files/logs/ " + newFolder);
	}
	
	public void startWifiDebugging(String ID, String IP) {
		
		String socket = IP + ":5555";
		command("adb -s " + ID + " shell settings put global adb_wifi_enabled 1");
		command("adb -s " + ID + " tcpip 5555"); 
		command("adb -s " + ID + " connect " + socket);

	}
	
	public void stopWifiDebugging(String ID, String IP) {
		
		String socket = IP + ":5555";
		command("adb -s " + ID + " shell settings put global adb_wifi_enabled 1");
		command("adb -s " + ID + " tcpip 5555"); 
		command("adb -s " + ID + " connect " + socket);

	}
	
	public void enableAnalyticsDebug(String ID) {
		command("adb -s " + ID + " shell setprop debug.firebase.analytics.app com.smithmicro.safepath.family");
		command("adb -s " + ID + " shell setprop log.tag.FA VERBOSE");
	}

}
