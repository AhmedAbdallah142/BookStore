package database.bookstore.viewController;

import database.bookstore.HelloApplication;
import database.bookstore.database.UserDatabase;
import database.bookstore.entites.User;
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

public class SignUpController {
	@FXML
	private TextField email;
	@FXML
	private TextField user_name;
	@FXML
	private TextField phone;
	@FXML
	private TextField address;
	@FXML
	private TextField first_name;
	@FXML
	private TextField last_name;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField confirm_password;
    @FXML
    protected void onBackClick(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sign Up...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ((Stage) ((Node)(event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    protected void onSignUpClick(Event event) throws IOException {
    	String email_string = email.getText().trim();
    	String user_name_string = user_name.getText().trim();
    	String phone_string = phone.getText().trim();
    	String first_name_string = first_name.getText().trim();
    	String last_name_string = last_name.getText().trim();
    	String address_string = address.getText().trim();
    	String password_string = password.getText().trim();
    	String confirm_password_string = confirm_password.getText().trim();
    	
    	if(!email_string.isEmpty() && !user_name_string.isEmpty() &&
    	   !phone_string.isEmpty() && !first_name_string.isEmpty() &&
    	   !last_name_string.isEmpty() && !password_string.isEmpty() &&
    	   !confirm_password_string.isEmpty() && !address_string.isEmpty() ) {
    		
    		if(password_string.equalsIgnoreCase(confirm_password_string)) {
    			User user = new User(user_name_string, password_string, first_name_string,
        				last_name_string,email_string, phone_string, address_string);
    			UserDatabase u = new UserDatabase();
    			try {
					u.SignUp(user);
					Stage stage = new Stage();
	                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
	                Scene scene = new Scene(fxmlLoader.load());
	                stage.setTitle("BookStore ...!");
	                stage.setScene(scene);
	                stage.setResizable(false);
	                stage.show();
	                ((Stage) ((Node)(event.getSource())).getScene().getWindow()).close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else {
    			
    		}
    	}else {
    		
    	}
    	
        }
    
}
