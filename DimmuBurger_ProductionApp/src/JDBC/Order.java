package JDBC;

import java.util.ArrayList;

public class Order {

	ArrayList<Burger> burgers = new ArrayList<Burger>();
	int orderNum;
	//int subOrderNum;
	
	public Order() {
		
	}
	
	public ArrayList<Burger> getBurgerList(){
		return this.burgers;
		
	}
	
	
	public void addBurger(Burger burger) {
		this.burgers.add(burger);
	}
	
	//maybe need??
	public void removeBurger(int i) {
		this.burgers.remove(i);
	}
	
	public void setOrderNum(int val) {
		this.orderNum=val;
	}
	
//	public void setSubOrderNum(int val) {
//		this.subOrderNum=val;
//	}
	
	
}
