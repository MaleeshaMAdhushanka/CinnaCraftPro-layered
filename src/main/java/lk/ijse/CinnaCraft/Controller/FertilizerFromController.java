package lk.ijse.CinnaCraft.Controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.CinnaCraft.Dto.FertilizerDto;
import lk.ijse.CinnaCraft.Model.FertilizerModel;
import lk.ijse.CinnaCraft.Tm.FertilizerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;


public class FertilizerFromController {

    @FXML
    private JFXButton btnClearFertilizer;

    @FXML
    private JFXButton btnDeleteFertilizer;

    @FXML
    private JFXButton btnFertilizer;

    @FXML
    private JFXButton btnSales;

    @FXML
    private JFXButton btnUpdateFertilizer;

    @FXML
    private JFXButton btnaddFertilizer;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colFertilizerId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private AnchorPane fertilizerPane;

    @FXML
    private TableView<FertilizerTm> tblFertilizer;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtDescription;

    @FXML
    private Text txtFertilizerId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSize;

    FertilizerModel fertilizerModel = new FertilizerModel();

    public void initialize() throws SQLException {
        setCellValueFactory();
        loadFertilizerDetails();
        generteNextFertilizerId();
        setListener();
    }

    private void setListener() {
        tblFertilizer.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue)-> {
                    FertilizerDto dto = new FertilizerDto(newValue.getFertilizerID(), newValue.getBrand(), newValue.getDescription(), newValue.getSize(), newValue.getPrice(), newValue.getQty());
                    setFields(dto);
                } );
    }

    private void setFields(FertilizerDto dto) {
        txtFertilizerId.setText(dto.getFertilizerID());
        txtBrand.setText(dto.getBrand());
        txtDescription.setText(dto.getDescription());
        txtSize.setText(dto.getSize());
        txtPrice.setText(String.valueOf(dto.getPrice()));
        txtQty.setText(String.valueOf(dto.getQty()));
    }

    private void generteNextFertilizerId() {

        try {
            String fertilizerId =  fertilizerModel.generateNextFertilizerId();
            txtFertilizerId.setText(fertilizerId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void loadFertilizerDetails() throws SQLException {
       ObservableList<FertilizerTm > obList =  FXCollections.observableArrayList();
        List<FertilizerDto> dtoList = fertilizerModel.getAllFertilizer();

        for (FertilizerDto dto : dtoList){
            obList.add(new FertilizerTm(
                    dto.getFertilizerID(),
                    dto.getBrand(),
                    dto.getDescription(),
                    dto.getSize(),
                    dto.getPrice(),
                    dto.getQty()

            ));
        }
        tblFertilizer.setItems(obList);
    }

    private void setCellValueFactory() {
        colFertilizerId.setCellValueFactory(new PropertyValueFactory<>("fertilizerID"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    @FXML
    void btnAddFertilizerOnAction(ActionEvent event) {

        boolean isValidated = validatedFields();

        if (!isValidated) {
            return;
        }



        String fertilizerId= txtFertilizerId.getText();
        String brand= txtBrand.getText();
        String description = txtDescription.getText();
        String size = txtSize.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());

        FertilizerDto dto = new FertilizerDto(fertilizerId,brand,description,size,price, qty);

        try {
            boolean isSaved = fertilizerModel.saveFertilizer(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "fertilizer  saved!").show();
                clearFields();
                //tables eka refresh karanna
                loadFertilizerDetails();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private boolean validatedFields() {
        String brand = txtBrand.getText();

        boolean isValidateBrand = Pattern.matches("[A-Za-z ]{3,}",brand);
        if (!isValidateBrand){
            txtBrand.requestFocus();
            txtBrand.getStyleClass().add("mfx-text-field-error");
            return false;
        }
        txtBrand.getStyleClass().removeAll("mfx-text-field-error");




        String qty = txtQty.getText();

        boolean isValidateQty = Pattern.matches("[1-9][0-9]*",qty);

        if (!isValidateQty){
            txtQty.getStyleClass().add("mfx-text-field-error");
            txtQty.requestFocus();
            return false;
        }
        txtQty.getStyleClass().removeAll("mfx-text-field-error");

        return true;

    }

    private void clearFields() {
        generteNextFertilizerId();
        txtBrand.clear();
        txtDescription.clear();
        txtSize.clear();
        txtQty.clear();
        txtPrice.clear();

    }


    @FXML
    void btnClearFertilizerOnAction(ActionEvent event) {
        generteNextFertilizerId();
        clearFields();


    }

    @FXML
    void btnDeleteFertilizerOnAction(ActionEvent event) {
        String fertilierId = txtFertilizerId.getText();

        try {
            boolean isDeleted = fertilizerModel.deleteFertilizer(fertilierId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Fertlizer deleted!").show();
                loadFertilizerDetails();
                clearFields();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSalesOnAction(ActionEvent event) throws IOException {
        /*
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/fertilizerSalesForm.fxml"));
        Pane registerPane = fxmlLoader.load();
        fertilizerPane.getChildren().clear();
        fertilizerPane.getChildren().add(registerPane);

         */

    }

    @FXML
    void btnUpdateFertilizerOnAction(ActionEvent event) {
       String fertilizerID = txtFertilizerId.getText();
      String band = txtDescription.getText();
       String size = txtSize.getText();
      double price = Double.parseDouble(txtPrice.getText());
       int qty = Integer.parseInt(txtQty.getText());

    }

}
