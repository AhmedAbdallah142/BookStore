package database.bookstore.viewController;

import database.bookstore.HelloApplication;
import database.bookstore.database.UserDatabase;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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
    	if(!email.getText().trim().isEmpty() && !password.getText().trim().isEmpty()) {
    		String email_string = email.getText().trim();
    		String password_string = password.getText().trim();
    		UserDatabase u = new UserDatabase();
    		try {
				if (u.LogIn(email_string, password_string)){
					System.out.println(email_string);
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
}