package database.bookstore.viewController;

import database.bookstore.HelloApplication;
import database.bookstore.entites.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerRepo {
    private static User CurrentUser;
    public static User getUser() {
        return CurrentUser;
    }
    public static void setUser(User user) {
        CurrentUser = user;
    }
    public static void createHomeStage(String userName) throws IOException {
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
}
