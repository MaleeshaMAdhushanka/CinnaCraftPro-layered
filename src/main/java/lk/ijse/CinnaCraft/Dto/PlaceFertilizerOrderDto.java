package lk.ijse.CinnaCraft.Dto;

import lk.ijse.CinnaCraft.Tm.FertilizeSalesCartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceFertilizerOrderDto {

    private String fertilizerOrderId;

    private String customerId;

    LocalDate date;

    private double total;

    private List<FertilizeSalesCartTm> tmList = new ArrayList<>();

}
