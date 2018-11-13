package JDBC;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



import java.awt.color.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



//text aligns to wrong plae on startup, fixed on refresh


public class admin_gui2 {
	
	private ArrayList<String>food = new ArrayList<String>();
	private JFrame adminframe;
	
	private JDBC jdbc = new JDBC();
	
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
	
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_gui2 window = new admin_gui2();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public admin_gui2() {
		
		setupAdminFrame();
		 
		setupRowHashMap();
		setupHeadingHashMap();	
		
		
		populateAdmin();
		setActionListeners();
		
	}
	
	public void setupAdminFrame(){
		adminframe = new JFrame();
		adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminframe.getContentPane().setLayout(grid);
		adminframe.pack();
		adminframe.setSize(800, 800);
		adminframe.setVisible(true);
		

	}
	
	public void populateAdmin() {
		adminframe.getContentPane().removeAll();
		Burger stock=jdbc.checkStockLevels();
		ArrayList<FoodItem> ings=stock.getIngredientList();
		
		
		//populate ingredient values from stock burger
		for(int i=0;i<ings.size();i++) {
			
			int rowNum=rowCount.get(ings.get(i).getName());
			
			 // bread1 in column 1, row 2
		    JLabel nameBun = new JLabel(ings.get(i).getName());
		    //nameBun.setFont(Font.font("Arial",  15));
		    con.gridx=nameCol;
		    con.gridy=rowNum;
		    adminframe.add(nameBun, con);
		    
		    // bread1 in column 2, row 2
		    JLabel quanBun = new JLabel(Integer.toString(ings.get(i).getQuantity()));
		    //quanBun.setFont(Font.font("Arial",  15));
		    con.gridx=quanCol;
		    con.gridy=rowNum;
		    adminframe.add(quanBun,con);
		    
		    // bread1 in column 3, row 2
		    JLabel minBun = new JLabel(Integer.toString(ings.get(i).getMinLevel()));
		    //minBun.setFont(Font.font("Arial",  15));
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
		   // nameCat.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		    con.fill = GridBagConstraints.HORIZONTAL;
		    con.weightx = 0.25;
		    con.gridx=breadX;
		    con.gridy=catRowCount.get(cat);
		    adminframe.add(nameCat, con); 
		    
		    // Quan in column 2, row 1
		    JLabel quantityCat = new JLabel(quanTitle);
		    //quantityCat.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		    con.fill = GridBagConstraints.HORIZONTAL;
		    con.weightx = 0.25;
		    con.gridx=quanCol;
		    con.gridy=catRowCount.get(cat);
		    adminframe.add(quantityCat, con);

		    // restock in column 3, row 1
		    JLabel restockCat = new JLabel(minTitle);
		    //restockCat.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		    con.fill = GridBagConstraints.HORIZONTAL;
		    con.weightx = 0.25;
		    con.gridx=minCol;
		    con.gridy=catRowCount.get(cat);
		    adminframe.add(restockCat, con);
		    
		    // toOrder in column 4, row 1
		    JLabel toOrderCat = new JLabel(reorderTitle);
		    //toOrderCat.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		    con.fill = GridBagConstraints.HORIZONTAL;
		    con.weightx = 0.25;
		    con.gridx=reordCol;
		    con.gridy=catRowCount.get(cat);
		    adminframe.add(toOrderCat, con);		
			
		}
		
		//setting the guess button attributes
		goBackBtn.setText("Go Back");
		//goBackBtn.setFont(Font.font(15));
		con.gridx=nameCol;
		con.gridy=sausY+8;
		adminframe.add(goBackBtn, con);
		
		
		//setting the guess button attributes
		submitBtn.setText("Submit");
		//submitBtn.setFont(Font.font(15));
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
	
	public void setActionListeners() {
		
		goBackBtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	adminframe.setVisible(false);
	        	//loginframe.setVisible(true);
	        	//inilializeLogin();
	         }

			         
	      });
		
		
		
		//action for Submit button
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				submitAction();
				
				populateAdmin();
				//now just need to redraw screen
			}
			
		}); 
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
	
}
