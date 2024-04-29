package lk.ijse.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FertilizerTm {

   private  String fertilizerID;

    private String brand;

    private String description;

    private String size;

    private double price;

    private int qty;

    public FertilizerTm(String fertilizerID, String brand, String description, String size, double price, double qty){
        this.fertilizerID = fertilizerID;
        this.brand = brand;
        this.description = description;
        this.size = size;
        this.price = price;
        this.qty = (int) qty;


    }


}
