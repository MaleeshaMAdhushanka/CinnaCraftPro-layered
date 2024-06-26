package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Tm.SalesCartTm;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.CinnamonOrderDetailDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CinnamonOrderDetailDAOImpl implements CinnamonOrderDetailDAO {

    @Override
    public boolean saveOrderDetail(String Cinnamon_order_ID, List<SalesCartTm> tmList) throws SQLException {

        for (SalesCartTm salesCartTm : tmList){
            if (!saveOrderDetail(Cinnamon_order_ID,salesCartTm)) {
                return false;
            }
        }
        return true;

    }

    @Override
       public boolean saveOrderDetail(String CinnamonOrderId, SalesCartTm salesCartTm) throws SQLException {

       return SQLUtil.crudUtil( "INSERT INTO Cinnamon_order_details VALUES(?,?,?,?)",

                  CinnamonOrderId,
                 salesCartTm.getPackId(),
                 salesCartTm.getQty(),
                  salesCartTm.getTotal());

    }
}
