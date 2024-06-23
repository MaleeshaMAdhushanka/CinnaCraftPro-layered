package lk.ijse.CinnaCraft.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.CinnaCraft.Dto.UserDto;
import lk.ijse.CinnaCraft.Model.UserModel;

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


    @FXML
    private Text txtMassage;

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


        boolean isEmailValidated = email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");


        if (!isEmailValidated){
            txtEmail.requestFocus();
            changeTextFieldStyle((MFXTextField) txtEmail);
            return false;
        }
        boolean isEmailExist = false;

        try {
            isEmailExist = userModel.searchEmail(email);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (isEmailExist){
            txtMassage.setText("Email Already Exist");
            txtEmail.getStyleClass().add("mfx-text-field-error");
            txtMassage.setVisible(true);
            return false;
        }

        txtEmail.getStyleClass().removeAll("mfx-text-field-error");
        txtMassage.setVisible(false);




        String UserName = txtUserName.getText();
        boolean isUserNameValidated = UserName.matches("[A-Za-z]{3,}");

        if (!isUserNameValidated){
            txtUserName.requestFocus();
            changeTextFieldStyle((MFXTextField) txtUserName);
            return false;
        }

        //Check Username Exist
        boolean isUsernameExist = false;

        try {
            isUsernameExist = userModel.searchUser(userName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (isUsernameExist){
            txtMassage.requestFocus();
            txtMassage.setText("Username Already Exist");
            txtMassage.setVisible(true);
            return false;
        }



        String Password = txtPassword.getText();
        boolean isPasswordValidated = Password.matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}");

        if (!isPasswordValidated){
            txtPassword.requestFocus();
            changeTextFieldStyle((MFXTextField) txtPassword);
            return false;
        }

        String reEnterPassword = txtReEnterPassword.getText();
        boolean isReEnterPasswordValidated = reEnterPassword.matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}");

        if (!isReEnterPasswordValidated){
            txtReEnterPassword.requestFocus();
            changeTextFieldStyle((MFXTextField) txtReEnterPassword);
            return false;
        }



        //Check Passwords

        if (!password.equals(reEnterPassword)){
            txtMassage.setText("Password doesnt Match");
            return false;
        }


        changeTextFieldStyle();

        return true;
    }

    private void changeTextFieldStyle() {

        txtMassage.setVisible(false);

        txtEmail.getStyleClass().removeAll("mfx-text-field-error");
        txtUserName.getStyleClass().removeAll("mfx-text-field-error");
        txtPassword.getStyleClass().removeAll("mfx-text-field-error");
        txtReEnterPassword.getStyleClass().removeAll("mfx-text-field-error");


    }

    private void changeTextFieldStyle(MFXTextField txtField) {

        //Disable the visibility of the error message
        txtMassage.setVisible(false);

        txtEmail.getStyleClass().removeAll("mfx-text-field-error");
        txtUserName.getStyleClass().removeAll("mfx-text-field-error");
        txtPassword.getStyleClass().removeAll("mfx-text-field-error");
        txtReEnterPassword.getStyleClass().removeAll("mfx-text-field-error");

        txtField.getStyleClass().add("mfx-text-field-error");

        //Enable the visibility of the error message
        if (txtField==txtEmail){
            txtMassage.setText("Invalid Email");
            txtMassage.setVisible(true);
        }
        else if (txtField==txtUserName){
            txtMassage.setText("Invalid Username");
            txtMassage.setVisible(true);
        }
        else if (txtField==txtPassword){
            txtMassage.setText("Enter a valid Password with at least 4 characters and a number");
            txtMassage.setVisible(true);
        }
        else if (txtField==txtReEnterPassword){
            txtMassage.setText("Invalid ReEnterPassword");
            txtMassage.setVisible(true);
        }


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
