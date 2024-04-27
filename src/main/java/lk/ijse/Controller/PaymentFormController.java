
package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class PaymentFormController {

    @FXML
    private JFXButton btnPay;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<?> cmbSupId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colPaymentsId;

    @FXML
    private AnchorPane payementsPane;

    @FXML
    private TableView<?> tblPayments;

    @FXML
    private Text txtAmount;

    @FXML
    private TextField txtFiledCinnamonBarkPrice;

    @FXML
    private Text txtName;

    @FXML
    private Text txtPayment;

    @FXML
    private Text txtPaymentId;

    @FXML
    private Text txtPaymentId1;

    @FXML
    void btnPayOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplieOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/supplierForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        payementsPane.getChildren().clear();
        payementsPane.getChildren().add(registerPane);


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSupIdOnAction(ActionEvent event) {

    }

}

