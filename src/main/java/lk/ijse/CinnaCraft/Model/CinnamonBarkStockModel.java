package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Dto.CinnamonBarkStock;
import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinnamonBarkStockModel {


    public String generateNextCinnamonBarkStockId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

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

    public boolean saveCinnamonBarkStock(CinnamonBarkStock dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Cinnamon_Bark_stock VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCinnamonStockID());
        pstm.setString(2, dto.getSupID());
        pstm.setString(3, dto.getCinnamonBookID());
        pstm.setDouble(4, dto.getAmount());
        pstm.setBoolean(5, dto.isPayed());

        return pstm.executeUpdate() > 0;
    }

    public double getTotalAmount(String CinnamonBookID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT SUM(amount) FROM Cinnamon_Bark_stock WHERE CinnamonBookID=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, CinnamonBookID);

        ResultSet resultSet = pstm.executeQuery();

        resultSet.next();

        return resultSet.getDouble(1);
    }

    public List<CinnamonBarkStock> getAllStockDetails(String dateBookId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Cinnamon_Bark_stock WHERE CinnamonBookID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dateBookId);

        List<CinnamonBarkStock> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String CinnamonStockID = resultSet.getString(1);
            String SupID = resultSet.getString(2);
            String CinnamonBookID = resultSet.getString(3);
            double amount = resultSet.getDouble(4);
            boolean isPayed = resultSet.getBoolean(5);

            CinnamonBarkStock dto = new CinnamonBarkStock(CinnamonStockID, SupID, CinnamonBookID, amount, isPayed);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public CinnamonBarkStock searchCinnamonBarkStock(String text) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Cinnamon_Bark_stock WHERE CinnamonStockID=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, text);

        ResultSet resultSet = pstm.executeQuery();

        CinnamonBarkStock dto = null;

        if (resultSet.next()) {
            String CinnamonStockID = resultSet.getString(1);
            String SupID = resultSet.getString(2);
            String CinnamonBookID = resultSet.getString(3);
            double amount = resultSet.getDouble(4);
            boolean isPayed = resultSet.getBoolean(5);

            dto = new CinnamonBarkStock(CinnamonStockID, SupID, CinnamonBookID, amount, isPayed);
        }

        return dto;
    }

    public boolean deleteCinnamonBarkStock(String CinnamonStockID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Cinnamon_Bark_stock WHERE CinnamonStockID=? ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, CinnamonStockID);

        return pstm.executeUpdate() > 0;

    }

    public boolean updateCinnamonBarkStock(CinnamonBarkStock cinnamonBarkStockDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Cinnamon_Bark_stock SET SupID=?, CinnamonBookID=?, amount=? WHERE CinnamonStockID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, cinnamonBarkStockDto.getSupID());
        pstm.setString(2, cinnamonBarkStockDto.getCinnamonBookID());
        pstm.setDouble(3, cinnamonBarkStockDto.getAmount());
        pstm.setString(4, cinnamonBarkStockDto.getCinnamonStockID());

        return pstm.executeUpdate() > 0;

    }

    public double getTotalCinnamonBarkSuppliedAmount(String SupID) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT SUM(amount) From Cinnamon_Bark_stock WHERE  SupID=? and isPayed =false";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, SupID);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0;

    }

    public boolean updatePayedStatus(String SupID) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Cinnamon_Bark_stock SET isPayed=true WHERE SupID=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, SupID);

        return pstm.executeUpdate() > 0;
    }
}
