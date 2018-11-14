package JDBC;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	//private GridLayout grid;belongs to the kitchen

	private ArrayList<String> names = new ArrayList<String>();
	private Burger[] display = new Burger[5];
	private ArrayList<Burger> allOrders = new ArrayList<Burger>();

	private String recStr = "received";
	private String progStr = "inprogress";
	private String doneStr = "complete";

	JLabel failedlogin = new JLabel("Sorry, please try again");
	JTextField username = new JTextField();
	JTextField password = new JTextField();	
	JButton loginBtn = new JButton("Login to Admin screen");
	JButton kitchenBtn = new JButton("Go to Kitchen screen");
	JButton goBackBtn1 = new JButton("Return to Login screen");
	JButton goBackBtn2 = new JButton("Return to Login screen");

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

	private ArrayList<String>food = new ArrayList<String>();
	private JFrame adminframe;



	private GridBagLayout grid =new GridBagLayout();
	private GridBagConstraints con = new GridBagConstraints();

	private JButton submitBtn = new JButton();
	private JButton goBackBtn = new JButton();
	private JScrollPane sp = new JScrollPane();
	//private Scene reOrderScn;
	//private Scene loginScn;
	//private Stage primaryStage;

	private String quanTitle = "Quantity in Stock:";
	private String minTitle = "Min restock level:";
	private String reorderTitle = "Quan to reorder:";

	private int breadX=1;
	private int breadY=1;
	private int baseY=breadY+5;
	private int prodY=baseY+6;
	private int chesY=prodY+6;
	private int sausY=chesY+6;

	private int nameCol=1;
	private int quanCol=2;
	private int minCol=3;
	private int reordCol=4;
	HashMap<String,Integer> rowCount = new HashMap<String,Integer>();
	HashMap<String,Integer> catRowCount = new HashMap<String,Integer>();
	//Burger stock=new Burger();

	JTextField orderBun;
	JTextField orderJnr;
	JTextField orderWrap;
	JTextField orderBeef;
	JTextField orderChic;
	JTextField orderTofu;
	JTextField orderFal;
	JTextField orderLet;
	JTextField orderTom;
	JTextField orderOni;
	JTextField orderCap;
	JTextField orderSwi;
	JTextField orderChed;
	JTextField orderHal;
	JTextField orderPan;
	JTextField orderTomS;
	JTextField orderBbq;
	JTextField orderPea;
	JTextField orderAio;
	JTextField orderMayo;
	JTextField orderMus;
	JTextField orderCool;



	//application class
	public Application_GUI() {

		initializeLogin();
		setupKitchenFrame();
		initializeKitchen();
		setupAdminFrame();
		initializeAdmin();
		setOnClickListeners();

		setupTimer();
	}

	public void initializeLogin() {

		loginframe = new JFrame();

		Box vbox = Box.createVerticalBox();

		failedlogin.setVisible(false);

		JLabel usernameLbl = new JLabel("Username:");
		getBigger(usernameLbl, 9); 
		increaseLableSize(usernameLbl,160, 80);//increase the size
		//usernameLbl.setBackground(Color.pink);
		//usernameLbl.setOpaque(true); 
		
		increaseLableSize(username,160, 80);//for the font size
		getBigger(username,9);
		
		
		JLabel passwordLbl = new JLabel("Password:");
		getBigger(passwordLbl, 9);
		increaseLableSize(passwordLbl,160, 80);//increase the size
		//passwordLbl.setBackground(Color.pink);
		//passwordLbl.setOpaque(true);
		
		increaseLableSize(password,160, 80);
		getBigger(password,9);
		
		increaseLableSize(loginBtn,160, 80);
		getBigger(loginBtn,6);
		
		
		vbox.add(failedlogin);
		vbox.add(usernameLbl);
		
		vbox.add(username);
		vbox.add(passwordLbl);
		
		vbox.add(password);
		
		vbox.add(loginBtn);
		


		JPanel left = new JPanel();
		left.setSize(400, 600);
		left.setBackground(Color.yellow);
		left.setVisible(true);
		left.add(vbox);


		JPanel right = new JPanel();
		right.setSize(400, 600);
		right.setBackground(Color.pink);
		right.setVisible(true);		
		right.add(kitchenBtn);
		increaseLableSize(kitchenBtn,250, 80);
		getBigger(kitchenBtn,6);


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



		//setupKitchenFrame();

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

		//maybe here???
//		setOnClickListeners();

		kitchenframe.getContentPane().removeAll();
		System.out.println("removing all ");

		kitchenframe.getContentPane().add(Orders);
		kitchenframe.getContentPane().revalidate();
		kitchenframe.getContentPane().repaint();
		System.out.println("end of intial k");
	}

	public void setupKitchenFrame() {
		kitchenframe = new JFrame();
		kitchenframe.setBounds(100, 100, 900, 900);

		//may have to change sizing to account for number of subboxes
		kitchenframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kitchenframe.getContentPane().setLayout(new CardLayout(0, 0));
		kitchenframe.setVisible(false);

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

//				System.out.println("ping timer");

				Burger newOrder = jdbc.receiveNewOrder();

				//if new order exists
				if(newOrder != null) {

					newOrder.setState(recStr);
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
		topBar.add(goBackBtn1);

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

		//JLabel lblSuborder = new JLabel("SubOrder# ");
		//OrderLbls.add(lblSuborder);

		//JLabel lblSuborderVal = new JLabel(Integer.toString(burger.getSubOrderNum()));
		//OrderLbls.add(lblSuborderVal);

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
			//if state of burger (0) is inprogress,
			//inprog visible (false);
			//else inprog visible (true)
			if(display[n].getState().equals(progStr) && display[n]!=null) {
				inProg0.setVisible(false); 
			}else
			{ inProg0.setVisible(true); }

			System.out.println("state "+display[n].getState());


			//				inProg0.setVisible(true); //probably the right place for this

			//we want to add them both, but maybe only one is visible
			
			BtnArea.add(inProg0);
			BtnArea.add(done0);

		}else if(n == 1) {
			if(display[n].getState().equals(progStr) && display[n]!=null) {
				inProg1.setVisible(false); 
			}else
			{ inProg1.setVisible(true); }

			BtnArea.add(inProg1);
			BtnArea.add(done1);

		}else if(n == 2) {
			if(display[n].getState().equals(progStr) && display[n]!=null) {
				inProg2.setVisible(false); 
			}else
			{ inProg2.setVisible(true); }

			BtnArea.add(inProg2);
			BtnArea.add(done2);

		}else if(n == 3) {
			if(display[n].getState().equals(progStr) && display[n]!=null) {
				inProg3.setVisible(false); 
			}else
			{ inProg3.setVisible(true); }

			BtnArea.add(inProg3);
			BtnArea.add(done3);

		}else if(n == 4) {
			if(display[n].getState().equals(progStr) && display[n]!=null) {
				inProg4.setVisible(false); 
			}else
			{ inProg4.setVisible(true); }

			BtnArea.add(inProg4);
			BtnArea.add(done4);
		}
		return BtnArea;
	}
	
	

	public void setOnClickListeners() {



		//login to admin screen
		//Dimension maxSize = new Dimension(100, 100);
		//loginBtn.setMaximumSize(maxSize);
		//increaseLableSize(loginBtn,160, 80);
		//getBigger(loginBtn,90);
		loginBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//getBigger(loginBtn,150);
		
			//loginBtn.setSize(150, 75);
		           // setMaximumSize(getSize(78));
				boolean canLogin = jdbc.adminLogin(username.getText(), password.getText());

				if(canLogin) {

					loginframe.setVisible(false);
					adminframe.setVisible(true);
					adminframe.setBackground(Color.LIGHT_GRAY);
					kitchenframe.setVisible(false);
					//	        		initilizeKitchen();
				} else {

					failedlogin.setVisible(true);
					username.setText("");
					password.setText("");
				}
			}          
		});

		// sets up kitchen page
		//increaseLableSize(kitchenBtn,160, 80);
		kitchenBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				loginframe.setVisible(false);
				kitchenframe.setVisible(true);
				adminframe.setVisible(false);
				//				setupTimer();
				//setupKitchenFrame();
				//initializeKitchen();
			}
		});

		//return from kitchen to login
		increaseLableSize(goBackBtn,160, 80);
		goBackBtn1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				 
				kitchenframe.setVisible(false);
				adminframe.setVisible(false);
				loginframe.setVisible(true);
				initializeLogin();
			}          
		});

		//return from admin to login
		//change button name
		//create new button
		increaseLableSize(goBackBtn2,160, 80);
		goBackBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminframe.setVisible(false);
				loginframe.setVisible(true);
				kitchenframe.setVisible(false);
				initializeLogin();
			}


		});


		//admin screen save new quantities to db
		//action for Submit button
		increaseLableSize(submitBtn,160, 80);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				submitAction();

				initializeAdmin();
				//now just need to redraw screen
			}

		}); 

		//may need some changes to get visibility working right for button with initialization
		
		inProg0.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				inProg0.setVisible(false);
				//	        	 display.get(0).setState("In progress"); //do we need to do this???
				allOrders.get(0).setState(progStr);
				jdbc.updateOrderProgress(allOrders.get(0).getOrderNum(),allOrders.get(0).getSubOrderNum(),progStr);
				//do also need to change allORders, or just remove when done??
			}          
		});


		inProg1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				inProg1.setVisible(false);
				//	        	 display.get(0).setState("In progress"); //do we need to do this???
				allOrders.get(1).setState(progStr);
				jdbc.updateOrderProgress(allOrders.get(1).getOrderNum(),allOrders.get(1).getSubOrderNum(),progStr);
				//do also need to change allORders, or just remove when done??
			}          
		});


		inProg2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				inProg2.setVisible(false);
				//	        	 display.get(0).setState("In progress"); //do we need to do this???
				allOrders.get(2).setState(progStr);
				jdbc.updateOrderProgress(allOrders.get(2).getOrderNum(),allOrders.get(2).getSubOrderNum(),progStr);
				//do also need to change allORders, or just remove when done??
			}          
		});
		inProg3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				inProg3.setVisible(false);
				//	        	 display.get(0).setState("In progress"); //do we need to do this???
				allOrders.get(3).setState(progStr);
				jdbc.updateOrderProgress(allOrders.get(3).getOrderNum(),allOrders.get(3).getSubOrderNum(),progStr);
				//do also need to change allORders, or just remove when done??
			}          
		});

		inProg4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				inProg4.setVisible(false);
				//	        	 display.get(0).setState("In progress"); //do we need to do this???
				allOrders.get(4).setState(progStr);
				jdbc.updateOrderProgress(allOrders.get(4).getOrderNum(),allOrders.get(4).getSubOrderNum(),progStr);
				//do also need to change allORders, or just remove when done??
			}          
		});

		done0.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
