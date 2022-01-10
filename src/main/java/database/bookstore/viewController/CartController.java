package database.bookstore.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    }
    @FXML
    protected void onCheckOutClick(){

    }
}
