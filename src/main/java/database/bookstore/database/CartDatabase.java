package database.bookstore.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import database.bookstore.entites.CartItem;

public class CartDatabase {
	
	private final Database dataBase;
	
	 public CartDatabase() {
	        dataBase = Database.getInstance();
	    }
	
	public void buy(ArrayList<CartItem> cart,String email) throws SQLException {
		try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String d = formatter.format(date);
            dataBase.getStatement().execute("START TRANSACTION;");
            
            for(CartItem item : cart) {
            	ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT copies FROM Book WHERE ISBN = '"+item.getISBN()+"';");
                resultSet.next();
                int quantity = Integer.parseInt(resultSet.getString("copies"));
                int q  = quantity - item.getQuantity();
                // update quantity
                dataBase.getStatement().execute("UPDATE Book SET copies = '"+q+"' WHERE ISBN = '"+item.getISBN()+"';");
                // add in sale
                dataBase.getStatement().execute("INSERT INTO Sale(user_email,ISBN,copies,date) VALUES ('"+email+"','"+item.getISBN()+
                		"','"+quantity+"','"+d+"');");
            }
            dataBase.getStatement().execute("COMMIT;");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            dataBase.getStatement().execute("ROLLBACK;");
        }
	}
}
