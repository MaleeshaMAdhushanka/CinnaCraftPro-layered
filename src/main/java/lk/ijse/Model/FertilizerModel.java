package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.FertilizerDto;
import lk.ijse.Tm.FertilizerTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FertilizerModel {

    public String generateNextFertilizerId() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT fertilizerID  FROM fertilizer ORDER BY fertilizerID DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        String currentFertilizerId = null;

        if (resultSet.next()) {
            currentFertilizerId = resultSet.getString(1);

            return splitFertilizerId(currentFertilizerId);


        }
        return  splitFertilizerId(currentFertilizerId);


    }

    private String splitFertilizerId(String currentFertilizerId) {

        if (currentFertilizerId != null){
            String[] split = currentFertilizerId.split("F");
            int selectedId = Integer.parseInt(split[1]);
            if (selectedId < 9){
                selectedId++;
                return "F00"+selectedId;
            }else if (selectedId < 99){
                selectedId++;
                return "F0"+selectedId;
            }else {
                selectedId++;
                return "F"+selectedId;
            }

        }
        return "F001";

    }

    public static boolean saveFertilizer(FertilizerDto dto) throws  SQLException{
        Connection  connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO fertilizer VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getFertilizerID());
        pstm.setString(2, dto.getBrand());
        pstm.setString(3, dto.getDescription());
        pstm.setString(4, dto.getSize());
        pstm.setDouble(5, dto.getPrice());
        pstm.setInt(6, dto.getQty());


        boolean isSaved =  pstm.executeUpdate() > 0;

        return isSaved;



    }
    public  List<FertilizerDto> getAllFertilizer() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM fertilizer";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<FertilizerDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();


        while (resultSet.next()) {
            String  fertilizerID = resultSet.getString(1);
            String   brand = resultSet.getString(2);
            String   description = resultSet.getString(3);
            String  size  = resultSet.getString(4);
            double price  = resultSet.getDouble(5);
            int  qty  = resultSet.getInt(6);

            var dto = new FertilizerDto(fertilizerID,brand,description,size,price,qty);
            dtoList.add(dto);
        }


        return dtoList;
    }
    public boolean deleteFertilizer(String fertilizerId) throws  SQLException{

    Connection connection = DbConnection.getInstance().getConnection();

    String sql = "DELETE FROM fertilizer WHERE fertilizerId = ?";
    PreparedStatement pstm = connection.prepareStatement(sql);

    pstm.setString(1, fertilizerId);

    return pstm.executeUpdate() > 0;

    }
    public FertilizerDto searchFertilizer(String fertilizerId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM fertilizer WHERE fertilizerId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, fertilizerId);

        ResultSet resultSet = pstm.executeQuery();

        FertilizerDto dto=null;

        if (resultSet.next()){
            String fertilizerID = resultSet.getString(1);
            String brand = resultSet.getString(2);
            String description = resultSet.getString(3);
            String size = resultSet.getString(4);
            double price = resultSet.getDouble(5);
            int qty  = resultSet.getInt(6);

            dto = new FertilizerDto(fertilizerID,brand,description,size,price,qty);
        }

        return dto;
    }
    public boolean updateFertilizer(FertilizerDto fertilizerDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE fertilizer SET brand = ? , desctiption = ? , size = ?, price = ?,qty = ? WHERE fertilizerID = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, fertilizerDto.getBrand());
        pstm.setString(2, fertilizerDto.getDescription());
        pstm.setString(3, fertilizerDto.getSize());
        pstm.setDouble(4, fertilizerDto.getPrice());
        pstm.setInt(5, fertilizerDto.getQty());
        pstm.setString(6, fertilizerDto.getFertilizerID());

        return pstm.executeUpdate() > 0;


    }


    }
