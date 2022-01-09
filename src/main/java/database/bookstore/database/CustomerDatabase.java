package database.bookstore.database;

import java.sql.SQLException;

import database.bookstore.entites.Book;

public class CustomerDatabase {

    private final Database dataBase;

    public CustomerDatabase() {
        dataBase = Database.getInstance();
    }

    public void insertBook(Book b) throws SQLException {
    	dataBase.getStatement().execute("INSERT INTO Book VALUES"
                + "( "
                + b.getISBN() + ","
                + "'" + b.getTitle() + "'" + ","
                + "'" + b.getPublisher() + "'" + ","
                + "'" + b.getPublication_year() + "'" + ","
                + b.getPrice() +","
                + "'" + b.getCategory() + "'" + ","
                + b.getCopies() + ","
                + b.getThreshold()
                +");"
        );
        for (String authorName : b.getAuthors()){
            dataBase.getStatement().execute("INSERT INTO Author VALUES"
                    + "("
                    + "'" + authorName + "'" + ","
                    + b.getISBN()
                    + "):"
            );
        }

    }
    
    
}
