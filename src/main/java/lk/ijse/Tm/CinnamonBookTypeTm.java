package lk.ijse.Tm;


import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CinnamonBookTypeTm {


    private  String CinnamonBookTypeID;

    private String type;

    private double amount;
    private MFXButton deleteButton;

    {

        ImageView delete = new ImageView(new Image("/assets/icons8delete48.png"));
        delete.setFitHeight(30);
        delete.setPreserveRatio(true);

        deleteButton = new MFXButton("",delete);

        deleteButton.setCursor(javafx.scene.Cursor.HAND);
        deleteButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white");

        deleteButton.setPrefHeight(30);
        deleteButton.setPrefWidth(100);
    }


    public CinnamonBookTypeTm(String cinnamonBookTypeId, String cinnamonType, double amount) {

    }
}
