package lk.ijse.CinnaCraft.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CinnamonBarkStockDto {
    private String CinnamonStockID;

    private String SupID;

    private String CinnamonBookID;

    private double amount;

    private boolean isPayed;




}
