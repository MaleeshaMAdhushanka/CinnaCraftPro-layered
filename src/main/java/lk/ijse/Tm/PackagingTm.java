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

public class PackagingTm {
    private String  packagingDetailsId;

    private String type;

    private  String size;

    private int count;

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

    public PackagingTm(String packagingDetailsId, String type,String size, int count) {
        this.packagingDetailsId = packagingDetailsId;

        this.type = type;

        this.size = size;
        this.count = count;

    }


}
