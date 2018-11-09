package JDBC;

import java.util.ArrayList;

public class Burger {
	
	private ArrayList<FoodItem> ingredients = new ArrayList<FoodItem>();
	private String burgerName;
	private int subOrderNum;
	
	public Burger() {
		
	}
	
	public ArrayList<FoodItem> getIngredientList(){
		return this.ingredients;
	}
	
	public void addIngredient(FoodItem fooditem) {
		this.ingredients.add(fooditem);
	}
	
	public void setName(String name) {
		this.burgerName=name;
	}
	
	public String getName() {
		return this.burgerName;
	}
	
	public void setSubOrderNum(int val) {
		this.subOrderNum=val;
	}
	
	public int getSubOrderNum() {
		return this.subOrderNum;
	}
}
