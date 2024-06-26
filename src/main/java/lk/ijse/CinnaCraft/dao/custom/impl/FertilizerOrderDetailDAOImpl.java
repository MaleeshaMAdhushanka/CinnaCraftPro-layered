package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Tm.FertilizeSalesCartTm;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.FertilizerOrderDetailDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FertilizerOrderDetailDAOImpl implements FertilizerOrderDetailDAO {

    @Override
    public boolean saveFertilizerOrderDetail(String fertilizerOrderId, List<FertilizeSalesCartTm> tmList) throws SQLException {
        for (FertilizeSalesCartTm cartTm: tmList){
            if (!saveFertilizerOrderDetails(fertilizerOrderId,cartTm)){
                return false;
            }
        }
        return true;
    }


    private boolean saveFertilizerOrderDetails(String fertilizerOrderId, FertilizeSalesCartTm cartTm) throws SQLException {


        return SQLUtil.crudUtil( "INSERT INTO fertilizer_order_details VALUES (?,?,?,?)",

                fertilizerOrderId,
                cartTm.getFertilizerId(),
                cartTm.getQty(),
                cartTm.getTotal());


    }
}
