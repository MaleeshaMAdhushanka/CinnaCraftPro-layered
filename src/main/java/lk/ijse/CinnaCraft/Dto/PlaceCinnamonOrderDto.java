package lk.ijse.CinnaCraft.Dto;


import lk.ijse.CinnaCraft.Tm.SalesCartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PlaceCinnamonOrderDto {
    private String Cinnamon_order_ID;

    private String CusID;

    private LocalDate date;

    private double total;

    private List<SalesCartTm> TmList = new ArrayList<>();
}
