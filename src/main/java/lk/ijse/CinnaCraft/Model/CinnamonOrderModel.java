package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CinnamonOrderModel {

    public String generateNextOrderId()throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT Cinnamon_order_ID FROM Cinnamon_orders  ORDER BY Cinnamon_order_ID DESC LIMIT 1";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        String currentOrderId = null;

        if (resultSet.next()) {
            currentOrderId = resultSet.getString(1);
            return  splitOrderId(currentOrderId);
        }
        return splitOrderId(currentOrderId);



    }
    private String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String [] split = currentOrderId.split("O");
            int seletedId = Integer.parseInt(split[1]);

            if (seletedId < 9) {
                seletedId++;
                return "O00"+ seletedId;
            }else if (seletedId < 99){
                seletedId ++;
                return "O0" + seletedId;
            }else {
                seletedId++;
                return "O" + seletedId;
            }
        }
        return "O001";

    }
    public static boolean saveOrder(String Cinnamon_order_ID, String CusID, LocalDate date) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO Cinnamon_orders VALUES(?, ?, ?)";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, Cinnamon_order_ID);
        pstm.setString(2, CusID);
        pstm.setDate(3, java.sql.Date.valueOf(date));

        return   pstm.executeUpdate()>0;
    }
    public int getOrderCount(String date) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(Cinnamon_order_ID) FROM Cinnamon_orders WHERE date=?";

        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, date);

        ResultSet resultSet = pstm.executeQuery();

        int count = 0;

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;


    }
















}
