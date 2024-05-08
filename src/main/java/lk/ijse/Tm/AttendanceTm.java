package lk.ijse.Tm;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceTm {


    private String empId;

    private String empName;

    private String inTime;

    private String outTime;

    private MFXCheckbox mark;

    private MFXButton removeButton;

    {
        ImageView delete = new ImageView(new Image("/assets/icons8delete48.png"));
        delete.setFitHeight(30);
        delete.setPreserveRatio(true);

        removeButton = new MFXButton("",delete);

        removeButton.setCursor(javafx.scene.Cursor.HAND);
        removeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white");


        removeButton.setPrefHeight(30);
        removeButton.setPrefWidth(100);


        mark = new MFXCheckbox();
        mark.setCursor(javafx.scene.Cursor.HAND);



    }

    public AttendanceTm(String empId, String empName, String inTime, String outTime) {
        this.empId = empId;
        this.empName = empName;
        this.inTime = inTime;
        this.outTime = outTime;
    }


}
