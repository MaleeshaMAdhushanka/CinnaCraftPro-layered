package lk.ijse.CinnaCraft.Controller;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.CinnaCraft.Dto.PackagingDto;
import lk.ijse.CinnaCraft.Model.CinnamonTypeModel;
import lk.ijse.CinnaCraft.Model.PackagingModel;
import lk.ijse.CinnaCraft.Tm.PackagingTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PackagingFromController {



        @FXML
        private JFXButton btnSizeEdit;

        @FXML
        private JFXButton btnUpdatePackage;

        @FXML
        private TableColumn<?, ?> colPackId;

        @FXML
        private TableColumn<?, ?> colPackageCount;

        @FXML
        private TableColumn<?, ?> colPrice;

        @FXML
        private TableColumn<?, ?> colSize;

        @FXML
        private TableColumn<?, ?> colType;

        @FXML
        private Text lblId;

        @FXML
        private Text lblType;

        @FXML
        private Text lblSize;

        @FXML
        private TableView<PackagingTm> tblPackaging;

        @FXML
        private MFXTextField txtFieldCount;



        PackagingModel packagingModel = new PackagingModel();

    CinnamonTypeModel cinnamonTypeModel = new CinnamonTypeModel();



    public void initialize() throws SQLException {
        setCellValueFactory();
        loadPackingDetails();
        setListener();

    }

    private void setListener() {
        tblPackaging.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    PackagingDto dto = new PackagingDto(newValue.getPackageID(), newValue.getType(), newValue.getSize(),newValue.getPackageCount(),newValue.getPrice());
                    setFields(dto);
                });


    }

    private void setFields(PackagingDto dto) {
        lblId.setText(dto.getPackId());
        lblType.setText(dto.getTypeId());
        lblSize.setText(dto.getDescription());
        txtFieldCount.setText(String.valueOf(dto.getPackageCount()));


    }


    private void loadPackingDetails() throws SQLException {

        ObservableList<PackagingTm> obList = FXCollections.observableArrayList();


        List<PackagingDto> dtoList = packagingModel.getAllPackaging();

        for (PackagingDto dto : dtoList){
            obList.add(new PackagingTm(
                    dto.getPackId(),
                    cinnamonTypeModel.getCinnamonType(dto.getTypeId()),
                    dto.getDescription(),
                     dto.getPrice(),
                    dto.getPackageCount()
            ));
        }
        tblPackaging.setItems(obList);



    }

    private void setCellValueFactory() {
        colPackId.setCellValueFactory(new PropertyValueFactory<>("PackageID"));
        colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("Size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPackageCount.setCellValueFactory(new PropertyValueFactory<>("PackageCount"));

    }


    @FXML
    void btnSizeEditOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/packagingDetailsForm.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.setTitle("Packaging Details");
        stage1.centerOnScreen();
        stage1.show();


    }

    @FXML
    void btnUpdatePackageOnAction(ActionEvent event) throws SQLException {
    //    colPackId.getText();


        String packId = lblId.getText();
        String count =  txtFieldCount.getText();

       boolean isUpdated = packagingModel.updatePackageCount(packId, Integer.parseInt(count));
         if(isUpdated){
             new Alert(Alert.AlertType.CONFIRMATION, "packaging updaated").show();
             loadPackingDetails();
         }else {
             new Alert(Alert.AlertType.ERROR,"error").show();
         }
}














    @FXML
    void txtFieldCountOnAction(ActionEvent event) {







    }

}
