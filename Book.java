package ManageInventory;

public class Book extends Item{
	
	double db_AdditionalShippingCost = 0;

	public Book()
	{}
		
	public Book(int itemId, String category, String name, double cost, double price, double weight, String purchaseDate, String soldDate) {
		super(itemId, category, name, cost, price, weight, purchaseDate, soldDate);
		// TODO Auto-generated constructor stub
	}

	//Override
	public double fn_CalculateShipping(double weight) {
		int roundedWeight = (int) Math.ceil(weight);
		
		if(roundedWeight%16 == 0) {
			return 2.66 + (roundedWeight/16-1)*0.51;
		}
		
		else {
			return fn_CalculateShipping(roundedWeight+1);
		}
	}
	
}
