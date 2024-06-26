package lk.ijse.CinnaCraft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FertilizerOrder {
    private String fertilizerOrderId;
    private String customerId;
    LocalDate date;
}

