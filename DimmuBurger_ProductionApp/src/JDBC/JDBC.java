package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JDBC {

	/*
	 * Test methods
	 */

	//test pull food from ingredients

	private Statement s;
	private Connection connection;

	public static void main(String [] args){

		JDBC jdbc = new JDBC();

		FoodItem testFoodItem = new FoodItem("base", "tofu", 30, 10, 3.00);

		//jdbc.testInsertQuery(testFoodItem);

		//testFoodItem = jdbc.testSelectQuery("chicken");

		//System.out.println("Test food item: " + testFoodItem.getName());

		//		int rowCount;
		//
		//		rowCount = jdbc.testTableSize();
		//
		//		System.out.println("Row count of ingredients table: " + rowCount);

		//		boolean login;
		//		
		//		login = jdbc.adminLogin("testuser", "testpass");
		//		
		//		System.out.println("Login is true?: " + login);

		//		Burger stockLevels;
		//		
		//		stockLevels = jdbc.checkStockLevels();
		//		
		//		ArrayList<FoodItem> ings = stockLevels.getIngredientList();
		//		for(int i=0;i<ings.size();i++) {
		//			System.out.println("Stock levels of "+ings.get(i).getName()+" is "+ings.get(i).getQuantity());
		//		}
		//		boolean restock = jdbc.reorderStock(stockLevels);
		//		
		//		System.out.println("restock: " + restock);


		//		System.out.println("Stock levels of");
		//		FoodItem temp1=new FoodItem("","chicken",1,0,0);
		//		FoodItem temp2 = new FoodItem("","beef",1,0,0);

//		Burger newBurger = jdbc.receiveNewOrder();
//		System.out.println("Received burger order of name: " + newBurger.getName());
		
//		boolean updateOrder = jdbc.updateOrderProgress( 6, 1, "received" );
	}

	//		HashMap<String,FoodItem> tempmap=new HashMap<String,FoodItem>();
	//		tempmap.put("chicken", temp1);
	//		
	//	
	
	// this is tested as working 8/11
	public void enterTestDatabase() {

		try {

			String databaseUser = "westsere";				//change
			String databaseUserPass = "sinsinsin";       	//change  	  
			Class.forName("org.postgresql.Driver");			//change
			connection = null;            
			String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/westsere_jdbc";	//change

			connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
			s = connection.createStatement();
		}
		catch(Exception e){

			System.out.println("Enter db Error: "+e.toString());

			e.printStackTrace();              
		}
	}

	// this is tested as working 8/11
	public FoodItem testSelectQuery(String nm) {

		FoodItem test =null;

		try
		{       	
			enterTestDatabase();

			//check table and column names
			ResultSet rs = s.executeQuery("select * from Ingredients where ingredient='" + nm + "'");

			//pull data from row
			//load into fooditem test object

			if(rs.next()) {

				String category=rs.getString("category");	//might need to change column names later
				String name=rs.getString("ingredient");
				int quantity=rs.getInt("quantityinstock");
				int minLevel=rs.getInt("restocklevel");
				double price=rs.getDouble("price");

				test=new FoodItem(category, name, quantity, minLevel, price);

			}
			rs.close();
			//            connection.close();
		}
		catch(Exception e){

			System.out.println("StockLevelCheck Error: " + e.toString()); 

			e.printStackTrace();               
		}			
		return test;	
	}

	// this is tested as working 8/11
	//test save food to ingredients
	public int testInsertQuery(FoodItem food) {

		int row = 0;

		try
		{       	
			enterTestDatabase();

			String foodname=food.getName();

			//check table and column names
			ResultSet rs = s.executeQuery("select * from ingredients where ingredient='"+foodname+"'");

			//pull data from row
			//load into fooditem test object

			if(!rs.next()) {

				String category=food.getCategory();	//might need to change column names later
				String name=food.getName();
				int quantity=food.getQuantity();
				int minlevel=food.getMinLevel();
				double price=food.getPrice();

				row=s.executeUpdate("insert into ingredients (category, ingredient, quantityinstock, restocklevel, price) values('"+category+"','"+name+"',"+quantity+","+minlevel+","+price+")");
				//insert into ingredients values ('base', 'chicken', 30, 10, 3.00);

			}
			rs.close();
			connection.close();
		}
		catch(Exception e){

			System.out.println("StockLevelCheck Error: " + e.toString());

			e.printStackTrace();
		}
		return row;
	}

	// this is tested as working 8/11
	//test count rows in ingredients
	public int testTableSize() {

		int rowCount=0;

		try
		{       	
			enterTestDatabase();

			//check table and column names
			ResultSet rs = s.executeQuery("select * from ingredients");

			//pull data from row
			//load into fooditem test object

			//trying to count row numbers
			while(rs.next()) {

				rs.next();

				rowCount = rs.getRow();
			}
			rs.close();
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println("StockLevelCheck Error: "+e.toString());
			e.printStackTrace();
		}
		return rowCount;
	}


	//------------------------------------------------------------------------------------------------
	/*
	 * Admin methods
	 */

	// this is tested as working 8/11
	//admin login
	public boolean adminLogin(String un, String pass){
		boolean res = true;
		try
		{       	
			enterTestDatabase();

			ResultSet rs = s.executeQuery("select * from adminlogin where username ='" + un 
					+ "' and password='" + pass + "'");
			if(! rs.next())
				res = false;            
			rs.close();
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println("LogIn Error: "+e.toString());
			e.printStackTrace();

			res = false;
		}
		return res;
	}  

	//tested as working 9/11
	//check ingredient stock
	//do we want this to pass out a burger object for simplicity??
	public Burger checkStockLevels (){

		Burger stockLevel = new Burger();
		//HashMap<String,FoodItem> stockLevel=new HashMap<String,FoodItem>();
		//query all items in ingredients
		try
		{       	
			enterTestDatabase();

			ResultSet rs = s.executeQuery("select * from Ingredients");

			//seperate out rows
			//pull data from rows
			//load into fooditem objects
			//load into hashmap

			while(rs.next()) {

				String category=rs.getString("category");
				String name=rs.getString("ingredient");
				int quantity=rs.getInt("quantityinstock");
				int minLevel=rs.getInt("restocklevel");
				double price=rs.getDouble("price");

				FoodItem foodTemp=new FoodItem(category, name, quantity, minLevel, price);

				stockLevel.addIngredient(foodTemp);
			}
			rs.close();
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println("StockLevelCheck Error: " + e.toString());

			e.printStackTrace();
		}
		return stockLevel;
	}

	//tested as working 9/11
	//reorder ingredients
	public boolean reorderStock(Burger toReorder) {

		boolean added=true;

		try
		{
			enterTestDatabase();

			//for all items in hashmap
			//check if name is in table
			//if so, update stock quantity
			ArrayList<FoodItem> toRestock=toReorder.getIngredientList();

			for(int i=0;i<toRestock.size();i++) {

				//pull ingredient name from arraylist
				String name=toRestock.get(i).getName();

				//get ingredient row
				ResultSet rs = s.executeQuery("select * from ingredients where ingredient ='" + name + "'");

				//if ingredient in table, update stock level
				if(rs.next()) {
					//get existing quantity
					int quantityExist=rs.getInt("quantityinstock");

					//get quanitity to increase by quantity from arraylist
					int quantityAdd=toRestock.get(i).getQuantity();
					int quantitySum=quantityExist+quantityAdd;

					//put new quantity into table
					int rsRestock = s.executeUpdate("update ingredients set quantityinstock = '" + quantitySum 
							+ "' where ingredient = '" + name + "'");
					//unsure how to use this int to tell if insertion was successful or not
					System.out.println("rsRestock int " + rsRestock);
				}else {
					//if there is already a definition for the word, then don't try to insert
					added=false;
				}
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println("Restock Error: " + e.toString());
			e.printStackTrace();
		}
		return added;
	}

	//---------------------------------------------------------------------------------------------------

	/*
	 * Kitchen methods
	 */

	// tested as working 13/11
	//receive new orders
	public Burger receiveNewOrder(){

		Burger newBurger = null;

		boolean neworderexists = false;

		try
		{
			enterTestDatabase();		

			// query limits to 1 result that picks up the oldest "new" order.
			ResultSet rs = s.executeQuery("select * from orders where orderstate = 'new' order by ordernumber asc limit 1");

			//if there is an order
			if(rs.next()) {  //loops through rows automatically

				//only instantiate if there is a new order, else want to return null object
				neworderexists = true;

				newBurger = new Burger();

				// get name, ordernumber, and subordernumber of burger
				String burgerName = rs.getString("burger");
				int orderNum = rs.getInt("ordernumber");
				int subOrderNum = rs.getInt("subordernumber");

				ResultSet ing = s.executeQuery("select * from recipes where burgername = '" + burgerName + "'");

				//if there is a recipe with burgername
				if(ing.next()) {

					// set name, ordernumber, and subordernumber in burger object	
					newBurger.setName(burgerName);
					newBurger.setOrderNum(orderNum);
					newBurger.setSubOrderNum(subOrderNum);

					//find column heading names
					ResultSetMetaData rsmd = ing.getMetaData();

					//find number of columns
					int columnCount = rsmd.getColumnCount();

					// The column count starts from 1
					//cycle through all columns to get ingredient quantities out
					for (int i = 2; i <= columnCount; i++) {

						String name = rsmd.getColumnName(i);

						int foodquan = ing.getInt(name);

						if(foodquan > 0) {

							FoodItem foodTemp = new FoodItem("", name, foodquan, 0, 0);

							newBurger.addIngredient(foodTemp);
						} 
					} 
				} 
			} 
		} 
		catch(Exception e)
		{
			System.out.println("UpdateState Error: " + e.toString());

			e.printStackTrace();
		}
		return newBurger;
	}


	// tested as working 13/11
	// update order progress
	public boolean updateOrderProgress(int orderNum, int subOrderNum, String state) {

		boolean updated = true;

		try
		{
			enterTestDatabase();

			// get ingredient row
			ResultSet rs = s.executeQuery("select orderstate from orders " + "where ordernumber = '" + orderNum + 
												"' and subordernumber = '" + subOrderNum + "'");
			// if order is in table, update state
			if(rs.next()) {

				// put new state into table
				int rsUpdateState = s.executeUpdate("update orders set orderstate = '" + state + "' " 
								+ "where ordernumber = '" + orderNum + "' and subordernumber = '" + subOrderNum + "'");
			}
			else {
				// if there is no result set for burger, state is not updated
				updated = false;
			}
			connection.close();
		}
		catch(Exception e)
		{
			System.out.println("UpdateState Error: " + e.toString());

			e.printStackTrace();
		}
		return updated;
	}

	//----------------------------------------------------------------------------------------------------------	
	/*
	 * Customer methods
	 */
	// don't need customer methods anymore as this is handled by the web end.
	// keeping these here for now, just in case.

//	//get available ingredients
//	//use admin checkStockLevels method
//
//
//	//order burger(s)
//	public int placeOrder(ArrayList<HashMap> order) {
//
//		int orderNum = 0;
//
//		enterTestDatabase();
//
//		try {
//			ResultSet rs = s.executeQuery("select * from orders "
//					+ "where  ='"+orderNum+"'");
//		} 
//		catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//		return orderNum;
//	}
//
//
//	//update order progress
//
//	public String checkOrderProgress(int orderNum) {
//
//		String state=null;
//		int rowNum=0;
//		int sum=0;
//
//		try
//		{
//			//         String databaseUser = "ahmed";
//			//         String databaseUserPass = "tmp123";           
//			//         Class.forName("org.postgresql.Driver");
//			//         Connection connection = null;            
//			//         String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";
//			//         connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
//			//         Statement s = connection.createStatement();
//
//			enterTestDatabase();
//
//			//check order is in db
//			//if so, get all rows for that order
//			//if some rows are in progress, return in progress
//			//if all rows are done, return done
//			//if all rows are received, return received
//
//
//			//change table name
//			//get all order rows
//			ResultSet rs = s.executeQuery("select * from crookfion_dictionary "
//					+ "where OrderNum ='" + orderNum + "'");
//			//loop through every row
//			while(rs.next()) {
//
//				rs.next();
//
//				String stateTemp=rs.getString("State");
//
//				if(stateTemp.equals("In Progress")) {
//
//					sum=sum+1;
//
//				}else if(stateTemp.equals("Done")) {
//
//					sum=sum+2;
//
//				} //no final else, don't want to add anything
//				rowNum++;        
//			}
//
//			//if all listed as received, will all be 0
//			if(sum==0) {
//				state="Order received";
//			} else if(sum==(rowNum*2)) {	//if all listed as complete, will be 2*rowNum
//				state="Order complete";
//			}else {						//anything else, will be in progress
//				state="Order in progress";
//			}
//			connection.close();
//		}
//		catch(Exception e)
//		{
//			System.out.println("CheckState Error: " + e.toString());
//			e.printStackTrace();
//		}
//		return state;
//	}

}
