package database.bookstore.viewController;

import database.bookstore.database.BookDatabase;
import database.bookstore.entites.Book;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminPanelController {
    @FXML
    private TextField addIsbn;
    @FXML
    private TextField publisherName;
    @FXML
    private TextField title;
    @FXML
    private TextField publicationYear;
    @FXML
    private TextField price;
    @FXML
    private TextField category;
    @FXML
    private TextField threshold;
    @FXML
    private TextField noCopies;
    @FXML
    private TextField authors;
    @FXML
    private TextField removeIsbn;
    @FXML
    private TextField OrderISBN;
    @FXML
    private TextField OrderNoCopies;
    @FXML
    private MenuButton confirmOrder;
    @FXML
    private TextField promoteUser;
    @FXML
    private TextField addPublisherName;
    @FXML
    private TextField addPublisherAddress;
    @FXML
    private TextField addPublisherPhone;
    @FXML
    private TextField addAuthorName;
    @FXML
    public TextField addCategoryName;

    @FXML
    protected void onAddClick(){
        Book book = new Book();
        book.setISBN(Integer.parseInt(String.valueOf(addIsbn)));
        book.setPublisher(String.valueOf(publisherName));
        book.setTitle(String.valueOf(title));
        book.setPublication_year(String.valueOf(publicationYear));
        book.setPrice(Double.parseDouble(String.valueOf(price)));
        book.setCategory(String.valueOf(category));
        book.setThreshold(Integer.parseInt(String.valueOf(threshold)));
        book.setCopies(Integer.parseInt(String.valueOf(noCopies)));
        ArrayList<String> authors_list = new ArrayList<String>(List.of(String.valueOf(authors).split(",")));

        BookDatabase bookDatabase = new BookDatabase();
        try {
            bookDatabase.insertBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onRemoveClick(){
        BookDatabase bookDatabase = new BookDatabase();
        try {
            bookDatabase.removeBook(Integer.valueOf(String.valueOf(removeIsbn)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAddOrderClick(){

    }
    @FXML
    protected void onConfirmOrderClick(){

    }
    @FXML
    protected void onPromoteUserClick(){

    }

    @FXML
    protected void onAddPublisherClick(){

    }
    @FXML
    protected void onAddAuthorClick(){

    }

    @FXML
    protected void onAddCategoryClick(){

    }

}
