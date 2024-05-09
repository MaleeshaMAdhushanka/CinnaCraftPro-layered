package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.Dto.CustomerDto;
import lk.ijse.Model.CustomerModel;
import lk.ijse.Tm.CustomerTm;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFormController {

    @FXML
    private JFXButton btnAddCustomer;

    @FXML
    private JFXButton btnClearCustomer;

    @FXML
    private JFXButton btnDeleteCustomer;

    @FXML
    private JFXButton btnUpdateCustomer;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colMobileNo;

    @FXML
    private TableView<CustomerTm> tblCustomer;

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
    private TextField txtSearch;


    CustomerModel customerModel = new CustomerModel();


    public void initialize() throws SQLException {
        setCellValueFactory();
        loadCustomerDetails();
        generateNextCustomerId();
        setListener();
    }

    private void setListener() {
        tblCustomer.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    CustomerDto dto = new CustomerDto(newValue.getCusId(), newValue.getFirstName(), newValue.getLastName(),newValue.getAddress(),newValue.getEmail(),newValue.getMobileNo());
                    setFields(dto);
                });
    }

    private void setFields(CustomerDto dto) {

        txtCustomerId.setText(dto.getCusId());
        txtFirstName.setText(dto.getFirstName());
        txtLastName.setText(dto.getLastName());
        txtAddress.setText(dto.getAddress());
        txtMobileNo.setText(dto.getMobileNo());
        txtEmail.setText(dto.getEmail());
    }

    private void setCellValueFactory() {
            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
            colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
    }

    private void loadCustomerDetails() throws SQLException {

        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        List<CustomerDto> dtoList = customerModel.getAllCustomers();

        for (CustomerDto dto:dtoList){

                obList.add( new CustomerTm(
                        dto.getCusId(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getAddress(),
                        dto.getEmail(),
                        dto.getMobileNo()
                ));

            }

        tblCustomer.setItems(obList);



    }


    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

        boolean isCustomerValidated = validateCustomer();
        if (!isCustomerValidated ) {
            return;
        }

        String cusId= txtCustomerId.getText();
        String firstName= txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String mobileNo = txtMobileNo.getText();

        CustomerDto dto = new CustomerDto(cusId,firstName,lastName,address,email,mobileNo);

        try {
            boolean isSaved = customerModel.saveCustomer(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!").show();
                clearFields();
                //tables eka refresh karanna
                loadCustomerDetails();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private boolean validateCustomer() {

        String firstName= txtFirstName.getText();
        boolean isFirstNameValidated = Pattern.matches("[A-Za-z]{3,}",firstName);
        if (!isFirstNameValidated){
            txtFirstName.requestFocus();
            txtFirstName.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtFirstName.getStyleClass().removeAll("mfx-text-field-error");


        String lastName = txtLastName.getText();
        boolean isLastNameValidated = Pattern.matches("[A-Za-z]{3,}",lastName);
        if (!isLastNameValidated){
            txtLastName.requestFocus();
            txtLastName.getStyleClass().add("mfx-text-field-error");

            return false;
        }

        txtLastName.getStyleClass().removeAll("mfx-text-field-error");

        String address = txtAddress.getText();
        boolean isAddressValidated = Pattern.matches("[A-Za-z0-9/ ]{3,}",address);
        if (!isAddressValidated){
            txtAddress.requestFocus();
            txtAddress.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtAddress.getStyleClass().removeAll("mfx-text-field-error");


        String email = txtEmail.getText();
        boolean isEmailValidated = Pattern.matches("[A-Za-z0-9@.]{3,}",email);
        if (!isEmailValidated){
            txtEmail.requestFocus();
            txtEmail.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtEmail.getStyleClass().removeAll("mfx-text-field-error");

        String mobileNo = txtMobileNo.getText();
        boolean isMobileNoValidated = Pattern.matches("[0-9]{3,}",mobileNo);
        if (!isMobileNoValidated){
            txtMobileNo.requestFocus();
            txtMobileNo.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtMobileNo.getStyleClass().removeAll("mfx-text-field-error");

        return true;



    }

    private void clearFields() {
        generateNextCustomerId();
        txtAddress.clear();
        txtEmail.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtMobileNo.clear();
    }

    private void generateNextCustomerId() {
        try{
            String customerId=customerModel.generateNextCustomerId();
            txtCustomerId.setText(customerId);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearCustomerOnAction(ActionEvent event) {

        generateNextCustomerId();
        clearFields();

    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {

        String customerId = txtCustomerId.getText();

        try {
            boolean isDeleted = customerModel.deleteCustomer(customerId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!").show();
                loadCustomerDetails();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {


        String cusId= txtCustomerId.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String email =  txtEmail.getText();
        String mobileNo = txtMobileNo.getText();

        try{
            boolean isUpdated = customerModel.updateCustomer(new CustomerDto(cusId,firstName,lastName,address,email,mobileNo));

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated").show();
                loadCustomerDetails();
            }

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
