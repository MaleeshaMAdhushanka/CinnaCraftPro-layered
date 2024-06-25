package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.dao.SuperDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CinnaCraftDetailDAO extends SuperDAO {

     double getHourlyRate() throws SQLException;

     double getOTRate() throws SQLException ;

     double getCinnamonBrakPrice() throws  SQLException;
     boolean updateCinnamonBrakPrice(double price) throws SQLException;

     boolean updateHourlyRateAndOt( double hourlyRate, double oT) throws SQLException;
}
