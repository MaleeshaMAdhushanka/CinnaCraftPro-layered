package lk.ijse.CinnaCraft.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lk.ijse.CinnaCraft.Dto.SupplierDto;
import lk.ijse.CinnaCraft.Model.SupplierModel;
import lk.ijse.CinnaCraft.Tm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class SupplierFormController {

    @FXML
    private JFXButton btnAddSupplier;

    @FXML
    private JFXButton btnClearSupplier;

    @FXML
    private JFXButton btnDeleteSupplier;

    @FXML
    private JFXButton btnPayments;

    @FXML
    private JFXButton btnUpdateSupplier3;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBank;

    @FXML
    private TableColumn<?, ?> colBankNo;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colMobileNo;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private AnchorPane supplierPane;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtBank;

    @FXML
    private TextField txtBankNo;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMobileNo;

    @FXML
    private TextField txtSearch;

    @FXML
    private Text txtSupplierId;

    SupplierModel supplierModel = new SupplierModel();

    public void initialize() throws SQLException {
        setCellValueFavctory();
        loadSupplierDetails();
        generateNextSupplierId();
        setListener();



    }

    private void setListener() {
        tblSupplier.getSelectionModel().selectedItemProperty()
                .addListener((observable , oldValue, newValue)->{
                SupplierDto dto = new SupplierDto(newValue.getSupId(), newValue.getFirstName(), newValue.getLastName(), newValue.getAddress(), newValue.getBank(), newValue.getBankNo(), newValue.getMobileNo());
                setFields(dto);
        } );
    }

    private void setFields(SupplierDto dto) {
        txtSupplierId.setText(dto.getSupId());
        txtFirstName.setText(dto.getFirstName());
        txtLastName.setText(dto.getLastName());
        txtAddress.setText(dto.getAddress());
        txtBank.setText(dto.getBank());
        txtBankNo.setText(dto.getBankNo());
        txtMobileNo.setText(dto.getMobileNo());
    }

    private void generateNextSupplierId()  {

        try {
           String supplierId = supplierModel.generateNextSupplierId();
            txtSupplierId.setText(supplierId);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }




    }

    private void loadSupplierDetails() throws SQLException {

       ObservableList<SupplierTm> obList =  FXCollections.observableArrayList();

      List<SupplierDto> dtoList =  supplierModel.getAllSuppliers();

      for (SupplierDto dto:dtoList){
          obList.add(new SupplierTm(
                  dto.getSupId(),
                  dto.getFirstName(),
                  dto.getLastName(),
                  dto.getAddress(),
                  dto.getBank(),
                  dto.getBankNo(),
                  dto.getMobileNo()

          ));
      }
      tblSupplier.setItems(obList);



    }

    private void setCellValueFavctory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colBank.setCellValueFactory(new PropertyValueFactory<>("bank"));
        colBankNo.setCellValueFactory(new PropertyValueFactory<>("bankNo"));
        colMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {

        boolean isSupplierValidated = validateSupplier();
        if (!isSupplierValidated) {
            return;
        }



       String SupID = txtSupplierId.getText();
       String firstName =  txtFirstName.getText();
       String lastName = txtLastName.getText();
       String address = txtAddress.getText();
       String bank = txtBank.getText();
       String bankNo =  txtBankNo.getText();
       String mobileNo = txtMobileNo.getText();


        SupplierDto dto = new SupplierDto(SupID, firstName, lastName, address, bank, bankNo, mobileNo);


        try {
           boolean isSaved =  supplierModel.saveSupplier(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier saved!").show();
                clearFields();

                //aye api tabels eka refresh kara
                loadSupplierDetails();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private boolean validateSupplier() {

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

        String bank = txtBank.getText();

        boolean isBankValidated = Pattern.matches("[A-Za-z0-9/ ]{3,}",bank);

        if (!isBankValidated){
            txtBank.requestFocus();
            txtBank.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtBank.getStyleClass().removeAll("mfx-text-field-error");


        String bankNo = txtBankNo.getText();

        boolean isBankNoValidated = Pattern.matches("[0-9]{3,}",bankNo);

        if (!isBankNoValidated){
            txtBankNo.requestFocus();
            txtBankNo.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtBankNo.getStyleClass().removeAll("mfx-text-field-error");



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
        generateNextSupplierId();
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtBank.clear();
        txtBankNo.clear();
        txtMobileNo.clear();


    }

    @FXML
    void btnClearSupplierOnAction(ActionEvent event) {
        generateNextSupplierId();
        clearFields();

    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {
        String  supplierId= txtSupplierId.getText();

        try {
            boolean isDeleted = SupplierModel.deleteSupplier(supplierId);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!").show();
                loadSupplierDetails();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }

    @FXML
    void btnPaymentsOnAction(ActionEvent event) throws IOException {


       FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/paymentForm.fxml"));
       Pane registerPane = fxmlLoader.load();
       supplierPane.getChildren().clear();
       supplierPane.getChildren().add(registerPane);

    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {

        String SupID = txtSupplierId.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address =  txtAddress.getText();
        String bank = txtBank.getText();
         String bankNo = txtBankNo.getText();
         String mobileNo =  txtMobileNo.getText();

        try {
         boolean isUpdated =   supplierModel.updateSupplier(new SupplierDto(SupID, firstName, lastName, address, bank, bankNo, mobileNo));
         if (isUpdated){
             new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated").show();
             loadSupplierDetails();
         }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
