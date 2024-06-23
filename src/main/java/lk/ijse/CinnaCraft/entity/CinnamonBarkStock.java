package lk.ijse.CinnaCraft.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CinnamonBarkStock {
    private String CinnamonStockID;

    private String SupID;

    private String CinnamonBookID;

    private double amount;

    private boolean isPayed;




}
