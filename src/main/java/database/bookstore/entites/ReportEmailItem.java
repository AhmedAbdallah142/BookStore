package database.bookstore.entites;

public class ReportEmailItem {

	String Email;
	double Total_purchases;
	public ReportEmailItem(String email, double Total_purchases) {
		super();
		Email = email;
		this.Total_purchases = Total_purchases;
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
	public double getTotal_purchases() {
		return Total_purchases;
	}
	public void setTotal_purchases(double Total_purchases) {
		this.Total_purchases = Total_purchases;
	}
	
}
