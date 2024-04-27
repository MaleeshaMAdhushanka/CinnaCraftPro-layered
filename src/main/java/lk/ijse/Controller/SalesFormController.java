package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SalesFormController {

    @FXML
    private JFXButton btnAddCart;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnRecipt;

    @FXML
    private ComboBox<?> cmbCinnamonType;

    @FXML
    private ComboBox<?> cmbCustomerNum;

    @FXML
    private ComboBox<?> cmbPackSize;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private Text txtCount;

    @FXML
    private Text txtDate;

    @FXML
    private TextField txtFieldQty;

    @FXML
    private Text txtName;

    @FXML
    private Text txtNetTotal;

    @FXML
    private Text txtOrderId;

    @FXML
    private Text txtPrice;

    @FXML
    void btnAddCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnReciptOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCinnamonTypeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void cmbPackSizeOnAction(ActionEvent event) {

    }

    @FXML
    void txtFieldQtyOnAction(ActionEvent event) {

    }

}
