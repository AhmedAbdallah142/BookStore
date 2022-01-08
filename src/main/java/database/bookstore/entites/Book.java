package database.bookstore.entites;

public class Book {
	int ISBN;
	String Title;
	String Publisher;
	double Price;
	String Category;
	int Copies;
	int Threshold;
	String Publication_year;
	
	public Book(int iSBN, String title, String publisher, double price, String category, int copies, int threshold,
			String publication_year) {
		super();
		ISBN = iSBN;
		Title = title;
		Publisher = publisher;
		Price = price;
		Category = category;
		Copies = copies;
		Threshold = threshold;
		Publication_year = publication_year;
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
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public int getCopies() {
		return Copies;
	}
	public void setCopies(int copies) {
		Copies = copies;
	}
	public int getThreshold() {
		return Threshold;
	}
	public void setThreshold(int threshold) {
		Threshold = threshold;
	}
	public String getPublication_year() {
		return Publication_year;
	}
	public void setPublication_year(String publication_year) {
		Publication_year = publication_year;
	}
	
	
}