System.out.println("Count listeners: "+((JButton) e.getSource()).getActionListeners().length);
				jdbc.updateOrderProgress(allOrders.get(0).getOrderNum(),allOrders.get(0).getSubOrderNum(),doneStr);
//				System.out.println("updated 0 "+updated);
				System.out.println("name at 0 "+allOrders.get(0).getName());
				System.out.println("state at 0 "+allOrders.get(0).getState());
				allOrders.remove(0);
//				System.out.println("name at 0 after remove "+allOrders.get(0).getName());
				inProg0.setVisible(true);
//				System.out.println("orders size "+allOrders.size());

				//need to redraw kitchen
				initializeKitchen();
				System.out.println("after initial K in onclick done0");
			}          
		});


		done1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Count listeners: "+((JButton) e.getSource()).getActionListeners().length);
				jdbc.updateOrderProgress(allOrders.get(1).getOrderNum(),allOrders.get(1).getSubOrderNum(),doneStr);
				//	        	 display.remove(4);
				System.out.println("state at 1 "+allOrders.get(1).getState());
				allOrders.remove(1);
				inProg1.setVisible(true);
				System.out.println("orders size "+allOrders.size());

				//need to redraw kitchen
				initializeKitchen();
			}          
		});

		done2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Count listeners: "+((JButton) e.getSource()).getActionListeners().length);
				jdbc.updateOrderProgress(allOrders.get(2).getOrderNum(),allOrders.get(2).getSubOrderNum(),doneStr);
				//	        	 display.remove(2);
				allOrders.remove(2);
				inProg2.setVisible(true);
				System.out.println("orders size "+allOrders.size());

				//need to redraw kitchen
				initializeKitchen();
			}          
		});
		
		done3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Count listeners: "+((JButton) e.getSource()).getActionListeners().length);
				jdbc.updateOrderProgress(allOrders.get(3).getOrderNum(),allOrders.get(3).getSubOrderNum(),doneStr);
				//	        	 display.remove(2);
				allOrders.remove(3);
				inProg3.setVisible(true);
				System.out.println("orders size "+allOrders.size());

				//need to redraw kitchen
				initializeKitchen();
			}          
		});
		
		done4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jdbc.updateOrderProgress(allOrders.get(4).getOrderNum(),allOrders.get(4).getSubOrderNum(),doneStr);
				//	        	 display.remove(2);
				allOrders.remove(4);
				inProg4.setVisible(true);
				System.out.println("orders size "+allOrders.size());

				//need to redraw kitchen
				initializeKitchen();
			}          
		});
	}	


	public void setupAdminFrame(){
		adminframe = new JFrame();
		adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminframe.getContentPane().setLayout(grid);
		adminframe.pack();
		adminframe.setSize(1000, 1000);
		adminframe.setVisible(true);
		setupRowHashMap();
		setupHeadingHashMap();
		//populateAdmin();
		adminframe.setVisible(false);
		//setActionListeners();

	}
	public void getBigger(Component obj, int n) {
		
		Font f = obj.getFont();

		  // create a new, smaller font from the current font
		  Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+n);

		  // set the new font in the editing area
		  obj.setFont(f2);
	}
	public void increaseLableSize(Component obj,int width, int height) {
	obj.setPreferredSize(new Dimension(width,height));
	}
	public void initializeAdmin() {
		adminframe.getContentPane().removeAll();
		Burger stock=jdbc.checkStockLevels();
		ArrayList<FoodItem> ings=stock.getIngredientList();

//		setOnClickListeners();

		//populate ingredient values from stock burger
		for(int i=0;i<ings.size();i++) {

			int rowNum=rowCount.get(ings.get(i).getName());

			// bread1 in column 1, row 2
			JLabel nameBun = new JLabel(ings.get(i).getName());
			//nameBun.setFont(Font.font("Arial",  15));
			getBigger(nameBun,9) ;
			con.gridx=nameCol;
			con.gridy=rowNum;
			adminframe.add(nameBun, con);

			// bread1 in column 2, row 2
			JLabel quanBun = new JLabel(Integer.toString(ings.get(i).getQuantity()));
			
			//getBigger(quanBun,9) ;
			con.gridx=quanCol;
			con.gridy=rowNum;
			adminframe.add(quanBun,con);

			// bread1 in column 3, row 2
			JLabel minBun = new JLabel(Integer.toString(ings.get(i).getMinLevel()));
			getBigger(minBun,9) ;
			con.gridx=minCol;
			con.gridy=rowNum;
			adminframe.add(minBun, con);

			if(ings.get(i).getMinLevel()>ings.get(i).getQuantity()) {		    	
				nameBun.setForeground(Color.RED);
				quanBun.setForeground(Color.RED);
				minBun.setForeground(Color.RED);
			}


		} //end arraylist loop

		//populate category headings
		for(String cat:catRowCount.keySet()) {

			//bread headings
			// Ing name in column 1, row 1
			JLabel nameCat = new JLabel(cat);
		
			getBigger(nameCat,9) ;
			con.fill = GridBagConstraints.HORIZONTAL;
			con.weightx = 0.25;
			con.gridx=breadX;
			con.gridy=catRowCount.get(cat);
			adminframe.add(nameCat, con); 

			// Quan in column 2, row 1
			JLabel quantityCat = new JLabel(quanTitle);
			getBigger(quantityCat,9) ;
			con.fill = GridBagConstraints.HORIZONTAL;
			con.weightx = 0.25;
			con.gridx=quanCol;
			con.gridy=catRowCount.get(cat);
			adminframe.add(quantityCat, con);

			// restock in column 3, row 1
			JLabel restockCat = new JLabel(minTitle);
			getBigger(restockCat,9) ;
			con.fill = GridBagConstraints.HORIZONTAL;
			con.weightx = 0.25;
			con.gridx=minCol;
			con.gridy=catRowCount.get(cat);
			adminframe.add(restockCat, con);

			// toOrder in column 4, row 1
			JLabel toOrderCat = new JLabel(reorderTitle);
			//toOrderCat.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			getBigger(toOrderCat,9) ;
			con.fill = GridBagConstraints.HORIZONTAL;
			con.weightx = 0.25;
			con.gridx=reordCol;
			con.gridy=catRowCount.get(cat);
			adminframe.add(toOrderCat, con);		

		}

		//setting the guess button attributes
		goBackBtn1.setText("Go Back");
		//goBackBtn.setFont(Font.font(15));
		increaseLableSize(goBackBtn1,160, 80);// to increase the size
		con.gridx=nameCol;
		con.gridy=sausY+8;
		adminframe.add(goBackBtn1, con);


		//setting the guess button attributes
		submitBtn.setText("Submit");
		//submitBtn.setFont(Font.font(15));
		increaseLableSize(submitBtn,160, 80);// to increase th size
		con.gridx=reordCol;
		con.gridy=sausY+8;
		adminframe.add(submitBtn, con);

		setupTextFields();

		//		sp.setContent(grid);
		//		adminframe.getContentPane().removeAll();
		//		adminframe.getContentPane().setLayout(grid);
		adminframe.getContentPane().revalidate();
		adminframe.getContentPane().repaint();

	}

	//check this against heroku db
	public void setupRowHashMap() {

		rowCount.put("bread bun",breadY+1);
		rowCount.put("junior", breadY+2);
		rowCount.put("wrap", breadY+3);

		rowCount.put("beef", baseY+1);
		rowCount.put("chicken", baseY+2);
		rowCount.put("tofu", baseY+3);
		rowCount.put("falafel", baseY+4);

		rowCount.put("lettuce",prodY+1);
		rowCount.put("tomato", prodY+2);
		rowCount.put("onion", prodY+3);
		rowCount.put("capsicum", prodY+4);

		rowCount.put("swiss", chesY+1);
		rowCount.put("cheddar", chesY+2);
		rowCount.put("haloumi", chesY+3);
		rowCount.put("paneer", chesY+4);

		rowCount.put("tomato s", sausY+1);
		rowCount.put("bbq", sausY+2);
		rowCount.put("peanut", sausY+3);
		rowCount.put("garlic aioli", sausY+4);
		rowCount.put("mayo", sausY+5);
		rowCount.put("mustard", sausY+6);
		rowCount.put("cool ranch", sausY+7);


	}

	public void setupHeadingHashMap() {

		catRowCount.put("Bread:",breadY);
		catRowCount.put("Base:", baseY);
		catRowCount.put("Produce:", prodY);
		catRowCount.put("Cheese:", chesY);
		catRowCount.put("Sauces:", sausY);

	}

	//check against heroku db
	public void setupTextFields() {
		// bread1 in column 4, row 2
		orderBun = new JTextField("0");
		//orderBun.setFont(Font.font("Arial",  15));
		con.fill = GridBagConstraints.HORIZONTAL;
		con.weightx = 0.25;
		con.gridx=reordCol;
		con.gridy=breadY+1;
		adminframe.add(orderBun, con);

		// bread2 in column 4, row 3
		orderJnr = new JTextField("0");
		//orderJnr.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=breadY+2;
		adminframe.add(orderJnr,con);

		// bread3 in column 4, row 4
		orderWrap = new JTextField("0");
		//orderWrap.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=breadY+3;
		adminframe.add(orderWrap, con);


		// base1 in column 4, row 7
		orderBeef = new JTextField("0");
		//orderBeef.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=baseY+1;
		adminframe.add(orderBeef, con);

		// base1 in column 4, row 8
		orderChic = new JTextField("0");
		//orderChic.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=baseY+2;
		adminframe.add(orderChic, con);

		// base3 in column 4, row 9
		orderFal = new JTextField("0");
		//orderFal.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=baseY+3;
		adminframe.add(orderFal, con);

		// base4 in column 4, row 10
		orderTofu = new JTextField("0");
		//orderTofu.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=baseY+4;
		adminframe.add(orderTofu, con);

		// prod1 in column 4, row 13
		orderLet = new JTextField("0");
		//orderLet.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=prodY+1;
		adminframe.add(orderLet, con);

		// prod2 in column 4, row 14
		orderTom = new JTextField("0");
		//orderTom.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=prodY+2;
		adminframe.add(orderTom, con);

		// prod3 in column 4, row 15
		orderOni = new JTextField("0");
		//orderOni.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=prodY+3;
		adminframe.add(orderOni, con);

		// prod4 in column 4, row 16
		orderCap = new JTextField("0");
		//orderCap.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=prodY+4;
		adminframe.add(orderCap, con);

		// ches1 in column 4, row 19
		orderSwi = new JTextField("0");
		//orderSwi.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=chesY+1;
		adminframe.add(orderSwi, con);

		// ches2 in column 4, row 20
		orderChed = new JTextField("0");
		//orderChed.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=chesY+2;
		adminframe.add(orderChed, con);

		// ches3 in column 4, row 21
		orderHal = new JTextField("0");
		//orderHal.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=chesY+3;
		adminframe.add(orderHal, con);

		// ches4 in column 4, row 22
		orderPan = new JTextField("0");
		//orderPan.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=chesY+4;
		adminframe.add(orderPan, con);

		// saus1 in column 4, row 25
		orderTomS = new JTextField("0");
		//orderPan.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=sausY+1;
		adminframe.add(orderTomS, con);

		// saus2 in column 4, row 26
		orderBbq = new JTextField("0");
		//orderPan.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=sausY+2;
		adminframe.add(orderBbq, con);

		// saus3 in column 4, row 27
		orderPea = new JTextField("0");
		//orderPan.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=sausY+3;
		adminframe.add(orderPea, con);

		// saus4 in column 4, row 28
		orderAio = new JTextField("0");
		//orderPan.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=sausY+4;
		adminframe.add(orderAio, con);

		// saus5 in column 4, row 29
		orderMayo = new JTextField("0");
		//orderPan.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=sausY+5;
		adminframe.add(orderMayo, con);

		// saus6 in column 4, row 30
		orderMus = new JTextField("0");
		//orderPan.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=sausY+6;
		adminframe.add(orderMus, con);

		// saus7 in column 4, row 25
		orderCool = new JTextField("0");
		//orderPan.setFont(Font.font("Arial",  15));
		con.gridx=reordCol;
		con.gridy=sausY+7;
		adminframe.add(orderCool, con);
	}



	public void submitAction() {

		Burger toReorder=new Burger();
		FoodItem foodTemp=new FoodItem("","bread bun",Integer.parseInt(orderBun.getText()),0,0);
		System.out.println(foodTemp.getName());
		toReorder.addIngredient(foodTemp);

		foodTemp=new FoodItem("","junior",Integer.parseInt(orderJnr.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","wrap",Integer.parseInt(orderWrap.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","beef",Integer.parseInt(orderBeef.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","chicken",Integer.parseInt(orderChic.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","tofu",Integer.parseInt(orderTofu.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","falafel",Integer.parseInt(orderFal.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","lettuce",Integer.parseInt(orderLet.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","tomato",Integer.parseInt(orderTom.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","onion",Integer.parseInt(orderOni.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","capsicum",Integer.parseInt(orderCap.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","swiss",Integer.parseInt(orderSwi.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","cheddar",Integer.parseInt(orderChed.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","haloumi",Integer.parseInt(orderHal.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","paneer",Integer.parseInt(orderPan.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","tomato s",Integer.parseInt(orderTomS.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","bbq",Integer.parseInt(orderBbq.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","peanut",Integer.parseInt(orderPea.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","garlic aioli",Integer.parseInt(orderAio.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","mayo",Integer.parseInt(orderMayo.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","mustard",Integer.parseInt(orderMus.getText()),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","cool ranch",Integer.parseInt(orderCool.getText()),0,0);
		toReorder.addIngredient(foodTemp);



		boolean hasReordered = jdbc.reorderStock(toReorder);
		System.out.println("ordered "+hasReordered);
	}




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
}

