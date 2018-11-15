package JDBC;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;

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



	private Burger[] display = new Burger[5];
	private ArrayList<Burger> allOrders = new ArrayList<Burger>();

	private String recStr = "received";
	private String progStr = "inprogress";
	private String doneStr = "complete";

	JLabel failedlogin = new JLabel("Sorry, please try again");
	JLabel usernameLbl=new JLabel("Username:");
	JTextField username = new JTextField();

	JLabel passwordLbl = new JLabel("Password:");


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


	private JFrame adminframe;

	JLabel ordTitle = new JLabel();

	private GridBagLayout grid =new GridBagLayout();
	private GridBagConstraints con = new GridBagConstraints();

	private JButton submitBtn = new JButton();


	private String quanTitle = "Quantity in Stock:";
	private String minTitle = "Min restock level:";
	private String reorderTitle = "Quan to reorder:";

	private int breadX=1;
	private int breadY=1;
	private int baseY=breadY+5;
	private int prodY=baseY+6;
	private int chesY=prodY+10;	//increase size by 4
	private int sausY=chesY+6;

	private int nameCol=1;
	private int quanCol=2;
	private int minCol=3;
	private int reordCol=4;
	HashMap<String,Integer> rowCount = new HashMap<String,Integer>();
	HashMap<String,Integer> catRowCount = new HashMap<String,Integer>();


	JTextField orderBun=new JTextField();
	JTextField orderJnr=new JTextField();
	JTextField orderWrap=new JTextField();
	
	JTextField orderBeef=new JTextField();
	JTextField orderChic=new JTextField();
	JTextField orderTofu=new JTextField();
	JTextField orderFal=new JTextField();
	
	JTextField orderLet=new JTextField();
	JTextField orderTom=new JTextField();
	JTextField orderOni=new JTextField();
	JTextField orderCap=new JTextField();
	JTextField orderAvo=new JTextField();	//4x new textfields
	JTextField orderCar=new JTextField();
	JTextField orderPine=new JTextField();
	JTextField orderPic=new JTextField();
	
	JTextField orderSwi=new JTextField();
	JTextField orderChed=new JTextField();
	JTextField orderHal=new JTextField();
	JTextField orderPan=new JTextField();
	
	JTextField orderTomS=new JTextField();
	JTextField orderCur=new JTextField();
	JTextField orderItal=new JTextField();
	JTextField orderAio=new JTextField();
	JTextField orderMayo=new JTextField();

	//application class
	public Application_GUI() {

		setupLoginFrame();
		initializeLogin();
		setupKitchenFrame();
		initializeKitchen();
		setupAdminFrame();
		initializeAdmin();
		setOnClickListeners();

		setupTimer();
	}
	
	public void setupLoginFrame() {
		loginframe = new JFrame();
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.pack();
		loginframe.setSize(800, 800);
		
		loginframe.setVisible(true);
		
		getBigger(usernameLbl, 9); 
		increaseLableSize(usernameLbl,160, 80);//increase the size
		
		increaseLableSize(username,160, 80);//for the font size
		getBigger(username,9);
		
		getBigger(passwordLbl, 9);
		increaseLableSize(passwordLbl,160, 80);//increase the size
		
		increaseLableSize(password,160, 80);
		getBigger(password,9);
		
		increaseLableSize(loginBtn,160, 80);
		getBigger(loginBtn,6);
		
		increaseLableSize(kitchenBtn,250, 80);
		getBigger(kitchenBtn,6);
		
	}

	public void initializeLogin() {

		

		Box vbox = Box.createVerticalBox();

		failedlogin.setVisible(false);

		
		username.setText("");
		password.setText("");
		
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

		JSplitPane splitPane = new JSplitPane();
		splitPane.setSize(500, 500);
		splitPane.setDividerSize(20);
		splitPane.setDividerLocation(350);
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(left);
		splitPane.setRightComponent(right);

		
		loginframe.getContentPane().removeAll();


		loginframe.getContentPane().add(splitPane);
		loginframe.getContentPane().revalidate();
		loginframe.getContentPane().repaint();

		
	}

	private void initializeKitchen() {


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



		kitchenframe.getContentPane().removeAll();


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
		kitchenframe.setVisible(false);
		
		getBigger(ordTitle,6);
		getBigger(goBackBtn1,6);
		increaseLableSize(goBackBtn1,120,80);
		
		getBigger(inProg0,6);
		increaseLableSize(inProg0,140,80);
		getBigger(inProg1,6);
		increaseLableSize(inProg1,140,80);
		getBigger(inProg2,6);
		increaseLableSize(inProg2,140,80);
		getBigger(inProg3,6);
		increaseLableSize(inProg3,140,80);
		getBigger(inProg4,6);
		increaseLableSize(inProg4,140,80);
		
		getBigger(done0,6);
		increaseLableSize(done0,140,80);
		getBigger(done1,6);
		increaseLableSize(done1,140,80);
		getBigger(done2,6);
		increaseLableSize(done2,140,80);
		getBigger(done3,6);
		increaseLableSize(done3,140,80);
		getBigger(done4,6);
		increaseLableSize(done4,140,80);
		
		
		
	}

	// timer is initialized with the production line app
	// pings and accepts orders regardless of whether the kitchen page is up
	// currently continues to run even if app if closed...
	public void setupTimer() {

//		System.out.println("setup timer");
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

					boolean decreased=jdbc.decreaseStock(newOrder);
					initializeAdmin();
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

		ordTitle.setText("Total number of Orders: "+allOrders.size());


		topBar.add(ordTitle);


		return topBar;
	}

	public Box createNewSubBox(int n) {



		Burger burger = display[n];
		ArrayList<FoodItem> ings = burger.getIngredientList();

		Box SubOrder = Box.createHorizontalBox();
		Box WordArea = Box.createVerticalBox(); 
		SubOrder.add(WordArea);

		Box OrderLbls = Box.createHorizontalBox();
		WordArea.add(OrderLbls);

		//maybe combine the two order labels into one?
		JLabel lblOrder = new JLabel("Order# ");
		getBigger(lblOrder,6);
		OrderLbls.add(lblOrder);

		JLabel lblOrderVal = new JLabel(Integer.toString(burger.getOrderNum()) + "  ");
		getBigger(lblOrderVal,6);
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


		getBigger(FoodTxt,6);	//here because is rebuilt every method call

		WordArea.add(FoodTxt);

		Box BtnArea = createNewBtnBox(n);
		SubOrder.add(BtnArea);

		return SubOrder;
	}

	public Box createNewBtnBox(int n) {

		Box BtnArea = Box.createVerticalBox();

		//if is correct button, and haven't clicked in progress for that button
		if(n == 0) {					

			if(display[n].getState().equals(progStr) && display[n]!=null) {
				inProg0.setVisible(false); 
			}else{
				inProg0.setVisible(true); 
			}


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

		loginBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				boolean canLogin = jdbc.adminLogin(username.getText(), password.getText());

				if(canLogin) {

					loginframe.setVisible(false);
					adminframe.setVisible(true);
					adminframe.setBackground(Color.LIGHT_GRAY);
					kitchenframe.setVisible(false);
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
				adminframe.setVisible(false);

			}
		});

		goBackBtn1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
				kitchenframe.setVisible(false);
				adminframe.setVisible(false);
				loginframe.setVisible(true);
				initializeLogin();
			}          
		});


		goBackBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
				adminframe.setVisible(false);
				loginframe.setVisible(true);
				kitchenframe.setVisible(false);
				initializeLogin();
			}


		});


		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				submitAction();

				initializeAdmin();

			}

		}); 

		
		inProg0.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				inProg0.setVisible(false);

				allOrders.get(0).setState(progStr);
				jdbc.updateOrderProgress(allOrders.get(0).getOrderNum(),allOrders.get(0).getSubOrderNum(),progStr);

			}          
		});


		inProg1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				inProg1.setVisible(false);

				allOrders.get(1).setState(progStr);
				jdbc.updateOrderProgress(allOrders.get(1).getOrderNum(),allOrders.get(1).getSubOrderNum(),progStr);

			}          
		});


		inProg2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				inProg2.setVisible(false);

				allOrders.get(2).setState(progStr);
				jdbc.updateOrderProgress(allOrders.get(2).getOrderNum(),allOrders.get(2).getSubOrderNum(),progStr);

			}          
		});
		inProg3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				inProg3.setVisible(false);

				allOrders.get(3).setState(progStr);
				jdbc.updateOrderProgress(allOrders.get(3).getOrderNum(),allOrders.get(3).getSubOrderNum(),progStr);

			}          
		});

		inProg4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				inProg4.setVisible(false);

				allOrders.get(4).setState(progStr);
				jdbc.updateOrderProgress(allOrders.get(4).getOrderNum(),allOrders.get(4).getSubOrderNum(),progStr);

			}          
		});

		done0.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jdbc.updateOrderProgress(allOrders.get(0).getOrderNum(),allOrders.get(0).getSubOrderNum(),doneStr);

				allOrders.remove(0);

				inProg0.setVisible(true);

				initializeKitchen();

			}          
		});


		done1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jdbc.updateOrderProgress(allOrders.get(1).getOrderNum(),allOrders.get(1).getSubOrderNum(),doneStr);

				allOrders.remove(1);
				inProg1.setVisible(true);

				initializeKitchen();
			}          
		});

		done2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jdbc.updateOrderProgress(allOrders.get(2).getOrderNum(),allOrders.get(2).getSubOrderNum(),doneStr);
	
				allOrders.remove(2);
				inProg2.setVisible(true);

				initializeKitchen();
			}          
		});
		
		done3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jdbc.updateOrderProgress(allOrders.get(3).getOrderNum(),allOrders.get(3).getSubOrderNum(),doneStr);

				allOrders.remove(3);
				inProg3.setVisible(true);

				initializeKitchen();
			}          
		});
		
		done4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jdbc.updateOrderProgress(allOrders.get(4).getOrderNum(),allOrders.get(4).getSubOrderNum(),doneStr);
			
				allOrders.remove(4);
				inProg4.setVisible(true);

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

		adminframe.setVisible(false);

		
		getBigger(goBackBtn2,6);
		increaseLableSize(goBackBtn2,120,80);

		getBigger(submitBtn,6);
		increaseLableSize(submitBtn,120,80);
		
		getBigger(orderBun,6) ;
		getBigger(orderJnr,6) ;
		getBigger(orderWrap,6) ;
		getBigger(orderBeef,6) ;
		getBigger(orderChic,6) ;
		getBigger(orderFal,6) ;
		getBigger(orderTofu,6) ;
		getBigger(orderLet,6) ;
		getBigger(orderTom,6) ;
		getBigger(orderOni,6) ;
		getBigger(orderCap,6) ;
		getBigger(orderAvo,6) ;
		getBigger(orderCar,6) ;
		getBigger(orderPine,6) ;
		getBigger(orderPic,6) ;
		getBigger(orderSwi,6) ;
		getBigger(orderChed,6) ;
		getBigger(orderHal,6) ;
		getBigger(orderPan,6) ;
		getBigger(orderTomS,6) ;
		getBigger(orderAio,6) ;
		getBigger(orderMayo,6) ;
		getBigger(orderItal,6) ;
		getBigger(orderCur,6) ;
		
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



		//populate ingredient values from stock burger
		for(int i=0;i<ings.size();i++) {

			int rowNum=rowCount.get(ings.get(i).getName());

			// bread1 in column 1, row 2
			JLabel nameBun = new JLabel(ings.get(i).getName());

			getBigger(nameBun,6) ;

			con.gridx=nameCol;
			con.gridy=rowNum;
			adminframe.add(nameBun, con);

			// bread1 in column 2, row 2
			JLabel quanBun = new JLabel(Integer.toString(ings.get(i).getQuantity()));
			

			getBigger(quanBun,6) ;

			con.gridx=quanCol;
			con.gridy=rowNum;
			adminframe.add(quanBun,con);

			// bread1 in column 3, row 2
			JLabel minBun = new JLabel(Integer.toString(ings.get(i).getMinLevel()));

			getBigger(minBun,6) ;

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
		goBackBtn2.setText("Go Back");

		con.gridx=nameCol;

		con.gridy=sausY+6;

		adminframe.add(goBackBtn2, con);


		//setting the guess button attributes
		submitBtn.setText("Submit");

		con.gridx=reordCol;
		con.gridy=sausY+6;
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

		rowCount.put("burgerbun",breadY+1);
		rowCount.put("junior", breadY+2);
		rowCount.put("wrap", breadY+3);

		rowCount.put("beef", baseY+1);
		rowCount.put("chicken", baseY+2);
		rowCount.put("tofu", baseY+4);
		rowCount.put("falafel", baseY+3);

		rowCount.put("lettuce",prodY+1);
		rowCount.put("tomato", prodY+2);
		rowCount.put("onion", prodY+3);
		rowCount.put("capsicum", prodY+4);
		
		//new lines
		rowCount.put("avocado",prodY+5);
		rowCount.put("carrot", prodY+6);
		rowCount.put("pineapple", prodY+7);
		rowCount.put("pickles", prodY+8);

		rowCount.put("swiss", chesY+1);
		rowCount.put("cheddar", chesY+2);
		rowCount.put("halloumi", chesY+3);
		rowCount.put("paneer", chesY+4);

		rowCount.put("tomatosauce", sausY+1);
		rowCount.put("aioli", sausY+2);
		rowCount.put("mayo", sausY+3);
		rowCount.put("italian", sausY+4);
		rowCount.put("curry", sausY+5);
//		rowCount.put("mustard", sausY+6);
//		rowCount.put("cool ranch", sausY+7);


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
		orderBun.setText("0");// = new JTextField("0");
		con.fill = GridBagConstraints.HORIZONTAL;
		con.weightx = 0.25;
		con.gridx=reordCol;
		con.gridy=breadY+1;
		adminframe.add(orderBun, con);

		// bread2 in column 4, row 3
		orderJnr.setText("0");// = new JTextField("0");	
		con.gridx=reordCol;
		con.gridy=breadY+2;
		adminframe.add(orderJnr,con);

		// bread3 in column 4, row 4
		orderWrap.setText("0");// = new JTextField("0");
		con.gridx=reordCol;
		con.gridy=breadY+3;
		adminframe.add(orderWrap, con);


		// base1 in column 4, row 7
		orderBeef.setText("0");// = new JTextField("0");
		con.gridx=reordCol;
		con.gridy=baseY+1;
		adminframe.add(orderBeef, con);

		// base1 in column 4, row 8
		orderChic.setText("0");// = new JTextField("0");
		con.gridx=reordCol;
		con.gridy=baseY+2;
		adminframe.add(orderChic, con);

		// base3 in column 4, row 9
		orderFal.setText("0");// = new JTextField("0");
		con.gridx=reordCol;
		con.gridy=baseY+3;
		adminframe.add(orderFal, con);

		// base4 in column 4, row 10
		orderTofu.setText("0");// = new JTextField("0");
		con.gridx=reordCol;
		con.gridy=baseY+4;
		adminframe.add(orderTofu, con);

		// prod1 in column 4, row 13
		orderLet.setText("0");// = new JTextField("0");
		con.gridx=reordCol;
		con.gridy=prodY+1;
		adminframe.add(orderLet, con);

		// prod2 in column 4, row 14
		orderTom.setText("0");// = new JTextField("0");
		con.gridx=reordCol;
		con.gridy=prodY+2;
		adminframe.add(orderTom, con);

		// prod3 in column 4, row 15
		orderOni.setText("0");// = new JTextField("0");
		con.gridx=reordCol;
		con.gridy=prodY+3;
		adminframe.add(orderOni, con);

		// prod4 in column 4, row 16
		orderCap.setText("0");// = new JTextField("0");
		con.gridx=reordCol;
		con.gridy=prodY+4;
		adminframe.add(orderCap, con);
		
		
		// prod4 in column 4, row 16
				orderAvo.setText("0");// = new JTextField("0");		
				con.gridx=reordCol;
				con.gridy=prodY+5;
				adminframe.add(orderAvo, con);
				
				// prod4 in column 4, row 16
				orderCar.setText("0");// = new JTextField("0");			
				con.gridx=reordCol;
				con.gridy=prodY+6;
				adminframe.add(orderCar, con);
				
				// prod4 in column 4, row 16
				orderPine.setText("0");// = new JTextField("0");			
				con.gridx=reordCol;
				con.gridy=prodY+7;
				adminframe.add(orderPine, con);
								
				// prod4 in column 4, row 16
				orderPic.setText("0");// = new JTextField("0");				
				con.gridx=reordCol;
				con.gridy=prodY+8;
				adminframe.add(orderPic, con);
		
		
		
		

		// ches1 in column 4, row 19
		orderSwi.setText("0");// = new JTextField("0");		
		con.gridx=reordCol;
		con.gridy=chesY+1;
		adminframe.add(orderSwi, con);

		// ches2 in column 4, row 20
		orderChed.setText("0");// = new JTextField("0");		
		con.gridx=reordCol;
		con.gridy=chesY+2;
		adminframe.add(orderChed, con);

		// ches3 in column 4, row 21
		orderHal.setText("0");// = new JTextField("0");		
		con.gridx=reordCol;
		con.gridy=chesY+3;
		adminframe.add(orderHal, con);

		// ches4 in column 4, row 22
		orderPan.setText("0");// = new JTextField("0");		
		con.gridx=reordCol;
		con.gridy=chesY+4;
		adminframe.add(orderPan, con);

		// saus1 in column 4, row 25
		orderTomS.setText("0");// = new JTextField("0");	
		con.gridx=reordCol;
		con.gridy=sausY+1;
		adminframe.add(orderTomS, con);

		// saus2 in column 4, row 26
		orderAio.setText("0");// = new JTextField("0");		
		con.gridx=reordCol;
		con.gridy=sausY+2;
		adminframe.add(orderAio, con);

		// saus3 in column 4, row 27
		orderMayo.setText("0");// = new JTextField("0");		
		con.gridx=reordCol;
		con.gridy=sausY+3;
		adminframe.add(orderMayo, con);

		// saus4 in column 4, row 28
		orderItal.setText("0");// = new JTextField("0");		
		con.gridx=reordCol;
		con.gridy=sausY+4;
		adminframe.add(orderItal, con);

		// saus5 in column 4, row 29
		orderCur.setText("0");// = new JTextField("0");		
		con.gridx=reordCol;
		con.gridy=sausY+5;
		adminframe.add(orderCur, con);

//		// saus6 in column 4, row 30
//		orderMus = new JTextField("0");
//		//orderPan.setFont(Font.font("Arial",  15));
//		con.gridx=reordCol;
//		con.gridy=sausY+6;
//		adminframe.add(orderMus, con);
//
//		// saus7 in column 4, row 25
//		orderCool = new JTextField("0");
//		//orderPan.setFont(Font.font("Arial",  15));
//		con.gridx=reordCol;
//		con.gridy=sausY+7;
//		adminframe.add(orderCool, con);
	}

	public static String filterString(String string) {
        
       // string = string.replaceAll("\\D+", "");
       // string = string.replaceAll("[^0-9]", "");
        
        //System.out.println(string);
        
        return string;
    }


	public void submitAction() {

		Burger toReorder=new Burger();
		FoodItem foodTemp=new FoodItem("","burgerbun",Integer.parseInt(filterString(orderBun.getText())),0,0);

		toReorder.addIngredient(foodTemp);

		foodTemp=new FoodItem("","junior",Integer.parseInt(filterString(orderJnr.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","wrap",Integer.parseInt(filterString(orderWrap.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		
		foodTemp=new FoodItem("","beef",Integer.parseInt(filterString(orderBeef.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","chicken",Integer.parseInt(filterString(orderChic.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","tofu",Integer.parseInt(filterString(orderTofu.getText())),0,0);
		toReorder.addIngredient(foodTemp);		
		foodTemp=new FoodItem("","falafel",Integer.parseInt(filterString(orderFal.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		
		foodTemp=new FoodItem("","lettuce",Integer.parseInt(filterString(orderLet.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","tomato",Integer.parseInt(filterString(orderTom.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","onion",Integer.parseInt(filterString(orderOni.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","capsicum",Integer.parseInt(filterString(orderCap.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		
		foodTemp=new FoodItem("","avocado",Integer.parseInt(filterString(orderAvo.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","carrot",Integer.parseInt(filterString(orderCar.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","pineapple",Integer.parseInt(filterString(orderPine.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","pickles",Integer.parseInt(filterString(orderPic.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		
			
		foodTemp=new FoodItem("","swiss",Integer.parseInt(filterString(orderSwi.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","cheddar",Integer.parseInt(filterString(orderChed.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","halloumi",Integer.parseInt(filterString(orderHal.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","paneer",Integer.parseInt(filterString(orderPan.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		
		foodTemp=new FoodItem("","tomatosauce",Integer.parseInt(filterString(orderTomS.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","aioli",Integer.parseInt(filterString(orderAio.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","mayo",Integer.parseInt(filterString(orderMayo.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","italian",Integer.parseInt(filterString(orderItal.getText())),0,0);
		toReorder.addIngredient(foodTemp);
		foodTemp=new FoodItem("","curry",Integer.parseInt(filterString(orderCur.getText())),0,0);
		toReorder.addIngredient(foodTemp);
//		foodTemp=new FoodItem("","mustard",Integer.parseInt(orderMus.getText()),0,0);
//		toReorder.addIngredient(foodTemp);
//		foodTemp=new FoodItem("","cool ranch",Integer.parseInt(orderCool.getText()),0,0);
//		toReorder.addIngredient(foodTemp);



		boolean hasReordered = jdbc.reorderStock(toReorder);

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

