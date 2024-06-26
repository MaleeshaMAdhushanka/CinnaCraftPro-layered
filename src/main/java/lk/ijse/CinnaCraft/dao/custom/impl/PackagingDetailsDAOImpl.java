package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.PackagingDetailsDAO;
import lk.ijse.CinnaCraft.entity.PackingCountAmount;
import lk.ijse.CinnaCraft.entity.PackingDetails;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PackagingDetailsDAOImpl implements PackagingDetailsDAO {


    @Override
    public ArrayList<PackingDetails> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(PackingDetails entity) throws SQLException {


        return  SQLUtil.crudUtil("INSERT INO packaging_details VALUES(?, ?, ?, ?, ?,?,?) ",

        entity.getPackagingDetailsId(),
        Date.valueOf(entity.getDate()),
        entity.getPackId(),
        entity.getCount(),
        entity.getAmount(),
        entity.isConfirmed(),
        entity.getTotal());

    }

    @Override
    public boolean update(PackingDetails dto) throws SQLException {
        return false;
    }

    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {

     return  SQLUtil.crudUtil("DELETE FROM packaging_details WHERE packagingDetailsId=?", id);

    }

    @Override
    public String generateId() throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil("SELECT packagingDetailsId FROM  packaging_details ORDER BY  packagingDetailsId DESC LIMIT 1");


        String currentPackingId = null;

        if (resultSet.next()) {
            currentPackingId = resultSet.getString(1);
            return splitPackingId(currentPackingId);

        }
        return splitPackingId(currentPackingId);

    }

    private String splitPackingId(String currentPackingId){
        if (currentPackingId != null) {
            String [] split = currentPackingId.split("PD");
            int selectedId = Integer.parseInt(split[1]);

            if (selectedId < 9) {
                selectedId++;
                return "PD00" + selectedId;
            }else if(selectedId < 99){
                selectedId++;
                return "PD0" + selectedId;
            }else {
                selectedId++;
                return "PD" + selectedId;
            }

        }
        return "PD001";
    }

    @Override
    public PackingDetails search(String id) throws SQLException {
        return null;
    }

    public double getTotalDescreasedAmount(String CinnamonTypeId)  throws SQLException{


        ResultSet resultSet = SQLUtil.crudUtil("SELECT SUM(amount) FROM packaging_details WHERE packId IN(SELECT packId FROM  packing WHERE typeId=?) AND isConfirmed =0 ", CinnamonTypeId);
        if (resultSet.next()) {
            return  resultSet.getDouble(1);
        }
        return  0;

    }
    public List<PackingDetails> loadAllPackagingDetails(LocalDate date) throws SQLException {


       ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM packaging_details WHERE date=?", date);


        List<PackingDetails> packingDetailsList = new ArrayList<>();

        while (resultSet.next()) {
                 packingDetailsList.add(new PackingDetails(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getBoolean(6),
                    resultSet.getDouble(7)
            ));

        }

        return packingDetailsList;
    }


    public List<PackingCountAmount> getTotalCountAmount(LocalDate date) throws SQLException {


        ResultSet resultSet = SQLUtil.crudUtil("SELECT packId,SUM(count),SUM(amount) FROM packaging_details WHERE date=? AND isConfirmed=0 GROUP BY packId", date);


        List<PackingCountAmount> packaingCountAmountList = new ArrayList<>();

        while (resultSet.next()) {
             packaingCountAmountList .add(new PackingCountAmount(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getDouble(3)
            ));
        }
        return packaingCountAmountList;

    }

    public boolean confirmPackaging(LocalDate parse) throws SQLException {

     return  SQLUtil.crudUtil( "UPDATE packaging_details SET isConfirmed=1 WHERE date=?", Date.valueOf(parse));



    }

}
