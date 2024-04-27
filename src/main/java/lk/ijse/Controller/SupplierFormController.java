package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierFormController {

    @FXML
    private JFXButton btnAddSupplier;

    @FXML
    private JFXButton btnPayments;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBank;

    @FXML
    private TableColumn<?, ?> colBankNo;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colMobileNo;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private AnchorPane supplierPane;

    @FXML
    private TableView<?> tblSupplier;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) throws IOException {

      FXMLLoader loader =  new FXMLLoader(this.getClass().getResource("/view/addSupplierForm.fxml"));
      Parent rootNode =  loader.load();

       Scene scene = new Scene(rootNode);
       Stage stage1 = new Stage();
       stage1.setScene(scene);
       stage1.setTitle("Add Supplier");
       stage1.centerOnScreen();
       stage1.show();






    }

    @FXML
    void btnPaymentsOnAction(ActionEvent event) throws IOException {
      FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/view/paymentForm.fxml"));;
       Pane registerPane = (Pane) fxmlLoader.load();
       supplierPane.getChildren().clear();
       supplierPane.getChildren().add(registerPane);


    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
