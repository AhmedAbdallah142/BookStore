package database.bookstore.viewController;

import database.bookstore.database.BookDatabase;
import database.bookstore.database.UserDatabase;
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
    protected void onAddClick() throws Exception {
        if (addIsbn.getText().isEmpty()){
            throw new RuntimeException("ISBN is null");
        }
        if (publisherName.getText().isEmpty()){
            throw new RuntimeException("publisher name is null");
        }
        if (title.getText().isEmpty()){
            throw new RuntimeException("title is null");
        }
        if (publicationYear.getText().isEmpty()){
            throw new RuntimeException("publication year is null");
        }
        if (price.getText().isEmpty()){
            throw new RuntimeException("price is null");
        }
        if (category.getText().isEmpty()){
            throw new RuntimeException("category is null");
        }
        if (threshold.getText().isEmpty()){
            throw new RuntimeException("threshold is null");
        }
        if (noCopies.getText().isEmpty()){
            throw new RuntimeException("copies is null");
        }
        if (authors.getText().isEmpty()){
            throw new RuntimeException("authors is null");
        }
        Book book = new Book();
        book.setISBN(Integer.parseInt(addIsbn.getText()));
        book.setPublisher(publisherName.getText());
        book.setTitle(title.getText());
        book.setPublication_year(publicationYear.getText());
        book.setPrice(Double.parseDouble(price.getText()));
        book.setCategory(category.getText());
        book.setThreshold(Integer.parseInt(threshold.getText()));
        book.setCopies(Integer.parseInt(noCopies.getText()));
        ArrayList<String> authors_list = new ArrayList<String>(List.of(authors.getText().split(",")));

        BookDatabase bookDatabase = new BookDatabase();
        try {
            bookDatabase.insertBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onRemoveClick(){
        if (removeIsbn.getText().isEmpty()){
            throw new RuntimeException("remove ISBN is null");
        }
        BookDatabase bookDatabase = new BookDatabase();
        try {
            bookDatabase.removeBook(Integer.valueOf(removeIsbn.getText()));
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
        if (addAuthorName.getText().isEmpty()){
            throw new RuntimeException("author is null");
        }
        UserDatabase userDatabase = new UserDatabase();
        try {
            userDatabase.addAuthor(addAuthorName.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAddCategoryClick(){
        if (addCategoryName.getText().isEmpty()){
            throw new RuntimeException("category is null");
        }
        BookDatabase bookDatabase = new BookDatabase();
        try {
            bookDatabase.addCategory(addCategoryName.getText().trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
