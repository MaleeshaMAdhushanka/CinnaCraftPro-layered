
package lk.ijse.CinnaCraft.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lk.ijse.CinnaCraft.Dto.FertilizerDto;
import lk.ijse.CinnaCraft.Dto.PlaceFertilizerOrderDto;
import lk.ijse.CinnaCraft.Dto.SupplierDto;
import lk.ijse.CinnaCraft.Model.FertilizerModel;
import lk.ijse.CinnaCraft.Model.FertilizerOrderModel;
import lk.ijse.CinnaCraft.Model.PlaceFertilizerOrderModel;
import lk.ijse.CinnaCraft.Model.SupplierModel;
import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Tm.FertilizeSalesCartTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class FertilizerSalesFormController {

    @FXML
    private JFXButton btnAddCart;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnFertilizer;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnReceipt;

    @FXML
    private JFXButton btnSales;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbFertilizer;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colFertilizerId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private AnchorPane fertilizerSalesPane;

    @FXML
    private TableView<FertilizeSalesCartTm> tblCart;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtDescription;

    @FXML
    private TextField txtFieldQty;

    @FXML
    private Text txtName;

    @FXML
    private Text txtNetTotal;

    @FXML
    private Text txtOrderId;

    @FXML
    private Text txtQtyOnHand;

    @FXML
    private Text txtUnitPrice;

    ArrayList<ArrayList<String>> fertilizerDetails = new ArrayList<>();


    private final ObservableList<FertilizeSalesCartTm> obList = FXCollections.observableArrayList();

    SupplierModel supplierModel = new SupplierModel();

    FertilizerModel fertilizerModel = new FertilizerModel();

    FertilizerOrderModel fertilizerOrderModel = new FertilizerOrderModel();

    PlaceFertilizerOrderModel placeFertilizerOrderModel = new PlaceFertilizerOrderModel();

    //For report generation
    private  String lastFertilizerOrderId;

    private String lastSupId;
    private String lastSupName;
    private String lastTotal;


    public  void  initialize(){

        generateNextFertilizerOrderId();
        setDate();
        loadSupplierIds();
        loadItemIds();
        setCellValueFactory();
        loadFertilizerDetails();
    }

    private void loadFertilizerDetails() {

        try{

            List<FertilizerDto> dtoList = fertilizerModel.getAllFertilizer();

            for (int i = 0; i < dtoList.size(); i++){
                fertilizerDetails.add(new ArrayList<>());
                fertilizerDetails.get(i).add(dtoList.get(i).getFertilizerID());
                fertilizerDetails.get(i).add(String.valueOf(dtoList.get(i).getQty()));
            }

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    private void setCellValueFactory() {
        colFertilizerId.setCellValueFactory(new PropertyValueFactory<>("fertilizerId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("removeButton"));
    }

    private void generateNextFertilizerOrderId() {
        try{
            String orderId =fertilizerOrderModel.generateNextFertilizerOrderId();
            txtOrderId.setText(orderId);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadItemIds() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<FertilizerDto> allFertilizers = fertilizerModel.getAllFertilizer();
            for (FertilizerDto dto:allFertilizers){
                obList.add(dto.getFertilizerID());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        cmbFertilizer.setItems(obList);

    }

    private void loadSupplierIds()  {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SupplierDto> allSuppliers = supplierModel.getAllSuppliers();
            for (SupplierDto dto:allSuppliers){
                obList.add(dto.getSupId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        cmbCustomerId.setItems(obList);

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(now.toString());
    }

    @FXML
    void btnAddCartOnAction(ActionEvent event) {


        boolean isValidated = validateFields();

        if (!isValidated) {
            return;
        }

        String fertilizerId = cmbFertilizer.getValue();
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtFieldQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double total = qty * unitPrice;


        for (int i = 0; i < tblCart.getItems().size(); i++) {

            if (fertilizerId.equals(colFertilizerId.getCellData(i))){

                qty += (int) colQty.getCellData(i);
                total = qty * unitPrice;

                //uda hadagthth list eke ob list ta yewwa

                obList.get(i).setQty(qty);
                obList.get(i).setTotal(total);

                tblCart.refresh();
                calculateNetTotal();

                decreasePackageCount(obList.get(i).getFertilizerId(), Integer.parseInt(txtFieldQty.getText()));
                itemCountRefresh();
                txtFieldQty.clear();
                return;
            }
        }


        obList.add(new FertilizeSalesCartTm(
                fertilizerId,
                description,
                qty,
                total
        ));


        cartRefresh();


        tblCart.setItems(obList);
        calculateNetTotal();
        decreasePackageCount(fertilizerId,qty);
        itemCountRefresh();
        txtFieldQty.clear();

    }

    private boolean validateFields() {
        if (Objects.equals(cmbCustomerId.getValue(), "")){
            cmbCustomerId.requestFocus();
            cmbCustomerId.getStyleClass().add("mfx-combo-box-error");
            return false;
        }

        cmbCustomerId.getStyleClass().removeAll("mfx-combo-box-error");

        if (Objects.equals(cmbFertilizer.getValue(), "")){
            cmbFertilizer.requestFocus();
            cmbFertilizer.getStyleClass().add("mfx-combo-box-error");
            return false;
        }

        cmbFertilizer.getStyleClass().removeAll("mfx-combo-box-error");

        String qty = txtFieldQty.getText();

        boolean isValidateQty = Pattern.matches("[1-9][0-9]*",qty);

        if (!isValidateQty){
            txtFieldQty.requestFocus();
            txtFieldQty.getStyleClass().add("mfx-text-field-error");
            return false;
        }

        txtFieldQty.getStyleClass().removeAll("mfx-text-field-error");


        int count = getFertilizerCount(cmbFertilizer.getValue());

        return true;



    }

    private void cartRefresh() {

        for (int i = 0; i < obList.size(); i++) {
            final  int index = i;

            obList.get(i).getRemoveButton().setOnAction(event -> {

                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);


                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                //allert type ekata ena wishesha function ekak

                if (type.orElse(no)==yes) {
                    increasePackageCount(obList.get(index).getFertilizerId(),obList.get(index).getQty());
                    itemCountRefresh();
                    obList.remove(index);
                    cartRefresh();
                    calculateNetTotal();
                }

            });
        }
    }

    //delete karala aye gahadddi value eka ganata increse karaganna me method eka use kara

    private void increasePackageCount(String fertilizerId, int qty) {
        for (int i = 0; i < fertilizerDetails.size(); i++) {
            if (fertilizerId.equals(fertilizerDetails.get(i).get(0))){
                int count = Integer.parseInt(fertilizerDetails.get(i).get(1));
                count += qty;
                fertilizerDetails.get(i).set(1,String.valueOf(count));
                return;
            }
        }
    }

    private void itemCountRefresh() {
        String fertilizerId = cmbFertilizer.getValue();

        if (fertilizerId == null){
            return;
        }

        int count = getFertilizerCount(fertilizerId);

        txtQtyOnHand.setText(String.valueOf(count));
    }

    private int getFertilizerCount(String fertilizerId) {
        for (int i = 0; i < fertilizerDetails.size(); i++) {
            if (fertilizerDetails.get(i).get(0).equals(fertilizerId)){
                return Integer.parseInt(fertilizerDetails.get(i).get(1));
            }
        }
        return 0;
    }

    private void decreasePackageCount(String fertilizerId, int qty) {

        for (int i = 0; i < fertilizerDetails.size(); i++) {
            if (fertilizerId.equals(fertilizerDetails.get(i).get(0))){
                int count = Integer.parseInt(fertilizerDetails.get(i).get(1));
                count -= qty;
                fertilizerDetails.get(i).set(1,String.valueOf(count));
                return;
            }
        }
    }


    //net Toal eka cal karanna me method eka use kara
    private void calculateNetTotal() {
        double total = 0;

        for (int i = 0; i < tblCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        txtNetTotal.setText(String.valueOf(total));

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnFertilizerOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/fertilizerForm.fxml"));
        Pane registerPane = fxmlLoader.load();
        fertilizerSalesPane.getChildren().clear();
        fertilizerSalesPane.getChildren().add(registerPane);

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

        String orderId = txtOrderId.getText();
        LocalDate date = LocalDate.now();
        String cusId = cmbCustomerId.getValue();
        double total = Double.parseDouble(txtNetTotal.getText());


        List<FertilizeSalesCartTm> tmList = new ArrayList<>();

        for (FertilizeSalesCartTm tm : obList){
            tmList.add(tm);
        }


        PlaceFertilizerOrderDto dto = new PlaceFertilizerOrderDto(
                orderId,
                cusId,
                date,
                total,
                tmList
        );

        try{

            boolean isSuccess = placeFertilizerOrderModel.placeFertilizerOrder(dto);

            if (isSuccess){
                new Alert(Alert.AlertType.CONFIRMATION,"Order Placed Successfully").show();


                tblCart.getItems().clear();
                txtNetTotal.setText("-");
                txtDescription.setText("-");
                txtFieldQty.setText("0");
                //cmbCustomerId.setValue("");
                txtUnitPrice.setText("-");
                txtName.setText("-");
                txtQtyOnHand.setText("-");
                //cmbFertilizer.setValue("");

                generateNextFertilizerOrderId();

            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    @FXML
    void btnReceiptOnAction(ActionEvent event) throws JRException, SQLException {


        JasperDesign jasperDesign =
                JRXmlLoader.load("src/main/resources/report/CD.jrxml");
        JasperReport jasperReport =
                JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("OrderId",txtOrderId.getText());
       data.put("NetTotal",txtNetTotal.getText());





        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        data,
                        DbConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);




    }

    @FXML
    void cmbCustomersOnAction(ActionEvent event) {

        String supId= cmbCustomerId.getValue();

        try {
            SupplierDto dto = supplierModel.searchSupplier(supId);
            txtName.setText(dto.getFirstName());
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    @FXML
    void cmbFertilizerOnAction(ActionEvent event) {
        String fertilizerId = cmbFertilizer.getValue();

        try {
            FertilizerDto dto = fertilizerModel.searchFertilizer(fertilizerId);
            txtDescription.setText(dto.getDescription());
            txtQtyOnHand.setText(String.valueOf(dto.getQty()));
            txtUnitPrice.setText(String.valueOf(dto.getPrice()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void txtFieldQtyOnAction(ActionEvent event) {

    }

}

