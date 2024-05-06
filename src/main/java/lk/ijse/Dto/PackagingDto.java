package lk.ijse.Dto;


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

    private int PackageCount;

    private double price;


}
