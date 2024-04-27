
package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class FertilizerSalesFormController {

    @FXML
    private JFXButton btnAddCart;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnFertilizer;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnReceipt;

    @FXML
    private JFXButton btnSales;

    @FXML
    private ComboBox<?> cmbCustomerId;

    @FXML
    private ComboBox<?> cmbFertilizer;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colFertilizerId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private AnchorPane fertilizerSalesPane;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtDescription;

    @FXML
    private TextField txtFieldQty;

    @FXML
    private Text txtName;

    @FXML
    private Text txtNetTotal;

    @FXML
    private Text txtOrderId;

    @FXML
    private Text txtQtyOnHand;

    @FXML
    private Text txtUnitPrice;

    @FXML
    void btnAddCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnFertilizerOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/fertilizerForm.fxml"));
        Pane registerPane = fxmlLoader.load();
        fertilizerSalesPane.getChildren().clear();
        fertilizerSalesPane.getChildren().add(registerPane);

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnReceiptOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomersOnAction(ActionEvent event) {

    }

    @FXML
    void cmbFertilizerOnAction(ActionEvent event) {

    }

    @FXML
    void txtFieldQtyOnAction(ActionEvent event) {

    }

}

