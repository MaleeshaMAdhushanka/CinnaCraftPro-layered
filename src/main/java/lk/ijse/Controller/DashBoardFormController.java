package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashBoardFormController {

    @FXML
    private JFXButton btnCinnamon;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnFertilizer;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnPackaging;

    @FXML
    private JFXButton btnProcessing;

    @FXML
    private JFXButton btnSales;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private AnchorPane mainPane;


    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashBoardMainForm.fxml"));

        Pane registerPane= (Pane)fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(registerPane);

        //active the dashboard Button
        setButtonActive(btnDashboard);

    }
    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        setButtonActive(btnDashboard);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashBoardMainForm.fxml"));
        Pane registerPane = (Pane)fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(registerPane);


    }



    @FXML
    void btnCinnamonOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {

    }



    @FXML
    void btnEmployeeOnAvtion(ActionEvent event) {

    }

    @FXML
    void btnFertilizerOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnPackagingOnAction(ActionEvent event) {

    }

    @FXML
    void btnProcessingOnAction(ActionEvent event) {

    }

    @FXML
    void btnSalesOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
    }


    private void setButtonActive(JFXButton btnDashboard) {
        btnDashboard.getStyleClass().removeAll("active- button" , "button");
    }

}
