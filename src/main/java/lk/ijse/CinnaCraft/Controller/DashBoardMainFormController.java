package lk.ijse.CinnaCraft.Controller;

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
import lk.ijse.CinnaCraft.Dto.CinnamonTypeDto;
import lk.ijse.CinnaCraft.Model.CinnamonBookModel;
import lk.ijse.CinnaCraft.Model.CinnamonTypeModel;
import lk.ijse.CinnaCraft.bo.BOFactory;
import lk.ijse.CinnaCraft.bo.custom.CinnamonBookBo;
import lk.ijse.CinnaCraft.bo.custom.CinnamonTypeBO;

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

    private final CinnamonBookBo cinnamonBookBo = (CinnamonBookBo) BOFactory.getInstance().getBO(BOFactory.BoTypes.CINNAMON_BOOK);

    private final CinnamonTypeBO cinnamonTypeBO = (CinnamonTypeBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.CINNAMON_TYPE);


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


            int Cassia =0;
            int verum =0;
            int burmannii =0;
            try {

                List<CinnamonTypeDto> dto = cinnamonTypeBO.getAllCinnamonType();

                Cassia= (int)dto.get(0).getAmount();
                verum= (int) dto.get(1).getAmount();
                burmannii= (int) dto.get(2).getAmount();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(" Cassia", Cassia),
                    new PieChart.Data("verum ", verum),
                    new PieChart.Data("burmannii ", burmannii));


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


