import java.awt.BorderLayout;
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
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

public class MyFrame extends JFrame implements ActionListener, PropertyChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton saveButton1, saveButton2, saveButton3, saveButton4;
	JButton wifiDebbug1, wifiDebbug2, wifiDebbug3, wifiDebbug4;
	JButton enableFirebase1, enableFirebase2, enableFirebase3, enableFirebase4;
	JButton devicesButton;
	JButton fileButton;
	JButton installButton;
	
	JLabel label1a, label2a, label3a, label4a, label5a, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14, label15, label16, label17, label18, label19, label20;
	JPanel panel1, panel2, panel3, panel4, panel5, subPanel;
	
	JLabel[] staticLabels = {label1a, label2a, label3a, label4a};
	JLabel[] dynamicLabels = {label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14, label15, label16};
	
	JRadioButton radio1, radio2, radio3, radio4;
	
	Util command1 = new Util();
	ArrayList<String> output1 = command1.getConnectedDevices();
	
	JTextField fileTextField;
	
	Path temp = null;
	
	File file = null;
	
	JProgressBar bar, bar2, bar3, bar4;

	private static int counter = 0;
	private static final Object lock = new Object();
	
	ArrayList<String> ips = new ArrayList<String>();
	
	MyFrame(){
		
		//Get list of wlan0 IP addresses 
		for (int i = 0; i < output1.size(); i++) {
				ips.add(command1.getWlanIp(output1.get(i)));
		}
		
		//Setting the menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");	
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		this.setJMenuBar(menuBar);
		
		
		//Setting up panels
		panel1 = new JPanel();
		panel1.setBounds(2, 60, 198, 210);
		panel1.setBorder(BorderFactory.createEtchedBorder());
		panel1.setLayout(new BorderLayout());
		
		panel2 = new JPanel();
		panel2.setBounds(200, 60, 200, 210);
		panel2.setBorder(BorderFactory.createEtchedBorder());
		panel2.setLayout(new BorderLayout());
		panel2.setVisible(false);
		
		panel3 = new JPanel();
		panel3.setBounds(400, 60, 200, 210);
		panel3.setBorder(BorderFactory.createEtchedBorder());
		panel3.setLayout(new BorderLayout());
		panel3.setVisible(false);
		
		panel4 = new JPanel();
		panel4.setBounds(600, 60, 200, 210);
		panel4.setBorder(BorderFactory.createEtchedBorder());
		panel4.setLayout(new BorderLayout());
		panel4.setVisible(false);
		
		panel5 = new JPanel();
		panel5.setBounds(800, 60, 200, 210);
		panel5.setBorder(BorderFactory.createEtchedBorder());
		panel5.setLayout(new BorderLayout());
		panel5.setVisible(false);
		
		//Setting up labels
		label1a = new JLabel();
		label2a = new JLabel();
		label3a = new JLabel();
		label4a = new JLabel();
		label5a = new JLabel();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();
		label9 = new JLabel();
		label10 = new JLabel();
		label11 = new JLabel();
		label12 = new JLabel();
		label13 = new JLabel();
		label14 = new JLabel();
		label15 = new JLabel();
		label16 = new JLabel();
		label17 = new JLabel();
		label18 = new JLabel();
		label19 = new JLabel();
		label20 = new JLabel();
		
		label1a.setBounds(0, 0, 200, 30);
		label1a.setText("Serial number:  ");
		label1a.setHorizontalAlignment(JLabel.RIGHT);
		label1a.setFont(new Font("Calibri", Font.BOLD,20));
		label2a.setBounds(0, 22, 200, 30);
		label2a.setText("Manufacturer:  ");
		label2a.setHorizontalAlignment(JLabel.RIGHT);
		label2a.setFont(new Font("Calibri", Font.BOLD,20));
		label3a.setBounds(0, 44, 200, 30);
		label3a.setText("Model:  ");
		label3a.setHorizontalAlignment(JLabel.RIGHT);
		label3a.setFont(new Font("Calibri", Font.BOLD,20));
		label4a.setBounds(0, 66, 200, 30);
		label4a.setText("OS Version: ");
		label4a.setHorizontalAlignment(JLabel.RIGHT);
		label4a.setFont(new Font("Calibri", Font.BOLD,20));
		label5a.setBounds(0, 88, 200, 30);
		label5a.setText("WLAN IP Address: ");
		label5a.setHorizontalAlignment(JLabel.RIGHT);
		label5a.setFont(new Font("Calibri", Font.BOLD,20));
		label1.setBounds(0, 0, 200, 30);
		label1.setHorizontalAlignment(JLabel.CENTER);
		label2.setBounds(0, 22, 200, 30);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label3.setBounds(0, 44, 200, 30);
		label3.setHorizontalAlignment(JLabel.CENTER);
		label4.setBounds(0, 66, 200, 30);
		label4.setHorizontalAlignment(JLabel.CENTER);
		label5.setBounds(0, 88, 200, 30);
		label5.setHorizontalAlignment(JLabel.CENTER);
		label6.setBounds(0, 0, 200, 30);
		label6.setHorizontalAlignment(JLabel.CENTER);
		label7.setBounds(0, 22, 200, 30);
		label7.setHorizontalAlignment(JLabel.CENTER);
		label8.setBounds(0, 44, 200, 30);
		label8.setHorizontalAlignment(JLabel.CENTER);
		label9.setBounds(0, 66, 200, 30);
		label9.setHorizontalAlignment(JLabel.CENTER);
		label10.setBounds(0, 88, 200, 30);
		label10.setHorizontalAlignment(JLabel.CENTER);
		label11.setBounds(0, 0, 200, 30);
		label11.setHorizontalAlignment(JLabel.CENTER);
		label12.setBounds(0, 22, 200, 30);
		label12.setHorizontalAlignment(JLabel.CENTER);
		label13.setBounds(0, 44, 200, 30);
		label13.setHorizontalAlignment(JLabel.CENTER);
		label14.setBounds(0, 66, 200, 30);
		label14.setHorizontalAlignment(JLabel.CENTER);
		label15.setBounds(0, 88, 200, 30);
		label15.setHorizontalAlignment(JLabel.CENTER);
		label16.setBounds(0, 0, 200, 30);
		label16.setHorizontalAlignment(JLabel.CENTER);
		label17.setBounds(0, 22, 200, 30);
		label17.setHorizontalAlignment(JLabel.CENTER);
		label18.setBounds(0, 44, 200, 30);
		label18.setHorizontalAlignment(JLabel.CENTER);
		label19.setBounds(0, 66, 200, 30);
		label19.setHorizontalAlignment(JLabel.CENTER);
		label20.setBounds(0, 88, 200, 30);
		label20.setHorizontalAlignment(JLabel.CENTER);
		
		devicesButton = new JButton("Display Connected Devices");
		devicesButton.setBounds(2, 2, 198, 50);
		devicesButton.addActionListener(this);
		devicesButton.setFocusable(false);
		devicesButton.setBorder(BorderFactory.createEtchedBorder());
		devicesButton.setBackground(Color.lightGray);
		devicesButton.setFont(new Font("Calibri", Font.BOLD, 13));
		
		ImageIcon buttonIcon = new ImageIcon("display.png");
		Image image = buttonIcon.getImage();
		Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
		buttonIcon = new ImageIcon(newimg);
		devicesButton.setIcon(buttonIcon);
		
		fileButton = new JButton("Select Build");
		fileButton.setBounds(2, 270, 100, 25);
		fileButton.addActionListener(this);
		fileButton.setFocusable(false);
		fileButton.setBorder(BorderFactory.createEtchedBorder());
		fileButton.setBackground(Color.lightGray);
		fileButton.setFont(new Font("Calibri", Font.BOLD, 15));
		this.add(fileButton);
		
		saveButton1 = new JButton("SafePath logs");
		saveButton1.setBounds(210, 25, 90, 30);
		saveButton1.addActionListener(this);
		saveButton1.setFocusable(false);
		saveButton1.setBorder(BorderFactory.createEtchedBorder());
		saveButton1.setBackground(Color.lightGray);
		saveButton1.setFont(new Font("Calibri", Font.BOLD, 15));
		saveButton1.setVisible(false);
		this.add(saveButton1);
		
		saveButton2 = new JButton("SafePath logs");
		saveButton2.setBounds(410, 25, 90, 30);
		saveButton2.addActionListener(this);
		saveButton2.setFocusable(false);
		saveButton2.setBorder(BorderFactory.createEtchedBorder());
		saveButton2.setBackground(Color.lightGray);
		saveButton2.setFont(new Font("Calibri", Font.BOLD, 15));
		saveButton2.setVisible(false);
		this.add(saveButton2);
		
		saveButton3 = new JButton("SafePath logs");
		saveButton3.setBounds(610, 25, 90, 30);
		saveButton3.addActionListener(this);
		saveButton3.setFocusable(false);
		saveButton3.setBorder(BorderFactory.createEtchedBorder());
		saveButton3.setBackground(Color.lightGray);
		saveButton3.setFont(new Font("Calibri", Font.BOLD, 15));
		saveButton3.setVisible(false);
		this.add(saveButton3);
		
		saveButton4 = new JButton("SafePath logs");
		saveButton4.setBounds(810, 25, 90, 30);
		saveButton4.addActionListener(this);
		saveButton4.setFocusable(false);
		saveButton4.setBorder(BorderFactory.createEtchedBorder());
		saveButton4.setBackground(Color.lightGray);
		saveButton4.setFont(new Font("Calibri", Font.BOLD, 15));
		saveButton4.setVisible(false);
		this.add(saveButton4);
		
		wifiDebbug1 = new JButton("WiFi Debug");
		wifiDebbug1.setBounds(300, 25, 90, 30);
		wifiDebbug1.addActionListener(this);
		wifiDebbug1.setFocusable(false);
		wifiDebbug1.setBorder(BorderFactory.createEtchedBorder());
		wifiDebbug1.setBackground(Color.lightGray);
		wifiDebbug1.setFont(new Font("Calibri", Font.BOLD, 15));
		if (output1.size() > 0) {
			if (output1.get(0).endsWith(":5555")) {
				wifiDebbug1.setEnabled(false);
			}
		}
		wifiDebbug1.setVisible(false);
		this.add(wifiDebbug1);
		
		wifiDebbug2 = new JButton("WiFi Debug");
		wifiDebbug2.setBounds(500, 25, 90, 30);
		wifiDebbug2.addActionListener(this);
		wifiDebbug2.setFocusable(false);
		wifiDebbug2.setBorder(BorderFactory.createEtchedBorder());
		wifiDebbug2.setBackground(Color.lightGray);
		wifiDebbug2.setFont(new Font("Calibri", Font.BOLD, 15));
		if (output1.size() > 1) {
			if (output1.get(1).endsWith(":5555")) {
				wifiDebbug2.setEnabled(false);
			}
		}
		wifiDebbug2.setVisible(false);
		this.add(wifiDebbug2);
		
		wifiDebbug3 = new JButton("WiFi Debug");
		wifiDebbug3.setBounds(700, 25, 90, 30);
		wifiDebbug3.addActionListener(this);
		wifiDebbug3.setFocusable(false);
		wifiDebbug3.setBorder(BorderFactory.createEtchedBorder());
		wifiDebbug3.setBackground(Color.lightGray);
		wifiDebbug3.setFont(new Font("Calibri", Font.BOLD, 15));
		if (output1.size() > 2) {
			if (output1.get(2).endsWith(":5555")) {
				wifiDebbug3.setEnabled(false);
			}
		}
		wifiDebbug3.setVisible(false);
		this.add(wifiDebbug3);
		
		wifiDebbug4 = new JButton("WiFi Debug");
		wifiDebbug4.setBounds(900, 25, 90, 30);
		wifiDebbug4.addActionListener(this);
		wifiDebbug4.setFocusable(false);
		wifiDebbug4.setBorder(BorderFactory.createEtchedBorder());
		wifiDebbug4.setBackground(Color.lightGray);
		wifiDebbug4.setFont(new Font("Calibri", Font.BOLD, 15));
		if (output1.size() > 3) {
			if (output1.get(3).endsWith(":5555")) {
				wifiDebbug4.setEnabled(false);
			}
		}
		wifiDebbug4.setVisible(false);
		this.add(wifiDebbug4);
		
		enableFirebase1 = new JButton("Enable Firebase");
		enableFirebase1.setBounds(200, 400, 120, 30);
		enableFirebase1.addActionListener(this);
		enableFirebase1.setFocusable(false);
		enableFirebase1.setBorder(BorderFactory.createEtchedBorder());
		enableFirebase1.setBackground(Color.lightGray);
		enableFirebase1.setFont(new Font("Calibri", Font.BOLD, 15));
		enableFirebase1.setVisible(false);
		this.add(enableFirebase1);
		
		enableFirebase2 = new JButton("Enable Firebase");
		enableFirebase2.setBounds(320, 400, 120, 30);
		enableFirebase2.addActionListener(this);
		enableFirebase2.setFocusable(false);
		enableFirebase2.setBorder(BorderFactory.createEtchedBorder());
		enableFirebase2.setBackground(Color.lightGray);
		enableFirebase2.setFont(new Font("Calibri", Font.BOLD, 15));
		enableFirebase2.setVisible(false);
		this.add(enableFirebase2);
		
		enableFirebase3 = new JButton("Enable Firebase");
		enableFirebase3.setBounds(440, 400, 120, 30);
		enableFirebase3.addActionListener(this);
		enableFirebase3.setFocusable(false);
		enableFirebase3.setBorder(BorderFactory.createEtchedBorder());
		enableFirebase3.setBackground(Color.lightGray);
		enableFirebase3.setFont(new Font("Calibri", Font.BOLD, 15));
		enableFirebase3.setVisible(false);
		this.add(enableFirebase3);
		
		enableFirebase4 = new JButton("Enable Firebase");
		enableFirebase4.setBounds(560, 400, 120, 30);
		enableFirebase4.addActionListener(this);
		enableFirebase4.setFocusable(false);
		enableFirebase4.setBorder(BorderFactory.createEtchedBorder());
		enableFirebase4.setBackground(Color.lightGray);
		enableFirebase4.setFont(new Font("Calibri", Font.BOLD, 15));
		enableFirebase4.setVisible(false);
		this.add(enableFirebase4);
		
		installButton = new JButton("Install");
		installButton.setBounds(2, 300, 100, 50);
		installButton.addActionListener(this);
		installButton.setFocusable(false);
		installButton.setBorder(BorderFactory.createEtchedBorder());
		installButton.setBackground(Color.lightGray);
		installButton.setFont(new Font("Calibri", Font.BOLD, 25));
		installButton.setEnabled(false);
		this.add(installButton);
		
		fileTextField = new JTextField();
		fileTextField.setPreferredSize(new Dimension(250, 40));
		fileTextField.setBounds(102, 270, 500, 25);
		fileTextField.setEditable(false);
		this.add(fileTextField);
		
		this.setTitle("Adb Toolkit");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(1015,500);
		ImageIcon icon = new ImageIcon("logo.png");
		this.setIconImage(icon.getImage());
		this.add(devicesButton);
		
		panel1.add(label1a);
		panel1.add(label2a);
		panel1.add(label3a);
		panel1.add(label4a);
		panel1.add(label5a);
		this.add(panel1);
		
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);	
		
		panel2.setVisible(false);
		panel3.setVisible(false);
		panel4.setVisible(false);
		panel5.setVisible(false);
			
		panel2.add(label1);
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);
		panel2.add(label5);

		panel3.add(label6);
		panel3.add(label7);
		panel3.add(label8);
		panel3.add(label9);
		panel3.add(label10);
		
		panel4.add(label11);
		panel4.add(label12);
		panel4.add(label13);
		panel4.add(label14);
		panel4.add(label15);	
		
		panel5.add(label16);
		panel5.add(label17);
		panel5.add(label18);
		panel5.add(label19);
		panel5.add(label20);
		
		radio1 = new JRadioButton("Device 1");
		radio1.addActionListener(this);
		radio1.setBounds(262, 2, 200, 15);
		radio1.setVisible(false);
		this.add(radio1);
		
		radio2 = new JRadioButton("Device 2");
		radio2.addActionListener(this);
		radio2.setBounds(462, 2, 200, 15);
		radio2.setVisible(false);
		this.add(radio2);
		
		radio3 = new JRadioButton("Device 3");
		radio3.addActionListener(this);
		radio3.setBounds(662, 2, 200, 15);
		radio3.setVisible(false);
		this.add(radio3);
		
		radio4 = new JRadioButton("Device 4");
		radio4.addActionListener(this);
		radio4.setBounds(862, 2, 200, 15);
		radio4.setVisible(false);
		this.add(radio4);
		
		bar = new JProgressBar();
		bar.setBounds(102, 300, 500, 25);
		bar.setStringPainted(true);
		bar.setFont(new Font("Calibri", Font.BOLD, 13));
		bar.setBackground(new Color(238, 238, 238));
		bar.setString("Waiting for build...");
		this.add(bar);
		
