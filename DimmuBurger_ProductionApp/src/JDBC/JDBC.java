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
	
	public static void main(String [ ] args){
		
		JDBC jdbc = new JDBC();
		
		FoodItem chicken = new FoodItem("base", "chicken2", 30, 10, 3.00);
		
		jdbc.testInsertQuery(chicken);
	}
	
	public void enterTestDatabase() {
		
		try {
		String databaseUser = "westsere";				//change
        String databaseUserPass = "sinsinsin";       	//change  	  
        Class.forName("org.postgresql.Driver");		//change
        Connection connection = null;            
        String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/dimmuburger_jdbc";	//change
        connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
        s = connection.createStatement();
		}catch(Exception e)
        {
            System.out.println("Enter db Error: "+e.toString());
            e.printStackTrace();
                       
        }
	}
	
	public FoodItem testSelectQuery(String nm) {
		
		FoodItem test =null;
		try
        {       	
//            String databaseUser = "ahmed";				//change
//            String databaseUserPass = "tmp123";       	//change  	  
//            Class.forName("org.postgresql.Driver");		//change
//            Connection connection = null;            
//            String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";	//change
//            connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
//            Statement s = connection.createStatement();
			
			enterTestDatabase();
            
            //check table and column names
            ResultSet rs = s.executeQuery("select * from Ingredients where name='"+nm+"'");

            //pull data from row
            //load into fooditem test object

            if(rs.next()) {
            	rs.next();
            	String category=rs.getString("Category");	//might need to change column names later
            	String name=rs.getString("Name");
            	int quantity=rs.getInt("Quantity");
            	int minLevel=rs.getInt("MinLevel");
            	double price=rs.getDouble("Price");
            	test=new FoodItem(category, name, quantity, minLevel, price);
            	
            }
            rs.close();
//            connection.close();
        }
        catch(Exception e)
        {
            System.out.println("StockLevelCheck Error: "+e.toString());
            e.printStackTrace();
                       
        }
				
		return test;
		
	}
	
	
	//test save food to ingredients
	public int testInsertQuery(FoodItem food) {
		
		int row = 0;
		
		try
        {       	
//            String databaseUser = "ahmed";				//change
//            String databaseUserPass = "tmp123";       	//change  	  
//            Class.forName("org.postgresql.Driver");		//change
//            Connection connection = null;            
//            String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";	//change
//            connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
//            Statement s = connection.createStatement();
			
			String databaseUser = "westsere";				//change
	        String databaseUserPass = "sinsinsin";       	//change  	  
	        Class.forName("org.postgresql.Driver");		//change
	        Connection connection = null;            
	        String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/westsere_jdbc";	//change
	        connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
	        Statement s = connection.createStatement();
			
			
//			enterTestDatabase();
            
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
        catch(Exception e)
        {
            System.out.println("StockLevelCheck Error: "+e.toString());
            e.printStackTrace();
                       
        }
				
		return row;
		
	}
	
	
	//test count rows in ingredients
	public int testTableSize(String nm) {
		
		int rowCount=0;
		
		try
        {       	
//            String databaseUser = "ahmed";				//change
//            String databaseUserPass = "tmp123";       	//change  	  
//            Class.forName("org.postgresql.Driver");		//change
//            Connection connection = null;            
//            String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";	//change
//            connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
//            Statement s = connection.createStatement();
			
			enterTestDatabase();
            
            //check table and column names
            ResultSet rs = s.executeQuery("select * from ingredients");

            //pull data from row
            //load into fooditem test object

            //trying to count row numbers
            while(rs.next()) {
            	rs.next();
            	rowCount=rs.getRow();
            	
            	
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
	//admin login
	 public boolean adminLogin(String un, String pass){
        boolean res = true;
        try
        {       	
//            String databaseUser = "ahmed";				//change
//            String databaseUserPass = "tmp123";       	//change  	  
//            Class.forName("org.postgresql.Driver");		//change
//            Connection connection = null;            
//            String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";	//change
//            connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
//            Statement s = connection.createStatement();
            //change
        	
        	enterTestDatabase();
        	
            ResultSet rs = s.executeQuery("select * from crookfion_users where name='"+un+"' and password='"+pass+"'");
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
	
	//check ingredient stock
	 
	 public HashMap<String,FoodItem> checkStockLevels (){
		 HashMap<String,FoodItem> stockLevel=new HashMap<String,FoodItem>();
		 //query all items in ingredients
		 try
	        {       	
//	            String databaseUser = "ahmed";				//change
//	            String databaseUserPass = "tmp123";       	//change  	  
//	            Class.forName("org.postgresql.Driver");		//change
//	            Connection connection = null;            
//	            String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";	//change
//	            connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
//	            Statement s = connection.createStatement();
	            //change
			 
			 	enterTestDatabase();
			 
	            ResultSet rs = s.executeQuery("select * from Ingredients");
	            //seperate out rows
	            //pull data from rows
	            //load into fooditem objects
	            //load into hashmap
	            while(rs.next()) {
	            	rs.next();
	            	String category=rs.getString("Category");	//might need to change column names later
	            	String name=rs.getString("Name");
	            	int quantity=rs.getInt("Quantity");
	            	int minLevel=rs.getInt("MinLevel");
	            	double price=rs.getDouble("Price");
	            	FoodItem foodTemp=new FoodItem(category, name, quantity, minLevel, price);
	            	stockLevel.put(name, foodTemp);
	            }
	            rs.close();
	            connection.close();
	        }
	        catch(Exception e)
	        {
	            System.out.println("StockLevelCheck Error: "+e.toString());
	            e.printStackTrace();
	            
	           
	        }
		 
		 return stockLevel;
	 }
	
	//reorder ingredients
 public boolean reorderStock(HashMap<String,FoodItem> toReorder) {
	 boolean added=true;
 	
 	try
     {
//         String databaseUser = "ahmed";
//         String databaseUserPass = "tmp123";           
//         Class.forName("org.postgresql.Driver");
//         Connection connection = null;            
//         String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";
//         connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
//         Statement s = connection.createStatement();
 		
 		enterTestDatabase();

         //for all items in hashmap
         //check if name is in table
         //if so, update stock quantity
         
         for(String n: toReorder.keySet()) {
        	//change table name
        	 //get ingredient row
             ResultSet rs = s.executeQuery("select name from crookfion_dictionary where name ='"+n+"'");
           //if ingredient in table, update stock level
             if(rs.next()) {
            	 //get existing quantity
            	 int quantityExist=rs.getInt("Quantity");
            	            	
    			 //get quanitity to increase by from hashmap
            	 int quantityAdd=toReorder.get(n).getQuantity();
            	 int quantitySum=quantityExist+quantityAdd;
              
            	 //put new quantity into table
              	int rsRestock = s.executeUpdate("update Ingredients set Quantity = '"+quantitySum+"' where Name = '"+n+"'");
              	//unsure how to use this int to tell if insertion was successful or not
              System.out.println("rsRestock int "+rsRestock);
          	}else {
          		//if there is already a definition for the word, then don't try to insert
              	added=false;
              }
                          
         }
                 
         connection.close();
     }
     catch(Exception e)
     {
         System.out.println("Restock Error: "+e.toString());
         e.printStackTrace();

     }
 	 	
 	return added;
	 }
 
 //---------------------------------------------------------------------------------------------------
	
	/*
	 * Kitchen methods
	 */
	//recieve new orders
 
 
	
	//update order progress
 public boolean updateOrderProgress(int orderNum, int subOrderNum, String state) {
	 boolean updated=true;
 	
 	try
     {
//         String databaseUser = "ahmed";
//         String databaseUserPass = "tmp123";           
//         Class.forName("org.postgresql.Driver");
//         Connection connection = null;            
//         String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";
//         connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
//         Statement s = connection.createStatement();
 		
 		enterTestDatabase();

         //check order is in db
         //if so, update progress
         
         
        	//change table name
        	 //get ingredient row
             ResultSet rs = s.executeQuery("select State from crookfion_dictionary "
             		+ "where OrderNum ='"+orderNum+"' and SubOrderNum='"+subOrderNum+"'");
           //if order is in table, update state
             if(rs.next()) {
              
            	 //put new state into table
              	int rsUpdateState = s.executeUpdate("update Orders set State = '"+state+"' "
              			+ "where OrderNum ='"+orderNum+"' and SubOrderNum='"+subOrderNum+"'");
              	//unsure how to use this int to tell if insertion was successful or not
              
          	}else {
          		//if there is already a definition for the word, then don't try to insert
              	updated=false;
              }
                                         
         connection.close();
     }
     catch(Exception e)
     {
         System.out.println("UpdateState Error: "+e.toString());
         e.printStackTrace();

     }
 	 	
 	return updated;
	 }
	
//----------------------------------------------------------------------------------------------------------	
	/*
	 * Customer methods
	 */
	
	//get available ingredients
 	//use admin checkStockLevels method
 	
	
	//order burger(s)
 public int placeOrder(ArrayList<HashMap> order) {
	 
	 int orderNum = 0;
	 
	 enterTestDatabase();
	 
	 try {
		ResultSet rs = s.executeQuery("select * from orders "
		  		+ "where  ='"+orderNum+"'");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 
	 
	 
	 return orderNum;
 }
 
	
	//update order progress
 
 public String checkOrderProgress(int orderNum) {
	 String state=null;
	 int rowNum=0;
	 int sum=0;
 	
 	try
     {
//         String databaseUser = "ahmed";
//         String databaseUserPass = "tmp123";           
//         Class.forName("org.postgresql.Driver");
//         Connection connection = null;            
//         String url = "jdbc:postgresql://db.ecs.vuw.ac.nz/"+databaseUser+"_jdbc";
//         connection = DriverManager.getConnection(url, databaseUser, databaseUserPass);
//         Statement s = connection.createStatement();
 		
 		enterTestDatabase();

         //check order is in db
         //if so, get all rows for that order
         //if some rows are in progress, return in progress
         //if all rows are done, return done
         //if all rows are received, return received
         
         
         //change table name
    	 //get all order rows
         ResultSet rs = s.executeQuery("select * from crookfion_dictionary "
         		+ "where OrderNum ='"+orderNum+"'");
         //loop through every row
         while(rs.next()) {
        	 rs.next();
        	 
         	String stateTemp=rs.getString("State");
         	if(stateTemp.equals("In Progress")) {

         		sum=sum+1;
         	}else if(stateTemp.equals("Done")) {

         		sum=sum+2;
         	} //no final else, don't want to add anything
        	  rowNum++;        
      	}

         //if all listed as received, will all be 0
         if(sum==0) {
        	 state="Order received";
         } else if(sum==(rowNum*2)) {	//if all listed as complete, will be 2*rowNum
        	 state="Order complete";
         }else {						//anything else, will be in progress
        	 state="Order in progress";
         }
                                         
         connection.close();
     }
     catch(Exception e)
     {
         System.out.println("CheckState Error: "+e.toString());
         e.printStackTrace();

     }
 	 	
 	return state;
	 }
	
}
