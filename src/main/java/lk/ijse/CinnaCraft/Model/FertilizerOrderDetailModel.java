package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Tm.FertilizeSalesCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FertilizerOrderDetailModel {

    public boolean saveFertilizerOrderDetail(String fertilizerOrderId, List<FertilizeSalesCartTm> tmList) throws SQLException {

        for (FertilizeSalesCartTm cartTm: tmList){
            if (!saveFertilizerOrderDetails(fertilizerOrderId,cartTm)){
                return false;
            }
        }
        return true;
    }

    private boolean saveFertilizerOrderDetails(String fertilizerOrderId, FertilizeSalesCartTm cartTm) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO fertilizer_order_details VALUES (?,?,?,?)";


        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,fertilizerOrderId);
        pstm.setString(2,cartTm.getFertilizerId());
        pstm.setInt(3,cartTm.getQty());
        pstm.setDouble(4, cartTm.getTotal());

        return pstm.executeUpdate() > 0;

    }

}
