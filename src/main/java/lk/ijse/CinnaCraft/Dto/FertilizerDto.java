package lk.ijse.CinnaCraft.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FertilizerDto {

    private  String fertilizerID;

    private String brand;

    private String description;

    private String size;

    private double price;

    private int qty;


}
