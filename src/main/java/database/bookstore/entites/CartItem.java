package database.bookstore.entites;

public class CartItem {

	int ISBN;
	int quantity;
	double Price;
	public CartItem(int iSBN, int quantity) {
		super();
		ISBN = iSBN;
		this.quantity = quantity;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}
}
