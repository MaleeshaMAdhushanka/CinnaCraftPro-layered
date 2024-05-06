package lk.ijse.Tm;


import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CinnamonBarkStockTm {

    private String cinnamonBarkStockId;

    private  String supId;
    private String SupName;

    private double amount;

    private boolean isPayed;

    private MFXButton updateButton;

    private MFXButton deleteButton;
    {

        ImageView update = new ImageView(new Image("/assets/icons8update48.png"));
        ImageView delete = new ImageView(new Image("/assets/icons8delete48.png"));


        update.setFitHeight(30);
        update.setPreserveRatio(true);


        delete.setFitHeight(30);
        delete.setPreserveRatio(true);


        updateButton = new MFXButton("", update);
        deleteButton = new MFXButton("",delete);

        updateButton.setCursor(javafx.scene.Cursor.HAND);
        deleteButton.setCursor(javafx.scene.Cursor.HAND);

        updateButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
        deleteButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white");

        updateButton.setPrefHeight(30);
        updateButton.setPrefWidth(100);
        deleteButton.setPrefHeight(30);
        deleteButton.setPrefWidth(100);
    }


    public CinnamonBarkStockTm(String cinnamonStockID, String supID, String supName, double amount) {
        this.cinnamonBarkStockId = cinnamonStockID;
        this.supId = supID;
        this.SupName = supName;
        this.amount = amount;
    }
}
