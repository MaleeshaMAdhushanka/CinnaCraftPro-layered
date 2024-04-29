package lk.ijse.Model;

import lk.ijse.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CinnamonBookModel {

    public boolean updateCinnamonBookAmount(String CinnamonBookId, double amount) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE cinnamon_book SET dailyAmount = ? WHERE CinnamonBookId =?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setDouble(1, amount);
        pstm.setString(2, CinnamonBookId);

         return  pstm.executeUpdate() > 0;


    }
}
