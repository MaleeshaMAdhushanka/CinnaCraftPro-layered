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

public class SalesCartTm {

    private String packId;
    ;

    private String type;

    private String size;

    private int qty;

    private double total;

    private MFXButton removeButton;

    {

        ImageView delete = new ImageView(new Image("/assets/icons8delete48.png"));
        delete.setFitHeight(30);
        delete.setPreserveRatio(true);

        removeButton = new MFXButton("", delete);

        removeButton.setCursor(javafx.scene.Cursor.HAND);
        removeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white");

        removeButton.setPrefHeight(30);
        removeButton.setPrefWidth(100);
    }


   // public SalesCartTm() {

   // }
}