//		Collections.reverse(output1);
//		Collections.reverse(ips);
		
		for (int i = 0; i < output1.size(); i++) {
				
			if (i == 0) {
				label1.setText(output1.get(i));
				label1.setVisible(true);
				label2.setText(command1.getDeviceManufacturer(output1.get(i)));
				label2.setVisible(true);
				label3.setText(command1.getDeviceModel(output1.get(i)));
				label3.setVisible(true);
				label4.setText("Android " + command1.getDeviceOSVersion(output1.get(i)));
				label4.setVisible(true);
				label5.setText(ips.get(0));
				label5.setVisible(true);
				panel2.setVisible(true);
				radio1.setSelected(true);
				radio1.setVisible(true);
				saveButton1.setVisible(true);
				if (output1.get(i).endsWith(":5555")) {
					wifiDebbug1.setEnabled(false);
				}
				wifiDebbug1.setVisible(true);
				enableFirebase1.setVisible(true);
					
			} else if (i == 1){
					
				label6.setText(output1.get(i));
				label6.setVisible(true);
				label7.setText(command1.getDeviceManufacturer(output1.get(i)));
				label7.setVisible(true);
				label8.setText(command1.getDeviceModel(output1.get(i)));
				label8.setVisible(true);
				label9.setText("Android " + command1.getDeviceOSVersion(output1.get(i)));
				label9.setVisible(true);	
				label10.setText(ips.get(1));
				label10.setVisible(true);
				panel3.setVisible(true);
				radio2.setSelected(true);
				radio2.setVisible(true);	
				saveButton2.setVisible(true);
				if (output1.get(i).endsWith(":5555")) {
					wifiDebbug2.setEnabled(false);
				}
				wifiDebbug2.setVisible(true);
				enableFirebase2.setVisible(true);
				
			} else if (i == 2){
					
				label11.setText(output1.get(i));
				label11.setVisible(true);
				label12.setText(command1.getDeviceManufacturer(output1.get(i)));
				label12.setVisible(true);
				label13.setText(command1.getDeviceModel(output1.get(i)));
				label13.setVisible(true);
				label14.setText("Android " + command1.getDeviceOSVersion(output1.get(i)));
				label14.setVisible(true);
				label15.setText(ips.get(2));
				label15.setVisible(true);
				panel4.setVisible(true);
				radio3.setSelected(true);
				radio3.setVisible(true);
				saveButton3.setVisible(true);
				if (output1.get(i).endsWith(":5555")) {
					wifiDebbug3.setEnabled(false);
				}
				wifiDebbug3.setVisible(true);
				enableFirebase3.setVisible(true);
					
			} else if (i == 3){
					
				label16.setText(output1.get(i));
				label16.setVisible(true);
				label17.setText(command1.getDeviceManufacturer(output1.get(i)));
				label17.setVisible(true);
				label18.setText(command1.getDeviceModel(output1.get(i)));
				label18.setVisible(true);
				label19.setText("Android " + command1.getDeviceOSVersion(output1.get(i)));
				label19.setVisible(true);
				label20.setText(ips.get(3));
				label20.setVisible(true);
				panel5.setVisible(true);
				radio4.setSelected(true);
				radio4.setVisible(true);
				saveButton4.setVisible(true);
				if (output1.get(i).endsWith(":5555")) {
					wifiDebbug4.setEnabled(false);
				}
				wifiDebbug4.setVisible(true);
				enableFirebase4.setVisible(true);
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
		
		if(e.getSource() == devicesButton) {
			
			output1 = command1.getConnectedDevices();
			Collections.reverse(output1);
			Collections.reverse(ips);
			for (int i = 0; i < output1.size(); i++) {
				ips.add(command1.getWlanIp(output1.get(i)));

		}
			
			// set panels and radio buttons invisible
			panel2.setVisible(false);
			panel3.setVisible(false);
			panel4.setVisible(false);
			panel5.setVisible(false);
			radio1.setVisible(false);
			radio2.setVisible(false);
			radio3.setVisible(false);
			radio4.setVisible(false);
			
			saveButton1.setVisible(false);
			saveButton2.setVisible(false);
			saveButton3.setVisible(false);
			saveButton4.setVisible(false);
			wifiDebbug1.setVisible(false);
			wifiDebbug2.setVisible(false);
			wifiDebbug3.setVisible(false);
			wifiDebbug4.setVisible(false);
			enableFirebase1.setVisible(false);
			enableFirebase2.setVisible(false);
			enableFirebase3.setVisible(false);
			enableFirebase4.setVisible(false);
			
			panel2.add(label1);
			panel2.add(label2);
			panel2.add(label3);
			panel2.add(label4);
			panel2.add(label5);
			
			panel3.add(label6);
			panel3.add(label7);
			panel3.add(label8);
			panel3.add(label9);
			panel3.add(label10);
			
			panel4.add(label11);
			panel4.add(label12);
			panel4.add(label13);
			panel4.add(label14);
			panel4.add(label15);
			
			panel5.add(label16);
			panel5.add(label17);
			panel5.add(label18);
			panel5.add(label19);
			panel5.add(label20);
			
			for (int i = 0; i < output1.size(); i++) {
				
				if (i == 0) {
					
					panel2.setVisible(true);
					label1.setText(output1.get(i));
					label1.setVisible(true);
					label2.setText(command1.getDeviceManufacturer(output1.get(i)));
					label2.setVisible(true);
					label3.setText(command1.getDeviceModel(output1.get(i)));
					label3.setVisible(true);
					label4.setText("Android " + command1.getDeviceOSVersion(output1.get(i)));
					label4.setVisible(true);
					label5.setText(ips.get(0));
					label5.setVisible(true);
					radio1.setSelected(true);
					radio1.setVisible(true);
					saveButton1.setVisible(true);
					if (output1.get(i).endsWith(":5555")) {
						wifiDebbug1.setEnabled(false);
					}
					wifiDebbug1.setVisible(true);
					enableFirebase1.setVisible(true);
				
				} else if (i == 1){
					
					panel3.setVisible(true);
					label6.setText(output1.get(i));
					label6.setVisible(true);
					label7.setText(command1.getDeviceManufacturer(output1.get(i)));
					label7.setVisible(true);
					label8.setText(command1.getDeviceModel(output1.get(i)));
					label8.setVisible(true);
					label9.setText("Android " + command1.getDeviceOSVersion(output1.get(i)));
					label9.setVisible(true);
					label10.setText(ips.get(1));
					label10.setVisible(true);
					radio2.setSelected(true);
					radio2.setVisible(true);
					saveButton2.setVisible(true);
					if (output1.get(i).endsWith(":5555")) {
						wifiDebbug2.setEnabled(false);
					}
					wifiDebbug2.setVisible(true);
					enableFirebase2.setVisible(true);
					
				} else if (i == 2){
					
					panel4.setVisible(true);
					label11.setText(output1.get(i));
					label11.setVisible(true);
					label12.setText(command1.getDeviceManufacturer(output1.get(i)));
					label12.setVisible(true);
					label13.setText(command1.getDeviceModel(output1.get(i)));
					label13.setVisible(true);
					label14.setText("Android " + command1.getDeviceOSVersion(output1.get(i)));
					label14.setVisible(true);
					label15.setText(ips.get(2));
					label15.setVisible(true);
					radio3.setSelected(true);
					radio3.setVisible(true);
					saveButton3.setVisible(true);
					if (output1.get(i).endsWith(":5555")) {
						wifiDebbug3.setEnabled(false);
					}
					wifiDebbug3.setVisible(true);
					enableFirebase3.setVisible(true);
					
				} else if (i == 3){
					
					panel5.setVisible(true);
					label16.setText(output1.get(i));
					label16.setVisible(true);
					label17.setText(command1.getDeviceManufacturer(output1.get(i)));
					label17.setVisible(true);
					label18.setText(command1.getDeviceModel(output1.get(i)));
					label18.setVisible(true);
					label19.setText("Android " + command1.getDeviceOSVersion(output1.get(i)));
					label19.setVisible(true);
					label20.setText(ips.get(3));
					label20.setVisible(true);
					radio4.setSelected(true);
					radio4.setVisible(true);
					saveButton4.setVisible(true);
					if (output1.get(i).endsWith(":5555")) {
						wifiDebbug4.setEnabled(false);
					}
					wifiDebbug4.setVisible(true);
					enableFirebase4.setVisible(true);
					
				}
				
			}
			
		} else if (e.getSource()==fileButton) {
			
			JFileChooser fileChooser = new JFileChooser("C:\\Users\\dtomic\\OneDrive - Smith Micro Software\\SP7\\Master builds\\");
			int response = fileChooser.showOpenDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				fileTextField.setText(file.getName());
				if (radio1.isSelected() || radio2.isSelected() || radio3.isSelected() || radio4.isSelected()) {
					installButton.setEnabled(true);
				}
			}
			
			
		} else if (e.getSource()==saveButton1) {
			
			JFileChooser fileChooser = new JFileChooser("C:\\logs");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int response = fileChooser.showSaveDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				output1 = command1.getConnectedDevices();	
				command1.saveLogs(output1.get(0), file.getAbsolutePath());
		
			}
			
		} else if (e.getSource()==saveButton2) {
			
			JFileChooser fileChooser = new JFileChooser("C:\\logs");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int response = fileChooser.showSaveDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				output1 = command1.getConnectedDevices();			
				command1.saveLogs(output1.get(1), file.getAbsolutePath());		
			}
			
		} else if (e.getSource()==saveButton3) {
			
			JFileChooser fileChooser = new JFileChooser("C:\\logs");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int response = fileChooser.showSaveDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				output1 = command1.getConnectedDevices();			
				command1.saveLogs(output1.get(2), file.getAbsolutePath());		
			}
			
		} else if (e.getSource()==saveButton4) {
			
			JFileChooser fileChooser = new JFileChooser("C:\\logs");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int response = fileChooser.showSaveDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				output1 = command1.getConnectedDevices();			
				command1.saveLogs(output1.get(3), file.getAbsolutePath());		
			}
			
		} else if (e.getSource()== wifiDebbug1) {
			
			output1 = command1.getConnectedDevices();	
			command1.startWifiDebugging(output1.get(0), ips.get(0));
			JOptionPane.showMessageDialog(this, "Debugging over WiFi is enabled!" + "\n" + "If prompted on the device, allow wireless debugging on specific wifi network." + "\n" + "You may disconnect USB cable from this device.", "Enable WiFi Debugging.", JOptionPane.INFORMATION_MESSAGE);
			if (output1.get(0).endsWith(":5555")) {
				wifiDebbug1.setEnabled(false);
			}
		} else if (e.getSource()== wifiDebbug2) {
			
			output1 = command1.getConnectedDevices();	
			command1.startWifiDebugging(output1.get(1), ips.get(1));
			JOptionPane.showMessageDialog(this, "Debugging over WiFi is enabled!" + "\n" + "If prompted on the device, allow wireless debugging on specific wifi network." + "\n" + "You may disconnect USB cable from this device.", "Enable WiFi Debugging.", JOptionPane.INFORMATION_MESSAGE);
			if (output1.get(1).endsWith(":5555")) {
				wifiDebbug2.setEnabled(false);
			}
		} else if (e.getSource()== wifiDebbug3) {
			
			output1 = command1.getConnectedDevices();	
			command1.startWifiDebugging(output1.get(2), ips.get(2));
			JOptionPane.showMessageDialog(this, "Debugging over WiFi is enabled!" + "\n" + "If prompted on the device, allow wireless debugging on specific wifi network." + "\n" + "You may disconnect USB cable from this device.", "Enable WiFi Debugging.", JOptionPane.INFORMATION_MESSAGE);
			if (output1.get(2).endsWith(":5555")) {
				wifiDebbug3.setEnabled(false);
			}
			
		} else if (e.getSource()== wifiDebbug4) {
			
			output1 = command1.getConnectedDevices();	
			command1.startWifiDebugging(output1.get(3), ips.get(3));
			JOptionPane.showMessageDialog(this, "Debugging over WiFi is enabled!" + "\n" + "If prompted on the device, allow wireless debugging on specific wifi network." + "\n" + "You may disconnect USB cable from this device.", "Enable WiFi Debugging.", JOptionPane.INFORMATION_MESSAGE);
			if (output1.get(3).endsWith(":5555")) {
				wifiDebbug4.setEnabled(false);
			}
		} else if (e.getSource()== enableFirebase1) {
			
			output1 = command1.getConnectedDevices();	
			command1.enableAnalyticsDebug(output1.get(0));
			JOptionPane.showMessageDialog(this, "Firebase Debugging enabled!" + "\n" + "Make sure 'Logging Analytics Events' toggle button is also enabled in Debug menu.", "Enable Firebase Debugging", JOptionPane.INFORMATION_MESSAGE);
			
		} else if (e.getSource()== enableFirebase2) {
			
			output1 = command1.getConnectedDevices();	
			command1.enableAnalyticsDebug(output1.get(1));
			JOptionPane.showMessageDialog(this, "Firebase Debugging enabled!" + "\n" + "Make sure 'Logging Analytics Events' toggle button is also enabled in Debug menu.", "Enable Firebase Debugging", JOptionPane.INFORMATION_MESSAGE);

			
		} else if (e.getSource()== enableFirebase3) {
			
			output1 = command1.getConnectedDevices();	
			command1.enableAnalyticsDebug(output1.get(2));
			JOptionPane.showMessageDialog(this, "Firebase Debugging enabled!" + "\n" + "Make sure 'Logging Analytics Events' toggle button is also enabled in Debug menu.", "Enable Firebase Debugging", JOptionPane.INFORMATION_MESSAGE);

			
		} else if (e.getSource()== enableFirebase4) {
			
			output1 = command1.getConnectedDevices();	
			command1.enableAnalyticsDebug(output1.get(3));
			JOptionPane.showMessageDialog(this, "Firebase Debugging enabled!" + "\n" + "Make sure 'Logging Analytics Events' toggle button is also enabled in Debug menu.", "Enable Firebase Debugging", JOptionPane.INFORMATION_MESSAGE);

			
		} else if (e.getSource()==installButton) {	
			output1 = command1.getConnectedDevices();
			bar.setString("Installing...");
			bar.setIndeterminate(true);
			bar.setBackground(new Color(238, 238, 238));
	        installButton.setEnabled(false);
	        
			if (radio1.isSelected() && output1.size() > 0) {
				Task task1 = new Task("adb -s " + output1.get(0) + " install " + "\"" + file.getAbsolutePath() + "\"");
		        task1.addPropertyChangeListener(this);
		        task1.execute();
			} 
			if (radio2.isSelected() && output1.size() > 1) {
				Task task2 = new Task("adb -s " + output1.get(1) + " install " + "\"" + file.getAbsolutePath() + "\"");
		        task2.addPropertyChangeListener(this);
		        task2.execute();
			}
			if (radio3.isSelected() && output1.size() > 2){
				Task task3 = new Task("adb -s " + output1.get(2) + " install " + "\"" + file.getAbsolutePath() + "\"");
		        task3.addPropertyChangeListener(this);
		        task3.execute();					
			}
			if (radio4.isSelected() && output1.size() > 3) {
				Task task4 = new Task("adb -s " + output1.get(3) + " install " + "\"" + file.getAbsolutePath() + "\"");
		        task4.addPropertyChangeListener(this);
		        task4.execute();
			}
		} 
	}
	
	//Thread synchronization to determine the end of all processes
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
        	command1.command(command);
            return null;
        }

        @Override
        public void done() {
        	decreaseCounter();
        	if (counter == 0) {
        		bar.setString("Done!");
        		bar.setBackground(Color.green);
        		installButton.setEnabled(true);
        		bar.setIndeterminate(false);
        	}
        }
    }
}