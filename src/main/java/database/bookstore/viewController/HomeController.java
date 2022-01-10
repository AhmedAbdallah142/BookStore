package database.bookstore.viewController;

import database.bookstore.HelloApplication;
import database.bookstore.database.BookDatabase;
import database.bookstore.entites.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HomeController {
    @FXML
    private Text username;
    @FXML
    private TextField search;
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, Integer> ISBN;
    @FXML
    private TableColumn<Book, String> Title;
    @FXML
    private TableColumn<Book, Integer> noOfCopies;
    @FXML
    private TableColumn<Book, Double> price;
    @FXML
    private TableColumn<Book, String> Publisher;
    @FXML
    private TableColumn<Book, String> Category;
    @FXML
    private TableColumn<Book, String> Authors;
    @FXML
    private TableColumn<Book, String> Year;

    @FXML
    public void initialize() throws SQLException {
        BookDatabase bookDatabase = new BookDatabase();
        ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        noOfCopies.setCellValueFactory(new PropertyValueFactory<>("Copies"));
        Publisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Authors.setCellValueFactory(t -> t.getValue().getAuthorsProperty());
        Year.setCellValueFactory(new PropertyValueFactory<>("Publication_year"));
        tableView.getItems().addAll(bookDatabase.fetchBooks());
    }

    @FXML
    protected void onCartClick(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Cart.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Cart ...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    protected void onProfileClick(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SignUpController s = fxmlLoader.getController();
        s.ChangeMood(false);
        stage.setTitle("Modify info ...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    protected void onAdminClick(Event event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminPanel.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("AdminPanel ...!");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onSearchClick() {
        try {
            if (search.getText().isEmpty())
                throw new RuntimeException("search is null");
            BookDatabase bookDatabase = new BookDatabase();
            ObservableList<Book> data = FXCollections.observableList(bookDatabase.search(search.getText()));
            tableView.setItems(data);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }


    @FXML
    protected void onReportsClick(Event event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Reports.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Reports ...!");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void setUserName(String userName) {
        this.username.setText(userName);
    }
}
