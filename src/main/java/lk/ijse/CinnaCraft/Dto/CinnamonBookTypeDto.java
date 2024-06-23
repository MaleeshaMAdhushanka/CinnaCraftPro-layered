package lk.ijse.CinnaCraft.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CinnamonBookTypeDto {
     private String CinnamonBookTypeId;

     private LocalDate date;

     private String typeId;

     private double amount;

     private boolean isConfirmed;









}
