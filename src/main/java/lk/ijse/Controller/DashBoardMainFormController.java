package lk.ijse.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.Dto.CinnamonTypeDto;
import lk.ijse.Model.CinnamonBookModel;
import lk.ijse.Model.CinnamonTypeModel;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

    CinnamonTypeModel cinnamonTypeModel = new CinnamonTypeModel();


    public void initialize() {
        updateDate();
        updateTime();
        setUpPieChart();
    }



    private void setUserName() {
    }

    private void setUpAreaChart() {
    }

    private void setUpPieChart() {


            int blackTea =0;
            int greenTea =0;
            int oolongTea =0;
            try {

                List<CinnamonTypeDto> dto = cinnamonTypeModel.getAllCinnamonType();

                blackTea= (int)dto.get(0).getAmount();
                greenTea= (int) dto.get(1).getAmount();
                oolongTea= (int) dto.get(2).getAmount();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Black ", blackTea),
                    new PieChart.Data("Green ", greenTea),
                    new PieChart.Data("Oolong ", oolongTea));


            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(),data.pieValueProperty()
                            )
                    )
            );

            chartProduction.getData().addAll(pieChartData);


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


