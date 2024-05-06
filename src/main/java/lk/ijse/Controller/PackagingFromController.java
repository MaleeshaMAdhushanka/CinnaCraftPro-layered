package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.Dto.*;
import lk.ijse.Model.*;
import lk.ijse.Tm.PackagingTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackagingFromController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private JFXButton btnSizeEdit;

    @FXML
    private Text txtcassia;

    @FXML
    private Text txtverum;

    @FXML
    private Text txtburmannii;


    @FXML
    private MFXFilterComboBox<String> cmbDate;
    @FXML
    private MFXComboBox<String> cmbPackSize;


    @FXML
    private MFXComboBox<String> cmbCinnamonType;



    @FXML
    private TableColumn<?, ?> colCount;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colPackId;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<PackagingTm> tblPackaging;

    @FXML
    private TextField txtFieldCount;

    @FXML
    private Text txtSupplierId;

    private final CinnamonBookModel cinnamonBookModel = new CinnamonBookModel();

    private final CinnamonTypeModel cinnamonTypeModel = new CinnamonTypeModel();

    private final PackagingModel packagingModel = new PackagingModel();

    private final PackagingDetailsModel packagingDetailsModel = new PackagingDetailsModel();

    private final PackagingTransActionModel packagingTransActionModel = new PackagingTransActionModel();


    public void initialize(){
        loadDates();
        generateNextPackingId();
        loadCinnamonTypes();
        setCellValueFactory();
        loadCinnamonTypeAmounts();
    }

    private void loadCinnamonTypeAmounts() {


        try {


          List<CinnamonTypeDto> dto =   cinnamonTypeModel.getAllCinnamonType();

           double cassiaAmount =  dto.get(0).getAmount()-packagingDetailsModel.getTotalDescreasedAmount(dto.get(0).getTypeId());
           double  verumAmount = dto.get(1).getAmount()- packagingDetailsModel.getTotalDescreasedAmount(dto.get(1).getTypeId());
           double  burmanniiAmount = dto.get(2).getAmount() - packagingDetailsModel.getTotalDescreasedAmount(dto.get(2).getTypeId());

          txtcassia.setText(cassiaAmount +" Kg");
          txtverum.setText(verumAmount + " kg");
          txtburmannii.setText(burmanniiAmount + " kg");

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void setCellValueFactory() {
        colPackId.setCellValueFactory(new PropertyValueFactory<>("packagingDeatilsId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        
    }

    private void loadCinnamonTypes() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<CinnamonTypeDto> cinnamonTypesList = cinnamonTypeModel.getAllCinnamonType();

            for (CinnamonTypeDto dto : cinnamonTypesList){
                obList.add(dto.getType());
            }
            cmbCinnamonType.setItems(obList);
        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void generateNextPackingId() {

        try {
            String packagingId  = packagingDetailsModel.generateNextPackId();
            txtFieldCount.setText(packagingId);
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
    void btnAddOnAction(ActionEvent event) {

        boolean isValidated = validateFields();

        if (!isValidated) {
            return;

        }
        String packagingDetailsId = txtSupplierId.getText();
        String date = cmbDate.getSelectionModel().getSelectedItem();
        String cinnamonType = cmbCinnamonType.getSelectionModel().getSelectedItem();
        String packSize =  cmbPackSize.getSelectionModel().getSelectedItem();
        int count = Integer.parseInt(txtFieldCount.getText());



        //to get cinnamon type Id
        try {
            String cinnamonTypeId = cinnamonTypeModel.getCinnamonTypeId(cinnamonType);

           double currentAmount =   cinnamonTypeModel.getCinnamonAmount(cinnamonType);

           double currentPackageDetailsAmount = packagingDetailsModel.getTotalDescreasedAmount(cinnamonTypeId);
            double decreasedAmount = calculateDecreasedAmount(packSize, count);

            if ((currentAmount-(decreasedAmount+currentPackageDetailsAmount))<0) {
                new Alert(Alert.AlertType.WARNING, "Not enough Amount").show();
                return;

            }
            String typeId = cinnamonTypeModel.getCinnamonTypeId(cinnamonType);
            String packId = packagingModel.getPackId(cinnamonTypeId, packSize);

            PackingDetailsDto dto = new PackingDetailsDto(packagingDetailsId, LocalDate.parse(cmbDate.getText()),packId, count, decreasedAmount, false);

            boolean isAdded =  packagingDetailsModel.addPackingDetails(dto);


            if (isAdded) {
                generateNextPackingId();
                loadAllPackagingDetails(cmbDate.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Added").show();
                loadCinnamonTypeAmounts();
            }


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }

    private void loadAllPackagingDetails(String date) {


        try {

         List<PackingDetailsDto> dtoList =  packagingDetailsModel.loadAllPackagingDetails(LocalDate.parse(date));

         ObservableList<PackagingTm> obList = FXCollections.observableArrayList();

         for (PackingDetailsDto dto : dtoList){

             PackagingDto packagingDto = packagingModel.searchPackaging(dto.getPackId());

             String type = cinnamonTypeModel.getCinnamonType(packagingDto.getTypeId());

             PackagingTm tm = new PackagingTm(dto.getPackagingDetailsId(),type, packagingDto.getDescription(), dto.getCount());

             obList.add(tm);

         }

            for (int i = 0; i < obList.size(); i++) {
                final   int index = i;


                if (dtoList.get(index).isConfirmed()) {
                    obList.get(i).getDeleteButton().setDisable(true);

                }

                obList.get(i).getDeleteButton().setOnAction(actionEvent -> {
                    String packageDetailsId = dtoList.get(index).getPackagingDetailsId();
                    deletePackageDetails(packageDetailsId);
                });
            }
            tblPackaging.setItems(obList);


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void deletePackageDetails(String packageDetailsId) {


        try {
            boolean isDeleted = packagingDetailsModel.deletePackageDetails(packageDetailsId);

            if (isDeleted) {
                loadCinnamonTypeAmounts();
                refershTable();
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            }
            else {
                new Alert(Alert.AlertType.WARNING, "try Again");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refershTable() {
        loadAllPackagingDetails(String.valueOf(cmbDate.getText()));
    }


    private double calculateDecreasedAmount(String packSize, int count) {

        boolean isKexits = packSize.toUpperCase().contains("K");
        double amount = 0;

        // Create a matcher with the input string
        double convertedPackSize = 0;

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(packSize);


        // Find the first match
        if (matcher.find()) {
            convertedPackSize = Integer.parseInt(matcher.group());
        }

        if (isKexits) {
            amount = convertedPackSize * 1000;
        } else {
            amount = convertedPackSize;
        }

        return (amount * count)/1000;


    }

    private boolean validateFields() {


            if (Objects.equals(cmbDate.getText(), "")){
                cmbDate.requestFocus();
                cmbDate.getStyleClass().add("mfx-combo-box-error");
                return false;
            }

            cmbDate.getStyleClass().removeAll("mfx-combo-box-error");

            if (Objects.equals(cmbCinnamonType.getText(), "")){
                cmbCinnamonType.requestFocus();
                cmbCinnamonType.getStyleClass().add("mfx-combo-box-error");
                return false;
            }
            cmbCinnamonType.getStyleClass().removeAll("mfx-combo-box-error");

            if (Objects.equals(cmbPackSize.getText(), "")){
                cmbPackSize.requestFocus();
                cmbPackSize.getStyleClass().add("mfx-combo-box-error");
                return false;
            }
            cmbPackSize.getStyleClass().removeAll("mfx-combo-box-error");

            String count = txtFieldCount.getText();

            boolean isValidateCount = Pattern.matches("[1-9][0-9]*", String.valueOf(count));

            if (!isValidateCount){
                txtFieldCount.requestFocus();
                txtFieldCount.getStyleClass().add("mfx-text-field-error");
                return false;
            }
            txtFieldCount.getStyleClass().removeAll("mfx-text-field-error");

            return true;


        }



    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {

        //to calculate the total amount
        try {
            List<PackingCountAmountDto> dtoList = packagingDetailsModel.getTotalCountAmount(LocalDate.parse(cmbDate.getSelectionModel().getSelectedItem()));

            //tp updated the confirm statues
            boolean isConfirmed = packagingTransActionModel.confirmPackaging(LocalDate.parse(cmbDate.getSelectionModel().getSelectedItem()), dtoList);

            if (isConfirmed) {
                new Alert(Alert.AlertType.CONFIRMATION, "Confirmed").show();
                refershTable();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSizeEditOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/packagingDetailsForm.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle("Packaging Details");
        stage1.centerOnScreen();
        stage1.show();



    }
    @FXML
    void cmbDateOnAction(ActionEvent event) {

        loadAllPackagingDetails(cmbDate.getSelectionModel().getSelectedItem());


    }

    @FXML
    void cmbCinnamonTypeOnAction(ActionEvent event) {
        //to clear Selection

        cmbPackSize.getSelectionModel().clearSelection();

        String cinnamonType = cmbCinnamonType.getSelectionModel().getSelectedItem();;
        loadPackSizes(cinnamonType);



    }


    private void loadPackSizes(String cinnamonType) {

        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
          String cinnnamonTypeId =  cinnamonTypeModel.getCinnamonTypeId(cinnamonType);

          List<PackagingDto> packagingList = packagingModel.getAllPackaging(cinnnamonTypeId);

          for (PackagingDto dto : packagingList){
              obList.add(dto.getDescription());

          }

          cmbPackSize.setItems(obList);
        }
         catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtFiledCountOnAction(ActionEvent event) {

    }

}
