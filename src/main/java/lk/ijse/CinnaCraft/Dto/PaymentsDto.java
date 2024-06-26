package lk.ijse.CinnaCraft.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentsDto {

    private String paymentId;
    private String supId;
    private double amount;
    private double payment;
    private LocalDate date;

}
