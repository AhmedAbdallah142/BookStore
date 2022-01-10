package database.bookstore.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

public class ReportsController {
    @FXML
    private Text Page;
    @FXML
    protected void onLeftArrowClick() {
        try {
            int page = Integer.parseInt(Page.getText());
            Page.setText(String.valueOf(page == 1 ? 1:page-1));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onRightArrowClick() {
        try {
            int page = Integer.parseInt(Page.getText());
            Page.setText(String.valueOf(page+1));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
