package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;

import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.FertilizerDAO;
import lk.ijse.CinnaCraft.dao.custom.FertilizerOrderDAO;
import lk.ijse.CinnaCraft.entity.FertilizerOrder;

import java.sql.*;
import java.util.ArrayList;

public class FertilizerOrderDAOImpl implements FertilizerOrderDAO {


    @Override
    public ArrayList<FertilizerOrder> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(FertilizerOrder entity) throws SQLException {


       return SQLUtil.crudUtil( "INSERT INTO fertilizer_order VALUES (?,?,?)", entity.getFertilizerOrderId(), entity.getCustomerId(), entity.getDate());
    }

    @Override
    public boolean update(FertilizerOrder entity) throws SQLException {
        return false;
    }

    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String generateId() throws SQLException {

      ResultSet resultSet = SQLUtil.crudUtil( "SELECT fert0id FROM fertilizer_order ORDER BY fert0id DESC LIMIT 1");


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

    @Override
    public FertilizerOrder search(String id) throws SQLException {
        return null;
    }
}
