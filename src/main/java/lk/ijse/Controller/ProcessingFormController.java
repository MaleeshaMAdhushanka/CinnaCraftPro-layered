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
import javafx.scene.text.Text;
import lk.ijse.Dto.CinnamonBookDto;
import lk.ijse.Dto.CinnamonBookTypeDto;
import lk.ijse.Dto.CinnamonTypeDto;
import lk.ijse.Model.CinnamonBookModel;
import lk.ijse.Model.CinnamonBookTypeModel;
import lk.ijse.Model.CinnamonTypeModel;
import lk.ijse.Model.ProcessingModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcessingFormController {

    @FXML
    private TableColumn<?, ?> ColId;

    @FXML
    private TableColumn<?, ?> ColType;

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
    private TableView<?> tblProcessing;

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

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {

    }

    @FXML
    void cmbDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {

    }

}
