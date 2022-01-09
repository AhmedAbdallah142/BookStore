package database.bookstore.database;

import java.sql.SQLException;

import database.bookstore.entites.Book;

public class CustomerDatabase {

    private final Database dataBase;

    public CustomerDatabase() {
        dataBase = Database.getInstance();
    }

    public void insertBook(Book b) throws SQLException {
    	dataBase.getStatement().execute("");
    }
    
    
}
