package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.FertilizerDto;
import lk.ijse.CinnaCraft.Tm.FertilizeSalesCartTm;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.FertilizerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FertilizerDAOImpl implements FertilizerDAO {


    public  ArrayList<FertilizerDto> getAll() throws SQLException {


        ResultSet resultSet =  SQLUtil.crudUtil( "SELECT * FROM fertilizer");
        ArrayList<FertilizerDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            FertilizerDto dto = new FertilizerDto(
                    resultSet.getString("fertilizerID"),
                    resultSet.getString("brand"),
                    resultSet.getString("description"),
                    resultSet.getString("size"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("qty_on_hand"));
            dtoList.add(dto);
        }

        return dtoList;
    }

    public  boolean save(FertilizerDto dto) throws  SQLException{


        return SQLUtil.crudUtil("INSERT INTO fertilizer VALUES(?, ?, ?, ?, ?, ?)", dto.getFertilizerID(), dto.getBrand(), dto.getDescription(), dto.getSize(), dto.getPrice(), dto.getQty());

    }

    @Override
    public boolean update(FertilizerDto dto) throws SQLException {


        return SQLUtil.crudUtil( "UPDATE fertilizer SET brand = ? , description = ? , size = ?, price = ?,qty = ? WHERE fertilizerID = ?", dto.getBrand(), dto.getDescription(), dto.getSize(), dto.getPrice(), dto.getQty(),dto.getFertilizerID());

    }


    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws  SQLException{


        return  SQLUtil.crudUtil("DELETE FROM fertilizer WHERE fertilizerId = ?", id);

    }


    public String generateId() throws SQLException {



         ResultSet resultSet  = SQLUtil.crudUtil( "SELECT fertilizerID  FROM fertilizer ORDER BY fertilizerID DESC LIMIT 1");

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





    public FertilizerDto search(String id) throws SQLException {

        ResultSet  resultSet = SQLUtil.crudUtil( "SELECT * FROM fertilizer WHERE fertilizerId = ?", id);

        FertilizerDto dto=null;

        if (resultSet.next()){
            dto = new FertilizerDto(
             resultSet.getString("fertilizerID"),
             resultSet.getString("brand"),
             resultSet.getString("description"),
             resultSet.getString("size"),
             resultSet.getDouble("price"),
              resultSet.getInt("qty_on_hand")
            );
        }

        return dto;
    }




    @Override
    public boolean updateFertilizer(List<FertilizeSalesCartTm> tmList) throws SQLException {
        for (FertilizeSalesCartTm cartTm: tmList){
            if (!updateQty(cartTm)){
                return false;
            }

        }
        return true;

    }

    @Override
    public boolean updateQty(FertilizeSalesCartTm cartTm) throws SQLException {
        return SQLUtil.crudUtil("UPDATE fertilizer SET qty_on_hand = qty_on_hand - ? WHERE fertilizerId = ?", cartTm.getQty(),  cartTm.getFertilizerId());

    }
}
