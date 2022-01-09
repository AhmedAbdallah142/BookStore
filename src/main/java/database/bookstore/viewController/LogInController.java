package database.bookstore.viewController;

import database.bookstore.HelloApplication;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    protected void onSignUpClick(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sign Up...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ((Stage) ((Node)(event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    protected void onLogInClick(Event event) throws IOException {
        System.out.println(email.getText().isEmpty());
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Book Store ...!");
        stage.setScene(scene);
        HomeController h = fxmlLoader.getController();
        h.setUserName();
        stage.setResizable(false);
        stage.show();
        ((Stage) ((Node)(event.getSource())).getScene().getWindow()).close();
    }
}