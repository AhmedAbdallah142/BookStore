package database.bookstore.viewController;

import database.bookstore.HelloApplication;
import database.bookstore.database.BookDatabase;
import database.bookstore.database.UserDatabase;
import database.bookstore.entites.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Button clickButton;

    private boolean isSignUpMode = true;
    @FXML
    protected void onBackClick(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sign Up...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    protected void onSignUpClick(Event event) throws IOException {
        try {
            validateSignUp();
            String email_string = email.getText().trim();
            String user_name_string = user_name.getText().trim();
            String phone_string = phone.getText().trim();
            String first_name_string = first_name.getText().trim();
            String last_name_string = last_name.getText().trim();
            String address_string = address.getText().trim();
            String password_string = password.getText().trim();
            User user = new User(user_name_string, password_string, first_name_string,
                    last_name_string, email_string, phone_string, address_string,false);
            UserDatabase u = new UserDatabase();
            if(isSignUpMode) {
            	u.SignUp(user);
            }
            else {
            	 User us = ControllerRepo.getUser();
            	 user.setIs_manager(us.isIs_manager());
            	u.editUser(user);
            }
            createSignUpStage(user_name_string);
            ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
            ControllerRepo.setUser(user);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
//            if (alert.getResult() == ButtonType.YES) {
//                //do stuff
//            }
        }
    }

    private void validateSignUp() {
        if (email.getText().isEmpty())
            throw new RuntimeException("User Email Is Empty");
        if (user_name.getText().isEmpty())
            throw new RuntimeException("User Name Is Empty");
        if (phone.getText().isEmpty())
            throw new RuntimeException("User Phone Is Empty");
        if (first_name.getText().isEmpty())
            throw new RuntimeException("User First Is Empty");
        if (last_name.getText().isEmpty())
            throw new RuntimeException("User Last Name Is Empty");
        if (address.getText().isEmpty())
            throw new RuntimeException("User Address Is Empty");
        if (password.getText().isEmpty())
            throw new RuntimeException("Password Is Empty");
        if (!confirm_password.getText().equalsIgnoreCase(password.getText()))
            throw new RuntimeException("Confirm Password doesn't Match Password");
    }

    private void createSignUpStage(String userName) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HomeController h = fxmlLoader.getController();
        h.setUserName(userName);
        stage.setTitle("BookStore ...!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void ChangeMood(boolean isSignUpMode){
        if (isSignUpMode)
            clickButton.setText("Sign Up..");
        else {
            clickButton.setText("Modify");
            User u = ControllerRepo.getUser();
            email.setText(u.getEmail());
            email.setEditable(false);
            user_name.setText(u.getUser_name());
            phone.setText(u.getPhone_number());
            address.setText(u.getAddress());
            first_name.setText(u.getFirst_name());
            last_name.setText(u.getLast_name());
            password.setText(u.getPassword());
            confirm_password.setText(u.getPassword());
        }
        this.isSignUpMode = isSignUpMode;
    }
}
