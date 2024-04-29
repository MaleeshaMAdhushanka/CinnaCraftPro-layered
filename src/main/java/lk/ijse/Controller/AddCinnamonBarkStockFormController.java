
package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lk.ijse.Dto.CinnamonBarkStockDto;
import lk.ijse.Dto.CinnamonBookDto;
import lk.ijse.Dto.SupplierDto;
import lk.ijse.Model.CinnamonBarkStockModel;
import lk.ijse.Model.SupplierModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AddCinnamonBarkStockFormController {

    @FXML
    private JFXButton btnAddStock;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private ComboBox<String> cmbSupplierId;

    @FXML
    private TextField txtAmount;

    @FXML
    private Text txtCinnamonBarkStockId;

    @FXML
    private Text txtCinnamonBarkStockId1;

    @FXML
    private Text txtDate;


    SupplierModel supplierModel = new SupplierModel();

    CinnamonBarkStockModel cinnamonBarkStockModel = new CinnamonBarkStockModel();

    public void initialize() throws SQLException {
        loadSupplierIds();
        generateNextCinnamonBarkStockId();
        setDate();

    }

    private void setDate() {
        String string = LocalDate.now().toString();
        txtDate.setText(string);
    }

    private void generateNextCinnamonBarkStockId() throws SQLException {
        String stockId = cinnamonBarkStockModel.generateNextCinnamonBarkStockId();
        txtCinnamonBarkStockId.setText(stockId);
    }

    private void loadSupplierIds() throws SQLException {

        ObservableList<String> obList = FXCollections.observableArrayList();

        try{

            List<SupplierDto> supplierList =  supplierModel.getAllSuppliers();

            for (SupplierDto supplierDto: supplierList){
                obList.add(supplierDto.getSupId());
            }

            cmbSupplierId.setItems(obList);

        }
        catch (SQLException e){
            throw  new RuntimeException(e);
        }

    }

    @FXML
    void btnAddStockOnAction(ActionEvent event) {
        validateFields();

        String stockId = txtCinnamonBarkStockId.getText();
        String supplier = cmbSupplierId.getSelectionModel().getSelectedItem();
        String amount = txtAmount.getText();
        String date = txtDate.getText();


    }

    private void validateFields() {
    }

    @FXML
    void btnCancel(ActionEvent event) {

    }

}

