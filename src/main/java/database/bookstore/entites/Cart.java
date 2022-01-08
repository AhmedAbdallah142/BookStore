package database.bookstore.entites;

import java.util.ArrayList;

public class Cart {
	int id;
	ArrayList<CartItem> items;
	public Cart(int id, ArrayList<CartItem> items) {
		super();
		this.id = id;
		this.items = items;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<CartItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}
	
}
