package database.bookstore.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<Order> getOrders(Integer offset) throws SQLException{
		ArrayList<Order> orders = new ArrayList<Order>();
		ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT * FROM bookstore.Order LIMIT 50 OFFSET " + (offset-1)*50 + " ;");
		while(resultSet.next()) {
			Order o = new Order();
			o.setID(Integer.parseInt(resultSet.getString("order_id")));
			o.setISBN(Integer.parseInt(resultSet.getString("ISBN")));
			o.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
			orders.add(o);
		}
		return orders;
	}
}
