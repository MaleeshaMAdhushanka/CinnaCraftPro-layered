
package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.Dto.CinnamonTypeDto;
import lk.ijse.Dto.PackagingDto;
import lk.ijse.Model.CinnamonTypeModel;
import lk.ijse.Model.PackagingModel;
import lk.ijse.Tm.PackagingDetailsTm;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class PackagingDetailsFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private MFXComboBox<String>  cmbCinnamonType;



    @FXML
    private TableColumn<?, ?> colPackId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<PackagingDetailsTm> tblPackDetails;


    @FXML
    private Text txtMassage;

    @FXML
    private TextField txtFieldPrice;

    @FXML
    private TextField txtFieldSize;

    @FXML
    private Text txtPackId;


     private final PackagingModel packagingModel = new PackagingModel();

     private final CinnamonTypeModel cinnamonTypeModel = new CinnamonTypeModel();


     public void initialize(){
         generateNextPackId();
         setCellValueFactory();
         loadCinnamonType();
         loadAllPackages();
         setListener();
     }

    private void setListener() {
         tblPackDetails.getSelectionModel().selectedItemProperty()
                 .addListener((observable, oldValue, newValue) -> {

                     //This Listener works every time something happened to the table
                     //So To if nothing selected and if its worked it give null point error
                     //to stop if just simply check if newValue is null
                     if (newValue == null) {
                         return;
                     }
                     PackagingDetailsTm seletedItem = new PackagingDetailsTm(
                             newValue.getPackId(),
                             newValue.getType(),
                             newValue.getSize(),
                             newValue.getPrice(),
                             newValue.getRemoveButton()

                     );
                     setFields(seletedItem);
                     setButtons(false);

                 } );
    }

    private void setButtons(boolean value) {
         btnAdd.setVisible(value);
         btnUpdate.setVisible(!value);

    }

    private void setFields(PackagingDetailsTm seletedItem) {

         txtPackId.setText(seletedItem.getPackId());
         cmbCinnamonType.selectItem(seletedItem.getType());
         txtFieldSize.setText(seletedItem.getSize());
         txtFieldPrice.setText(String.valueOf(seletedItem.getPrice()));

    }

    private void loadAllPackages() {
        ObservableList<PackagingDetailsTm> oblist = FXCollections.observableArrayList();

        try {

            List<PackagingDto> dtoList = packagingModel.getAllPackaging();

            for (PackagingDto dto : dtoList) {

                String type = cinnamonTypeModel.getCinnamonType(dto.getTypeId());

                oblist.add(new PackagingDetailsTm(
                                dto.getPackId(),
                                type,
                                dto.getDescription(),
                                dto.getPrice()
                        )
                );
            }


            for (int i = 0; i < oblist.size(); i++) {
                final int index = i;
              //  final  String packId = oblist.get(i).getPackID();

                oblist.get(i).getRemoveButton().setOnAction(event -> {

                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if (type.orElse(no)==yes) {
                        String packId = dtoList.get(index).getPackId();
                        deletePackage(packId);
                    }

                });

            }
            tblPackDetails.setItems(oblist);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void deletePackage(String packId) {

        try {
            boolean isDeleted = packagingModel.deletePackage(packId);
            if (isDeleted) {
                new  Alert(Alert.AlertType.CONFIRMATION, "Package Deleted");
                loadAllPackages();
                clearFields();
                generateNextPackId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadCinnamonType() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try{
            List<CinnamonTypeDto> cinnamonTypesList = cinnamonTypeModel.getAllCinnamonType();
            for (CinnamonTypeDto cinnamonTypeDto : cinnamonTypesList){
                obList.add(cinnamonTypeDto.getType());
            }
            cmbCinnamonType.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void setCellValueFactory() {
         colPackId.setCellValueFactory(new PropertyValueFactory<>("packId"));
         colType.setCellValueFactory(new PropertyValueFactory<>("type"));
         colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
         colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
         colRemove.setCellValueFactory(new PropertyValueFactory<>("removeButton"));

    }

    private void generateNextPackId() {

         try {

             String lastPackId = packagingModel.generateNextPackId();
             txtPackId.setText(lastPackId);
         }catch (SQLException e){
             new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
         }

    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
         boolean isValidated = validatedFields();
        if (!isValidated) {
            return;
        }
        try {
           String packId =  txtPackId.getText();
           String type = cmbCinnamonType.getText();

           String typeId =  cinnamonTypeModel.getCinnamonTypeId(type);

          String size =  txtFieldSize.getText();
          String price = txtFieldPrice.getText();

            PackagingDto dto = new PackagingDto(packId, typeId, size, 0, Double.parseDouble(price));


            boolean isAdded = packagingModel.addPackage(dto);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Package Added").show();
                loadAllPackages();
                generateNextPackId();
                clearFields();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void clearFields() {
         txtFieldPrice.clear();
         txtFieldSize.clear();
         cmbCinnamonType.clear();
         cmbCinnamonType.getSelectionModel().clearSelection();
    }

    private boolean validatedFields() {
        if (Objects.equals(cmbCinnamonType.getControlCssMetaData(), "")) {
            cmbCinnamonType.requestFocus();
            cmbCinnamonType.getStyleClass().add("mfx-combo-box-error");
            return false;
        }
        cmbCinnamonType.getStyleClass().removeAll("mfx-combo-box-error");


        String size = txtFieldSize.getText();

        boolean isValidateSize = Pattern.matches("([1-9]\\d*)(?:Kg|g)",size);

        if (isValidateSize) {
            txtFieldSize.requestFocus();
            txtFieldSize.getStyleClass().add("mfx-text-field-error");
            txtMassage.setVisible(true);
            return  false;

        }
        txtMassage.setVisible(false);
        txtFieldSize.getStyleClass().removeAll("mfx-text-field-error");

        String price = txtFieldPrice.getText();

        boolean isValidatePrice = Pattern.matches("[0-9]{1,}",price);

        if (isValidatePrice) {
            txtFieldPrice.requestFocus();
            txtFieldPrice.getStyleClass().add("mfx-text-field-error");
            return  false;
        }

        txtFieldPrice.getStyleClass().removeAll("mfx-text-field-error");

        return true;

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
         setButtons(true);
         clearFields();
         generateNextPackId();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

         boolean isValidate = validatedFields();

        if (!isValidate) {
            return;
        }
        try {
            String packId = txtPackId.getText();
            String type = cmbCinnamonType.getText();
            String typeId = cinnamonTypeModel.getCinnamonTypeId(type);
            String size = txtFieldSize.getText();;
            String price = txtFieldPrice.getText();

            boolean isAdded = packagingModel.updatedPack(packId, typeId, size, Double.parseDouble(price));
            if (isAdded ) {
                new Alert(Alert.AlertType.CONFIRMATION, "Package updated!").show();
                loadAllPackages();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void cmbCinnamonTypeOnAction(ActionEvent event) {

    }

}
