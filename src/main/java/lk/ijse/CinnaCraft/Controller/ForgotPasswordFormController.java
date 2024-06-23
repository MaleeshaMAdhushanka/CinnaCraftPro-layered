package lk.ijse.CinnaCraft.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lk.ijse.CinnaCraft.Model.UserModel;

import java.io.IOException;


public class ForgotPasswordFormController {



    @FXML
    private JFXButton btnCanecel;

    @FXML
    private JFXButton btnReset;

    @FXML
    private AnchorPane forgotPasswordPane;

    @FXML
    private TextField txtEmail;

    @FXML
    private Text txtMessage;

    @FXML
    private TextField txtUserName;

    private UserModel model = new UserModel();


    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginForm.fxml"));
      Pane registerPane = fxmlLoader.load();
      forgotPasswordPane.getChildren().clear();
      forgotPasswordPane.getChildren().add(registerPane);
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        
    }
    /*

    @FXML
    void btnResetOnAction(ActionEvent event) {
       boolean isDetailsVerified = validateDetails();

        if (!isDetailsVerified) {
            return;

        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/passwordResetOtpForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        PasswordResetOtpFormController controller= fxmlLoader.getController();
        controller.setUserNameAndEmail(txtUserName.getText(), txtEmail.getText());
        forgotPasswordPane.getChildren().clear();
        forgotPasswordPane.getChildren().add(registerPane);
        controller.sendOtp();
    }

     *



    private boolean validateDetails() {
    }



    @FXML
    void txtEmailOnAction(ActionEvent event) {
        btnResetOnAction(event);

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
       btnResetOnAction(event);
    }

     */

}

