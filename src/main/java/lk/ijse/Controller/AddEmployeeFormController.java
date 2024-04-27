
package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddEmployeeFormController {

    @FXML
    private JFXButton btnAddEmployee;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private ComboBox<?> cmbSex;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private TextField txtAddress;

    @FXML
    private Text txtEmployeeId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMobileNo;

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Stage addCustomerStage = (Stage) btnCancel.getScene().getWindow();
        addCustomerStage.close();

    }

}

