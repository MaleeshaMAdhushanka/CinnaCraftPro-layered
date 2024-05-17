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
import lk.ijse.Dto.CinnamonTypeDto;
import lk.ijse.Dto.CustomerDto;
import lk.ijse.Dto.PackagingDto;
import lk.ijse.Dto.PlaceCinnamonOrderDto;
import lk.ijse.Model.*;
import lk.ijse.Tm.SalesCartTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class SalesFormController {

    @FXML
    private JFXButton btnAddCart;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnReceipt;

    @FXML
    private MFXComboBox<String> cmbCinnamonType;

    @FXML
    private MFXFilterComboBox<String> cmbCustomerNum;

    @FXML
    private MFXComboBox<String> cmbPackSize;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<SalesCartTm> tblCart;

    @FXML
    private Text txtCount;

    @FXML
    private Text txtDate;

    @FXML
    private MFXTextField txtFieldQty;

    @FXML
    private Text txtName;

    @FXML
    private Text txtNetTotal;

    @FXML
    private Text txtOrderId;

    @FXML
    private Text txtPrice;


    @FXML
    private Text txtMassage;


    ArrayList<ArrayList<String> > pacakageDetails = new ArrayList<>();

    private final ObservableList<SalesCartTm> obList = FXCollections.observableArrayList();

    private final CustomerModel customerModel = new CustomerModel();

    private final CinnamonOrderModel cinnamonOrderModel = new CinnamonOrderModel();


    private final CinnamonTypeModel cinnamonTypeModel = new CinnamonTypeModel();

    private final PackagingModel packagingModel =new PackagingModel();

    private final PlaceCinnamonOrderModel placeCinnamonOrderModel = new PlaceCinnamonOrderModel();


    //for report Generation

    private String lastOrderId;

    private String lastCusId;
    private String lastCusName;
    private String lastTotal;


    public void initialize(){
        setCurrentDate();
        generateNextOrderId();
        loadCustomerIds();
        loadCinnamonTypes();
        setCellValueFactory();
        loadPackageDetails();

        //disable until order placed
        btnReceipt.setVisible(true);



    }

    private void loadPackageDetails() {
        try {
           List<PackagingDto> dtoList =  packagingModel.getAllPackaging();

            for (int i = 0; i < dtoList.size(); i++) {
                pacakageDetails.add(new ArrayList<>());
                pacakageDetails.get(i).add(dtoList.get(i).getPackId());
                pacakageDetails.get(i).add(String.valueOf(dtoList.get(i).getPackageCount()));

                System.out.println(dtoList.get(i).getPackageCount());
            }



        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {

        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("removeButton"));

    }

    private void loadCinnamonTypes() {


        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<CinnamonTypeDto> cinnamonTypesList = cinnamonTypeModel.getAllCinnamonType();


            for (CinnamonTypeDto  dto: cinnamonTypesList){
                obList.add(dto.getType());

            }
            cmbCinnamonType.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }


    private void loadCustomerIds() {

        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
         List<CustomerDto >cusList =  customerModel.getAllCustomers();
         for (CustomerDto dto : cusList){
             obList.add(dto.getMobileNo());
         }
         cmbCustomerNum.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void generateNextOrderId() {

        try {
          String orderId =  cinnamonOrderModel.generateNextOrderId();
          txtOrderId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage()).show();
        }

    }

    private void setCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(formatter);
        txtDate.setText(formattedDate);
    }


    @FXML
    void btnAddCartOnAction(ActionEvent event) {


        boolean isValidate = ValidateFields();
        if (!isValidate) {
            return;

        }
         String cinnamonType = cmbCinnamonType.getSelectionModel().getSelectedItem();
         String packSize = cmbPackSize.getSelectionModel().getSelectedItem();
         int qty = Integer.parseInt(txtFieldQty.getText());

         double uniPrice = Double.parseDouble(txtPrice.getText());
         double total = qty * uniPrice;


        for (int i = 0; i < tblCart.getItems().size(); i++) {



            if(cinnamonType.equals(colType.getCellData(i)) && packSize.equals(colSize.getCellData(i))) {

                qty +=(int) colQty.getCellData(i);
                total = qty * uniPrice;

                obList.get(i).setQty(qty);
                obList.get(i).setTotal(total);

                tblCart.refresh();
                calculateNetTotal();

                decreasePackageCount(obList.get(i).getPackId(), Integer.parseInt(txtFieldQty.getText()));
                itemCountRefresh();
                txtFieldQty.clear();
                return;

            }
        }
         //To find the pack  id
        String packId = null;

        try {
            String cinnamonTypeId=  cinnamonTypeModel.getCinnamonTypeId(cinnamonType);
            packId = packagingModel.getPackId(cinnamonTypeId, packSize);
        } catch (SQLException e) {
            throw  new RuntimeException(e);

        }

        obList.add(new SalesCartTm(
                packId,
                cinnamonType,
                packSize,
                qty,
                total

                ));

           cartRefresh();


           tblCart.setItems(obList);
           calculateNetTotal();
           decreasePackageCount(packId, qty);
           itemCountRefresh();
           txtFieldQty.clear();

    }
    private void itemCountRefresh() {


        String cinnamonType = cmbCinnamonType.getValue();
        String packSize = cmbPackSize.getValue();

        if (cinnamonType == null || packSize == null) {
            return;
        }


        try {
            //getting Cinnamon type id


            String cinnamonTypeId =  cinnamonTypeModel.getCinnamonTypeId(cinnamonType);




            //Finding packk id According to cinnamon Type id and packSize
            String packId = packagingModel.getPackId(cinnamonTypeId, packSize);


            PackagingDto dto = packagingModel.searchPackaging(packId);



            //get Details From Packaging Details Array List

            int count = getPackageCount(packId);


            //set Values
            txtCount.setText(String.valueOf(count));

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            e.printStackTrace();
        }


    }
    private void increasePackageCount(String packId, int qty) {
        for (int i = 0; i < pacakageDetails.size(); i++) {
            if (pacakageDetails.get(i).get(0).equals(packId)) {
                int count = Integer.parseInt(pacakageDetails.get(i).get(1));
                count += qty;
                pacakageDetails.get(i). set(1, String.valueOf(count));
                return;
            }
        }

    }

    private void decreasePackageCount(String packId, int qty) {
        for (int i = 0; i < pacakageDetails.size(); i++) {
            if (pacakageDetails.get(i).get(0).equals(packId)) {
                int count = Integer.parseInt(pacakageDetails.get(i).get(1));
                count-= qty;
                pacakageDetails.get(i). set(1, String.valueOf(count));
                return;
            }
        }


    }

    private void cartRefresh() {
        for (int i = 0; i < obList.size(); i++) {
            final  int index = i;

            obList.get(i).getRemoveButton().setOnAction(e -> {

                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if (type.orElse(no)==yes) {
                    increasePackageCount(obList.get(index).getPackId(), obList.get(index).getQty());
                    itemCountRefresh();
                    cartRefresh();
                    calculateNetTotal();
                }

            });
        }


    }



    private boolean ValidateFields() {
        if (Objects.equals(cmbCustomerNum.getText(), "")) {
            cmbCustomerNum.requestFocus();
            cmbCustomerNum.getStyleClass().add("mfx-combo-box-error");
            return  false;
        }
        cmbCustomerNum.getStyleClass().removeAll("mfx-combo-box-error");

        if (Objects.equals(cmbCinnamonType.getText(), "")) {
            cmbCinnamonType.requestFocus();
            cmbCinnamonType.getStyleClass().add("mfx-combo-box-error");
            return false;
        }
        cmbCinnamonType.getStyleClass().removeAll("mfx-combo-box-error");

        if (Objects.equals(cmbPackSize.getText(), "")) {
            cmbPackSize.requestFocus();
            cmbPackSize.getStyleClass().add("mfx-combo-box-error");
            return false;
        }
        cmbPackSize.getStyleClass().removeAll("mfx-combo-box-error");


        String qty = txtFieldQty.getText();

        boolean isValidateQty = Pattern.matches("[1-9][0-9]*", String.valueOf(qty));
        if (!isValidateQty) {
            txtFieldQty.requestFocus();
            txtFieldQty.getStyleClass().add("mfx-text-field-error");
            return false;
        }
        txtFieldQty.getStyleClass().removeAll("mfx-text-field-error");

        //To Validate Qty
       String cinnamonType = cmbCinnamonType.getSelectionModel().getSelectedItem();
       String packSize  = cmbPackSize.getSelectionModel().getSelectedItem();


       int count = 0;


        try {
            //Getting Cinnamon type id
          String cinnamonTypeId = cinnamonTypeModel.getCinnamonTypeId(cinnamonType);

            //Finding Pack id According to Cinnamon type id and pack size
          String packId = packagingModel.getPackId(cinnamonTypeId, packSize);

          //Get Details From Packing Details Arry List
            count = getPackageCount(packId);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        if (Integer.parseInt(qty)>count) {
            txtMassage.setVisible(true);
            return false;
        }
        txtMassage.setVisible(false);

        return true;

    }

    private void calculateNetTotal(){
        double total = 0;

        for (int i = 0; i < tblCart.getItems().size(); i++) {
            total +=(double) colTotal.getCellData(i);
        }
        txtNetTotal.setText(String.valueOf(total));
    }

    private int getPackageCount(String packId) {

        for (int i = 0; i < pacakageDetails.size(); i++) {
            if (pacakageDetails.get(i).get(0).equals(packId)) {
                return Integer.parseInt(pacakageDetails.get(i).get(1));
            }
        }

        return 0;

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

        for (int i = 0; i < obList.size(); i++) {
            increasePackageCount(obList.get(i).getPackId(), obList.get(i).getQty());
        }
        itemCountRefresh();
        obList.clear();

    }

    

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

        boolean  isValidate = validateFieldsPlaceOrder();

        if (!isValidate) {
            return;
        }

        String orderId = txtOrderId.getText();
        String cusNum = cmbCustomerNum.getValue();
        double total = Double.parseDouble(txtNetTotal.getText());

        String cusId = null;

        try {
            cusId = customerModel.searchCustomerId(cusNum);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LocalDate date = LocalDate.now();

        List<SalesCartTm> tmList = new ArrayList<>();

        for (SalesCartTm cartTm : obList){
            tmList.add(cartTm);
        }
        PlaceCinnamonOrderDto dto = new PlaceCinnamonOrderDto(
          orderId,
          cusId,
          date,
          total,
          tmList
        );

        try {
            boolean isSuccess = placeCinnamonOrderModel.placeOrder(dto);

            if (
                    isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "order Completed").show();
                setFieldsForReport(orderId, cusId, txtName.getText(), txtNetTotal.getText());
                tblCart.getItems().clear();

                txtNetTotal.setText("-");
                txtCount.setText("-");
                txtPrice.setText("-");
                txtFieldQty.setText("0");
                cmbCinnamonType.clear();
                cmbPackSize.clear();
                cmbCustomerNum.clear();

                generateNextOrderId();
            }


        } catch (SQLException e) {

            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void setFieldsForReport(String orderId, String cusId, String name, String total) {
        ///Active The receipt Button


        btnReceipt.setVisible(true);

        this.lastCusId = cusId;
        this.lastCusName = name;
        this.lastOrderId = orderId;
        this.lastTotal = total;


    }

    private boolean validateFieldsPlaceOrder() {
        if (Objects.equals(cmbCustomerNum.getText(), "")){
            cmbCustomerNum.requestFocus();;
            cmbCustomerNum.getStyleClass().add("mfx-combo-box-error");
            return false;
        }
        cmbCustomerNum.getStyleClass().removeAll("mfx-combo-box-error");

        return  true;
    }

    @FXML
    void btnReceiptOnAction(ActionEvent event) {








    }


    @FXML
    void cmbCinnamonTypeOnAction(ActionEvent event) {

        //To clear Selectiom
        cmbPackSize.getSelectionModel().clearSelection();
        txtCount.setText("-");
        txtPrice.setText("-");

        String cinnamonType = cmbCinnamonType.getSelectionModel().getSelectedItem();
        loadPackSizes(cinnamonType);

    }

    private void loadPackSizes(String cinnamonType) {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            String cinnamonTypeId  = cinnamonTypeModel.getCinnamonTypeId(cinnamonType);

            List<PackagingDto> packagingList = packagingModel.getAllPackaging(cinnamonTypeId);

            for (PackagingDto dto: packagingList){
                obList.add(dto.getDescription());
            }
            cmbPackSize.setItems(obList);


        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {

        String cusNum = cmbCustomerNum.getValue();

        try {

            String cusId = customerModel.searchCustomerId(cusNum);
            CustomerDto dto = customerModel.searchCustomer(cusId);
            txtName.setText(dto.getFirstName());


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void cmbPackSizeOnAction(ActionEvent event) {

        String cinnamonType = cmbCinnamonType.getSelectionModel().getSelectedItem();
        String packSize = cmbPackSize.getSelectionModel().getSelectedItem();


        //had to use this because  when the user selects a  cinnamon type and size and then selects another Cinnamon type it automatically called
        //this action method so the packsize is goinh null

        if (packSize == null) {
            return;
        }




        try {
            //Getting cinnamon typed id
            String cinnamonTypeId = cinnamonTypeModel.getCinnamonTypeId(cinnamonType);


            //Finding Pack id Accordig to cinnamon type id and pack size;
            String packId = packagingModel.getPackId(cinnamonTypeId, packSize);





            PackagingDto dto = packagingModel.searchPackaging(packId);



            //get  details from Packaging Details Array List

            int count = getPackageCount(packId);



            //set Values
            txtCount.setText(String.valueOf(count));
            txtPrice.setText(String.valueOf(dto.getPrice()));

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }

    @FXML
    void txtFieldQtyOnAction(ActionEvent event) {

    }

}

