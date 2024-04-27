package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CinnamonBarkFormController {

    @FXML
    private JFXButton btnAddStock;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colStockId;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TableView<?> tableCinnamonBrakStock;

    @FXML
    private Text txtAmount;

    @FXML
    void btnAddStockOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/addCinnamonBarkStockForm.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle("Add Cinnamon Bark Stock");
        stage1.centerOnScreen();
        stage1.show();

    }

    @FXML
    void dpDateOnAction(ActionEvent event) {

    }

}
