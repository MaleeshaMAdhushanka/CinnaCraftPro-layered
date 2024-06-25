package lk.ijse.CinnaCraft.dao.custom.impl;

import com.mysql.cj.protocol.ResultsetRow;
import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CinnamonBarkStockDto;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.CinnamonBarkStockDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinnamonBarkStockDAOImpl implements CinnamonBarkStockDAO {


    @Override
    public ArrayList<CinnamonBarkStockDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(CinnamonBarkStockDto dto) throws SQLException {

        return SQLUtil.crudUtil("INSERT INTO Cinnamon_Bark_stock VALUES(?, ?, ?, ?, ?)",
                dto.getCinnamonStockID(),
                dto.getSupID(),
                dto.getCinnamonBookID(),
                dto.getAmount(),
                dto.isPayed());

    }
    @Override
    public boolean update(CinnamonBarkStockDto dto) throws SQLException {

        return SQLUtil.crudUtil( "UPDATE Cinnamon_Bark_stock SET SupID=?, CinnamonBookID=?, amount=? WHERE CinnamonStockID=?",
                dto.getSupID(),
                dto.getCinnamonBookID(),
                dto.getAmount(),
                dto.getCinnamonStockID());

    }


    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }
    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.crudUtil( "DELETE FROM Cinnamon_Bark_stock WHERE CinnamonStockID=? ", id);

    }

    public String generateId() throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil("SELECT CinnamonStockID FROM Cinnamon_Bark_stock ORDER BY  CinnamonStockID DESC LIMIT 1");


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


    @Override
    public CinnamonBarkStockDto search(String id) throws SQLException {
        ResultSet resultSet =SQLUtil.crudUtil("SELECT * FROM Cinnamon_Bark_stock WHERE CinnamonStockID=?", id),;

        if (resultSet.next()) {
            return new CinnamonBarkStockDto(
                    resultSet.getString("CinnamonStockID"),
                    resultSet.getString("SupID"),
                    resultSet.getString("CinnamonBookID"),
                    resultSet.getDouble("amount"),
                    resultSet.getBoolean("isPayed")
            );

        }
        return null;
    }

    public boolean updatePayedStatus(String SupID) throws SQLException {

        return SQLUtil.crudUtil("UPDATE Cinnamon_Bark_stock SET isPayed=true WHERE SupID=?", SupID);


    }

    public double getTotalCinnamonBarkSuppliedAmount(String SupID) throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil( "SELECT SUM(amount) From Cinnamon_Bark_stock WHERE  SupID=? and isPayed =false", SupID);

        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0;

    }

    public List<CinnamonBarkStockDto> getAllStockDetails(String dateBookId) throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM Cinnamon_Bark_stock WHERE CinnamonBookID=?", dateBookId);


        List<CinnamonBarkStockDto> cinnamonBarkStocks = new ArrayList<>();

        while (resultSet.next()) {
            cinnamonBarkStocks.add(new CinnamonBarkStockDto(
                    resultSet.getString("CinnamonStockID"),
                    resultSet.getString("SupID"),
                    resultSet.getString("amount"),
                    resultSet.getDouble("CinnamonBookID"),
                    resultSet.getBoolean("isPayed")
            ));
        }


        return cinnamonBarkStocks;

    }

    public double getTotalAmount(String CinnamonBookID) throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil( "SELECT SUM(amount) FROM Cinnamon_Bark_stock WHERE CinnamonBookID=?", CinnamonBookID);

        resultSet.next();

        return resultSet.getDouble(1);
    }


}
