package lk.ijse.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AttendanceDto {

     private String attendanceId;

     private LocalDate date;

     private String empId;

     private LocalTime inTime;

     private LocalTime outTime;

     private boolean isPayed;

}
