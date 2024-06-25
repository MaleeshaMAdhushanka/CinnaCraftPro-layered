package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Dto.FertilizerDto;
import lk.ijse.CinnaCraft.Tm.FertilizeSalesCartTm;
import lk.ijse.CinnaCraft.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface FertilizerDAO  extends CrudDAO<FertilizerDto> {
     boolean updateFertilizer(List<FertilizeSalesCartTm> tmList) throws SQLException;
     boolean updateQty(FertilizeSalesCartTm cartTm) throws SQLException;


    }
