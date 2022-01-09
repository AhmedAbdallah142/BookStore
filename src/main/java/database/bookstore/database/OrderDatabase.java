package database.bookstore.database;

import java.sql.SQLException;

import database.bookstore.entites.Order;

public class OrderDatabase {
	
	private final Database dataBase;
	
	public OrderDatabase() {
        dataBase = Database.getInstance();
    }
	
	public void place_order(Order o) throws SQLException {
		dataBase.getStatement().execute("INSERT INTO Order (ISBN,quantity) VALUES ('"+o.getISBN()+"','"+o.getQuantity()+"');");
	}

	public void confirm_order(int id) throws SQLException {
		dataBase.getStatement().execute("DELETE FROM Order WHERE order_id = '"+id+"';");
	}
}
