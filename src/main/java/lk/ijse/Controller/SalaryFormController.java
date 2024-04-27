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

public class SalaryFormController {

    @FXML
    private JFXButton btnAttendance;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnPay;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<?> cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDaysCount;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private AnchorPane salaryPane;

    @FXML
    private TableView<?> tblSalary;

    @FXML
    private TextField txtFieldHourlyRate;

    @FXML
    private TextField txtFieldOt;

    @FXML
    private TextField txtFieldOther;

    @FXML
    private Text txtHourlyPayment;

    @FXML
    private Text txtNAme;

    @FXML
    private Text txtOtPayment;

    @FXML
    private Text txtSalaryId;

    @FXML
    private Text txtTotal;

    @FXML
    void btnAttendanceOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/attendanceForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        salaryPane.getChildren().clear();
        salaryPane.getChildren().add(registerPane);

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnPayOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtFieldOtherOnAction(ActionEvent event) {

    }

}
