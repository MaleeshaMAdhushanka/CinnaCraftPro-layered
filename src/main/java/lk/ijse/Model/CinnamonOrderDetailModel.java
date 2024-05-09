package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Tm.SalesCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CinnamonOrderDetailModel {


    public boolean saveOrderDetail(String Cinnamon_order_ID, List<SalesCartTm> tmList) throws SQLException{

        for (SalesCartTm salesCartTm : tmList){
            if (!saveOrderDetail(Cinnamon_order_ID,salesCartTm)) {
                return false;
            }
        }
        return true;

    }

    private boolean saveOrderDetail(String CinnamonOrderId, SalesCartTm salesCartTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO Cinnamon_order_details VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, CinnamonOrderId);
        pstm.setString(2, salesCartTm.getPackId());
        pstm.setInt(3, salesCartTm.getQty());
        pstm.setDouble(4, salesCartTm.getTotal());

        return pstm.executeUpdate()>0;
    }
}
