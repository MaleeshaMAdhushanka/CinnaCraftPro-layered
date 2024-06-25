
package lk.ijse.CinnaCraft.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.CinnaCraft.Dto.AttendanceDto;
import lk.ijse.CinnaCraft.Dto.EmployeeDto;
import lk.ijse.CinnaCraft.Model.AttendanceModel;
import lk.ijse.CinnaCraft.Model.EmployeeModel;
import lk.ijse.CinnaCraft.Tm.AttendanceTm;
import lk.ijse.CinnaCraft.dao.custom.AttendanceDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.AttendanceDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    private ComboBox<String> cmbEmployeeId;

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
    private TableView<AttendanceTm> tblAttendance;

    @FXML
    private Text txtAttendanceId;

    @FXML
    private Text txtFirstName;

    @FXML
    private Text txtLastName;

    @FXML
    private Text txtTime;


    private final EmployeeModel employeeModel = new EmployeeModel();

    AttendanceModel attendanceModel = new AttendanceModel();


    public void  initialize(){
        setCurrentDate();
        setCurrentTime();
        generateNextAttendanceId();
        loadEmployeeId();
        loadAllAttendanceDetails(dpDate.getValue());
        setCellValueFactory();
    }


    private void setCellValueFactory() {

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colInTime.setCellValueFactory(new PropertyValueFactory<>("inTime"));
        colOutTime.setCellValueFactory(new PropertyValueFactory<>("outTime"));
        colMark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("removeButton"));

    }

    @FXML
    void btnAttendOnAction(ActionEvent event) {

        boolean attendanceValidate = attendanceValidate();
        if (!attendanceValidate) {
            return;
        }

        String attendanceId = txtAttendanceId.getText();
        String empId = cmbEmployeeId.getValue();
        LocalDate date = LocalDate.now();
        LocalTime inTime = LocalTime.now();


        AttendanceDto dto = new AttendanceDto(attendanceId,date,empId,inTime,null,false);

        try{

            if (attendanceModel.searchAttendance(empId,date)){
                new Alert(Alert.AlertType.WARNING, "Attendance Already Marked").show();
            }
            else {
                boolean isAdded = attendanceModel.markAttendance(dto);

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Attendance Marked").show();
                    clearFields();
                    loadAllAttendanceDetails(dpDate.getValue());
                }

            }

        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }

    private boolean attendanceValidate() {

        if (Objects.equals(cmbEmployeeId.getValue(), "")){
            cmbEmployeeId.requestFocus();
            cmbEmployeeId.getStyleClass().add("mfx-combo-box-error");
            return false;
        }

        cmbEmployeeId.getStyleClass().removeAll("mfx-combo-box-error");

        return true;

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
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/qrScannerForm.fxml"));
        Parent rootNode = loader.load();

        QrScannerFormController qrScannerFormController = loader.getController();
        qrScannerFormController.setAttendanceFormController(this);

        Scene scene = new Scene(rootNode);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("QR Scanner");
        stage.show();

    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/salaryForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        attendancePane.getChildren().clear();
        attendancePane.getChildren().add(registerPane);

    }


    private void loadAllAttendanceDetails(LocalDate date) {


        try{

            List<AttendanceDto> attendanceList = attendanceModel.getAllAttendanceDetails(date);

            ObservableList<AttendanceTm> obList = FXCollections.observableArrayList();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");


            for (AttendanceDto dto : attendanceList){

                String inTime = dto.getInTime().format(formatter);

                String empName =  employeeModel.searchEmployee(dto.getEmpId()).getFirstName();

                String outTime ;

                if (dto.getOutTime() == null){
                    outTime= "-";
                }
                else {
                    outTime = dto.getOutTime().format(formatter);
                }

                obList.add(new AttendanceTm(dto.getEmpId(),empName,inTime,outTime));
            }

            for (int i = 0; i < obList.size(); i++) {

                final  int index = i;


                //To Disable the Mark And Remove Button If already marked
                if (attendanceList.get(index).getOutTime()!=null){
                    obList.get(i).getMark().setSelected(true);
                    obList.get(i).getMark().setDisable(true);
                    obList.get(i).getRemoveButton().setDisable(true);
                }


                obList.get(i).getMark().setOnAction(actionEvent -> {

                    String empId= attendanceList.get(index).getEmpId();
                    LocalTime outTime = LocalTime.now();
                    LocalDate currentDate = LocalDate.now();
                    updateOutTime(empId,outTime,currentDate);


                });

                obList.get(i).getRemoveButton().setOnAction(actionEvent -> {

                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if (type.orElse(no)==yes){
                        String attendanceId = attendanceList.get(index).getAttendanceId();
                        deleteAttendance(attendanceId);
                    }
                });

            }

            tblAttendance.setItems(obList);



        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }




    }

    private void updateOutTime(String empId, LocalTime outTime, LocalDate currentDate) {

        try {

            //Update The Out Time
            attendanceModel.updateOutTime(empId,outTime,currentDate);
            loadAllAttendanceDetails(LocalDate.now());
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }




    }

    private void deleteAttendance(String attendanceId) {

        try {
            System.out.println(attendanceId);
            boolean isDeleted = attendanceModel.deleteAttendance(attendanceId);


            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Attendance Deleted").show();
                loadAllAttendanceDetails(LocalDate.now());
            }
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void loadEmployeeId() {

        ObservableList<String> employeeIdList = FXCollections.observableArrayList();

        try{

            List<EmployeeDto> employeeList = employeeModel.getAllEmployee();

            for (EmployeeDto dto : employeeList){
                employeeIdList.add(dto.getEmpID());
            }

            cmbEmployeeId.setItems(employeeIdList);


        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }





    }

    private void generateNextAttendanceId() {

        try{

            String attendanceId = attendanceModel.generateNextAttendanceId();
            txtAttendanceId.setText(attendanceId);

        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }

    private void setCurrentTime() {
        // Get the current time
        LocalTime currentTime = LocalTime.now();

        // Format the time as HH:mm:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);

        txtTime.setText(formattedTime);
    }

    private void setCurrentDate() {
        dpDate.setValue(LocalDate.now());

    }


    private void clearFields() {
        generateNextAttendanceId();
        txtFirstName.setText("-");
        txtLastName.setText("-");

    }


    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {
        String empId = cmbEmployeeId.getValue();

        try{
            EmployeeDto dto = employeeModel.searchEmployee(empId);
            txtFirstName.setText(dto.getFirstName());
            txtLastName.setText(dto.getLastName());
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void dpDateOnAction(ActionEvent event) {
        loadAllAttendanceDetails(dpDate.getValue());
    }


    public void qrScanner(String empId){

        Platform.runLater(() -> {

            LocalDate date = LocalDate.now();
            LocalTime inTime = LocalTime.now();

            try{

                EmployeeDto edto = employeeModel.searchEmployee(empId);
                if (edto==null){
                    new Alert(Alert.AlertType.WARNING, "Invalid ID").show();
                    return;
                }

                boolean isAttend= attendanceModel.searchAttendance(empId,date);

                if (!isAttend){
                    String attendanceId = txtAttendanceId.getText();
                    AttendanceDto dto = new AttendanceDto(attendanceId,date,empId,inTime,null,false);
                    boolean isAdded = attendanceModel.markAttendance(dto);
                    if (isAdded) {
                        loadAllAttendanceDetails(dpDate.getValue());
                        generateNextAttendanceId();
                        new Alert(Alert.AlertType.CONFIRMATION, "Attendance Marked").show();
                    }
                }
                else {
                    boolean isOutTimeNull = attendanceModel.searchOutTime(empId,date);
                    if (!isOutTimeNull){
                        LocalTime outTime = LocalTime.now();
                        attendanceModel.updateOutTime(empId,outTime,date);
                        loadAllAttendanceDetails(dpDate.getValue());
                    }
                    else {
                        new Alert(Alert.AlertType.WARNING, "Attendance Already Marked").show();
                    }
                }

            }catch (SQLException e){
                System.out.println(e.getMessage());
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        });

    }





}

