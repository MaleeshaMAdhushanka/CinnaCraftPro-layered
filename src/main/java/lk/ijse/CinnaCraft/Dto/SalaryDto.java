package lk.ijse.CinnaCraft.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SalaryDto {

    private String salaryId;

    private String empID;

    private double amount;

    private  int dateCount;

    private LocalDate date;

}
