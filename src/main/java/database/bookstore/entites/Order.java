package database.bookstore.entites;

public class Order {
	int ID;
	int ISBN;
	int quantity;
	public Order(int iSBN, int quantity) {
		super();
		ISBN = iSBN;
		this.quantity = quantity;
	}
	
	public Order(int iD, int iSBN, int quantity) {
		super();
		ID = iD;
		ISBN = iSBN;
		this.quantity = quantity;
	}

	public Order() {

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

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
}
