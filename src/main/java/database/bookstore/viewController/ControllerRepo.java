package database.bookstore.viewController;

import database.bookstore.HelloApplication;
import database.bookstore.database.BookDatabase;
import database.bookstore.entites.Cart;
import database.bookstore.entites.CartItem;
import database.bookstore.entites.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerRepo {
    private static User CurrentUser;
    private static ArrayList<CartItem> userCart = new ArrayList<>();
    public static User getUser() {
        return CurrentUser;
    }
    public static void setUser(User user) {
        CurrentUser = user;
    }

    public static void AddToCart(CartItem item) throws SQLException{
    	BookDatabase b = new BookDatabase();
    	if(b.checkBook(item.getISBN())) {
    		for(CartItem i : userCart) {
        		if(i.getISBN() == item.getISBN()) {
        			item.setQuantity(i.getQuantity()+item.getQuantity());
        			break;
        		}
        	}
            userCart.add(item);
    	}else {
    		throw new RuntimeException("Book not found in DataBase !!");
    	}	
    }

    public static void ResetCart(){
        userCart = new ArrayList<>();
    }
    public static ArrayList<CartItem> getUserCart(){
        return userCart;
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
