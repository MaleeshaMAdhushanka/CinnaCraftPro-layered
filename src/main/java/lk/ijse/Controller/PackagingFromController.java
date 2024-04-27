package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

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
    private ComboBox<?> cmbCinnamonType;

    @FXML
    private ComboBox<?> cmbDate;

    @FXML
    private ComboBox<?> cmbPackSize;

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
    private TableView<?> tblPackaging;

    @FXML
    private TextField txtFieldCount;

    @FXML
    private Text txtSupplierId;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {

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
    void cmbCinnamonTypeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtFiledCountOnAction(ActionEvent event) {

    }

}
