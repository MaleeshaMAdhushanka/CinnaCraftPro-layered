
package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddCustomerFormController {

    @FXML
    private JFXButton btnAddCustomer;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TextField txtAddress;

    @FXML
    private Text txtCustomerId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMobileNo;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage addCustomerStage =(Stage)  btnCancel.getScene().getWindow();
        addCustomerStage.close();
    }

}
