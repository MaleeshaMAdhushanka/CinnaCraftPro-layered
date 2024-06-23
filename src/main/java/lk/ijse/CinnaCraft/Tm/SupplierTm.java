package lk.ijse.CinnaCraft.Tm;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierTm {

    private String supId;
    private String firstName;
    private  String lastName;
    private  String address;
    private String bank;
    private String bankNo;
    private String mobileNo;

}
