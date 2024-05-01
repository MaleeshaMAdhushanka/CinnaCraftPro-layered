package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.PlaceFertilizerOrderDto;

import java.sql.*;

public class FertilizerOrderModel {


    public String generateNextFertilizerOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT fert0id FROM fertilizer_order ORDER BY fert0id DESC LIMIT 1";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        String currentFertilizerOrderId = null;

        if(resultSet.next()){
            currentFertilizerOrderId = resultSet.getString(1);

            return splitFertilizerOrderId(currentFertilizerOrderId);
        }

        return splitFertilizerOrderId(currentFertilizerOrderId);

    }

    private String splitFertilizerOrderId(String currentFertilizerOrderId) {

        if (currentFertilizerOrderId != null){
            String[] split = currentFertilizerOrderId.split("FO");
            int selectedId = Integer.parseInt(split[1]);
            if (selectedId < 9){
                selectedId++;
                return "FO00"+selectedId;
            }else if (selectedId < 99){
                selectedId++;
                return "FO0"+selectedId;
            }else {
                selectedId++;
                return "FO"+selectedId;
            }

        }
        return "FO001";
    }

    public boolean saveFertilizerOrder(PlaceFertilizerOrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO fertilizer_order VALUES (?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getFertilizerOrderId());
        pstm.setString(2,dto.getCustomerId());
        pstm.setDate(3, Date.valueOf(dto.getDate()));


        return pstm.executeUpdate() > 0;

    }
}
