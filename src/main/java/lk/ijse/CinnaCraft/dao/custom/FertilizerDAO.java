package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Tm.FertilizeSalesCartTm;
import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.entity.Fertilizer;

import java.sql.SQLException;
import java.util.List;

public interface FertilizerDAO  extends CrudDAO<Fertilizer> {
     boolean updateFertilizer(List<FertilizeSalesCartTm> tmList) throws SQLException;
     boolean updateQty(FertilizeSalesCartTm cartTm) throws SQLException;


    }
