package JDBC;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.Timer;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Checkbox;

public class kitchen_GUI {

	private JFrame kitchenframe;
	Box Orders;

	private ArrayList<String> names=new ArrayList<String>();
	private Burger[] display = new Burger[5]; //should maybe be array instead of arraylist??
	private ArrayList<Burger> allOrders = new ArrayList<Burger>();
	private JDBC jdbc = new JDBC();
	//private Box SubOrder;
	private Timer timer;
	private GridLayout grid;

	private String recStr = "Received";
	private String progStr = "In progress";
	private String doneStr = "Complete";

	//change these to progStr and doneStr
	JButton inProg0=new JButton(progStr);
	JButton done0=new JButton(doneStr);
	JButton inProg1=new JButton(progStr);
	JButton done1=new JButton(doneStr);
	JButton inProg2=new JButton(progStr);
	JButton done2=new JButton(doneStr);
	JButton inProg3=new JButton(progStr);
	JButton done3=new JButton(doneStr);
	JButton inProg4=new JButton(progStr);
	JButton done4=new JButton(doneStr);

	JButton goBackBtn = new JButton("Return");


	/**
	 * Create the application.
	 */
	public kitchen_GUI() {
		setupTimer();
		setupKitchenFrame();
		//		populateArrayList();
		//		populateArrayList();
		//		populateArrayList();
		//		 Burger newOrder = jdbc.receiveNewOrder();
		//         //if new order exists
		//         if(newOrder!=null) {
		//
		//       	  allOrders.add(newOrder);
		//       	  //initializeKitchen();
		////       	  boolean updated = jdbc.updateOrderProgress(newOrder.getOrderNum(), newOrder.getSubOrderNum(), recStr);
		//       	  
		//         }
		//         Burger newOrder1 = jdbc.receiveNewOrder();
		//         //if new order exists
		//         if(newOrder1!=null) {
		//
		//       	  allOrders.add(newOrder1);
		//       	boolean updated1 = jdbc.updateOrderProgress(newOrder1.getOrderNum(), newOrder1.getSubOrderNum(), recStr);
		//       	  //initializeKitchen();
		//       	  
		//       	  
		//         }
		//         Burger newOrder2 = jdbc.receiveNewOrder();
		//         //if new order exists
		//         if(newOrder2!=null) {
		//
		//       	  allOrders.add(newOrder2);
		//       	boolean updated2 = jdbc.updateOrderProgress(newOrder2.getOrderNum(), newOrder2.getSubOrderNum(), recStr);
		//       	  //initializeKitchen();
		//       	  
		//         }
		initializeKitchen();

		//setuptimer - to get new orders
		//copy orders to visible orders
		//initialize view
		//button action listeners for in prog and done



	}


	/**
	 * timer not working
	 */
	//	//want timer to work even if kitchen is not visible
	//	public void setupTimer() {
	//		
	//		System.out.println("setup timer");
	//		//currently set for 1 second refreshes, maybe change depending on interaction
	//		//but only refreshes display if sommat new found
	//		timer = new Timer(1000, new ActionListener() {
	//			
	//            public void actionPerformed(ActionEvent evt) {
	//            	//every second
	//            	//check db for new order
	//            	//save order to local arraylist of burgers
	//            	
	//            	//not hitting this statement
	//            	System.out.println("ping timer");
	//            	
	//              Burger newOrder = jdbc.receiveNewOrder();
	//              //if new order exists
	//              if(newOrder!=null) {
	//            	  //add new burger to all orders list,
	//            	  //redraw screen
	//            	  
	//            	  //ArrayList<FoodItem> ings = newOrder.getIngredientList(); 
	//
	//            	 // int ordNum=newOrder.getOrderNumber();
	//            	  //int subOrdNum = newOrder.getSubOrderNum();
	//            	  allOrders.add(newOrder);
	//            	  boolean updated = jdbc.updateOrderProgress(newOrder.getOrderNum(), newOrder.getSubOrderNum(), recStr);
	//            	  initializeKitchen();
	//            	  
	//              }
	//            }
	//
	//        });
	//
	//	}

