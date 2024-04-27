package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lk.ijse.Dto.UserDto;
import lk.ijse.Model.UserModel;
import lk.ijse.Util.EmailService;


import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountOtpFormController {

    @FXML
    private JFXButton btnCancel;


    @FXML
    private JFXButton btnReSend;

    @FXML
    private JFXButton btnVerify;

    @FXML
    private AnchorPane otpPane;

    @FXML
    private TextField txtFieldOtp;

    private UserModel userModel = new UserModel();


    @FXML
    private Text txtMassage;



    private UserDto userDto;

    private String otp;

    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/createAccountForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        otpPane.getChildren().clear();
        otpPane.getChildren().add(registerPane);

    }

    @FXML
    void btnReSendOnAction(ActionEvent event) {
        txtFieldOtp.getStyleClass().removeAll("mfx-text-field-error");
        txtMassage.setVisible(false);
        sendOtp();

    }

    @FXML
    void btnVerifyOnAction(ActionEvent event) throws IOException {

        String otp = txtFieldOtp.getText();

        if (!otp.equals(this.otp)) {
            txtMassage.setVisible(true);
            txtFieldOtp.requestFocus();
            txtFieldOtp.getStyleClass().add("mfx-text-field-error");
            return;
        }

        try{
            boolean isSaved = userModel.saveUser(userDto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Account Created").show();
                switchToLogin();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void switchToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        otpPane.getChildren().clear();
        otpPane.getChildren().add(registerPane);
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void sendOtp(){
        this.otp= EmailService.sendMail(userDto.getEmail());
    }


    @FXML
    void txtFieldOtpOnAction(ActionEvent event) throws IOException {
        btnVerifyOnAction(event);

    }





}
