package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.dao.SuperDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface CinnamonOrderDAO  extends SuperDAO {
     String generateNextOrderId()throws SQLException;
     boolean saveOrder(String Cinnamon_order_ID, String CusID, LocalDate date) throws SQLException ;
     int getOrderCount(String date) throws SQLException ;


}
