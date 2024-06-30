package lk.ijse.CinnaCraft.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Packaging {

    private String packId;
    private String typeId;
    private String description;

    private int packageCount;

    private double price;


}
