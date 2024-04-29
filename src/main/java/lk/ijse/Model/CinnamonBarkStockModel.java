package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.CinnamonBarkStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CinnamonBarkStockModel {


    public String  generateNextCinnamonBarkStockId() throws SQLException {
       Connection connection =  DbConnection.getInstance().getConnection();

       String sql = "SELECT CinnamonStockID FROM Cinnamon_Bark_stock ORDER BY  CinnamonStockID DESC LIMIT 1";
       PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        String currentCinnamonBarkStockID = null;

        if (resultSet.next()) {
          currentCinnamonBarkStockID = resultSet.getString(1);
            return splitCinnamonBarkStockID(currentCinnamonBarkStockID);

        }
        return splitCinnamonBarkStockID(currentCinnamonBarkStockID);

    }

    private String splitCinnamonBarkStockID(String currentCinnamonBarkStockID) {
        if (currentCinnamonBarkStockID != null) {
            String[] split = currentCinnamonBarkStockID.split("CS");
            int selectId = Integer.parseInt(split[1]);
            if (selectId < 9) {
                selectId++;
                return "CS00" + selectId;

            } else if (selectId < 99) {
                selectId++;
                return "CS0" + selectId;
            } else {
                selectId++;
                return "CS" + selectId;
            }

        }
        return "CS001";
    }

    public boolean saveCinnamonBarkStock(CinnamonBarkStockDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Cinnamon_Bark_stock VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCinnamonStockID());
        pstm.setString(2, dto.getSupID());
        pstm.setString(3, dto.getCinnamonBookID());
        pstm.setDouble(4, dto.getAmount());
        pstm.setBoolean(5, dto.isPayed());

        return pstm.executeUpdate() >0;
    }

}
