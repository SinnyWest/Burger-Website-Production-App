package JDBC;


public class FoodItem {

	private String category;
	private String name;
	private int quantity;
	private int minLevel;
	private double price;
	
	public FoodItem(String cat, String nm, int q, int min, double p) {
		this.category=cat;
		this.name=nm;
		this.quantity=q;
		this.minLevel=min;
		this.price=p;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
