package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CinaCraftDetailModel {

    public double getHourlyRate() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT hourlyRate FROM CINNACRAFT_DETAILS where detailsID=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, "CD001");

        double hourlyRate = 0;

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            hourlyRate = resultSet.getDouble(1);
        }
        return hourlyRate;
    }

    public double getOTRate() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT oT FROM  CINNACRAFT_DETAILS where detailsID=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, "CD001");

        double otRate = 0;

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            otRate = resultSet.getDouble(1);
        }
        return otRate;

    }

    public double getCinnamonBrakPrice() throws  SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT priceOFCinaBrak FROM  CINNACRAFT_DETAILS where detailsID=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, "CD001");

        double cinaBrakPrice = 0;

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            cinaBrakPrice = resultSet.getDouble(1);

        }
        return cinaBrakPrice;
    }
    public boolean updateCinnamonBrakPrice(double price) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE CINNACRAFT_DETAILS SET priceOFCinaBrak=? WHERE detailsID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setDouble(1, price);
        pstm.setString(2, "CD001");

        return pstm.executeUpdate() > 0;
    }

    public boolean updateHourlyRateAndOt( double hourlyRate, double oT) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE CINNACRAFT_DETAILS SET hourlyRate=?, oT=? WHERE  detailsID=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setDouble(1, hourlyRate);
        pstm.setDouble(2, oT);

        pstm.setString(3, "CD001");

        return  pstm.executeUpdate()>0;


    }


}




