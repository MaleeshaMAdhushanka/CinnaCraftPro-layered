package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Tm.SalesCartTm;
import lk.ijse.CinnaCraft.dao.SuperDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface CinnamonOrderDetailDAO  extends SuperDAO {
     boolean saveOrderDetail(String Cinnamon_order_ID, List<SalesCartTm> tmList) throws SQLException;


     boolean saveOrderDetail(String CinnamonOrderId, SalesCartTm salesCartTm) throws SQLException;
}
