package lk.ijse.Dto;


import lk.ijse.Tm.SalesCartTm;
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

    private List<SalesCartTm> TmList = new ArrayList<>();
}
