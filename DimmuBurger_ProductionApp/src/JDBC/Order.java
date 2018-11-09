package JDBC;

import java.util.ArrayList;

public class Order {

	ArrayList<Burger> burgers = new ArrayList<Burger>();
	
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
	
	
	
}
