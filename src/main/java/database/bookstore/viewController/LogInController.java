package database.bookstore.viewController;

import database.bookstore.HelloApplication;
import database.bookstore.database.UserDatabase;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    protected void onLogInClick(Event event){
        try {
            if (email.getText().trim().isEmpty() || password.getText().trim().isEmpty())
                throw new RuntimeException("Empty Fields");
            String email_string = email.getText().trim();
            String password_string = password.getText().trim();
            UserDatabase u = new UserDatabase();
            if (!u.LogIn(email_string, password_string))
                throw new RuntimeException("Wrong Email Or Password");
            ControllerRepo.setUser(u.getUser(email_string));
            ControllerRepo.createHomeStage(ControllerRepo.getUser().getUser_name());
            ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            e.printStackTrace();
        }

    }
}