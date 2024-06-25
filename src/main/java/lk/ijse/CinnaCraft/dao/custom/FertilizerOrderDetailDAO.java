package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Tm.FertilizeSalesCartTm;
import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.dao.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface FertilizerOrderDetailDAO extends SuperDAO {

      boolean saveFertilizerOrderDetail(String fertilizerOrderId, List<FertilizeSalesCartTm> tmList) throws SQLException;

}
