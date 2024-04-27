package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ProcessingFormController {

    @FXML
    private TableColumn<?, ?> ColId;

    @FXML
    private TableColumn<?, ?> ColType;

    @FXML
    private JFXButton btnAddDetails;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private ComboBox<?> cmbDate;

    @FXML
    private ComboBox<?> cmbType;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableView<?> tblProcessing;

    @FXML
    private TextField txtAmount;

    @FXML
    private Text txtCinnamonBookTypeId;

    @FXML
    void btnAddDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {

    }

    @FXML
    void cmbDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {

    }

}
