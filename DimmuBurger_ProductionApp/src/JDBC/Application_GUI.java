package JDBC;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Application_GUI {

	//JDBC jdbc = new JDBC();
	private JDBC jdbc = new JDBC();

	private JFrame loginframe;
	private JFrame kitchenframe;
	private Box Orders;
	private Timer timer;
	private GridLayout grid;

	private ArrayList<String> names = new ArrayList<String>();
	private Burger[] display = new Burger[5];
	private ArrayList<Burger> allOrders = new ArrayList<Burger>();

	private String recStr = "Received";
	private String progStr = "In progress";
	private String doneStr = "Complete";
	
	JLabel failedlogin = new JLabel("Sorry, please try again");
	JTextField username = new JTextField();
	JTextField password = new JTextField();
	
	JButton loginBtn = new JButton("Login to Admin screen");
	JButton kitchenBtn = new JButton("Go to Kitchen screen");
	JButton goBackBtn = new JButton("Return");
	
	JButton inProg0 = new JButton(progStr);
	JButton done0 = new JButton(doneStr);
	JButton inProg1 = new JButton(progStr);
	JButton done1 = new JButton(doneStr);
	JButton inProg2 = new JButton(progStr);
	JButton done2 = new JButton(doneStr);
	JButton inProg3 = new JButton(progStr);
	JButton done3 = new JButton(doneStr);
	JButton inProg4 = new JButton(progStr);
	JButton done4 = new JButton(doneStr);

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					Application_GUI window = new Application_GUI();
					
					window.loginframe.setVisible(true);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
	}

	public Application_GUI() {
		
		initializeLogin();
		
		initializeKitchen();
		
		setOnClickListeners();
		
		setupTimer();
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
	
	private void initializeKitchen() {
		
		setupKitchenFrame();
		
		System.out.println("setup kitchen");
		
		// empty out display
		for(int i = 0; i < display.length; i++) {
			
			display[i] = null;
		}

		//copy start of allOrders into display
		for(int i = 0; i < allOrders.size(); i++) {
			
			if(i < display.length) {
				
				display[i] = allOrders.get(i);
			}
		}

		//vertical box to hold all elements
		Orders = Box.createVerticalBox();

		//need to add some buttons and list size at top of page		
		Box topBox = addTopOfDisplay();
		Orders.add(topBox);

		//take display and draw to window
		for(int i=0;i<display.length;i++) {

			if(display[i]!=null) {
				
				Box subBox = createNewSubBox(i);

				Orders.add(subBox);
			}
		}
		setOnClickListeners();

		kitchenframe.getContentPane().removeAll();
		System.out.println("removing all ");

		kitchenframe.getContentPane().add(Orders);
		kitchenframe.getContentPane().revalidate();
		kitchenframe.getContentPane().repaint();
	}
	
	public void setupKitchenFrame() {
		
		kitchenframe = new JFrame();
		kitchenframe.setBounds(100, 100, 900, 900);
		
		//may have to change sizing to account for number of subboxes
		kitchenframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kitchenframe.getContentPane().setLayout(new CardLayout(0, 0));
	}
	
	// timer is initialized with the production line app
	// pings and accepts orders regardless of whether the kitchen page is up
	// currently continues to run even if app if closed...
		public void setupTimer() {

			System.out.println("setup timer");
			//currently set for 1 second refreshes, maybe change depending on interaction
			//but only refreshes display if sommat new found

			int delay = 1000;

			ActionListener taskPerformer = new ActionListener() {

				public void actionPerformed(ActionEvent evt) {

					System.out.println("ping timer");

					Burger newOrder = jdbc.receiveNewOrder();

					//if new order exists
					if(newOrder != null) {

						// add new burger to allOrders list
						allOrders.add(newOrder);

						// update order state
						boolean updated = jdbc.updateOrderProgress(newOrder.getOrderNum(), newOrder.getSubOrderNum(), recStr);

						// reinitialize kitchen screen to update orders accordingly
						initializeKitchen();  
					}
				}
			};
			// timer object taking the delay, and task as params
			new Timer(delay, taskPerformer).start();	
		}
		
		public Box addTopOfDisplay() {
			
			Box topBar = Box.createHorizontalBox();
			topBar.add(goBackBtn);

			JLabel ordTitle = new JLabel("Total number of Orders: "+allOrders.size());
			System.out.println("Total number of Orders: "+allOrders.size());
			
			topBar.add(ordTitle);
			System.out.println("created topbar");
			
			return topBar;
		}

		public Box createNewSubBox(int n) {
			
			System.out.println("created subbox " + n);
			
			Burger burger = display[n];
			ArrayList<FoodItem> ings = burger.getIngredientList();

			Box SubOrder = Box.createHorizontalBox();
			Box WordArea = Box.createVerticalBox(); 
			SubOrder.add(WordArea);

			Box OrderLbls = Box.createHorizontalBox();
			WordArea.add(OrderLbls);

			//maybe combine the two order labels into one?
			JLabel lblOrder = new JLabel("Order# ");
			OrderLbls.add(lblOrder);

			JLabel lblOrderVal = new JLabel(Integer.toString(burger.getOrderNum()) + "  ");
			OrderLbls.add(lblOrderVal);

			JLabel lblSuborder = new JLabel("SubOrder# ");
			OrderLbls.add(lblSuborder);

			JLabel lblSuborderVal = new JLabel(Integer.toString(burger.getSubOrderNum()));
			OrderLbls.add(lblSuborderVal);

			JTextArea FoodTxt = new JTextArea();
			FoodTxt.setLineWrap(true);

			for(int i = 0; i < ings.size(); i++) {
				
				if(ings.get(i).getQuantity() > 0) {
					
					FoodTxt.setText(FoodTxt.getText() + ings.get(i).getName() + "  " 
										+ ings.get(i).getQuantity() + " ; ");
				}	
			}

			FoodTxt.setText(FoodTxt.getText() + n);
			WordArea.add(FoodTxt);

			Box BtnArea = createNewBtnBox(n);
			SubOrder.add(BtnArea);

			return SubOrder;
		}

		public Box createNewBtnBox(int n) {
			
			Box BtnArea = Box.createVerticalBox();

			//if is correct button, and haven't clicked in progress for that button
			if(n == 0) {						// && !display[n].getState().equals(progStr)
				
				inProg0.setVisible(true); //probably the right place for this
				
				//we want to add them both, but maybe only one is visible
				BtnArea.add(inProg0);
				BtnArea.add(done0);
				
			}else if(n == 1) {
				
				BtnArea.add(inProg1);
				BtnArea.add(done1);
				
			}else if(n == 2) {
				
				BtnArea.add(inProg2);
				BtnArea.add(done2);
				
			}else if(n == 3) {
				
				BtnArea.add(inProg3);
				BtnArea.add(done3);
				
			}else if(n == 4) {
				
				BtnArea.add(inProg4);
				BtnArea.add(done4);
			}
			return BtnArea;
		}
	
	public void setOnClickListeners() {
		
		loginBtn.addActionListener(new ActionListener() {
			
	         public void actionPerformed(ActionEvent e) {
	        	 
	        	boolean canLogin = jdbc.adminLogin(username.getText(), password.getText());
	        	
	        	if(canLogin) {
	        		
	        		loginframe.setVisible(false);
//	        		adminframe.setVisible(true);
//	        		initilizeKitchen();
	        	} else {
	        		
	        		failedlogin.setVisible(true);
	        		username.setText("");
	        		password.setText("");
	        	}
	         }          
	      });
		
		// sets up kitchen page
		kitchenBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				loginframe.setVisible(false);
				kitchenframe.setVisible(true);
//				setupTimer();
				//setupKitchenFrame();
				//initializeKitchen();
			}
		});
		
		goBackBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				kitchenframe.setVisible(false);
				
				loginframe.setVisible(true);
				//inilializeLogin();
			}          
		});

		//may need some changes to get visibility working right for button with initialization
		inProg0.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) {
				
				inProg0.setVisible(false);
				//	        	 display.get(0).setState("In progress"); //do we need to do this???
				allOrders.get(0).setState(progStr);
				jdbc.updateOrderProgress(display[0].getOrderNum(),display[0].getSubOrderNum(),progStr);
				//do also need to change allORders, or just remove when done??
			}          
		});

		//not working on this button action
		done0.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				jdbc.updateOrderProgress(display[0].getOrderNum(),display[0].getSubOrderNum(),doneStr);
				//	        	 display.remove(0);
				allOrders.remove(0);
				System.out.println("orders size "+allOrders.size());

				//need to redraw kitchen
				initializeKitchen();
			}          
		});
	}
}
