package lk.ijse.CinnaCraft.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PackagingDto {

    private String packId;
    private String typeId;
    private String description;

    private int packageCount;

    private double price;


}
