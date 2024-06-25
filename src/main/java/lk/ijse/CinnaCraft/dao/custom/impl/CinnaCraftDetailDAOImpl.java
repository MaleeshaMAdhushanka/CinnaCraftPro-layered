package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.CinnaCraftDetailDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CinnaCraftDetailDAOImpl implements CinnaCraftDetailDAO {

    @Override
    public double getHourlyRate() throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil( "SELECT hourlyRate FROM CINNACRAFT_DETAILS where detailsID=?", "CD001");

        double hourlyRate = 0;

        if (resultSet.next()) {
            hourlyRate = resultSet.getDouble(1);
        }
        return hourlyRate;
    }
     @Override
    public double getOTRate() throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil( "SELECT oT FROM  CINNACRAFT_DETAILS where detailsID=?", "CD001");
        double otRate = 0;

        if (resultSet.next()) {
            otRate = resultSet.getDouble(1);
        }
        return otRate;

    }
     @Override
    public double getCinnamonBrakPrice() throws  SQLException {

        ResultSet resultSet = SQLUtil.crudUtil("SELECT priceOFCinaBrak FROM  CINNACRAFT_DETAILS where detailsID=?", "CD001");

        double cinaBrakPrice = 0;

        if (resultSet.next()) {
            cinaBrakPrice = resultSet.getDouble(1);

        }
        return cinaBrakPrice;
    }
      @Override
    public boolean updateCinnamonBrakPrice(double price) throws SQLException {


       return SQLUtil.crudUtil("UPDATE CINNACRAFT_DETAILS SET priceOFCinaBrak=? WHERE detailsID=?",price,"CD001");

    }
    @Override
    public boolean updateHourlyRateAndOt( double hourlyRate, double oT) throws SQLException{

        return SQLUtil.crudUtil( "UPDATE CINNACRAFT_DETAILS SET hourlyRate=?, oT=? WHERE  detailsID=?",hourlyRate , oT,"CD001");


    }

}
