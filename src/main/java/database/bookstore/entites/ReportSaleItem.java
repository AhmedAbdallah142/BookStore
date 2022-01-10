package database.bookstore.entites;

public class ReportSaleItem {

	int ISBN;
	String Title;
	int quantity;
	public ReportSaleItem(int iSBN, String title, int quantity) {
		super();
		ISBN = iSBN;
		Title = title;
		this.quantity = quantity;
	}
	public ReportSaleItem() {
		super();
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
