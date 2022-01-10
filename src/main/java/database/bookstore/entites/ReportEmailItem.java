package database.bookstore.entites;

public class ReportEmailItem {

	String Email;
	int quantity;
	public ReportEmailItem(String email, int quantity) {
		super();
		Email = email;
		this.quantity = quantity;
	}
	public ReportEmailItem() {
		super();
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
