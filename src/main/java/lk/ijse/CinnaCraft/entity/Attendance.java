package lk.ijse.CinnaCraft.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Attendance {

     private String attendanceId;

     private LocalDate date;

     private String empId;

     private LocalTime inTime;

     private LocalTime outTime;

     private boolean isPayed;

}
