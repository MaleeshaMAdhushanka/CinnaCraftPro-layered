package lk.ijse.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SalaryTm {

    private String salaryId;

    private  int daysCount;

    private  double amount;

    private LocalDate date;
}
