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
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerFormController {

    @FXML
    private JFXButton btnAddCustomer;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colMobileNo;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/addCustomerForm.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle("Add Cinnamon Bark Stock");
        stage1.centerOnScreen();
        stage1.show();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
