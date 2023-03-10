import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MyFrame extends JFrame implements ActionListener, PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Util command1 = new Util();
	ArrayList<String> output1 = command1.getConnectedDevices();
	private static int counter = 0;
	private static final Object lock = new Object();
	ArrayList<String> ips = new ArrayList<String>();
	SimpleAttributeSet center = new SimpleAttributeSet();

	ImageIcon buttonIcon = new ImageIcon("display.png");
	ImageIcon logo_tmo = new ImageIcon("tmo.png");
	ImageIcon logo_att = new ImageIcon("att.png");
	ImageIcon logo_product = new ImageIcon("product.png");
	ImageIcon frameIcon = new ImageIcon("Android.png");
	ImageIcon notInstalled = new ImageIcon("Android.png");

	Path temp = null;
	File file = null;

	JButton saveLogsButton1, saveLogsButton2, saveLogsButton3, saveLogsButton4;
	JButton wifiDebbug1, wifiDebbug2, wifiDebbug3, wifiDebbug4;
	JButton enableFirebase1, enableFirebase2, enableFirebase3, enableFirebase4;
	JButton reboot1, reboot2, reboot3, reboot4;
	JButton takeScreenshotButton1, takeScreenshotButton2, takeScreenshotButton3, takeScreenshotButton4;
	JButton uninstallApp1, uninstallApp2, uninstallApp3, uninstallApp4;
	JButton devicesButton;
	JButton fileButton;
	JButton installButton, uninstallAllButton;
	JRadioButton radio1, radio2, radio3, radio4;
	JTextField fileTextField;
	JLabel labelIcon1, labelIcon2, labelIcon3, labelIcon4;
	JTextPane staticPane;
	JTextPane device1TextPane, device2TextPane, device3TextPane, device4TextPane;
	JProgressBar bar;

	boolean radio1State = false, radio2State = false, radio3State = false, radio4State = false;

	MyFrame() {

		// Get list of wlan0 IP addresses
		for (int i = 0; i < output1.size(); i++) {
			ips.add(command1.getWlanIp(output1.get(i)));
		}

		Image image1 = buttonIcon.getImage();
		Image newimg = image1.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		buttonIcon = new ImageIcon(newimg);

		Image image2 = logo_tmo.getImage();
		newimg = image2.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		logo_tmo = new ImageIcon(newimg);

		Image image3 = logo_att.getImage();
		newimg = image3.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		logo_att = new ImageIcon(newimg);

		Image image4 = logo_product.getImage();
		newimg = image4.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		logo_product = new ImageIcon(newimg);

		Image image5 = notInstalled.getImage();
		newimg = image5.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
		notInstalled = new ImageIcon(newimg);

		// Setting the menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);

		this.setJMenuBar(menuBar);

		labelIcon1 = new JLabel();
		labelIcon1.setBounds(275, 0, 120, 50);
		labelIcon1.setIcon(notInstalled);
		labelIcon1.setText("test text");
		labelIcon1.setVisible(false);

		labelIcon2 = new JLabel();
		labelIcon2.setBounds(485, 0, 120, 50);
		labelIcon2.setIcon(notInstalled);
		labelIcon2.setText("test text");
		labelIcon2.setVisible(false);

		labelIcon3 = new JLabel();
		labelIcon3.setBounds(695, 0, 120, 50);
		labelIcon3.setIcon(notInstalled);
		labelIcon3.setText("test text");
		labelIcon3.setVisible(false);

		labelIcon4 = new JLabel();
		labelIcon4.setBounds(905, 0, 120, 50);
		labelIcon4.setIcon(notInstalled);
		labelIcon4.setText("test text");
		labelIcon4.setVisible(false);

		devicesButton = new JButton("Display Connected Devices");
		devicesButton.setBounds(0, 0, 198, 50);
		devicesButton.addActionListener(this);
		devicesButton.setFocusable(false);
		devicesButton.setBorder(BorderFactory.createEtchedBorder());
		devicesButton.setBackground(Color.lightGray);
		devicesButton.setFont(new Font("Calibri", Font.BOLD, 13));
		devicesButton.setIcon(buttonIcon);

		fileButton = new JButton("Select Build");
		fileButton.setBounds(0, 270, 100, 25);
		fileButton.addActionListener(this);
		fileButton.setFocusable(false);
		fileButton.setBorder(BorderFactory.createEtchedBorder());
		fileButton.setBackground(Color.lightGray);
		fileButton.setFont(new Font("Calibri", Font.BOLD, 15));
		this.add(fileButton);

		saveLogsButton1 = new JButton("Pull SP Logs");
		saveLogsButton1.setBounds(200, 160, 95, 30);
		saveLogsButton1.addActionListener(this);
		saveLogsButton1.setFocusable(false);
		saveLogsButton1.setBorder(BorderFactory.createEtchedBorder());
		saveLogsButton1.setBackground(Color.lightGray);
		saveLogsButton1.setFont(new Font("Calibri", Font.BOLD, 15));
		saveLogsButton1.setVisible(false);
		this.add(saveLogsButton1);

		saveLogsButton2 = new JButton("Pull SP Logs");
		saveLogsButton2.setBounds(410, 160, 95, 30);
		saveLogsButton2.addActionListener(this);
		saveLogsButton2.setFocusable(false);
		saveLogsButton2.setBorder(BorderFactory.createEtchedBorder());
		saveLogsButton2.setBackground(Color.lightGray);
		saveLogsButton2.setFont(new Font("Calibri", Font.BOLD, 15));
		saveLogsButton2.setVisible(false);
		this.add(saveLogsButton2);

		saveLogsButton3 = new JButton("Pull SP Logs");
		saveLogsButton3.setBounds(620, 160, 95, 30);
		saveLogsButton3.addActionListener(this);
		saveLogsButton3.setFocusable(false);
		saveLogsButton3.setBorder(BorderFactory.createEtchedBorder());
		saveLogsButton3.setBackground(Color.lightGray);
		saveLogsButton3.setFont(new Font("Calibri", Font.BOLD, 15));
		saveLogsButton3.setVisible(false);
		this.add(saveLogsButton3);

		saveLogsButton4 = new JButton("Pull SP Logs");
		saveLogsButton4.setBounds(830, 160, 95, 30);
		saveLogsButton4.addActionListener(this);
		saveLogsButton4.setFocusable(false);
		saveLogsButton4.setBorder(BorderFactory.createEtchedBorder());
		saveLogsButton4.setBackground(Color.lightGray);
		saveLogsButton4.setFont(new Font("Calibri", Font.BOLD, 15));
		saveLogsButton4.setVisible(false);
		this.add(saveLogsButton4);

		takeScreenshotButton1 = new JButton("Screenshot");
		takeScreenshotButton1.setBounds(310, 190, 90, 30);
		takeScreenshotButton1.addActionListener(this);
		takeScreenshotButton1.setFocusable(false);
		takeScreenshotButton1.setBorder(BorderFactory.createEtchedBorder());
		takeScreenshotButton1.setBackground(Color.lightGray);
		takeScreenshotButton1.setFont(new Font("Calibri", Font.BOLD, 15));
		takeScreenshotButton1.setVisible(false);
		this.add(takeScreenshotButton1);

		takeScreenshotButton2 = new JButton("Screenshot");
		takeScreenshotButton2.setBounds(520, 190, 90, 30);
		takeScreenshotButton2.addActionListener(this);
		takeScreenshotButton2.setFocusable(false);
		takeScreenshotButton2.setBorder(BorderFactory.createEtchedBorder());
		takeScreenshotButton2.setBackground(Color.lightGray);
		takeScreenshotButton2.setFont(new Font("Calibri", Font.BOLD, 15));
		takeScreenshotButton2.setVisible(false);
		this.add(takeScreenshotButton2);

		takeScreenshotButton3 = new JButton("Screenshot");
		takeScreenshotButton3.setBounds(730, 190, 90, 30);
		takeScreenshotButton3.addActionListener(this);
		takeScreenshotButton3.setFocusable(false);
		takeScreenshotButton3.setBorder(BorderFactory.createEtchedBorder());
		takeScreenshotButton3.setBackground(Color.lightGray);
		takeScreenshotButton3.setFont(new Font("Calibri", Font.BOLD, 15));
		takeScreenshotButton3.setVisible(false);
		this.add(takeScreenshotButton3);

		takeScreenshotButton4 = new JButton("Screenshot");
		takeScreenshotButton4.setBounds(940, 190, 90, 30);
		takeScreenshotButton4.addActionListener(this);
		takeScreenshotButton4.setFocusable(false);
		takeScreenshotButton4.setBorder(BorderFactory.createEtchedBorder());
		takeScreenshotButton4.setBackground(Color.lightGray);
		takeScreenshotButton4.setFont(new Font("Calibri", Font.BOLD, 15));
		takeScreenshotButton4.setVisible(false);
		this.add(takeScreenshotButton4);

		uninstallApp1 = new JButton("Uninstall");
		uninstallApp1.setBounds(310, 220, 90, 30);
		uninstallApp1.addActionListener(this);
		uninstallApp1.setFocusable(false);
		uninstallApp1.setBorder(BorderFactory.createEtchedBorder());
		uninstallApp1.setBackground(Color.lightGray);
		uninstallApp1.setFont(new Font("Calibri", Font.BOLD, 15));
		uninstallApp1.setEnabled(false);
		uninstallApp1.setVisible(false);
		this.add(uninstallApp1);

		uninstallApp2 = new JButton("Uninstall");
		uninstallApp2.setBounds(520, 220, 90, 30);
		uninstallApp2.addActionListener(this);
		uninstallApp2.setFocusable(false);
		uninstallApp2.setBorder(BorderFactory.createEtchedBorder());
		uninstallApp2.setBackground(Color.lightGray);
		uninstallApp2.setFont(new Font("Calibri", Font.BOLD, 15));
		uninstallApp2.setEnabled(false);
		uninstallApp2.setVisible(false);
		this.add(uninstallApp2);

		uninstallApp3 = new JButton("Uninstall");
		uninstallApp3.setBounds(730, 220, 90, 30);
		uninstallApp3.addActionListener(this);
		uninstallApp3.setFocusable(false);
		uninstallApp3.setBorder(BorderFactory.createEtchedBorder());
		uninstallApp3.setBackground(Color.lightGray);
		uninstallApp3.setFont(new Font("Calibri", Font.BOLD, 15));
		uninstallApp3.setEnabled(false);
		uninstallApp3.setVisible(false);
		this.add(uninstallApp3);

		uninstallApp4 = new JButton("Uninstall");
		uninstallApp4.setBounds(940, 220, 90, 30);
		uninstallApp4.addActionListener(this);
		uninstallApp4.setFocusable(false);
		uninstallApp4.setBorder(BorderFactory.createEtchedBorder());
		uninstallApp4.setBackground(Color.lightGray);
		uninstallApp4.setFont(new Font("Calibri", Font.BOLD, 15));
		uninstallApp4.setEnabled(false);
		uninstallApp4.setVisible(false);
		this.add(uninstallApp4);

		wifiDebbug1 = new JButton("WiFi Debug");
		wifiDebbug1.setBounds(310, 160, 90, 30);
		wifiDebbug1.addActionListener(this);
		wifiDebbug1.setFocusable(false);
		wifiDebbug1.setBorder(BorderFactory.createEtchedBorder());
		wifiDebbug1.setBackground(Color.lightGray);
		wifiDebbug1.setFont(new Font("Calibri", Font.BOLD, 15));
		if (output1.size() > 0) {
			if (output1.get(0).endsWith(":5555")) {
				wifiDebbug1.setText("Disable WiFi");
			}
		}
		wifiDebbug1.setVisible(false);
		this.add(wifiDebbug1);

		wifiDebbug2 = new JButton("WiFi Debug");
		wifiDebbug2.setBounds(520, 160, 90, 30);
		wifiDebbug2.addActionListener(this);
		wifiDebbug2.setFocusable(false);
		wifiDebbug2.setBorder(BorderFactory.createEtchedBorder());
		wifiDebbug2.setBackground(Color.lightGray);
		wifiDebbug2.setFont(new Font("Calibri", Font.BOLD, 15));
		if (output1.size() > 1) {
			if (output1.get(1).endsWith(":5555")) {
				wifiDebbug2.setText("Disable WiFi");
			}
		}
		wifiDebbug2.setVisible(false);
		this.add(wifiDebbug2);

		wifiDebbug3 = new JButton("WiFi Debug");
		wifiDebbug3.setBounds(730, 160, 90, 30);
		wifiDebbug3.addActionListener(this);
		wifiDebbug3.setFocusable(false);
		wifiDebbug3.setBorder(BorderFactory.createEtchedBorder());
		wifiDebbug3.setBackground(Color.lightGray);
		wifiDebbug3.setFont(new Font("Calibri", Font.BOLD, 15));
		if (output1.size() > 2) {
			if (output1.get(2).endsWith(":5555")) {
				wifiDebbug3.setText("Disable WiFi");
			}
		}
		wifiDebbug3.setVisible(false);
		this.add(wifiDebbug3);

		wifiDebbug4 = new JButton("WiFi Debug");
		wifiDebbug4.setBounds(940, 160, 90, 30);
		wifiDebbug4.addActionListener(this);
		wifiDebbug4.setFocusable(false);
		wifiDebbug4.setBorder(BorderFactory.createEtchedBorder());
		wifiDebbug4.setBackground(Color.lightGray);
		wifiDebbug4.setFont(new Font("Calibri", Font.BOLD, 15));
		if (output1.size() > 3) {
			if (output1.get(3).endsWith(":5555")) {
				wifiDebbug4.setText("Disable WiFi");
			}
		}
		wifiDebbug4.setVisible(false);
		this.add(wifiDebbug4);

		enableFirebase1 = new JButton("Firebase");
		enableFirebase1.setBounds(200, 220, 95, 30);
		enableFirebase1.addActionListener(this);
		enableFirebase1.setFocusable(false);
		enableFirebase1.setBorder(BorderFactory.createEtchedBorder());
		enableFirebase1.setBackground(Color.lightGray);
		enableFirebase1.setFont(new Font("Calibri", Font.BOLD, 15));
		enableFirebase1.setEnabled(false);
		enableFirebase1.setVisible(false);
		this.add(enableFirebase1);

		enableFirebase2 = new JButton("Firebase");
		enableFirebase2.setBounds(410, 220, 95, 30);
		enableFirebase2.addActionListener(this);
		enableFirebase2.setFocusable(false);
		enableFirebase2.setBorder(BorderFactory.createEtchedBorder());
		enableFirebase2.setBackground(Color.lightGray);
		enableFirebase2.setFont(new Font("Calibri", Font.BOLD, 15));
		enableFirebase2.setEnabled(false);
		enableFirebase2.setVisible(false);
		this.add(enableFirebase2);

		enableFirebase3 = new JButton("Firebase");
		enableFirebase3.setBounds(620, 220, 95, 30);
		enableFirebase3.addActionListener(this);
		enableFirebase3.setFocusable(false);
		enableFirebase3.setBorder(BorderFactory.createEtchedBorder());
		enableFirebase3.setBackground(Color.lightGray);
		enableFirebase3.setFont(new Font("Calibri", Font.BOLD, 15));
		enableFirebase3.setEnabled(false);
		enableFirebase3.setVisible(false);
		this.add(enableFirebase3);

		enableFirebase4 = new JButton("Firebase");
		enableFirebase4.setBounds(830, 220, 95, 30);
		enableFirebase4.addActionListener(this);
		enableFirebase4.setFocusable(false);
		enableFirebase4.setBorder(BorderFactory.createEtchedBorder());
		enableFirebase4.setBackground(Color.lightGray);
		enableFirebase4.setFont(new Font("Calibri", Font.BOLD, 15));
		enableFirebase4.setEnabled(false);
		enableFirebase4.setVisible(false);
		this.add(enableFirebase4);

		reboot1 = new JButton("Reboot");
		reboot1.setBounds(200, 190, 95, 30);
		reboot1.addActionListener(this);
		reboot1.setFocusable(false);
		reboot1.setBorder(BorderFactory.createEtchedBorder());
		reboot1.setBackground(Color.lightGray);
		reboot1.setFont(new Font("Calibri", Font.BOLD, 15));
		reboot1.setVisible(false);
		this.add(reboot1);

		reboot2 = new JButton("Reboot");
		reboot2.setBounds(410, 190, 95, 30);
		reboot2.addActionListener(this);
		reboot2.setFocusable(false);
		reboot2.setBorder(BorderFactory.createEtchedBorder());
		reboot2.setBackground(Color.lightGray);
		reboot2.setFont(new Font("Calibri", Font.BOLD, 15));
		reboot2.setVisible(false);
		this.add(reboot2);

		reboot3 = new JButton("Reboot");
		reboot3.setBounds(620, 190, 95, 30);
		reboot3.addActionListener(this);
		reboot3.setFocusable(false);
		reboot3.setBorder(BorderFactory.createEtchedBorder());
		reboot3.setBackground(Color.lightGray);
		reboot3.setFont(new Font("Calibri", Font.BOLD, 15));
		reboot3.setVisible(false);
		this.add(reboot3);

		reboot4 = new JButton("Reboot");
		reboot4.setBounds(830, 190, 95, 30);
		reboot4.addActionListener(this);
		reboot4.setFocusable(false);
		reboot4.setBorder(BorderFactory.createEtchedBorder());
		reboot4.setBackground(Color.lightGray);
		reboot4.setFont(new Font("Calibri", Font.BOLD, 15));
		reboot4.setVisible(false);
		this.add(reboot4);

		installButton = new JButton("Install");
		installButton.setBounds(0, 300, 100, 50);
		installButton.addActionListener(this);
		installButton.setFocusable(false);
		installButton.setBorder(BorderFactory.createEtchedBorder());
		installButton.setBackground(Color.lightGray);
		installButton.setFont(new Font("Calibri", Font.BOLD, 25));
		installButton.setEnabled(false);
		this.add(installButton);

		uninstallAllButton = new JButton("Uninstall All");
		uninstallAllButton.setBounds(0, 200, 100, 50);
		uninstallAllButton.addActionListener(this);
		uninstallAllButton.setFocusable(false);
		uninstallAllButton.setBorder(BorderFactory.createEtchedBorder());
		uninstallAllButton.setBackground(Color.lightGray);
		uninstallAllButton.setFont(new Font("Calibri", Font.BOLD, 16));
		uninstallAllButton.setEnabled(false);
		int j = 0;
		while (j < output1.size()) {
			if (command1.checkIfInstalled(output1.get(j))) {
				uninstallAllButton.setEnabled(true);
			}
			j++;
			break;
		}
		this.add(uninstallAllButton);

		fileTextField = new JTextField();
		fileTextField.setPreferredSize(new Dimension(250, 40));
		fileTextField.setBounds(102, 270, 505, 25);
		fileTextField.setEditable(false);
		this.add(fileTextField);

		staticPane = new JTextPane();
		staticPane.setBounds(0, 50, 200, 110);
		staticPane.setEditable(false);
		staticPane.setVisible(true);
		staticPane.setBackground(new Color(238, 238, 238));
		staticPane.setBorder(BorderFactory.createEtchedBorder());
		staticPane.setFont(new Font("Calibri", Font.BOLD, 16));
		StyledDocument doc = staticPane.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		staticPane.setText("Serial number: " + "\n" + "Manufacturer: " + "\n" + "Model: " + "\n" + "OS Version: " + "\n"
				+ "WiFi IP Address: ");
		this.add(staticPane);

		device1TextPane = new JTextPane();
		device1TextPane.setBounds(200, 50, 200, 110);
		device1TextPane.setEditable(false);
		device1TextPane.setVisible(false);
		device1TextPane.setBackground(new Color(238, 238, 238));
		device1TextPane.setBorder(BorderFactory.createEtchedBorder());
		device1TextPane.setFont(new Font("Calibri", Font.BOLD, 16));
		StyledDocument doc1 = device1TextPane.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc1.setParagraphAttributes(0, doc1.getLength(), center, false);
		this.add(device1TextPane);

		device2TextPane = new JTextPane();
		device2TextPane.setBounds(410, 50, 200, 110);
		device2TextPane.setEditable(false);
		device2TextPane.setVisible(false);
		device2TextPane.setBackground(new Color(238, 238, 238));
		device2TextPane.setBorder(BorderFactory.createEtchedBorder());
		device2TextPane.setFont(new Font("Calibri", Font.BOLD, 16));
		StyledDocument doc2 = device2TextPane.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc2.setParagraphAttributes(0, doc2.getLength(), center, false);
		this.add(device2TextPane);

		device3TextPane = new JTextPane();
		device3TextPane.setBounds(620, 50, 200, 110);
		device3TextPane.setEditable(false);
		device3TextPane.setVisible(false);
		device3TextPane.setBackground(new Color(238, 238, 238));
		device3TextPane.setBorder(BorderFactory.createEtchedBorder());
		device3TextPane.setFont(new Font("Calibri", Font.BOLD, 16));
		StyledDocument doc3 = device3TextPane.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc3.setParagraphAttributes(0, doc3.getLength(), center, false);
		this.add(device3TextPane);

		device4TextPane = new JTextPane();
		device4TextPane.setBounds(830, 50, 200, 110);
		device4TextPane.setEditable(false);
		device4TextPane.setVisible(false);
		device4TextPane.setBackground(new Color(238, 238, 238));
		device4TextPane.setBorder(BorderFactory.createEtchedBorder());
		device4TextPane.setFont(new Font("Calibri", Font.BOLD, 16));
		StyledDocument doc4 = device4TextPane.getStyledDocument();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc4.setParagraphAttributes(0, doc4.getLength(), center, false);
		this.add(device4TextPane);

		this.setTitle("Adb Toolkit");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(1045, 420);
		this.setIconImage(frameIcon.getImage());
		this.add(devicesButton);
		this.add(labelIcon1);
		this.add(labelIcon2);
		this.add(labelIcon3);
		this.add(labelIcon4);

		radio1 = new JRadioButton("Device 1");
		radio1.addActionListener(this);
		radio1.setBounds(200, 20, 75, 15);
		radio1.setVisible(false);
		this.add(radio1);

		radio2 = new JRadioButton("Device 2");
		radio2.addActionListener(this);
		radio2.setBounds(410, 20, 75, 15);
		radio2.setVisible(false);
		this.add(radio2);

		radio3 = new JRadioButton("Device 3");
		radio3.addActionListener(this);
		radio3.setBounds(620, 20, 75, 15);
		radio3.setVisible(false);
		this.add(radio3);

		radio4 = new JRadioButton("Device 4");
		radio4.addActionListener(this);
		radio4.setBounds(830, 20, 75, 15);
		radio4.setVisible(false);
		this.add(radio4);

		bar = new JProgressBar();
		bar.setBounds(102, 300, 505, 25);
		bar.setStringPainted(true);
		bar.setFont(new Font("Calibri", Font.BOLD, 13));
		bar.setBackground(new Color(238, 238, 238));
		bar.setString("");
		this.add(bar);

		for (int i = 0; i < output1.size(); i++) {

			if (i == 0) {

				radio1.setSelected(true);
				radio1.setVisible(true);
				System.out.println(command1.getSafePathPackage(output1.get(0)));
				switch (command1.getSafePathPackage(output1.get(0))) {
				case "com.smithmicro.tmobile.familymode.test":
					labelIcon1.setIcon(logo_tmo);
					labelIcon1.setText("Family Mode");
					labelIcon1.setVisible(true);
					uninstallApp1.setEnabled(true);
					uninstallApp1.setVisible(true);
					enableFirebase1.setEnabled(true);
					enableFirebase1.setVisible(true);
					saveLogsButton1.setEnabled(true);
					break;
				case "com.smithmicro.safepath.family":
					labelIcon1.setIcon(logo_product);
					labelIcon1.setText("SP Family");
					labelIcon1.setVisible(true);
					uninstallApp1.setEnabled(true);
					uninstallApp1.setVisible(true);
					enableFirebase1.setEnabled(true);
					enableFirebase1.setVisible(true);
					saveLogsButton1.setEnabled(true);
					break;
				case "com.smithmicro.att.securefamily":
					labelIcon1.setIcon(logo_att);
					labelIcon1.setText("Secure Family");
					labelIcon1.setVisible(true);
					uninstallApp1.setEnabled(true);
					uninstallApp1.setVisible(true);
					enableFirebase1.setEnabled(true);
					enableFirebase1.setVisible(true);
					saveLogsButton1.setEnabled(true);
					break;
				case "":
					labelIcon1.setIcon(notInstalled);
					labelIcon1.setText("Not Installed");
					labelIcon1.setVisible(true);
					uninstallApp1.setVisible(true);
					enableFirebase1.setVisible(true);
					saveLogsButton1.setEnabled(false);
					break;
				}
				saveLogsButton1.setVisible(true);
				wifiDebbug1.setText("WiFi Debug");
				if (output1.get(i).endsWith(":5555")) {
					wifiDebbug1.setText("Disable WiFi");
				}
				wifiDebbug1.setVisible(true);
				enableFirebase1.setVisible(true);
				reboot1.setVisible(true);
				device1TextPane.setText(output1.get(i) + "\n" + command1.getDeviceManufacturer(output1.get(i)) + "\n"
						+ command1.getDeviceModel(output1.get(i)) + "\n" + "Android "
						+ command1.getDeviceOSVersion(output1.get(i)) + "\n" + ips.get(i));
				device1TextPane.setVisible(true);
				takeScreenshotButton1.setVisible(true);

			} else if (i == 1) {

				radio2.setSelected(true);
				radio2.setVisible(true);
				System.out.println(command1.getSafePathPackage(output1.get(1)));
				switch (command1.getSafePathPackage(output1.get(1))) {
				case "com.smithmicro.tmobile.familymode.test":
					labelIcon2.setIcon(logo_tmo);
					labelIcon2.setText("Family Mode");
					labelIcon2.setVisible(true);
					uninstallApp2.setEnabled(true);
					uninstallApp2.setVisible(true);
					enableFirebase2.setEnabled(true);
					enableFirebase2.setVisible(true);
					saveLogsButton2.setEnabled(true);
					break;
				case "com.smithmicro.safepath.family":
					labelIcon2.setIcon(logo_product);
					labelIcon2.setText("SP Family");
					labelIcon2.setVisible(true);
					uninstallApp2.setEnabled(true);
					uninstallApp2.setVisible(true);
					enableFirebase2.setEnabled(true);
					enableFirebase2.setVisible(true);
					saveLogsButton2.setEnabled(true);
					break;
				case "com.smithmicro.att.securefamily":
					labelIcon2.setIcon(logo_att);
					labelIcon2.setText("Secure Family");
					labelIcon2.setVisible(true);
					uninstallApp2.setEnabled(true);
					uninstallApp2.setVisible(true);
					enableFirebase2.setEnabled(true);
					enableFirebase2.setVisible(true);
					saveLogsButton2.setEnabled(true);
					break;
				case "":
					labelIcon2.setIcon(notInstalled);
					labelIcon2.setText("Not Installed");
					labelIcon2.setVisible(true);
					uninstallApp2.setVisible(true);
					enableFirebase2.setVisible(true);
					saveLogsButton2.setEnabled(false);
					break;
				}
				saveLogsButton2.setVisible(true);
				wifiDebbug2.setText("WiFi Debug");
				if (output1.get(i).endsWith(":5555")) {
					wifiDebbug2.setText("Disable WiFi");
				}
				wifiDebbug2.setVisible(true);
				enableFirebase2.setVisible(true);
				reboot2.setVisible(true);
				device2TextPane.setText(output1.get(i) + "\n" + command1.getDeviceManufacturer(output1.get(i)) + "\n"
						+ command1.getDeviceModel(output1.get(i)) + "\n" + "Android "
						+ command1.getDeviceOSVersion(output1.get(i)) + "\n" + ips.get(i));
				device2TextPane.setVisible(true);
				takeScreenshotButton2.setVisible(true);

			} else if (i == 2) {

				radio3.setSelected(true);
				radio3.setVisible(true);
				System.out.println(command1.getSafePathPackage(output1.get(2)));
				switch (command1.getSafePathPackage(output1.get(2))) {
				case "com.smithmicro.tmobile.familymode.test":
					labelIcon3.setIcon(logo_tmo);
					labelIcon3.setText("Family Mode");
					labelIcon3.setVisible(true);
					uninstallApp3.setEnabled(true);
					uninstallApp3.setVisible(true);
					enableFirebase3.setEnabled(true);
					enableFirebase3.setVisible(true);
					saveLogsButton3.setEnabled(true);
					break;
				case "com.smithmicro.safepath.family":
					labelIcon3.setIcon(logo_product);
					labelIcon3.setText("SP Family");
					labelIcon3.setVisible(true);
					uninstallApp3.setEnabled(true);
					uninstallApp3.setVisible(true);
					enableFirebase3.setEnabled(true);
					enableFirebase3.setVisible(true);
					saveLogsButton3.setEnabled(true);
					break;
				case "com.smithmicro.att.securefamily":
					labelIcon3.setIcon(logo_att);
					labelIcon3.setText("Secure Family");
					labelIcon3.setVisible(true);
					uninstallApp3.setEnabled(true);
					uninstallApp3.setVisible(true);
					enableFirebase3.setEnabled(true);
					enableFirebase3.setVisible(true);
					saveLogsButton3.setEnabled(true);
					break;
				case "":
					labelIcon3.setIcon(notInstalled);
					labelIcon3.setText("Not Installed");
					labelIcon3.setVisible(true);
					uninstallApp3.setVisible(true);
					enableFirebase3.setVisible(true);
					saveLogsButton3.setEnabled(false);
					break;
				}
				saveLogsButton3.setVisible(true);
				wifiDebbug3.setText("WiFi Debug");
				if (output1.get(i).endsWith(":5555")) {
					wifiDebbug3.setText("Disable WiFi");
				}
				wifiDebbug3.setVisible(true);
				enableFirebase3.setVisible(true);
				reboot3.setVisible(true);
				device3TextPane.setText(output1.get(i) + "\n" + command1.getDeviceManufacturer(output1.get(i)) + "\n"
						+ command1.getDeviceModel(output1.get(i)) + "\n" + "Android "
						+ command1.getDeviceOSVersion(output1.get(i)) + "\n" + ips.get(i));
				device3TextPane.setVisible(true);
				takeScreenshotButton3.setVisible(true);

			} else if (i == 3) {

				radio4.setSelected(true);
				radio4.setVisible(true);
				System.out.println(command1.getSafePathPackage(output1.get(3)));
				switch (command1.getSafePathPackage(output1.get(3))) {
				case "com.smithmicro.tmobile.familymode.test":
					labelIcon4.setIcon(logo_tmo);
					labelIcon4.setText("Family Mode");
					labelIcon4.setVisible(true);
					uninstallApp4.setEnabled(true);
					uninstallApp4.setVisible(true);
					enableFirebase4.setEnabled(true);
					enableFirebase4.setVisible(true);
					saveLogsButton4.setEnabled(true);
					break;
				case "com.smithmicro.safepath.family":
					labelIcon4.setIcon(logo_product);
					labelIcon4.setText("SP Family");
					labelIcon4.setVisible(true);
					uninstallApp4.setEnabled(true);
					uninstallApp4.setVisible(true);
					enableFirebase4.setEnabled(true);
					enableFirebase4.setVisible(true);
					saveLogsButton4.setEnabled(true);
					break;
				case "com.smithmicro.att.securefamily":
					labelIcon4.setIcon(logo_att);
					labelIcon4.setText("SecureFamily");
					labelIcon4.setVisible(true);
					uninstallApp4.setEnabled(true);
					uninstallApp4.setVisible(true);
					enableFirebase4.setEnabled(true);
					enableFirebase4.setVisible(true);
					saveLogsButton4.setEnabled(true);
					break;
				case "":
					labelIcon4.setIcon(notInstalled);
					labelIcon4.setText("Not Installed");
					labelIcon4.setVisible(true);
					uninstallApp4.setVisible(true);
					enableFirebase4.setVisible(true);
					saveLogsButton4.setEnabled(false);
					break;
				}
				saveLogsButton4.setVisible(true);
				wifiDebbug4.setText("WiFi Debug");
				if (output1.get(i).endsWith(":5555")) {
					wifiDebbug4.setText("Disable WiFi");
				}
				wifiDebbug4.setVisible(true);
				enableFirebase4.setVisible(true);
				reboot4.setVisible(true);
				device4TextPane.setText(output1.get(i) + "\n" + command1.getDeviceManufacturer(output1.get(i)) + "\n"
						+ command1.getDeviceModel(output1.get(i)) + "\n" + "Android "
						+ command1.getDeviceOSVersion(output1.get(i)) + "\n" + ips.get(i));
				device4TextPane.setVisible(true);
				takeScreenshotButton4.setVisible(true);
			}

		}

		this.setVisible(true);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			bar.setIndeterminate(false);
			bar.setValue(progress);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == devicesButton) {
			device1TextPane.setVisible(false);
			device2TextPane.setVisible(false);
			device3TextPane.setVisible(false);
			device4TextPane.setVisible(false);

			radio1.setVisible(false);
			radio2.setVisible(false);
			radio3.setVisible(false);
			radio4.setVisible(false);

			saveLogsButton1.setVisible(false);
			saveLogsButton2.setVisible(false);
			saveLogsButton3.setVisible(false);
			saveLogsButton4.setVisible(false);

			takeScreenshotButton1.setVisible(false);
			takeScreenshotButton2.setVisible(false);
			takeScreenshotButton3.setVisible(false);
			takeScreenshotButton4.setVisible(false);

			wifiDebbug1.setVisible(false);
			wifiDebbug2.setVisible(false);
			wifiDebbug3.setVisible(false);
			wifiDebbug4.setVisible(false);

			enableFirebase1.setEnabled(false);
			enableFirebase2.setEnabled(false);
			enableFirebase3.setEnabled(false);
			enableFirebase4.setEnabled(false);
			enableFirebase1.setVisible(false);
			enableFirebase2.setVisible(false);
			enableFirebase3.setVisible(false);
			enableFirebase4.setVisible(false);

			uninstallApp1.setEnabled(false);
			uninstallApp2.setEnabled(false);
			uninstallApp3.setEnabled(false);
			uninstallApp4.setEnabled(false);
			uninstallApp1.setVisible(false);
			uninstallApp2.setVisible(false);
			uninstallApp3.setVisible(false);
			uninstallApp4.setVisible(false);

			reboot1.setVisible(false);
			reboot2.setVisible(false);
			reboot3.setVisible(false);
			reboot4.setVisible(false);

			labelIcon1.setVisible(false);
			labelIcon2.setVisible(false);
			labelIcon3.setVisible(false);
			labelIcon4.setVisible(false);

			output1.clear();
			ips.clear();
			output1 = command1.getConnectedDevices();
			for (int i = 0; i < output1.size(); i++) {
				ips.add(command1.getWlanIp(output1.get(i)));
			}

			for (int i = 0; i < output1.size(); i++) {
				if (i == 0) {

					radio1.setSelected(true);
					radio1.setVisible(true);
					switch (command1.getSafePathPackage(output1.get(0))) {
					case "com.smithmicro.tmobile.familymode.test":
						labelIcon1.setIcon(logo_tmo);
						labelIcon1.setText("Family Mode");
						labelIcon1.setVisible(true);
						uninstallApp1.setEnabled(true);
						uninstallApp1.setVisible(true);
						enableFirebase1.setEnabled(true);
						enableFirebase1.setVisible(true);
						saveLogsButton1.setEnabled(true);
						break;
					case "com.smithmicro.safepath.family":
						labelIcon1.setIcon(logo_product);
						labelIcon1.setText("SP Family");
						labelIcon1.setVisible(true);
						uninstallApp1.setEnabled(true);
						uninstallApp1.setVisible(true);
						enableFirebase1.setEnabled(true);
						enableFirebase1.setVisible(true);
						saveLogsButton1.setEnabled(true);
						break;
					case "com.smithmicro.att.securefamily":
						labelIcon1.setIcon(logo_att);
						labelIcon1.setText("Secure Family");
						labelIcon1.setVisible(true);
						uninstallApp1.setEnabled(true);
						uninstallApp1.setVisible(true);
						enableFirebase1.setEnabled(true);
						enableFirebase1.setVisible(true);
						saveLogsButton1.setEnabled(true);
						break;
					case "":
						labelIcon1.setIcon(notInstalled);
						labelIcon1.setText("Not Installed");
						labelIcon1.setVisible(true);
						uninstallApp1.setVisible(true);
						enableFirebase1.setVisible(true);
						saveLogsButton1.setEnabled(false);
						break;
					}
					saveLogsButton1.setVisible(true);
					wifiDebbug1.setText("WiFi Debug");
					if (output1.get(i).endsWith(":5555")) {
						wifiDebbug1.setText("Disable WiFi");
					}
					wifiDebbug1.setVisible(true);
					enableFirebase1.setVisible(true);
					reboot1.setVisible(true);
					device1TextPane.setText(output1.get(i) + "\n" + command1.getDeviceManufacturer(output1.get(i))
							+ "\n" + command1.getDeviceModel(output1.get(i)) + "\n" + "Android "
							+ command1.getDeviceOSVersion(output1.get(i)) + "\n" + ips.get(i));
					device1TextPane.setVisible(true);
					takeScreenshotButton1.setVisible(true);

				} else if (i == 1) {

					radio2.setSelected(true);
					radio2.setVisible(true);
					switch (command1.getSafePathPackage(output1.get(1))) {
					case "com.smithmicro.tmobile.familymode.test":
						labelIcon2.setIcon(logo_tmo);
						labelIcon2.setText("Family Mode");
						labelIcon2.setVisible(true);
						uninstallApp2.setEnabled(true);
						uninstallApp2.setVisible(true);
						enableFirebase2.setEnabled(true);
						enableFirebase2.setVisible(true);
						saveLogsButton2.setEnabled(true);
						break;
					case "com.smithmicro.safepath.family":
						labelIcon2.setIcon(logo_product);
						labelIcon2.setText("SP Family");
						labelIcon2.setVisible(true);
						uninstallApp2.setEnabled(true);
						uninstallApp2.setVisible(true);
						enableFirebase2.setEnabled(true);
						enableFirebase2.setVisible(true);
						saveLogsButton2.setEnabled(true);
						break;
					case "com.smithmicro.att.securefamily":
						labelIcon2.setIcon(logo_att);
						labelIcon2.setText("Secure Family");
						labelIcon2.setVisible(true);
						uninstallApp2.setEnabled(true);
						uninstallApp2.setVisible(true);
						enableFirebase2.setEnabled(true);
						enableFirebase2.setVisible(true);
						saveLogsButton2.setEnabled(true);
						break;
					case "":
						labelIcon2.setIcon(notInstalled);
						labelIcon2.setText("Not Installed");
						labelIcon2.setVisible(true);
						uninstallApp2.setVisible(true);
						enableFirebase2.setVisible(true);
						saveLogsButton2.setEnabled(false);
						break;
					}
					saveLogsButton2.setVisible(true);
					wifiDebbug2.setText("WiFi Debug");
					if (output1.get(i).endsWith(":5555")) {
						wifiDebbug2.setText("Disable WiFi");
					}
					wifiDebbug2.setVisible(true);
					enableFirebase2.setVisible(true);
					reboot2.setVisible(true);
					device2TextPane.setText(output1.get(i) + "\n" + command1.getDeviceManufacturer(output1.get(i))
							+ "\n" + command1.getDeviceModel(output1.get(i)) + "\n" + "Android "
							+ command1.getDeviceOSVersion(output1.get(i)) + "\n" + ips.get(i));
					device2TextPane.setVisible(true);
					takeScreenshotButton2.setVisible(true);

				} else if (i == 2) {

					radio3.setSelected(true);
					radio3.setVisible(true);
					switch (command1.getSafePathPackage(output1.get(2))) {
					case "com.smithmicro.tmobile.familymode.test":
						labelIcon3.setIcon(logo_tmo);
						labelIcon3.setText("Family Mode");
						labelIcon3.setVisible(true);
						uninstallApp3.setEnabled(true);
						uninstallApp3.setVisible(true);
						enableFirebase3.setEnabled(true);
						enableFirebase3.setVisible(true);
						saveLogsButton3.setEnabled(true);
						break;
					case "com.smithmicro.safepath.family":
						labelIcon3.setIcon(logo_product);
						labelIcon3.setText("SP Family");
						labelIcon3.setVisible(true);
						uninstallApp3.setEnabled(true);
						uninstallApp3.setVisible(true);
						enableFirebase3.setEnabled(true);
						enableFirebase3.setVisible(true);
						saveLogsButton3.setEnabled(true);
						break;
					case "com.smithmicro.att.securefamily":
						labelIcon3.setIcon(logo_att);
						labelIcon3.setText("Secure Family");
						labelIcon3.setVisible(true);
						uninstallApp3.setEnabled(true);
						uninstallApp3.setVisible(true);
						enableFirebase3.setEnabled(true);
						enableFirebase3.setVisible(true);
						saveLogsButton3.setEnabled(true);
						break;
					case "":
						labelIcon3.setIcon(notInstalled);
						labelIcon3.setText("Not Installed");
						labelIcon3.setVisible(true);
						uninstallApp3.setVisible(true);
						enableFirebase3.setVisible(true);
						saveLogsButton3.setEnabled(false);
						break;
					}
					wifiDebbug3.setText("WiFi Debug");
					if (output1.get(i).endsWith(":5555")) {
						wifiDebbug3.setText("Disable WiFi");
					}
					saveLogsButton3.setVisible(true);
					wifiDebbug3.setVisible(true);
					enableFirebase3.setVisible(true);
					reboot3.setVisible(true);
					device3TextPane.setText(output1.get(i) + "\n" + command1.getDeviceManufacturer(output1.get(i))
							+ "\n" + command1.getDeviceModel(output1.get(i)) + "\n" + "Android "
							+ command1.getDeviceOSVersion(output1.get(i)) + "\n" + ips.get(i));
					device3TextPane.setVisible(true);
					takeScreenshotButton3.setVisible(true);

				} else if (i == 3) {

					radio4.setSelected(true);
					radio4.setVisible(true);
					switch (command1.getSafePathPackage(output1.get(3))) {
					case "com.smithmicro.tmobile.familymode.test":
						labelIcon4.setIcon(logo_tmo);
						labelIcon4.setText("FamilyMode");
						labelIcon4.setVisible(true);
						uninstallApp4.setEnabled(true);
						uninstallApp4.setVisible(true);
						enableFirebase4.setEnabled(true);
						enableFirebase4.setVisible(true);
						saveLogsButton4.setEnabled(true);
						break;
					case "com.smithmicro.safepath.family":
						labelIcon4.setIcon(logo_product);
						labelIcon4.setText("SP Family");
						labelIcon4.setVisible(true);
						uninstallApp4.setEnabled(true);
						uninstallApp4.setVisible(true);
						enableFirebase4.setEnabled(true);
						enableFirebase4.setVisible(true);
						saveLogsButton4.setEnabled(true);
						break;
					case "com.smithmicro.att.securefamily":
						labelIcon4.setIcon(logo_att);
						labelIcon4.setText("Secure Family");
						labelIcon4.setVisible(true);
						uninstallApp4.setEnabled(true);
						uninstallApp4.setVisible(true);
						enableFirebase4.setEnabled(true);
						enableFirebase4.setVisible(true);
						saveLogsButton4.setEnabled(true);
						break;
					case "":
						labelIcon4.setIcon(notInstalled);
						labelIcon4.setText("Not Installed");
						labelIcon4.setVisible(true);
						uninstallApp4.setVisible(true);
						enableFirebase4.setVisible(true);
						saveLogsButton4.setEnabled(false);
						break;
					}
					saveLogsButton4.setVisible(true);
					wifiDebbug4.setText("WiFi Debug");
					if (output1.get(i).endsWith(":5555")) {
						wifiDebbug4.setText("Disable WiFi");
					}
					wifiDebbug4.setVisible(true);
					enableFirebase4.setVisible(true);
					reboot4.setVisible(true);
					device4TextPane.setText(output1.get(i) + "\n" + command1.getDeviceManufacturer(output1.get(i))
							+ "\n" + command1.getDeviceModel(output1.get(i)) + "\n" + "Android "
							+ command1.getDeviceOSVersion(output1.get(i)) + "\n" + ips.get(i));
					device4TextPane.setVisible(true);
					takeScreenshotButton4.setVisible(true);
				}
			}

		} else if (e.getSource() == fileButton) {
			JFileChooser fileChooser = new JFileChooser(
					"C:\\Users\\dtomic\\OneDrive - Smith Micro Software\\SP7\\Master builds\\");
			int response = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				fileTextField.setText(file.getName());
				if (radio1.isSelected() || radio2.isSelected() || radio3.isSelected() || radio4.isSelected()) {
					installButton.setEnabled(true);
				}
				bar.setBackground(new Color(238, 238, 238));
				bar.setString("Waiting for build...");
			}

		} else if (e.getSource() == saveLogsButton1) {
			if (!command1.checkIfInstalled(output1.get(0))) {
				JOptionPane.showMessageDialog(this, "App is not installed!", "Safe Path Logs",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JFileChooser fileChooser = new JFileChooser("C:\\logs");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int response = fileChooser.showSaveDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					output1 = command1.getConnectedDevices();
					command1.saveLogs(output1.get(0), file.getAbsolutePath());
					JOptionPane.showMessageDialog(this, "Safe Path logs saved!", "Safe Path Logs",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} else if (e.getSource() == saveLogsButton2) {
			if (!command1.checkIfInstalled(output1.get(1))) {
				JOptionPane.showMessageDialog(this, "App is not installed!", "Safe Path Logs",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JFileChooser fileChooser = new JFileChooser("C:\\logs");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int response = fileChooser.showSaveDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					output1 = command1.getConnectedDevices();
					command1.saveLogs(output1.get(1), file.getAbsolutePath());
					JOptionPane.showMessageDialog(this, "Safe Path logs saved!", "Safe Path Logs",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} else if (e.getSource() == saveLogsButton3) {
			if (!command1.checkIfInstalled(output1.get(2))) {
				JOptionPane.showMessageDialog(this, "App is not installed!", "Safe Path Logs",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JFileChooser fileChooser = new JFileChooser("C:\\logs");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int response = fileChooser.showSaveDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					output1 = command1.getConnectedDevices();
					command1.saveLogs(output1.get(2), file.getAbsolutePath());
					JOptionPane.showMessageDialog(this, "Safe Path logs saved!", "Safe Path Logs",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} else if (e.getSource() == saveLogsButton4) {
			if (!command1.checkIfInstalled(output1.get(3))) {
				JOptionPane.showMessageDialog(this, "App is not installed!", "Safe Path Logs",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JFileChooser fileChooser = new JFileChooser("C:\\logs");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int response = fileChooser.showSaveDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					output1 = command1.getConnectedDevices();
					command1.saveLogs(output1.get(3), file.getAbsolutePath());
					JOptionPane.showMessageDialog(this, "Safe Path logs saved!", "Safe Path Logs",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} else if (e.getSource() == takeScreenshotButton1) {
			output1 = command1.getConnectedDevices();
			String output = command1.takeScreenshot(output1.get(0), "sdcard/", "screenshot.png");
			new File("C:/AdbToolkit_Screenshots/Device1").mkdirs();
			command1.pullFile(output1.get(0), output, "C:/AdbToolkit_Screenshots/Device1");
			JOptionPane.showMessageDialog(this,
					"Device1 screenshot captured!" + "\n" + "Location: C:/AdbToolkit_Screenshots/Device1",
					"Screenshot Capture", JOptionPane.INFORMATION_MESSAGE);

		} else if (e.getSource() == takeScreenshotButton2) {
			output1 = command1.getConnectedDevices();
			String output = command1.takeScreenshot(output1.get(1), "sdcard/", "screenshot.png");
			new File("C:/AdbToolkit_Screenshots/Device2").mkdirs();
			command1.pullFile(output1.get(1), output, "C:/AdbToolkit_Screenshots/Device2");
			JOptionPane.showMessageDialog(this,
					"Device2 screenshot captured!" + "\n" + "Location: C:/AdbToolkit_Screenshots/Device2",
					"Screenshot Capture", JOptionPane.INFORMATION_MESSAGE);

		} else if (e.getSource() == takeScreenshotButton3) {
			output1 = command1.getConnectedDevices();
			String output = command1.takeScreenshot(output1.get(2), "sdcard/", "screenshot.png");
			new File("C:/AdbToolkit_Screenshots/Device3").mkdirs();
			command1.pullFile(output1.get(2), output, "C:/AdbToolkit_Screenshots/Device3");
			JOptionPane.showMessageDialog(this,
					"Device3 screenshot captured!" + "\n" + "Location: C:/AdbToolkit_Screenshots/Device3",
					"Screenshot Capture", JOptionPane.INFORMATION_MESSAGE);

		} else if (e.getSource() == takeScreenshotButton4) {
			output1 = command1.getConnectedDevices();
			String output = command1.takeScreenshot(output1.get(3), "sdcard/", "screenshot.png");
			new File("C:/AdbToolkit_Screenshots/Device4").mkdirs();
			command1.pullFile(output1.get(3), output, "C:/AdbToolkit_Screenshots/Device4");
			JOptionPane.showMessageDialog(this,
					"Device4 screenshot captured!" + "\n" + "Location: C:/AdbToolkit_Screenshots/Device4",
					"Screenshot Capture", JOptionPane.INFORMATION_MESSAGE);

		} else if (e.getSource() == wifiDebbug1) {
			if (!output1.get(0).endsWith(":5555")) {
				command1.startWifiDebugging(output1.get(0), ips.get(0));
				JOptionPane.showMessageDialog(this,
						"Debugging over WiFi is enabled!" + "\n"
								+ "If prompted on the device, allow wireless debugging on specific wifi network." + "\n"
								+ "You may disconnect USB cable from this device.",
						"Enable WiFi Debugging.", JOptionPane.INFORMATION_MESSAGE);
				wifiDebbug4.setText("Disable WiFi");
				devicesButton.doClick(200);
			} else {
				command1.stopWifiDebugging(output1.get(0), ips.get(0));
				JOptionPane.showMessageDialog(this, "Debugging over WiFi is disabled!", "Disable WiFi Debugging.",
						JOptionPane.INFORMATION_MESSAGE);
				devicesButton.doClick(100);
			}

		} else if (e.getSource() == wifiDebbug2) {
			if (!output1.get(1).endsWith(":5555")) {
				command1.startWifiDebugging(output1.get(1), ips.get(1));
				JOptionPane.showMessageDialog(this,
						"Debugging over WiFi is enabled!" + "\n"
								+ "If prompted on the device, allow wireless debugging on specific wifi network." + "\n"
								+ "You may disconnect USB cable from this device.",
						"Enable WiFi Debugging.", JOptionPane.INFORMATION_MESSAGE);
				wifiDebbug4.setText("Disable WiFi");
				devicesButton.doClick(200);
			} else {
				command1.stopWifiDebugging(output1.get(1), ips.get(1));
				JOptionPane.showMessageDialog(this, "Debugging over WiFi is disabled!", "Disable WiFi Debugging.",
						JOptionPane.INFORMATION_MESSAGE);
				devicesButton.doClick(100);
			}

		} else if (e.getSource() == wifiDebbug3) {
			if (!output1.get(2).endsWith(":5555")) {
				command1.startWifiDebugging(output1.get(2), ips.get(2));
				JOptionPane.showMessageDialog(this,
						"Debugging over WiFi is enabled!" + "\n"
								+ "If prompted on the device, allow wireless debugging on specific wifi network." + "\n"
								+ "You may disconnect USB cable from this device.",
						"Enable WiFi Debugging.", JOptionPane.INFORMATION_MESSAGE);
				wifiDebbug4.setText("Disable WiFi");
				devicesButton.doClick(200);
			} else {
				command1.stopWifiDebugging(output1.get(2), ips.get(2));
				JOptionPane.showMessageDialog(this, "Debugging over WiFi is disabled!", "Disable WiFi Debugging.",
						JOptionPane.INFORMATION_MESSAGE);
				devicesButton.doClick(100);
			}

		} else if (e.getSource() == wifiDebbug4) {
			if (!output1.get(3).endsWith(":5555")) {
				command1.startWifiDebugging(output1.get(3), ips.get(3));
				JOptionPane.showMessageDialog(this,
						"Debugging over WiFi is enabled!" + "\n"
								+ "If prompted on the device, allow wireless debugging on specific wifi network." + "\n"
								+ "You may disconnect USB cable from this device.",
						"Enable WiFi Debugging.", JOptionPane.INFORMATION_MESSAGE);
				wifiDebbug4.setText("Disable WiFi");
				devicesButton.doClick(200);
			} else {
				command1.stopWifiDebugging(output1.get(3), ips.get(3));
				JOptionPane.showMessageDialog(this, "Debugging over WiFi is disabled!", "Disable WiFi Debugging.",
						JOptionPane.INFORMATION_MESSAGE);
				devicesButton.doClick(100);
			}

		} else if (e.getSource() == enableFirebase1) {
			output1 = command1.getConnectedDevices();
			command1.enableAnalyticsDebug(output1.get(0), command1.getSafePathPackage(output1.get(0)));
			if (command1.checkIfInstalled(output1.get(0))) {
				JOptionPane.showMessageDialog(this,
						"Firebase Debugging enabled!" + "\n"
								+ "Make sure 'Logging Analytics Events' toggle button is also enabled in Debug menu.",
						"Enable Firebase Debugging", JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getSource() == enableFirebase2) {
			output1 = command1.getConnectedDevices();
			command1.enableAnalyticsDebug(output1.get(1), command1.getSafePathPackage(output1.get(1)));
			if (command1.checkIfInstalled(output1.get(1))) {
				JOptionPane.showMessageDialog(this,
						"Firebase Debugging enabled!" + "\n"
								+ "Make sure 'Logging Analytics Events' toggle button is also enabled in Debug menu.",
						"Enable Firebase Debugging", JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getSource() == enableFirebase3) {
			output1 = command1.getConnectedDevices();
			command1.enableAnalyticsDebug(output1.get(2), command1.getSafePathPackage(output1.get(2)));
			if (command1.checkIfInstalled(output1.get(2))) {
				JOptionPane.showMessageDialog(this,
						"Firebase Debugging enabled!" + "\n"
								+ "Make sure 'Logging Analytics Events' toggle button is also enabled in Debug menu.",
						"Enable Firebase Debugging", JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getSource() == enableFirebase4) {
			output1 = command1.getConnectedDevices();
			command1.enableAnalyticsDebug(output1.get(3), command1.getSafePathPackage(output1.get(3)));
			if (command1.checkIfInstalled(output1.get(3))) {
				JOptionPane.showMessageDialog(this,
						"Firebase Debugging enabled!" + "\n"
								+ "Make sure 'Logging Analytics Events' toggle button is also enabled in Debug menu.",
						"Enable Firebase Debugging", JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getSource() == reboot1) {
			command1.reboot(output1.get(0));

		} else if (e.getSource() == reboot2) {
			command1.reboot(output1.get(1));

		} else if (e.getSource() == reboot3) {
			command1.reboot(output1.get(2));

		} else if (e.getSource() == reboot4) {
			command1.reboot(output1.get(3));

		} else if (e.getSource() == uninstallApp1) {
			command1.uninstallApp(output1.get(0), command1.getSafePathPackage(output1.get(0)));
			JOptionPane.showMessageDialog(this, "App is uninstalled!", "Enable WiFi Debugging.",
					JOptionPane.INFORMATION_MESSAGE);
			saveLogsButton1.setEnabled(false);
			devicesButton.doClick();

		} else if (e.getSource() == uninstallApp2) {
			command1.uninstallApp(output1.get(1), command1.getSafePathPackage(output1.get(1)));
			JOptionPane.showMessageDialog(this, "App is uninstalled!", "Enable WiFi Debugging.",
					JOptionPane.INFORMATION_MESSAGE);
			saveLogsButton2.setEnabled(false);
			devicesButton.doClick();

		} else if (e.getSource() == uninstallApp3) {
			command1.uninstallApp(output1.get(2), command1.getSafePathPackage(output1.get(2)));
			JOptionPane.showMessageDialog(this, "App is uninstalled!", "Enable WiFi Debugging.",
					JOptionPane.INFORMATION_MESSAGE);
			saveLogsButton3.setEnabled(false);
			devicesButton.doClick();

		} else if (e.getSource() == uninstallApp4) {
			command1.uninstallApp(output1.get(3), command1.getSafePathPackage(output1.get(3)));
			JOptionPane.showMessageDialog(this, "App is uninstalled!", "Enable WiFi Debugging.",
					JOptionPane.INFORMATION_MESSAGE);
			saveLogsButton4.setEnabled(false);
			devicesButton.doClick();

		} else if (e.getSource() == installButton) {
			output1 = command1.getConnectedDevices();
			bar.setString("Installing...");
			bar.setIndeterminate(true);
			bar.setBackground(new Color(238, 238, 238));
			installButton.setEnabled(false);

			if (radio1.isSelected() && output1.size() > 0) {
				radio1State = true;
				Task task1 = new Task("adb -s " + output1.get(0) + " install " + "\"" + file.getAbsolutePath() + "\"");
				task1.addPropertyChangeListener(this);
				task1.execute();
			} else {
				radio1State = false;
			}

			if (radio2.isSelected() && output1.size() > 1) {
				radio2State = true;
				Task task2 = new Task("adb -s " + output1.get(1) + " install " + "\"" + file.getAbsolutePath() + "\"");
				task2.addPropertyChangeListener(this);
				task2.execute();
			} else {
				radio2State = false;
			}
			if (radio3.isSelected() && output1.size() > 2) {
				radio3State = true;
				Task task3 = new Task("adb -s " + output1.get(2) + " install " + "\"" + file.getAbsolutePath() + "\"");
				task3.addPropertyChangeListener(this);
				task3.execute();
			} else {
				radio3State = false;
			}
			if (radio4.isSelected() && output1.size() > 3) {
				radio4State = true;
				Task task4 = new Task("adb -s " + output1.get(3) + " install " + "\"" + file.getAbsolutePath() + "\"");
				task4.addPropertyChangeListener(this);
				task4.execute();
			} else {
				radio1State = false;
			}
			uninstallAllButton.setEnabled(true);

		} else if (e.getSource() == uninstallAllButton) {
			output1 = command1.getConnectedDevices();
			bar.setString("Uninstalling...");
			bar.setIndeterminate(true);
			bar.setBackground(new Color(238, 238, 238));
			uninstallAllButton.setEnabled(false);
			int i = 0;
			Task[] task = new Task[4];
			while (i < output1.size()) {
				task[i] = new Task("adb -s " + output1.get(i) + " shell pm uninstall "
						+ command1.getSafePathPackage(output1.get(i)));
				task[i].addPropertyChangeListener(this);
				task[i].execute();
				i++;
			}
		}
	}

	// Thread synchronization to determine the end of all processes
	public void increaseCounter() {
		synchronized (lock) {
			counter++;
		}
	}

	public void decreaseCounter() {
		synchronized (lock) {
			counter--;
		}
	}

	class Task extends SwingWorker<Void, Void> {
		private String command;

		public Task(String command) {
			this.command = command;
		}

		@Override
		public Void doInBackground() {
			increaseCounter();
			command1.runCommand(command);
			return null;
		}

		@Override
		public void done() {
			decreaseCounter();
			if (counter == 0) {
				bar.setString("Done!");
				bar.setBackground(Color.green);
				if (fileTextField.getText() != "") {
					installButton.setEnabled(true);
				}
				bar.setIndeterminate(false);
				devicesButton.doClick();
			}
		}
	}
}