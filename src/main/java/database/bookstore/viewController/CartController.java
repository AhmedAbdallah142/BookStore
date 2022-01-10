package database.bookstore.viewController;

import database.bookstore.entites.Cart;
import database.bookstore.entites.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class CartController {
    @FXML
    private TextField isbn;
    @FXML
    private TextField Quantity;
    @FXML
    private TextField CardId;
    @FXML
    private TextField CCV;
    @FXML
    private DatePicker expirationDate;

    @FXML
    private TableView<CartItem> cartTable;
    @FXML
    private TableColumn<CartItem,Integer> tableIsbn;
    @FXML
    private TableColumn<CartItem,Integer> tableQuantity;

    @FXML
    public void initialize(){
        tableIsbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        tableQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        ObservableList<CartItem> data = FXCollections.observableArrayList(ControllerRepo.getUserCart());
//        cartTable.setItems(data);
    }

    @FXML
    protected void onBackClick(){
        try {
            ControllerRepo.createHomeStage(ControllerRepo.getUser().getUser_name());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

    }
    @FXML
    protected void onAddToCartClick(){
        try {
            ControllerRepo.AddToCart(new CartItem(Integer.parseInt(isbn.getText()),Integer.parseInt(Quantity.getText())));
            ObservableList<CartItem> data = FXCollections.observableArrayList(ControllerRepo.getUserCart());
            System.out.println(ControllerRepo.getUserCart().get(0).getQuantity());
            cartTable.setItems(data);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

    }
    @FXML
    protected void onCheckOutClick(){

    }
}
