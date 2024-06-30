package lk.ijse.CinnaCraft.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {



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

    }
    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashBoardMainForm.fxml"));
        Pane registerPane = (Pane)fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(registerPane);


    }


    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customerForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(registerPane);


    }
    @FXML
    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/employeeForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(registerPane);

    }






    @FXML
    void btnFertilizerOnAction(ActionEvent event) throws IOException {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fertilizerSalesForm.fxml"));
            Pane registerPane = (Pane) fxmlLoader.load();
            mainPane.getChildren().clear();
            mainPane.getChildren().add(registerPane);
        }catch (IOException | RuntimeException e){
            System.out.println("Error loading fertilizer sales From: " + e.getMessage());
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage)btnLogout.getScene().getWindow();
        stage.close();

        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/mainForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle("Login Form");
        stage1.centerOnScreen();
        stage1.show();

    }

    @FXML
    void btnPackagingOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/packagingForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(registerPane);


    }



    @FXML
    void btnSalesOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/salesForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(registerPane);


    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/supplierForm.fxml"));
        System.out.println("Hiiiiiiiii");
        Pane registerPane = (Pane) fxmlLoader.load();

        mainPane.getChildren().clear();
        mainPane.getChildren().add(registerPane);
    }




}
