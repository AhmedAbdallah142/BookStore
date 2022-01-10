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
import javafx.scene.input.MouseButton;
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
    private Text Page;

    private boolean isSearchMode = false;

    @FXML
    public void initialize() throws SQLException {
        ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        noOfCopies.setCellValueFactory(new PropertyValueFactory<>("Copies"));
        Publisher.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Authors.setCellValueFactory(t -> t.getValue().getAuthorsProperty());
        Year.setCellValueFactory(new PropertyValueFactory<>("Publication_year"));
        loadBooks();
        tableView.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY && event.getClickCount() == 2) {
                    Book clickedRow = row.getItem();
                    modifyBook(clickedRow, (Stage) ((Node) (event.getSource())).getScene().getWindow());
                } else if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    System.out.println("Add To Cart Still Not Working");
                }
            });
            return row;
        });
    }

    @FXML
    protected void onSearchBoxTyping() {
        try {
            if (search.getText().isEmpty()) {
                isSearchMode = false;
                loadBooks();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    protected void onCartClick(Event event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Cart.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Cart ...!");
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
    protected void onProfileClick(Event event) {
        try {
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
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onAdminClick(Event event) {
        try {
            createAdminStage(true, null);
            ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void modifyBook(Book book, Stage s) {
        try {
            if (ControllerRepo.getUser().isIs_manager()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Need To modify Book with ISBN : " + book.getISBN() + " ?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    createAdminStage(false, book);
                    s.close();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void createAdminStage(boolean isAddMode, Book book) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AdminPanelController d = fxmlLoader.getController();
        d.changeMood(isAddMode, book);
        stage.setTitle("AdminPanel ...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void onSearchClick() {
        try {
            if (search.getText().isEmpty())
                throw new RuntimeException("search is null");
            BookDatabase bookDatabase = new BookDatabase();
            ObservableList<Book> data = FXCollections.observableList(bookDatabase.search(search.getText(), Integer.parseInt(Page.getText())));
            tableView.setItems(data);
            isSearchMode = true;
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

    private void loadBooks() throws SQLException {
        BookDatabase bookDatabase = new BookDatabase();
        ObservableList<Book> data = FXCollections.observableList(bookDatabase.fetchBooks(Integer.parseInt(Page.getText())));
        tableView.setItems(data);
    }

    @FXML
    protected void onLeftArrowClick() {
        try {
            int page = Integer.parseInt(Page.getText());
            if (page != 1){
                Page.setText(String.valueOf(page - 1));
                if (isSearchMode)
                    onSearchClick();
                else
                    loadBooks();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onRightArrowClick() {
        try {
            int page = Integer.parseInt(Page.getText());
            Page.setText(String.valueOf(page + 1));
            if (isSearchMode)
                onSearchClick();
            else
                loadBooks();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void setUserName(String userName) {
        this.username.setText(userName);
    }
}
