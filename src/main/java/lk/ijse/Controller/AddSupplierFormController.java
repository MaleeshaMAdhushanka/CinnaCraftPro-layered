
package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddSupplierFormController {

    @FXML
    private JFXButton btnAddSupplier;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtBank;

    @FXML
    private TextField txtBankNo;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMobileNo;

    @FXML
    private Text txtSupplierId;

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

        Stage addCustomerStage =(Stage)  btnCancel.getScene().getWindow();
        addCustomerStage.close();
    }

}

