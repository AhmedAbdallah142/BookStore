package database.bookstore.viewController;

import database.bookstore.database.BookDatabase;
import database.bookstore.database.OrderDatabase;
import database.bookstore.database.UserDatabase;
import database.bookstore.entites.Book;
import database.bookstore.entites.Order;
import database.bookstore.entites.Publisher;
import database.bookstore.entites.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TextField addCategoryName;
    @FXML
    private Button AddButton;
    @FXML
    private TableView<Order> OrderTable;
    @FXML
    private TableColumn<Order, Integer> orderTableOrderId;
    @FXML
    private TableColumn<Order, Integer> orderTableISBN;
    @FXML
    private TableColumn<Order, Integer> orderTableNCopies;

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> userTableEmail;
    @FXML
    private TableColumn<User, String> userTableName;
    @FXML
    private TableColumn<User, String> userTablePhone;
    @FXML
    private TableColumn<User, String> userTableAddress;
    @FXML
    private TableColumn<User, Boolean> userTableIsManager;

    private boolean isAddMood = true;

    @FXML
    public void initialize() {
        orderTableOrderId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        orderTableISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        orderTableNCopies.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        userTableEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userTableName.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        userTablePhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        userTableAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        userTableIsManager.setCellValueFactory(new PropertyValueFactory<>("is_manager"));
    }

    @FXML
    protected void onBackClick(Event event) throws IOException {
        ControllerRepo.createHomeStage(ControllerRepo.getUser().getUser_name());
        ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    protected void onAddClick() {
        try {
            validateAddBook();
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
            book.setAuthors(authors_list);
            BookDatabase bookDatabase = new BookDatabase();
            if (isAddMood)
                bookDatabase.insertBook(book);
            else {
                bookDatabase.modifyBook(book , Integer.valueOf(addIsbn.getText()), authors_list );
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void validateAddBook() {
        if (addIsbn.getText().isEmpty()) {
            throw new RuntimeException("ISBN is null");
        }
        if (publisherName.getText().isEmpty()) {
            throw new RuntimeException("publisher name is null");
        }
        if (title.getText().isEmpty()) {
            throw new RuntimeException("title is null");
        }
        if (publicationYear.getText().isEmpty()) {
            throw new RuntimeException("publication year is null");
        }
        if (price.getText().isEmpty()) {
            throw new RuntimeException("price is null");
        }
        if (category.getText().isEmpty()) {
            throw new RuntimeException("category is null");
        }
        if (threshold.getText().isEmpty()) {
            throw new RuntimeException("threshold is null");
        }
        if (noCopies.getText().isEmpty()) {
            throw new RuntimeException("copies is null");
        }
        if (authors.getText().isEmpty()) {
            throw new RuntimeException("authors is null");
        }
    }

    @FXML
    protected void onRemoveClick() {
        try {
            if (removeIsbn.getText().isEmpty())
                throw new RuntimeException("remove ISBN is null");
            BookDatabase bookDatabase = new BookDatabase();
            bookDatabase.removeBook(Integer.valueOf(removeIsbn.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onAddOrderClick() {
        try {
            if (OrderISBN.getText().isEmpty())
                throw new RuntimeException("order ISBN is null");
            if (OrderNoCopies.getText().isEmpty())
                throw new RuntimeException("order Copies is null");
            Order order = new Order();
            order.setISBN(Integer.parseInt(OrderISBN.getText()));
            order.setQuantity(Integer.parseInt(OrderNoCopies.getText()));
            OrderDatabase orderDatabase = new OrderDatabase();
            orderDatabase.place_order(order);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onConfirmOrderClick() {
        try {
            if (confirmOrder.getText().isEmpty())
                throw new RuntimeException("confirm order is null");
            OrderDatabase orderDatabase = new OrderDatabase();
            orderDatabase.confirm_order(Integer.parseInt(confirmOrder.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onPromoteUserClick() {
        try {
            if (promoteUser.getText().trim().isEmpty())
                throw new RuntimeException("promoteUser name is null");
            UserDatabase userDatabase = new UserDatabase();
            userDatabase.PromoteUser(promoteUser.getText().trim());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onAddPublisherClick() {
        try {
            if (addPublisherName.getText().trim().isEmpty())
                throw new RuntimeException("publisher name is null");
            if (addPublisherAddress.getText().trim().isEmpty())
                throw new RuntimeException("publisher address is null");
            if (addPublisherPhone.getText().trim().isEmpty())
                throw new RuntimeException("publisher phone is null");
            UserDatabase userDatabase = new UserDatabase();
            String addPublisherName_string = addPublisherName.getText().trim();
            String addPublisherAddress_string = addPublisherAddress.getText().trim();
            String addPublisherPhone_string = addPublisherPhone.getText().trim();
            Publisher publisher = new Publisher(addPublisherName_string, addPublisherAddress_string, addPublisherPhone_string);
            userDatabase.addPublisher(publisher);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onAddAuthorClick() {
        try {
            if (addAuthorName.getText().isEmpty())
                throw new RuntimeException("author is null");
            UserDatabase userDatabase = new UserDatabase();
            userDatabase.addAuthor(addAuthorName.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onAddCategoryClick() {
        try {
            if (addCategoryName.getText().isEmpty())
                throw new RuntimeException("category is null");
            BookDatabase bookDatabase = new BookDatabase();
            bookDatabase.addCategory(addCategoryName.getText().trim());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onClickOrderTab(Event e) {
        Tab t = (Tab) e.getSource();
        if (t.isSelected()) {
            System.out.println("Add To Order Table Here");
        }
    }

    @FXML
    protected void onClickUsersTab(Event event) {
        try {
            Tab t = (Tab) event.getSource();
            if (t.isSelected()) {
                UserDatabase u = new UserDatabase();
                userTable.getItems().addAll(u.getUsers());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }


    public void changeMood(boolean isAddMood, Book b) {
        if (isAddMood) {
            AddButton.setText("ADD");
            addIsbn.setEditable(true);
            addIsbn.setText(null);
            publisherName.setText(null);
            title.setText(null);
            publicationYear.setText(null);
            price.setText(null);
            category.setText(null);
            threshold.setText(null);
            noCopies.setText(null);
            authors.setText(null);
        } else {
            AddButton.setText("Modify");
            addIsbn.setText("" + b.getISBN());
            addIsbn.setEditable(false);
            publisherName.setText(b.getPublisher());
            title.setText(b.getTitle());
            publicationYear.setText(b.getPublication_year());
            price.setText("" + b.getPrice());
            category.setText(b.getCategory());
            threshold.setText("" + b.getThreshold());
            noCopies.setText("" + b.getCopies());
            authors.setText(b.getAuthorsProperty().getValue());
        }
        this.isAddMood = isAddMood;
    }

}
