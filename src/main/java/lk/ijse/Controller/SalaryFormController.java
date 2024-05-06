package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
import lk.ijse.Dto.SalaryDto;
import lk.ijse.Model.*;
import lk.ijse.Tm.SalaryTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

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
    private MFXFilterComboBox<String> cmbEmployeeId;


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
    private TableView<SalaryTm> tblSalary;

    @FXML
    private MFXTextField txtFieldHourlyRate;

    @FXML
    private MFXTextField txtFieldOt;


    @FXML
    private MFXTextField txtFieldOther;

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


    private final EmployeeModel employeeModel = new EmployeeModel();

    private final AttendanceModel attendanceModel = new AttendanceModel();

    private final SalaryTransactionModel salaryTransactionModel = new SalaryTransactionModel();

    private final CinaCraftDetailModel cinaCraftDetailModel = new CinaCraftDetailModel();

    private final SalaryModel salaryModel = new SalaryModel();


    public void  initialize(){

        setCellValueFactory();
        generateNextSalaryId();
        loadEmployeeIds();
        loadHourRateAndOt();

    }
    private void setCellValueFactory() {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colDaysCount.setCellValueFactory(new PropertyValueFactory<>("daysCount"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadHourRateAndOt() {

        try {
            double hourlyRate = cinaCraftDetailModel.getHourlyRate();
            double otRate = cinaCraftDetailModel.getOTRate();

            txtFieldHourlyRate.setText(String.valueOf(hourlyRate));
            txtFieldOt.setText(String.valueOf(otRate));

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void loadEmployeeIds() {


        ObservableList<String> employeeIdList = FXCollections.observableArrayList();

        try {

            List<EmployeeDto> employeeList =  employeeModel.getAllEmployee();

         for (EmployeeDto dto : employeeList){
             employeeIdList.add(dto.getEmpID());
         }

          cmbEmployeeId.setItems(employeeIdList);

        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void generateNextSalaryId() {

        try {
           String salaryId =  salaryModel.generateNextSalaryId();
           txtSalaryId.setText(salaryId);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }




    private void loadEmployeePaymentDetails(String empID){

        ObservableList<SalaryTm> oblist = FXCollections.observableArrayList();


        try {
            List<SalaryDto> salaryDtoList= salaryModel.getPaymentDetails(empID);

            for (SalaryDto dto : salaryDtoList){

                oblist.add(new SalaryTm(
                        dto.getSalaryId(),
                        dto.getDateCount(),
                        dto.getAmount(),
                        dto.getDate()

                ));

            }
            tblSalary.setItems(oblist);

        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    @FXML
    void btnAttendanceOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/attendanceForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        salaryPane.getChildren().clear();
        salaryPane.getChildren().add(registerPane);

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/employeeForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        salaryPane.getChildren().clear();
        salaryPane.getChildren().add(registerPane);


    }

    @FXML
    void btnPayOnAction(ActionEvent event) {

        boolean isValidate = ValidateFields();

        if (!isValidate) {
            return;
        }
        String salaryId = txtSalaryId.getText();
        String empID = cmbEmployeeId.getValue();
        double total = Double.parseDouble(txtTotal.getText());
        LocalDate date = LocalDate.now();


        int workDaysCount = 0;

        try {
            workDaysCount = attendanceModel.getWorkedDaysCount(empID);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        SalaryDto dto = new SalaryDto(salaryId, empID, total, workDaysCount, date);


        try {
            boolean isAdded =  salaryTransactionModel.addSalary(dto);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Added Successfully").show();
                loadEmployeePaymentDetails(empID);
                calculateSalary();

            }
            else {
                new Alert(Alert.AlertType.ERROR, "Paymnet Added Failed").show();
            }
        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private boolean ValidateFields() {


        if (Objects.equals(cmbEmployeeId.getText(), "")) {
            cmbEmployeeId.requestFocus();
            cmbEmployeeId.getStyleClass().add("mfx-combo-box-error");
            return  false;
        }
        cmbEmployeeId.getStyleClass().removeAll("mfx-combo-box-error");

        String hourlyRate = txtFieldHourlyRate.getText();

        boolean isHourlyRateValidated = Pattern.matches("[1-9][0-9]*(\\.[0-9]+)?",hourlyRate);

        if (!isHourlyRateValidated) {
            txtFieldHourlyRate.requestFocus();
            txtFieldHourlyRate.getStyleClass().add("mfx-combo-box-error");
            return  false;

        }
        txtFieldHourlyRate.getStyleClass().removeAll("mfx-text-field-error");

        String otRate = txtFieldOt.getText();

        boolean isOtRateValidated = Pattern.matches("[1-9][0-9]*(\\.[0-9]+)?",otRate);

        if (!isOtRateValidated) {
            txtFieldOt.requestFocus();
            txtFieldOt.getStyleClass().add("mfx-text-field-error");
            return false;
        }
        txtFieldOt.getStyleClass().removeAll("mfx-text-field-error");

        String other = txtFieldOther.getText();

        boolean  isOtherValidated = Pattern.matches("[0-9]+(\\.[0-9]+)?",other);

        if (!isOtherValidated) {
            txtFieldOther.requestFocus();
            txtFieldOther.getStyleClass().add("mfx-text-field-error");
            return  false;

        }
        txtFieldOther.getStyleClass().removeAll("mfx-text-field-error");

      return true;


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        //validate Fields

        String  hourlyRate = txtFieldHourlyRate.getText();

        boolean isHourlyRateValidated = Pattern.matches("[1-9][0-9]*(\\.[0-9]+)?" , hourlyRate);

        if (!isHourlyRateValidated) {
            txtFieldHourlyRate.requestFocus();
            txtFieldHourlyRate.getStyleClass().add("mfx-text-field-error");
            return;
        }
        txtFieldHourlyRate.getStyleClass().removeAll("mfx-text-field-error");

        String otRate = txtFieldOt.getText();

        boolean isOtRateValidated = Pattern.matches("[1-9][0-9]*(\\.[0-9]+)?", otRate);

        if (!isOtRateValidated) {
            txtFieldOt.requestFocus();
            txtFieldOt.getStyleClass().add("mfx-text-field-error");
            return;
        }
        txtFieldHourlyRate.getStyleClass().removeAll("mfx-text-field-error");


        try {
            boolean isUpdated = cinaCraftDetailModel.updateHourlyRateAndOt(Double.parseDouble(hourlyRate), Double.parseDouble(otRate));

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Hourly Rate and OT Rate Updated").show();
                if (Objects.equals(cmbEmployeeId.getText(), "")) {
                    return;

                }
            }
            loadHourRateAndOt();


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {

        String empID = cmbEmployeeId.getValue();

        //to romove the re border when employee is selected

        cmbEmployeeId.getStyleClass().removeAll("mfx-combo-box-error");


        try {
            loadEmployeePaymentDetails(empID);
            EmployeeDto employeeDto = employeeModel.searchEmployee(empID);
            txtNAme.setText(employeeDto.getFirstName());
            calculateSalary();


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void calculateSalary() throws SQLException {
        String empID = cmbEmployeeId.getValue();


        //Getting Date from the test Fileds

        int workedHoursCount = attendanceModel.getWorkedHoursCount(empID);
        int workedDaysCount = attendanceModel.getWorkedDaysCount(empID);

        //calculate required hours count

        int requiredHoursCount = workedDaysCount * 8;

        //calculating OT hours Count
        int otHoursCount = workedHoursCount - requiredHoursCount;


        //calcu basic paymenet eka

        double basicPayment = workedHoursCount * Double.parseDouble(txtFieldHourlyRate.getText());

        //cal kara oT payment eka
        double otPayment = 0;
        if (otHoursCount > 0) {
            otPayment = otHoursCount * Double.parseDouble(txtFieldOt.getText());

        }
        double otherPayment = Double.parseDouble(txtFieldOther.getText());

        double total = basicPayment + otPayment + otherPayment;

        txtHourlyPayment.setText(String.valueOf(basicPayment));
        txtOtPayment.setText(String.valueOf(otPayment));
        txtTotal.setText(String.valueOf(total));

        if (total == 0) {
            btnPay.setVisible(true);
        } else {
            btnPay.setVisible(false);
        }
    }

    @FXML
    void txtFieldOtherOnAction(ActionEvent event) {

        double hourlySalary = Double.parseDouble(txtHourlyPayment.getText());

        double otPayment = Double.parseDouble(txtOtPayment.getText());

        double otherPayment = Double.parseDouble(txtFieldOther.getText());

        double total = hourlySalary + otPayment + otherPayment;

        txtTotal.setText(String.valueOf(total));

    }






    }



