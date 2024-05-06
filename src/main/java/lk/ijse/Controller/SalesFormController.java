package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import lk.ijse.Model.CinnamonTypeModel;
import lk.ijse.Model.CustomerModel;
import lk.ijse.Model.PackagingModel;
import lk.ijse.Model.PlaceCinnamonOrderModel;
import lk.ijse.Tm.SalesCartTm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SalesFormController {

    @FXML
    private JFXButton btnAddCart;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnRecipt;

    @FXML
    private MFXComboBox<?> cmbCinnamonType;

    @FXML
    private MFXFilterComboBox<?> cmbCustomerNum;

    @FXML
    private MFXComboBox<?> cmbPackSize;

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
    private TableView<?> tblCart;

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


    }

    private void generateNextOrderId() {

    }

    private void setCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(formatter);
        txtDate.setText(formattedDate);
    }


    @FXML
    void btnAddCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnReciptOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCinnamonTypeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void cmbPackSizeOnAction(ActionEvent event) {

    }

    @FXML
    void txtFieldQtyOnAction(ActionEvent event) {

    }

}

