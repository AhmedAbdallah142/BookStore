package database.bookstore.entites;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;

public class Book {
	private int ISBN;
	private String Title;
	private String Publisher;
	private String Publication_year;
	private double Price;
	private String Category;
	private int Copies;
	private int Threshold;
	private ArrayList<String> authors;



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

	public Book() {

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
	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}
	
	public StringProperty getAuthorsProperty(){
        String concatenation = "";
        for (String a : this.authors) concatenation = concatenation.concat(a.concat(" ,"));
        return new SimpleStringProperty(concatenation);
    }


}
