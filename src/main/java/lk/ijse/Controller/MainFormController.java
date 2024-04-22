package lk.ijse.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainFormController {

    @FXML
    private AnchorPane mainUiPane;

     public void initialize() throws IOException {
     FXMLLoader fxmlLoader=   new FXMLLoader(getClass().getResource("/view/loginForm.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        mainUiPane.getChildren().clear();
        mainUiPane.getChildren().add(registerPane);
      }
}
