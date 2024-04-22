package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.Dto.UserDto;
import lk.ijse.Model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class CreateFromController {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private AnchorPane createAccountPane;

    @FXML
    private JFXButton btnCreateAccount;

    @FXML
    private TextField txtEmail;

    @FXML
    private Text txtGreetings;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRep;

    @FXML
    private TextField txtUsername;

    private UserModel userModel = new UserModel();

    @FXML
    void btnCreateAccountOnAction(ActionEvent event) throws SQLException, IOException {

        boolean isLoginValidated = validateLogin();

        if (!isLoginValidated){
            return;
        }

        String email = txtEmail.getText();
        String userName = txtUsername.getText();
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
        txtRep.clear();
        txtUsername.clear();
    }

    private boolean validateLogin() {

        String email = txtEmail.getText();
        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        return true;
    }

    @FXML
    void btncancelOnAction(ActionEvent event) throws IOException {
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

    public void btnLoginOnAction(ActionEvent actionEvent) {
    }
}
