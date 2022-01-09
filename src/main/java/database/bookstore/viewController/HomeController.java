package database.bookstore.viewController;

import database.bookstore.HelloApplication;
import database.bookstore.database.BookDatabase;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HomeController{
    @FXML
    private Text username;
    @FXML
    private TextField search;

    @FXML
    protected void onCartClick(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Cart.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Cart ...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ((Stage) ((Node)(event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    protected void onProfileClick(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Modify info ...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ((Stage) ((Node)(event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    protected void onAdminClick(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AdminPanel ...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ((Stage) ((Node)(event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    protected void onSearchClick(){
        if (search.getText().isEmpty()){
            throw new RuntimeException("search is null");
        }
        BookDatabase bookDatabase = new BookDatabase();
        try {
            bookDatabase.search(search.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @FXML
    protected void onReportsClick(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Reports.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Reports ...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ((Stage) ((Node)(event.getSource())).getScene().getWindow()).close();
    }

    public void setUserName(){
        username.setText("Faxawy");
    }
}
