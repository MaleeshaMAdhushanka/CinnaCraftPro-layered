package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.Dto.CinnamonBarkStockDto;
import lk.ijse.Dto.SupplierDto;
import lk.ijse.Model.AddCinnamonBarkTransactionModel;
import lk.ijse.Model.CinnamonBarkStockModel;
import lk.ijse.Model.CinnamonBookModel;
import lk.ijse.Model.SupplierModel;
import lk.ijse.Tm.CinnamonBarkStockTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

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
    private MFXFilterComboBox<String> cmbSupplierId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colStockId;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private MFXDatePicker dpDate;

    @FXML
    private TableView<CinnamonBarkStockTm> tableCinnamonBrakStock;

    @FXML
    private Text txtAmount;

    @FXML
    private MFXTextField txtAmount1;

    @FXML
    private Text txtCinnamonBarkStockId;

    @FXML
    private Text txtCinnamonBarkStockId1;

    @FXML
    private Text txtDate;

    private CinnamonBookModel cinnamonBookModel = new CinnamonBookModel();

    private final SupplierModel supplierModel = new SupplierModel();

    private final CinnamonBarkStockModel cinnamonBarkStockModel = new CinnamonBarkStockModel();

    private final AddCinnamonBarkTransactionModel addCinnamonBarkTransactionModel = new AddCinnamonBarkTransactionModel();


    public void initialize(){
        setCurrentDay();
        setCurrentDailyAmount(dpDate.getValue().toString());
        loadAllStockDetails(dpDate.getValue().toString());
        loadSupplierIds();
         generateNextCinnamonBarkStockId();
         setCellValueFactory();
         setListener();
    }
    private void setListener() {
        tableCinnamonBrakStock.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        CinnamonBarkStockDto dto = new CinnamonBarkStockDto(newValue.getCinnamonBarkStockId(), newValue.getSupId(), newValue.getSupName(),  newValue.getAmount(), newValue.isPayed());
                        // Use 'dto' as needed
                    }
                });
    }




    private void setCellValueFactory() {
        colStockId.setCellValueFactory(new PropertyValueFactory<>("cinnamonBarkStockId"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("SupName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));


    }

    private void generateNextCinnamonBarkStockId() {
        try {
         String cinnamonBarkStockId = cinnamonBarkStockModel.generateNextCinnamonBarkStockId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadSupplierIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {

            List<SupplierDto> supplierList = supplierModel.getAllSuppliers();

            for (SupplierDto supplierDto: supplierList){
                obList.add(supplierDto.getSupId());
            }
            cmbSupplierId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    private void loadAllStockDetails(String date) {

        try {
        ObservableList<CinnamonBarkStockTm>  obList =  FXCollections.observableArrayList();

            String dateBoolId  = cinnamonBookModel.getCinnamonBookId(date);

            List<CinnamonBarkStockDto > dtoList = cinnamonBarkStockModel.getAllStockDetails(dateBoolId);

            for (CinnamonBarkStockDto dto : dtoList){

                String supName = supplierModel.getSupplierName(dto.getSupID());

                obList.add(new CinnamonBarkStockTm(
                   dto.getCinnamonStockID(),
                   dto.getSupID(),
                   supName,
                   dto.getAmount()

                ));

            }

            for (int i = 0; i < obList.size(); i++){
                final int index = i;


                //To Disable Delete and update button if already paid

                if (dtoList.get(index).isPayed()){
                    obList.get(i).getDeleteButton().setDisable(true);
                    obList.get(i).getUpdateButton().setDisable(true);
                }


                obList.get(i).getUpdateButton().setOnAction(event -> {
                    btnUpdateStockOnAction(dtoList.get(index).getCinnamonStockID());
                });
                obList.get(i).getDeleteButton().setOnAction(event -> {

                    String cinnamonStockID= dtoList.get(index).getCinnamonStockID();
                    btnDeleteStockOnAction(cinnamonStockID);


                    try {
                        String teaBookId = cinnamonBookModel.getCinnamonBookId(String.valueOf(dpDate.getValue()));
                        double dailyAmount = cinnamonBarkStockModel.getTotalAmount(teaBookId);
                        cinnamonBookModel.updateCinnamonBookAmount(teaBookId,dailyAmount);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    setCurrentDailyAmount(dpDate.getValue().toString());
                    loadAllStockDetails(dpDate.getValue().toString());

                });


            }
            tableCinnamonBrakStock.setItems(obList);


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void setCurrentDailyAmount(String date) {

        try {
            boolean isExists = cinnamonBookModel.searchDate(date);

            if (isExists) {
                txtAmount.setText(String.valueOf(cinnamonBookModel.getAmount(date)) +" kg");
            }
            else {
                txtAmount.setText("No date");
            }
        } catch (SQLException e) {

             new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCurrentDay() {
        dpDate.setValue(java.time.LocalDate.now());
    }

    @FXML
    void btnAddStockOnAction(ActionEvent event) {
        boolean isValidated = validateFields();

        if (!isValidated) {
            return;
        }

        try {
            String CinnamonStockID = txtCinnamonBarkStockId.getText();
            String  SupID = cmbSupplierId.getText();
           String CinnamonBookID= cinnamonBookModel.getCinnamonBookId(txtDate.getText());
           double  amount = Double.parseDouble(txtAmount1.getText());

            CinnamonBarkStockDto cinnamonBarkStockDto = new CinnamonBarkStockDto(CinnamonStockID, SupID, CinnamonBookID, amount, false);
           boolean isSaved =  addCinnamonBarkTransactionModel.saveCinnamonBarkStock(cinnamonBarkStockDto, CinnamonBookID);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Stock Added").show();

                clearFields();

                setCurrentDailyAmount(txtDate.getText());
                loadAllStockDetails(txtDate.getText());
            }


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {
        generateNextCinnamonBarkStockId();
        txtAmount1.clear();
        cmbSupplierId.getSelectionModel().clearSelection();
    }

    private boolean validateFields() {
        String amount = txtAmount1.getText();
        boolean isAmountValidated = Pattern.matches("[0-9]{1,}", String.valueOf(amount));

        if (Objects.equals(cmbSupplierId.getText(), "")){
            cmbSupplierId.requestFocus();
            cmbSupplierId.getStyleClass().add("mfx-combo-box-error");
            return false;

        }
        cmbSupplierId.getStyleClass().removeAll("mfx-combo-box-error");

        if (!isAmountValidated){
            txtAmount1.requestFocus();
            txtAmount1.getStyleClass().add("mfx-combo-box-error");
            return false;
        }
        txtAmount1.getStyleClass().removeAll("mfx-combo-box-error");
        return true;



    }

    @FXML
    void btnClearStockOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteStockOnAction(String event) {

    }

    @FXML
    void btnUpdateStockOnAction(String event) {


        boolean isValidated = validateFields();

        if (!isValidated) {
            return;
        }

        String CinnamonStockID = txtCinnamonBarkStockId.getText();
        String  SupID = cmbSupplierId.getText();
        String date = txtDate.getText();
        double  amount = Double.parseDouble(txtAmount1.getText());


        try {
            String cinnamonBookId = cinnamonBookModel.getCinnamonBookId(date);

            boolean isUpdated = cinnamonBarkStockModel.updateCinnamonBarkStock(new CinnamonBarkStockDto(CinnamonStockID, SupID, date, amount, false));
            //Updated Daily Amount On UI

           double dailyAmount =  cinnamonBarkStockModel.getTotalAmount(cinnamonBookId);


           //update Database
            cinnamonBookModel.updateCinnamonBookAmount(cinnamonBookId, dailyAmount);

            setCurrentDailyAmount(date);
            loadAllStockDetails(date);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully").show();
            }



        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void dpDateOnAction(ActionEvent event) {

        String date = dpDate.getValue().toString();

        //this method is t0 check is there a record for that date in database

        dateCheck(date);
        setCurrentDailyAmount(date);
        loadAllStockDetails(date);


    }

    private void dateCheck(String date) {

        try {
            if (!cinnamonBookModel.searchDate(date)) {
                cinnamonBookModel.createCinnamonBookRecord(date);
                setCurrentDailyAmount(date);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void  loadCinnamonBarkStockDetails(){

        try {
           CinnamonBarkStockDto dto =  cinnamonBarkStockModel.searchCinnamonBarkStock(txtCinnamonBarkStockId.getText());
            setFields(dto);
        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(CinnamonBarkStockDto cinnamonBarkStock) {

        String date = null;

        try {
           date =  cinnamonBookModel.getCinnamonBookDate(cinnamonBarkStock.getCinnamonBookID());

        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        txtCinnamonBarkStockId.setText(cinnamonBarkStock.getCinnamonStockID());
        cmbSupplierId.setText(cinnamonBarkStock.getSupID());
        txtAmount1.setText(String.valueOf(cinnamonBarkStock.getAmount()));
        txtDate.setText(date);



    }

}

