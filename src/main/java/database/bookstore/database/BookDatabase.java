package database.bookstore.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bookstore.entites.Book;

public class BookDatabase {

	private final Database dataBase;

    public BookDatabase() {
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

    public void modifyBook(Book newBook , Integer ISBN , ArrayList<String> newAuthors) throws SQLException {
        // delete old authors that not found in newAuthors
        String newAuthorsNames = "";
        for (String name : newAuthors){
            if (newAuthorsNames.isEmpty()){
                newAuthorsNames += "(" + "'" + name + "'";
            }
            else {
                newAuthorsNames += "," + "'" + name + "'";
            }
        }
        newAuthorsNames += ")";


        String deleteAuthors = "DELETE FROM Author WHERE ISBN = " + ISBN +
                " AND author_name NOT IN " + newAuthorsNames + " ;" ;
        dataBase.getStatement().execute(deleteAuthors);

        // get author name which is already exists
        ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT author_name FROM Author WHERE ISBN = " + ISBN + ";" );
        while (resultSet.next()){
            newAuthors.remove(resultSet.getString("author_name"));
        }
        resultSet.close();

        // get new author name without the values that already exists
        String newAuthorsNames_updated = "";
        for (String name : newAuthors){
            if (newAuthorsNames_updated.isEmpty()){
                newAuthorsNames_updated += "(" + name + "," + ISBN +")" ;
            }
            else {
                newAuthorsNames_updated += "," + "(" + name + "," + ISBN + ")";
            }
        }
        newAuthorsNames_updated += ";";

        // insert into Author table the values
        dataBase.getStatement().execute("INSERT INTO Author VALUES" + newAuthorsNames_updated);

        // update existing book
        dataBase.getStatement().execute("UPDATE BOOK SET "
                + "ISBN = " + newBook.getISBN() + ","
                +"title = " + "'" + newBook.getTitle() + "'" + ","
                +"publisher = " + "'" + newBook.getPublisher() + "'" + ","
                +"publication_year = " + "'"+ newBook.getPublication_year() + "'" + ","
                +"category = " + "'" + newBook.getCategory() + "'" + ","
                + "copies = " + newBook.getCopies() + ","
                +"threshold = " + newBook.getThreshold() + " "
                + "WHERE ISBN = " + ISBN + ";"
        );

     }
    
}
