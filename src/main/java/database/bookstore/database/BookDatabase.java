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


    public double checkBook(Integer ISBN) throws SQLException {
        ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT price FROM Book WHERE ISBN = " + ISBN + ";");
        if (resultSet.next()){
            return Double.parseDouble(resultSet.getString("price"));
        }
        else {
            throw new RuntimeException("not exists");
        }
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
       /* // delete old authors that not found in newAuthors
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
        //dataBase.getStatement().execute("INSERT INTO author VALUES ('"+ newAuthorsNames_updated+"')");
        String insertQ = "INSERT INTO author VALUES" + newAuthorsNames_updated;
        try {
            if (!newAuthorsNames_updated.equals(" ;")) {
                dataBase.getStatement().execute(insertQ);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        dataBase.getStatement().execute("DELETE FROM author WHERE ISBN = " + ISBN + ";");
        for (String authorName : newAuthors){
            dataBase.getStatement().execute("INSERT INTO author VALUES"
                    + "("
                    + "'" + authorName + "'" + ","
                    + ISBN
                    + ");"
            );
        }


        // update existing book
        dataBase.getStatement().execute("UPDATE bOOK SET "
                + "ISBN = " + newBook.getISBN() + ","
                +"title = " + "'" + newBook.getTitle() + "'" + ","
                +"publisher = " + "'" + newBook.getPublisher() + "'" + ","
                +"publication_year = " + "'"+ newBook.getPublication_year() + "'" + ","
                +"category = " + "'" + newBook.getCategory() + "'" + ","
                + "copies = " + newBook.getCopies() + ","
                +"threshold = " + newBook.getThreshold() + ","
                + "price = " + newBook.getPrice() + " "
                + "WHERE ISBN = " + ISBN + ";"
        );
        return true;

     }


     private Book createBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        int isbn = Integer.parseInt(resultSet.getString("ISBN"));
        book.setISBN(isbn);
        book.setTitle(resultSet.getString("title"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setPublication_year(resultSet.getString("publication_year"));
        book.setPrice(Double.parseDouble(resultSet.getString("price")));
        book.setCategory(resultSet.getString("category"));
        book.setCopies(Integer.parseInt(resultSet.getString("copies")));
        book.setThreshold(Integer.parseInt(resultSet.getString("threshold")));
        ArrayList<String> authors = new ArrayList<String>();
        ResultSet r = dataBase.getStatement().executeQuery("SELECT author_name FROM Author WHERE ISBN = '"+isbn+"';");
        while(r.next()) {
        	authors.add(r.getString("author_name"));
        }
        book.setAuthors(authors);
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

    public void removeBook(Integer ISBN) throws SQLException {
        dataBase.getStatement().execute("DELETE FROM Book WHERE ISBN = " + ISBN);
    }

    public void addCategory(String category) throws SQLException {
        dataBase.getStatement().execute("INSERT INTO category VALUES" + "(" + "'" +category +"'" +  ");" );
    }

    public ArrayList<Book> search(String key , Integer offset) throws SQLException {
        String q = "SELECT DISTINCT * FROM Book As B JOIN Author As A  WHERE Category LIKE '%" + key + "%' OR A.ISBN LIKE '%" + key  + "%'"
                +"OR title LIKE '%" + key + "%'"
                +"OR author_name LIKE '%" + key + "%'"
                +"OR publisher LIKE '%" + key + "%'" + "GROUP BY B.ISBN  ORDER BY B.ISBN LIMIT 50 OFFSET " + (offset-1)*50 + " ;" ;
        /*ResultSet resultSet = dataBase.getStatement().executeQuery(q);
         */
        return getBooks(q);
    }

    public ArrayList<Book> fetchBooks(Integer offset) throws SQLException {
        String q = "SELECT * FROM book LIMIT 50 OFFSET " + (offset-1)*50 + " ;";
        return getBooks(q);
    }


}
