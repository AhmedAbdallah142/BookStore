package database.bookstore.entites;

public class Order {

	int order_id;
	int ISBN;
	int quantity;
	public Order(int order_id, int iSBN, int quantity) {
		super();
		this.order_id = order_id;
		ISBN = iSBN;
		this.quantity = quantity;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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
	
}
