package ManageInventory;

import java.text.DecimalFormat;

public class Item {

	private int itemId;
	private String category;
	private String name;
	private double cost;
	private double price;
	private double weight;
	private String purchaseDate;
	private String soldDate;
	
	
	//Constructor
	public Item()
	{}
	
	public Item(int itemId, String category, String name, double cost, double price, double weight, String purchaseDate, String soldDate) {
		this.itemId = itemId;
		this.category = category;
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.weight = weight;
		this.purchaseDate = purchaseDate;
		this.soldDate = soldDate;
	}
	
		
	//Get
	public int getItemId() {
		return itemId;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getName() {
		return name;
	}
	
	public double getCost() {
		return cost;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public String getPurchaseDate() {
		return purchaseDate;
	}
	
	public String getSoldDate() {
		return soldDate;
	}
	
	
	//Set
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public void setcategory(String category) {
		this.category = category;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	public void setSoldDate(String soldDate) {
		this.soldDate = soldDate;
	}
	
	
	private double fn_CalculateShipping(double weight) {
		int roundedWeight = (int) Math.ceil(weight);
		double cost = 0;
		if(roundedWeight >= 0 && roundedWeight <= 4) {
			cost = 2.66;
			return cost;
		}
		
		else if (roundedWeight >= 5 && roundedWeight <= 8) {
			cost = 2.79 + 0.13*(roundedWeight-5);
			return cost;
		}
		
		else if (roundedWeight >= 9 && roundedWeight <= 12) {
			cost = 3.34 + 0.16*(roundedWeight-9);
			return cost;
		}
		
		else if (roundedWeight >= 13 && roundedWeight <= 16) {
			cost = 4.10 + 0.18*(roundedWeight-13);
			return cost;
		}
		
		else if (roundedWeight >= 17 && roundedWeight <= 32){
			cost = 7.10;
			return cost;
		}
		
		else {
			cost = 12.45;
			return cost;
		}		
	}

	public double fn_CalculateNetProfit(){
		
		double db_NetProfit = 0;
		db_NetProfit = price - cost - fn_CalculateShipping(weight);		
		return db_NetProfit;
	}
		
}





