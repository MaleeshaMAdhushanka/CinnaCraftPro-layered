package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.CinnamonBookDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinnamonBookModel {



    public boolean updateCinnamonBookAmount(String CinnamonBookId, double amount) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Cinnamon_Book SET dailyAmount = ? WHERE CinnamonBookId =?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setDouble(1, amount);
        pstm.setString(2, CinnamonBookId);

         return  pstm.executeUpdate() > 0;


    }

    public String getCinnamonBookId(String date) throws SQLException{
       Connection connection =  DbConnection.getInstance().getConnection();

       String sql = "SELECT CinnamonBookId FROM Cinnamon_Book WHERE date=?";

       PreparedStatement pstm = connection.prepareStatement(sql);

       pstm.setString(1, date);

        ResultSet resultSet = pstm.executeQuery();

        resultSet.next();

         return  resultSet.getString(1);

    }

    public String getCinnamonBookDate(String CinnamonBookId) throws  SQLException{

       Connection connection =  DbConnection.getInstance().getConnection();

       String sql = "SELECT date FROM Cinnamon_Book WHERE CinnamonBookId=?";

       PreparedStatement pstm = connection.prepareStatement(sql);

       pstm.setString(1, CinnamonBookId);
       ResultSet resultSet = pstm.executeQuery();

       resultSet.next();

        return  resultSet.getString(1);
    }

    public boolean searchDate(String date ) throws SQLException{
       Connection connection =  DbConnection.getInstance().getConnection();

       String sql = "SELECT * FROM  Cinnamon_Book WHERE date=?";

       PreparedStatement  pstm  = connection.prepareStatement(sql);

       pstm.setString(1, date);

       ResultSet resultSet = pstm.executeQuery();

        return  resultSet.next();


    }
    public  boolean createCinnamonBookRecord(String date) throws  SQLException{

        String CinnamonBookId =  generateNextCinnamonBookId();

        double amount = 0.0;

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Cinnamon_Book VALUES(?, ?, ?)";

       PreparedStatement pstm =  connection.prepareStatement(sql);

       pstm.setString(1,  CinnamonBookId);
       pstm.setDouble(2, amount);
       pstm.setString(3, date);

       return  pstm.executeUpdate() > 0;

    }

    private String generateNextCinnamonBookId() throws  SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT CinnamonBookId  FROM Cinnamon_Book ORDER BY CinnamonBookId DESC LIMIT 1";

       ResultSet resultSet =  connection.prepareStatement(sql).executeQuery();

       String currentCinnamonBookId = null;

        if (resultSet.next()) {
            currentCinnamonBookId = resultSet.getString(1);
            return splitCinnamonBookId(currentCinnamonBookId);

        }
        return splitCinnamonBookId(currentCinnamonBookId);
    }

    private String splitCinnamonBookId(String currentCinnamonBookId) {

        if (currentCinnamonBookId != null) {
            String[] split =  currentCinnamonBookId.split("CB");
            int selectedId = Integer.parseInt(split[1]);
            if (selectedId < 9) {
                selectedId++;
                return "CB00" +selectedId;
            }else if (selectedId < 99){
                selectedId++;
                return "CB0" +selectedId;
            }else{
                selectedId++;
                return "CB" + selectedId;
            }
        }
        return "CB001";
    }
    public double getAmount(String string) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

       String sql =  "SELECT dailyAmount FROM Cinnamon_Book WHERE date=?";

       PreparedStatement pstm = connection.prepareStatement(sql);

       pstm.setString(1, string);

       ResultSet resultSet = pstm.executeQuery();

       double amount = 0.0;
        if (resultSet .next()) {
            amount = resultSet.getDouble(1);
        }
       return  amount;
    }
    public List<CinnamonBookDto> getAllCinnamonBookDetails() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Cinnamon_Book order by date";

        PreparedStatement pstm = connection.prepareStatement(sql);

        List<CinnamonBookDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String CinnamonBookId = resultSet.getString(1);
           double dailyAmount = resultSet.getDouble(2);
           String date =  resultSet.getString(3);

           CinnamonBookDto dto = new CinnamonBookDto(CinnamonBookId, dailyAmount, date);

           dtoList.add(dto);
        }
        return dtoList;
    }



}
