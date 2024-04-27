package lk.ijse.Controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeFormController {


    @FXML
    private JFXButton btnAddEmployee;

    @FXML
    private JFXButton btnAttendance;

    @FXML
    private JFXButton btnSalary;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDateOfBirth;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colMobileNo;

    @FXML
    private TableColumn<?, ?> colSex;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> collastName;

    @FXML
    private AnchorPane employeePane;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) throws IOException {

      FXMLLoader loader=  new FXMLLoader(this.getClass().getResource("/view/addEmployeeForm.fxml"));
      Parent rootNode = loader.load();

      Scene scene = new Scene(rootNode);
      Stage stage1 = new Stage();
      stage1.setScene(scene);
      stage1.setTitle("Add Employee");
      stage1.centerOnScreen();
      stage1.show();


    }

    @FXML
    void btnAttendanceOnAction(ActionEvent event) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/attendanceForm.fxml"));
     Pane registerPane  = (Pane) fxmlLoader.load();
     employeePane.getChildren().clear();
     employeePane.getChildren().add(registerPane);
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/salaryForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(registerPane);



    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}

