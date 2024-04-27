package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FertilizerFromController {

    @FXML
    private JFXButton btnFertilizer;

    @FXML
    private JFXButton btnSales;

    @FXML
    private JFXButton btnaddFertilizer;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colFertilizerId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private AnchorPane fertilizerPane;

    @FXML
    private TableView<?> tblFertilizer;

    @FXML
    void btnAddFertilizerOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/addFertilizerForm.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle("Add Cinnamon Bark Stock");
        stage1.centerOnScreen();
        stage1.show();

    }

    @FXML
    void btnSalesOnAction(ActionEvent event) {

    }

}
