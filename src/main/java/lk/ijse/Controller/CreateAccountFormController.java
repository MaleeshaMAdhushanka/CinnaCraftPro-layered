package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.Dto.UserDto;
import lk.ijse.Model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountFormController {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCreateAccount;

    @FXML
    private AnchorPane createAccountPane;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtReEnterPassword;

    @FXML
    private TextField txtUserName;

    private UserModel userModel = new UserModel();

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage)btnCancel.getScene().getWindow();
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
    void btnCreateAccountOnAction(ActionEvent event)  throws SQLException, IOException {
        boolean isLoginValidated = validateLogin();
        if (!isLoginValidated){
            return;
        }
        String email = txtEmail.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        UserDto dto = new UserDto(userName,password,email);

        //TO switch the UI

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/createAccountOtpForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        CreateAccountOtpFormController controller = fxmlLoader.getController();
        controller.setUserDto(dto);
        createAccountPane.getChildren().clear();
        createAccountPane.getChildren().add(registerPane);
        controller.sendOtp();

    }
    private void clearFields() {

        txtEmail.clear();
        txtPassword.clear();
        txtReEnterPassword.clear();
        txtUserName.clear();
    }

    private boolean validateLogin() {

        String email = txtEmail.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        return true;
    }



    @FXML
    void txtEmailOnAction(ActionEvent event) throws IOException {
       // btnCreateAccount(event);

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtReEnterPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

}
