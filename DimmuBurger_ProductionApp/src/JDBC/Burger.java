package JDBC;

import java.util.ArrayList;

public class Burger {
	
	private ArrayList<FoodItem> ingredients = new ArrayList<FoodItem>();
	
	public Burger() {
		
	}
	
	public ArrayList<FoodItem> getIngredientList(){
		return this.ingredients;
	}
	
	public void addIngredient(FoodItem fooditem) {
		this.ingredients.add(fooditem);
	}
	
}
