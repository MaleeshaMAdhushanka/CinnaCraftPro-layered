
package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AttendanceFormController {

    @FXML
    private AnchorPane attendancePane;

    @FXML
    private JFXButton btnAttend;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnSalary;

    @FXML
    private ComboBox<?> cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colInTime;

    @FXML
    private TableColumn<?, ?> colMark;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colOutTime;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TableView<?> tblAttendance;

    @FXML
    private Text txtAttendanceId;

    @FXML
    private Text txtFirstName;

    @FXML
    private Text txtLastName;

    @FXML
    private Text txtTime;

    @FXML
    void btnAttendOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/employeeForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        attendancePane.getChildren().clear();
        attendancePane.getChildren().add(registerPane);


    }

    @FXML
    void btnQrOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader=  new FXMLLoader(this.getClass().getResource("/view/qrScannerForm.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle("QR Scanner");
        stage1.centerOnScreen();
        stage1.show();

    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/salaryForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        attendancePane.getChildren().clear();
        attendancePane.getChildren().add(registerPane);

    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {

    }

    @FXML
    void dpDateOnAction(ActionEvent event) {

    }

}

