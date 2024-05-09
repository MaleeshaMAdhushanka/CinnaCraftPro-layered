package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lk.ijse.Dto.EmployeeDto;
import lk.ijse.Model.EmployeeModel;
import lk.ijse.Tm.EmployeeTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class EmployeeFormController {

    @FXML
    private JFXButton btnAddEmployee;

    @FXML
    private JFXButton btnAttendance;

    @FXML
    private JFXButton btnClearEmployee;

    @FXML
    private JFXButton btnDeleteEmployee;

    @FXML
    private JFXButton btnSalary;

    @FXML
    private JFXButton btnUpdateEmployee;
    @FXML
    private MFXComboBox<String> cmbSex;



    @FXML
    private TableColumn<?, ?> colAddress;


    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colMobileNo;

    @FXML
    private TableColumn<?, ?> colSex;

    @FXML
    private TableColumn<?, ?> collastName;


    @FXML
    private AnchorPane employeePane;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

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
    private TextField txtSearch;


    EmployeeModel employeeModel = new EmployeeModel();

    public void initialize()throws SQLException {
        setcellValueFactory();
        loadEmployeeDetails();
        generateNextEmployeeId();
        setListener();
        setGender();


    }
    private void setGender() {
        cmbSex.getItems().addAll("Male", "Female");
    }


    private void setListener() {
        tblEmployee.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    EmployeeDto  dto= new EmployeeDto(newValue.getEmpID(), newValue.getFirstName(), newValue.getLastName(), newValue.getAddress(), newValue.getSex(), newValue.getMobileNo());
                    setFields(dto);

                });
    }

    private void setFields(EmployeeDto dto) {
        txtEmployeeId.setText(dto.getEmpID());
        txtFirstName.setText(dto.getFirstName());
        txtLastName.setText(dto.getLastName());
        txtAddress.setText(dto.getAddress());
        txtMobileNo.setText(dto.getMobileNo());

    }

    private void loadEmployeeDetails() throws SQLException {
       ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

      List<EmployeeDto> dtoList =  employeeModel.getAllEmployee();

      for (EmployeeDto dto:dtoList){
        obList.add(new EmployeeTm(
                dto.getEmpID(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAddress(),
                dto.getSex(),
                dto.getMobileNo()
        ));

      }
      tblEmployee.setItems(obList);

    }

    private void setcellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        collastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        colMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));

    }
    private void generateNextEmployeeId(){
        try{
            String employeeId = employeeModel.generateNextEmployeeId();
            txtEmployeeId.setText(employeeId);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {
        boolean isEmployeeValidated = validateEmployee();

        if (!isEmployeeValidated ) {
            return;
        }


        String empId = txtEmployeeId.getText();
        String fistName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String sex = cmbSex.getText();
        String mobileNo = txtMobileNo.getText();

        EmployeeDto dto = new EmployeeDto(empId, fistName, lastName, address, sex, mobileNo);

        try {
            boolean isSaved = employeeModel.saveEmployee(dto);
            if (isSaved ) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved").show();
                clearFields();
                loadEmployeeDetails();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }
    private boolean validateEmployee() {

        String firstName = txtFirstName.getText();
        boolean isFirstNameValid = Pattern.matches("[A-Za-z]{3,}",firstName);
        if (!isFirstNameValid){
            txtFirstName.requestFocus();
            txtFirstName.getStyleClass().removeAll("mfx-text-field-error");
            return false;
        }

        txtFirstName.getStyleClass().removeAll("mfx-text-field-error");


        String lastName = txtLastName.getText();
        boolean isLastNameValid = Pattern.matches("[A-Za-z]{3,}",lastName);
        if (!isLastNameValid){
            txtLastName.requestFocus();
            txtLastName.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtLastName.getStyleClass().removeAll("mfx-text-field-error");


        String address = txtAddress.getText();
        boolean isAddressValid = Pattern.matches("[A-Za-z0-9/ ]{3,}",address);
        if (!isAddressValid){
            txtAddress.requestFocus();
            txtAddress.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtAddress.getStyleClass().removeAll("mfx-text-field-error");



        if (Objects.equals(cmbSex.getText(), "")){
            cmbSex.requestFocus();
            cmbSex.getStyleClass().add("mfx-combo-box-error");
            return false;
        }

        cmbSex.getStyleClass().removeAll("mfx-combo-box-error");






        String mobileNo = txtMobileNo.getText();
        boolean isMobileNoValid = Pattern.matches("[0-9]{3,}",mobileNo);
        if (!isMobileNoValid){
            txtMobileNo.requestFocus();
            txtMobileNo.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtMobileNo.getStyleClass().removeAll("mfx-text-field-error");

        return true;
    }


    private void clearFields() {
        generateNextEmployeeId();
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        cmbSex.getSelectionModel().clearSelection();
        txtMobileNo.clear();

    }

    @FXML
    void btnAttendanceOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/attendanceForm.fxml"));
        Pane registerPane = fxmlLoader.load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(registerPane);

    }

    @FXML
    void btnClearEmployeeOnAction(ActionEvent event) {
        generateNextEmployeeId();
        clearFields();

    }

    @FXML
    void btnDeletemployeeOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();

        try {
           boolean isDeleted = employeeModel.deleteEmployee(employeeId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted!").show();
                loadEmployeeDetails();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/salaryForm.fxml"));
        Pane registerPane = fxmlLoader.load();
        employeePane.getChildren().clear();
        employeePane.getChildren().add(registerPane);

    }

    @FXML
    void btnUpdatemployeeOnAction(ActionEvent event) {
        String empId = txtEmployeeId.getText();
        String firstName =  txtFirstName.getText();
        String lastName= txtLastName.getText();
        String address =   txtAddress.getText();
        String sex = cmbSex.getText();
        String mobileNo =  txtMobileNo.getText();

        try {
           boolean isUpdated = employeeModel.updateEmployee(new EmployeeDto(empId, firstName, lastName, address, sex, mobileNo));

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated").show();
                loadEmployeeDetails();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
