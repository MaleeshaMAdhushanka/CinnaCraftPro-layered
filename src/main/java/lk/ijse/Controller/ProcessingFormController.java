package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.Dto.CinnamonBookDto;
import lk.ijse.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.Dto.CinnamonBookTypeDto;
import lk.ijse.Dto.CinnamonTypeDto;
import lk.ijse.Model.CinnamonBookModel;
import lk.ijse.Model.CinnamonBookTypeModel;
import lk.ijse.Model.CinnamonTypeModel;
import lk.ijse.Model.ProcessingModel;
import lk.ijse.Tm.CinnamonBookTypeTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class ProcessingFormController {


    @FXML
    private TableColumn<?, ?> colId;


    @FXML
    private TableColumn<?, ?> colType;
    @FXML
    private JFXButton btnAddDetails;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private MFXFilterComboBox<String> cmbDate;

    @FXML
    private MFXComboBox<String> cmbType;


    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableView<CinnamonBookTypeTm> tblProcessing;

    @FXML
    private MFXTextField txtAmount;

    @FXML
    private Text txtCinnamonBookTypeId;


    private final CinnamonBookTypeModel cinnamonBookTypeModel= new CinnamonBookTypeModel();

    private final CinnamonBookModel cinnamonBookModel = new CinnamonBookModel();

    private final  CinnamonTypeModel cinnamonTypeModel = new CinnamonTypeModel();

    private final ProcessingModel processingModel = new ProcessingModel();


    public void initialize(){
        loadDates();
        loadTypes();
        setCellValueFactory();
        generateNextCinnamonBookTypeId();
        
        
        

    }

    private void generateNextCinnamonBookTypeId() {

        try {
            String cinnamonBookTypeId = cinnamonBookTypeModel.generateNextCinnamonBookTypeId();
            txtCinnamonBookTypeId.setText(cinnamonBookTypeId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("CinnamonBookTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));





    }

    private void loadTypes() {
        ObservableList<String> obList = FXCollections.observableArrayList();


        try {
            List<CinnamonTypeDto> cinnamonTypeList = cinnamonTypeModel.getAllCinnamonType();
            
            for (CinnamonTypeDto cinnamonTypeDto : cinnamonTypeList){
                obList.add(cinnamonTypeDto.getType());
            }
            cmbType.setItems(obList);
            
            
            
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void loadDates() {
        ObservableList<String> obList = FXCollections.observableArrayList();


        try {

            List<CinnamonBookDto> cinnamonBookList = cinnamonBookModel.getAllCinnamonBookDetails();

            for (CinnamonBookDto cinnamonBookDto : cinnamonBookList){
                obList.add(cinnamonBookDto.getDate());
            }
            cmbDate.setItems(obList);


        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }


    @FXML
    void btnAddDetailsOnAction(ActionEvent event) {

        boolean isValidated = validateFields();

        if (!isValidated) {
            return;
        }

         String cinnamonBookTypeId = txtCinnamonBookTypeId.getText();
        LocalDate date =  LocalDate.parse(cmbDate.getText());
        String type =  cmbType.getText();
        double amount = Double.parseDouble(txtAmount.getText());



        try {
            String  cinnamonTypeId = cinnamonTypeModel.getCinnamonTypeId(type);
            CinnamonBookTypeDto dto = new CinnamonBookTypeDto(cinnamonTypeId, date, cinnamonTypeId, amount, false);


            boolean isSaved = cinnamonBookTypeModel.saveCinnamonBookType(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Details saved");
                refreshTable();
                clearFields();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }




    }

    private void clearFields() {
        generateNextCinnamonBookTypeId();
        cmbType.getSelectionModel().clearSelection();
        txtAmount.clear();
    }

    private boolean validateFields() {

        if (Objects.equals(cmbDate.getText(), "")) {
            cmbDate.requestFocus();
            cmbDate.getStyleClass().add("mfx-combo-box-error");
            return  false;
        }
        cmbDate.getStyleClass().removeAll("mfx-combo-box-error");


        if (Objects.equals(cmbType.getText(), "")) {
            cmbType.requestFocus();
            cmbType.getStyleClass().add("mfx-combo-box-error");
            return  false;
        }
        cmbType.getStyleClass().removeAll("mfx-combo-box-error");

        String amount = txtAmount.getText();

        boolean isValidateAmount = Pattern.matches("[0-9]{1,}", String.valueOf(amount));
        if (!isValidateAmount) {
            txtAmount.requestFocus();
            txtAmount.getStyleClass().add("mfx-text-field-error");
            return false;
        }
        txtAmount.getStyleClass().removeAll("mfx-text-field-error");

        return true;


    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        txtAmount.clear();
        cmbType.getSelectionModel().clearSelection();
        cmbType.clear();
        cmbDate.getSelectionModel().clearSelection();
        cmbDate.clear();

    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {


        try {

            //To calculate the total amount

            List<CinnamonBookTypeDetailDto> dtoList = cinnamonBookTypeModel.getTotalAmount(LocalDate.parse(cmbDate.getValue()));

            //To update the cofirmed status

            boolean isConfirmed = processingModel.updateDetails(LocalDate.parse(cmbDate.getSelectionModel().getSelectedItem()), dtoList);

            if (isConfirmed) {
                new Alert(Alert.AlertType.CONFIRMATION, "Confirmed").show();
                refreshTable();
            }

        } catch (SQLException e) {
            new  Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void refreshTable() {
        loadAllProcessingDetails(cmbDate.getText());
    }

    @FXML
    void cmbDateOnAction(ActionEvent event) {
        loadAllProcessingDetails(cmbDate.getSelectionModel().getSelectedItem());

    }

    private void loadAllProcessingDetails(String date) {

        try {
           List<CinnamonBookTypeDto> dtoList = cinnamonBookTypeModel.getAllTeaBookTypeDetails(date);

           ObservableList<CinnamonBookTypeTm> obList = FXCollections.observableArrayList();

           for (CinnamonBookTypeDto dto : dtoList){

               //Getting the Name from Database
               String cinnamonType = cinnamonTypeModel.getCinnamonType(dto.getTypeId());

               CinnamonBookTypeTm tm = new CinnamonBookTypeTm(dto.getCinnamonBookTypeId(), cinnamonType, dto.getAmount());
               obList.add(tm);


               for (int i = 0; i < obList.size(); i++) {
                   final  int index = i;

                   //To disable the delete button if already confirmed

                   if (dtoList.get(index).isConfirmed()) {
                       obList.get(i).getDeleteButton().setDisable(true);

                   }


                   obList.get(i).getDeleteButton().setOnAction(actionEvent ->{
                       String CinnamonBookTypeId = dtoList.get(index).getCinnamonBookTypeId();
                       deleteCinnamonBookType(CinnamonBookTypeId);

                   });
               }
               tblProcessing.setItems(obList);



           }


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void deleteCinnamonBookType(String CinnamonBookTypeId){


        try {
            boolean isDeleted = cinnamonBookTypeModel.deleteCinnamonBookType(CinnamonBookTypeId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                refreshTable();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {
        btnAddDetailsOnAction(event);

    }

}
