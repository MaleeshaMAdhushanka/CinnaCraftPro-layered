package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.Dto.CinnamonBarkStockDto;
import lk.ijse.Dto.SupplierDto;
import lk.ijse.Model.AddCinnamonBarkTransactionModel;
import lk.ijse.Model.CinnamonBarkStockModel;
import lk.ijse.Model.CinnamonBookModel;
import lk.ijse.Model.SupplierModel;
import lk.ijse.Tm.SupplierTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CinnamonBarkFormController {

    @FXML
    private JFXButton btnAddStock;

    @FXML
    private JFXButton btnClearStock;

    @FXML
    private JFXButton btnDeleteStock;

    @FXML
    private JFXButton btnUpdateStock;

    @FXML
    private ComboBox<String> cmbSupplierId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colStockId;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TableView<SupplierTm> tableCinnamonBrakStock;

    @FXML
    private Text txtAmount;

    @FXML
    private TextField txtAmount1;

    @FXML
    private Text txtCinnamonBarkStockId;

    @FXML
    private Text txtCinnamonBarkStockId1;

    @FXML
    private Text txtDate;

    CinnamonBookModel cinnamonBookModel = new CinnamonBookModel();
    SupplierModel supplierModel = new SupplierModel();

    CinnamonBarkStockModel cinnamonBarkStockModel = new CinnamonBarkStockModel();
    AddCinnamonBarkTransactionModel addCinnamonBarkTransactionModel = new AddCinnamonBarkTransactionModel();

    public void initialize() throws SQLException {
        loadSupplierId();
        generateNextCinnamonBarkStockId();
        setDate();
        setCurrentDate();
        setCurrentDailyAmount(dpDate.getValue().toString());
        setCellValueFactory();
        loadSupplierDetails();
        setListener();


    }

    private void setListener() {
    }

    private void loadSupplierDetails() throws SQLException {

        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();
        List<SupplierDto> dtoList = supplierModel.getAllSuppliers();

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
        tableCinnamonBrakStock.setItems(obList);


    }

    private void setCellValueFactory() {
    //    colStockId.setCellValueFactory(new PropertyValueFactory<>());
    }

    private void setCurrentDailyAmount(String string) {
    }

    private void setCurrentDate() {
        dpDate.setValue(java.time.LocalDate.now());
    }

    private void setDate() {
        String string = LocalDate.now().toString();
        txtDate.setText(string);
    }

    private void generateNextCinnamonBarkStockId() throws SQLException {
        try {
            String id = cinnamonBarkStockModel.generateNextCinnamonBarkStockId();
            txtCinnamonBarkStockId.setText(id);

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void loadSupplierId() throws SQLException {

        ObservableList<String> obList  = FXCollections.observableArrayList();
        try {
            List<SupplierDto> supplierList =supplierModel.getAllSuppliers();
            for (SupplierDto supplierDto : supplierList) {
                obList.add(supplierDto.getSupId());
            }
            cmbSupplierId.setItems(obList);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddStockOnAction(ActionEvent event) {
        validateFields();

       String stockId =  txtCinnamonBarkStockId.getText();
       String supID = cmbSupplierId.getSelectionModel().getSelectedItem();
      //  String CinnamonBookId = CinnamonBookModel.getCinnamonBookId(txtDate.getText());
        double amount = Double.parseDouble(txtAmount1.getText());
        String date = txtDate.getText();



       // CinnamonBarkStockDto dto = new CinnamonBarkStockDto(stockId, supID, amount, date);
//
//        try {
//            //boolean isSaved = cinnamonBarkStockModel.saveCinnamonBarkStock(dto);
////            if (isSaved){
////                new Alert(Alert.AlertType.CONFIRMATION,"Stock  Saved").show();
////                clearFileds();
////                loadSupplierDetails();
////            }
//
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }

    }

    private void clearFileds() {
      //  generateNextCinnamonBarkStockId();
        cmbSupplierId.getSelectionModel().clearSelection();
        txtAmount1.clear();
     //   txtDate.clear();


    }

    private void validateFields() {
    }

    @FXML
    void btnClearStockOnAction(ActionEvent event) {
       // generateNextCinnamonBarkStockId();
        clearFileds();

    }

    @FXML
    void btnDeleteStockOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateStockOnAction(ActionEvent event) {

    }

    @FXML
    void dpDateOnAction(ActionEvent event) {

    }

}
