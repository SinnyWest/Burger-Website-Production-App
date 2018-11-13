package JDBC;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class Application_GUI {

	JDBC jdbc = new JDBC();

	private JFrame loginframe;
	
	JLabel failedlogin = new JLabel("Sorry, please try again");
	JTextField username = new JTextField();
	JTextField password = new JTextField();
	
	JButton loginBtn = new JButton("Login to Admin screen");
	JButton kitchenBtn = new JButton("Go to Kitchen screen");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application_GUI window = new Application_GUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 */
	public Application_GUI() {
		initializeLogin();
		setOnClickListeners();
	}

	
	public void initializeLogin() {
		loginframe = new JFrame();

		Box vbox = Box.createVerticalBox();
		
		
		failedlogin.setVisible(false);
		JLabel usernameLbl = new JLabel("Username:");
		
		
		
		
		JLabel passwordLbl = new JLabel("Password:");
		
		
		
		vbox.add(failedlogin);
		vbox.add(usernameLbl);
		vbox.add(username);
		vbox.add(passwordLbl);
		vbox.add(password);
		vbox.add(loginBtn);

		JPanel left = new JPanel();
		left.setSize(400, 600);
		//left.setBackground(Color.BLUE);
		left.setVisible(true);
		left.add(vbox);
		

		JPanel right = new JPanel();
		right.setSize(400, 600);
		//right.setBackground(Color.RED);
		right.setVisible(true);		
		right.add(kitchenBtn);


		JSplitPane splitPane = new JSplitPane();
		splitPane.setSize(500, 500);
		splitPane.setDividerSize(20);
		splitPane.setDividerLocation(350);
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(left);
		splitPane.setRightComponent(right);



		loginframe.add(splitPane);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.pack();
		loginframe.setSize(800, 800);
		loginframe.setVisible(true);
	}
	
	public void setOnClickListeners() {
		
		loginBtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	boolean canLogin = jdbc.adminLogin(username.getText(), password.getText());
	        	if(canLogin) {
	        		loginframe.setVisible(false);
//	        		kitchenframe.setVisible(true);
//	        		initilizeKitchen();
	        	} else {
	        		failedlogin.setVisible(true);
	        		username.setText("");
	        		password.setText("");
	        	}
	         }          
	      });
		
		kitchenBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
		
		
		
	}
}
