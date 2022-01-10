package database.bookstore.viewController;

import database.bookstore.database.CartDatabase;
import database.bookstore.entites.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

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
    private TableColumn<CartItem, Integer> tableIsbn;
    @FXML
    private TableColumn<CartItem, Integer> tableQuantity;

    @FXML
    public void initialize() {
        tableIsbn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        tableQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ObservableList<CartItem> data = FXCollections.observableArrayList(ControllerRepo.getUserCart());
        cartTable.setItems(data);
    }

    @FXML
    protected void onBackClick(Event event) {
        try {
            ControllerRepo.createHomeStage(ControllerRepo.getUser().getUser_name());
            ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML
    protected void onAddToCartClick() {
        try {
            ControllerRepo.AddToCart(new CartItem(Integer.parseInt(isbn.getText()), Integer.parseInt(Quantity.getText())));
            ObservableList<CartItem> data = FXCollections.observableArrayList(ControllerRepo.getUserCart());
            cartTable.setItems(data);
            cartTable.refresh();
            isbn.setText("");
            Quantity.setText("");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onCheckOutClick() {
        try {
            String CardId_string = CardId.getText().trim();
            if (CardId_string.length() != 14)
                throw new RuntimeException("WRONG CardId !!");
            String CCV_string = CCV.getText().trim();
            if (CCV_string.length() != 3)
                throw new RuntimeException("WRONG CVV !!");
            LocalDate localDate = expirationDate.getValue();
            if (localDate == null)
                throw new RuntimeException("ENTER EXPIRATION DATE !!");
            LocalDate current = LocalDate.now();
            boolean isBefore = localDate.isBefore(current);
            if (isBefore)
                throw new RuntimeException("WRONG EXPIRATION DATE !!");
            CartDatabase c = new CartDatabase();
            c.buy(ControllerRepo.getUserCart(), ControllerRepo.getUser().getEmail());
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successful Transaction", ButtonType.OK);
            alert.showAndWait();
            CardId.setText("");
            CCV.setText("");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
