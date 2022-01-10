package database.bookstore.viewController;

import database.bookstore.database.ReportDatabase;
import database.bookstore.entites.ReportEmailItem;
import database.bookstore.entites.ReportSaleItem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


public class ReportsController {
    @FXML
    private Text Page;
    @FXML
    private TableView<ReportSaleItem> totalSales;
    @FXML
    private TableColumn<ReportSaleItem, Integer> totalSalesISBN;
    @FXML
    private TableColumn<ReportSaleItem, String> totalSalesTitle;
    @FXML
    private TableColumn<ReportSaleItem, Integer> totalSalesQuantity;
    @FXML
    private TableView<ReportEmailItem> top5;
    @FXML
    private TableColumn<ReportEmailItem, Integer> top5Email;
    @FXML
    private TableColumn<ReportEmailItem, Double> top5Purchases;
    @FXML
    private TableView<ReportSaleItem> top10;
    @FXML
    private TableColumn<ReportSaleItem, String> top10ISBN;
    @FXML
    private TableColumn<ReportSaleItem, String> top10Title;
    @FXML
    private TableColumn<ReportSaleItem, String> top10Quantity;

    @FXML
    public void initialize() {
        try {
            totalSalesISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            totalSalesTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            totalSalesQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            top5Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
            top5Purchases.setCellValueFactory(new PropertyValueFactory<>("Total_purchases"));
            top10ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            top10Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            top10Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            ReportDatabase r = new ReportDatabase();
            totalSales.getItems().addAll(r.Total_Sales_For_Books(Integer.parseInt(Page.getText())));
            top5.getItems().addAll(r.Top_Customers_last_three());
            top10.getItems().addAll(r.Top_Sales_For_Books_last_three());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onTotalSalesPrint() {

    }
    @FXML
    protected void onTop5Print() {

    }
    @FXML
    protected void onTop10Print() {

    }

    @FXML
    protected void onLeftArrowClick() {
        try {
            int page = Integer.parseInt(Page.getText());
            if (page != 1){
                Page.setText(String.valueOf(page - 1));
                ReportDatabase r = new ReportDatabase();
                totalSales.getItems().addAll(r.Total_Sales_For_Books(Integer.parseInt(Page.getText())));
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void onRightArrowClick() {
        try {
            int page = Integer.parseInt(Page.getText());
            Page.setText(String.valueOf(page + 1));
            ReportDatabase r = new ReportDatabase();
            totalSales.getItems().addAll(r.Total_Sales_For_Books(Integer.parseInt(Page.getText())));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
