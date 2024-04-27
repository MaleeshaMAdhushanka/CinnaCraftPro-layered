package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.Model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Objects;

public class LoginFormController {

    @FXML
    private Button btnCreateAccount;

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane loginUiPane;

    @FXML
    private Text txtForgotPassword;

    @FXML
    private Text txtGreetings;

    @FXML
    private Text txtMassage;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    private final UserModel userModel = new UserModel();
    public  void initialize(){
        setGreetings();
    }

    private void setGreetings() {
        LocalTime currentTime = LocalTime.now();
        String greeting = (currentTime.getHour() < 12) ? "Good Morning" : "Good Evening";
        txtGreetings.setText(greeting);
    }

    @FXML
    void btnCreateAccountOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/createAccountForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        loginUiPane.getChildren().clear();
        loginUiPane.getChildren().add(registerPane);
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException{

        boolean isLoginValidated = validateLogin();

        if (!isLoginValidated){
            return;
        }


        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();

        //close kara current window eka
        Stage loginStage = (Stage) btnLogin.getScene().getWindow();
        loginStage.close();

    }

    private boolean validateLogin() {

        String userName = txtUsername.getText();

        String password = txtPassword.getText();

       //validate karanna userNme eka
        boolean isUsernameExist = false;
        
        try {
            isUsernameExist = userModel.searchUser(userName);
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        if (!isUsernameExist){

            new Alert(Alert.AlertType.ERROR," User doesnt exit.").show();
            return false;
        }




        //To validate both user name and password
        boolean isUserExist = false;
        try {
            isUserExist = userModel.searchUsernameAndPassword(userName,password);
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


        if (!isUserExist) {
            new Alert(Alert.AlertType.ERROR," Invalid Password ").show();
            return false;
        }



        UserModel.userName = userName;
        

        return true;
    }

    @FXML
    void txtForgotPasswordOnAction(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/forgotPasswordForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        loginUiPane.getChildren().clear();
        loginUiPane.getChildren().add(registerPane);

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) throws IOException {
        btnLoginOnAction(event);

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) throws IOException{
        btnLoginOnAction(event);

    }

}