	//want timer to work even if kitchen is not visible
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
				if(newOrder!=null) {

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


	public void setupKitchenFrame() {
		kitchenframe = new JFrame();
		kitchenframe.setBounds(100, 100, 900, 900);
		//may have to change sizing to account for number of subboxes
		kitchenframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kitchenframe.getContentPane().setLayout(new CardLayout(0, 0));
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeKitchen() {
		System.out.println("setup kitchen");
		//unsure if need this grid layout???????
		//grid = new GridLayout(5, 2,10,10);

		//also not working??

		//		kitchenframe = new JFrame();
		//		kitchenframe.setBounds(100, 100, 900, 900);
		//		//may have to change sizing to account for number of subboxes
		//		kitchenframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		kitchenframe.getContentPane().setLayout(new CardLayout(0, 0));

		//this is not working to empty out frame
		//need to empty out frame??
		//		if(Orders!=(null)) {
		//			Orders.removeAll();
		//			System.out.println("removing all ");
		//		}

		//need to empty out display
		for(int i=0;i<display.length;i++) {
			display[i]=null;
		}

		//copy start of allOrders into display
		for(int i=0;i<allOrders.size();i++) {
			if(i<display.length) {
				display[i]=allOrders.get(i);
				System.out.println("order input: "+allOrders.get(i).getName());
			}
		}

		//vertical box to hold all elements
		Orders = Box.createVerticalBox();
		System.out.println("created vbox");



		//need to add some buttons and list size at top of page		
		Box topBox = addTopOfDisplay();
		Orders.add(topBox);

		//take display and draw to window
		for(int i=0;i<display.length;i++) {

			if(display[i]!=null) {
				//				System.out.println("display "+display[i].getName());
				Box subBox = createNewSubBox(i);

				Orders.add(subBox);
			}
			//Orders.add(btnBox);
		}

		setOnClickListeners();

		//		if(kitchenframe!=null) {
		kitchenframe.getContentPane().removeAll();
		System.out.println("removing all ");
		//		}
		kitchenframe.getContentPane().add(Orders);
		kitchenframe.getContentPane().revalidate();
		kitchenframe.getContentPane().repaint();
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
		System.out.println("created subbox "+n);
		Burger burger=display[n];
		ArrayList<FoodItem> ings=burger.getIngredientList();

		Box SubOrder = Box.createHorizontalBox();

		Box WordArea = Box.createVerticalBox();
		SubOrder.add(WordArea);

		Box OrderLbls = Box.createHorizontalBox();
		WordArea.add(OrderLbls);

		//maybe combine the two order labels into one?
		JLabel lblOrder = new JLabel("Order# ");
		OrderLbls.add(lblOrder);

		JLabel lblOrderVal = new JLabel(Integer.toString(burger.getOrderNum())+"  ");
		OrderLbls.add(lblOrderVal);

		JLabel lblSuborder = new JLabel("SubOrder# ");
		OrderLbls.add(lblSuborder);

		JLabel lblSuborderVal = new JLabel(Integer.toString(burger.getSubOrderNum()));
		OrderLbls.add(lblSuborderVal);

		JTextArea FoodTxt = new JTextArea();
		FoodTxt.setLineWrap(true);

		for(int i=0;i<ings.size();i++) {
			if(ings.get(i).getQuantity()>0) {
				FoodTxt.setText(FoodTxt.getText()+ings.get(i).getName()+"  "+ings.get(i).getQuantity()+" ; ");
			}	
		}

		FoodTxt.setText(FoodTxt.getText()+n);
		WordArea.add(FoodTxt);

		//		Box BtnArea = Box.createVerticalBox();
		Box BtnArea = createNewBtnBox(n);
		SubOrder.add(BtnArea);



		//		JCheckBox checkbox = new JCheckBox("In progress");
		//		BtnArea.add(checkbox);
		//		
		//		JCheckBox chckbxNewCheckBox = new JCheckBox("Done");
		//		BtnArea.add(chckbxNewCheckBox);

		return SubOrder;
	}

	public Box createNewBtnBox(int n) {
		Box BtnArea = Box.createVerticalBox();

		//if is correct button, and haven't clicked in progress for that button
		if(n==0) {						// && !display[n].getState().equals(progStr)
			inProg0.setVisible(true); //probably the right place for this
			//we want to add them both, but maybe only one is visible
			BtnArea.add(inProg0);
			BtnArea.add(done0);
		}else if(n==1) {
			BtnArea.add(inProg1);
			BtnArea.add(done1);
		}else if(n==2) {
			BtnArea.add(inProg2);
			BtnArea.add(done2);
		}else if(n==3) {
			BtnArea.add(inProg3);
			BtnArea.add(done3);
		}else if(n==4) {
			BtnArea.add(inProg4);
			BtnArea.add(done4);
		}


		return BtnArea;
	}

	public void setOnClickListeners() {

		//onclick listeners for prog and done buttons
		//for each, will find the burger object and post state change back to db
		//on done, will post and change list
		//change both display and allOrders
		//call changeState (String state, int arrayindex)

		goBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kitchenframe.setVisible(false);
				//loginframe.setVisible(true);
				//inilializeLogin();
			}          
		});

		//may need some changes to get visibility working right for button with initilization
		inProg0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inProg0.setVisible(false);
				//	        	 display.get(0).setState("In progress"); //do we need to do this???
				allOrders.get(0).setState(progStr);
				jdbc.updateOrderProgress(display[0].getOrderNum(),display[0].getSubOrderNum(),progStr);
				//do also need to change allORders, or just remove when done??
			}          
		});

		//not working on this button aciotn
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

		//repeat these action events for each button

	}




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kitchen_GUI window = new kitchen_GUI();
					window.kitchenframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
