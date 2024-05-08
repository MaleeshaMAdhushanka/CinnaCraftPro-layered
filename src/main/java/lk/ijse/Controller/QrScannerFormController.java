
package lk.ijse.Controller;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;

public class QrScannerFormController {

    private Webcam webcam;
    private volatile boolean isThreadRunning = true;

    @FXML
    private ImageView imgView;

    AttendanceFormController attendanceFormController;

    @FXML
    void initialize() {
        webcam = Webcam.getDefault();
        webcam.open();

        // Start a thread to continuously grab frames from the webcam and update the ImageView
        new Thread(this::readQRCode).start();
    }

    private void readQRCode() {
        while (isThreadRunning) {
            try {
                BufferedImage image = webcam.getImage();
                Image fxImage = SwingFXUtils.toFXImage(image, null);

                // Update the ImageView on the JavaFX thread
                Platform.runLater(() -> imgView.setImage(fxImage));

                Result result = null;
                BufferedImage timage = null;

                if (webcam.isOpen()) {
                    if ((timage = webcam.getImage()) == null) {
                        continue;
                    }
                }

                LuminanceSource source = new BufferedImageLuminanceSource(timage);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    // QR code not found in the current frame
                }

                if (result != null) {
                    // Close the webcam, stop the thread, and close the application
                    stopWebcamAndClose();
                    attendanceFormController.qrScanner(result.getText());

                }

                Thread.sleep(33); // Capture frames at 30 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @FXML
    void btnCloseOnAction(ActionEvent event) {
        stopWebcamAndClose();
    }

    public void setAttendanceFormController(AttendanceFormController attendanceFormController) {
        this.attendanceFormController=attendanceFormController;
    }

    private void stopWebcamAndClose() {
        isThreadRunning = false;
        webcam.close();
        Platform.runLater(() -> {
            Stage window= (Stage) imgView.getScene().getWindow();
            window.close();
        });
    }
}

