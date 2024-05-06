package lk.ijse.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackingCountAmountDto {
    private String packId;
    private int count;
    private double amount;

}
