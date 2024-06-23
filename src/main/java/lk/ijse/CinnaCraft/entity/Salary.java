package lk.ijse.CinnaCraft.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Salary {

    private String salaryId;

    private String empID;

    private double amount;

    private  int dateCount;

    private LocalDate date;

}
