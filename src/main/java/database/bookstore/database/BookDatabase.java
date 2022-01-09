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

    public Boolean insertBook(Book b) throws SQLException {
    	dataBase.getStatement().execute("INSERT INTO book VALUES"
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
            dataBase.getStatement().execute("INSERT INTO author VALUES"
                    + "("
                    + "'" + authorName + "'" + ","
                    + b.getISBN()
                    + ");"
            );
        }
        return true;

    }

    public Boolean modifyBook(Book newBook , Integer ISBN , ArrayList<String> newAuthors) throws SQLException {
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


        String deleteAuthors = "DELETE FROM author WHERE ISBN = " + ISBN +
                " AND author_name NOT IN " + newAuthorsNames + " ;" ;
        dataBase.getStatement().execute(deleteAuthors);

        // get author name which is already exists
        ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT author_name FROM author WHERE ISBN = " + ISBN + ";" );
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
        dataBase.getStatement().execute("INSERT INTO author VALUES" + newAuthorsNames_updated);

        // update existing book
        dataBase.getStatement().execute("UPDATE bOOK SET "
                + "ISBN = " + newBook.getISBN() + ","
                +"title = " + "'" + newBook.getTitle() + "'" + ","
                +"publisher = " + "'" + newBook.getPublisher() + "'" + ","
                +"publication_year = " + "'"+ newBook.getPublication_year() + "'" + ","
                +"category = " + "'" + newBook.getCategory() + "'" + ","
                + "copies = " + newBook.getCopies() + ","
                +"threshold = " + newBook.getThreshold() + " "
                + "WHERE ISBN = " + ISBN + ";"
        );
        return true;

     }


     private Book createBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setISBN(Integer.parseInt(resultSet.getString("ISBN")));
        book.setTitle(resultSet.getString("title"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setPublication_year(resultSet.getString("publication_year"));
        book.setPrice(Double.parseDouble(resultSet.getString("price")));
        book.setCategory(resultSet.getString("category"));
        book.setCopies(Integer.parseInt(resultSet.getString("copies")));
        book.setThreshold(Integer.parseInt(resultSet.getString("threshold")));
        return book;
     }

     public Book searchByTitle(String title) throws SQLException {
        ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT * FROM book WHERE title = " + title + ";");
        Book book = createBook(resultSet);
        resultSet.close();
        return book;
     }

     public Book searchByISBN(Integer ISBN) throws SQLException {
         ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT * FROM book WHERE ISBN = " + ISBN + ";");
         Book book = createBook(resultSet);
         resultSet.close();
         return book;
     }


     private ArrayList<Book> getBooks(String q) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        ResultSet resultSet = dataBase.getStatement().executeQuery(q);
        while (resultSet.next()){
            books.add(createBook(resultSet));
        }
        resultSet.close();
        return books;
     }

     public ArrayList<Book> searchByAuthor (String author_name) throws SQLException {
        String q = "SELECT * FROM Book WHERE Author = " + "'" + author_name + "'" + ";";
        return getBooks(q);
     }

    public ArrayList<Book> searchByPublisher (String publisher_name) throws SQLException {
        String q = "SELECT * FROM Book WHERE publisher = " + "'" + publisher_name + "'" + ";";
        return getBooks(q);
    }

    public ArrayList<Book> searchByCategory (String category) throws SQLException {
        String q = "SELECT * FROM Book WHERE category = " + "'" + category + "'" + ";";
        return getBooks(q);
    }

    public boolean removeBook(Integer ISBN) throws SQLException {
        dataBase.getStatement().execute("DELETE FROM Book WHERE ISBN = " + ISBN);
        return true;
    }

    public boolean addCategory(String category) throws SQLException {
        dataBase.getStatement().execute("INSERT INTO category VALUES" + "(" + "'" +category +"'" +  ");" );
        return true;
    }

    public ArrayList<Book> search(String key) throws SQLException {
        String q = "SELECT * FROM Book JOIN Author WHERE Category LIKE %" + key + "% OR ISBN LIKE %" + key  + "%"
                +"OR title LIKE %" + key + "%"
                +"OR author_name LIKE %" + key + "%"
                +"OR publisher LIKE %" + key + "%" ;
        /*ResultSet resultSet = dataBase.getStatement().executeQuery(q);
         */
        return getBooks(q);
    }

    public ArrayList<Book> fetchBooks() throws SQLException {
        String q = "SELECT * FROM book";
        return getBooks(q);
    }


}
