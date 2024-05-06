package lk.ijse.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.Model.CinnamonBookModel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashBoardMainFormController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Text txtAttendance;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtCinnamonStock;

    @FXML
    private Text txtOrders;

    @FXML
    private Text txtTime;

    @FXML
    private Text txtUser;

    @FXML
    private AreaChart<?, ?> chartCinnamonStore;

    @FXML
    private PieChart chartProduction;

    private final CinnamonBookModel cinnamonBookModel = new CinnamonBookModel();



    public void initialize() {
        updateDate();
        updateTime();
    }



    private void setUserName() {
    }

    private void setUpAreaChart() {
    }

    private void setUpPieChart() {
    }

    private void setDashBoardTextFields() {
    }

    private void createCinnamonBookRecord() {
    }

    private void updateDate() {
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            txtTime.setText(timeNow());

        }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private String timeNow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss a");
        return dateFormat.format(new Date()) ;
    }


    private void updateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(formatter);
        txtDate.setText(formattedDate);
    }


}


