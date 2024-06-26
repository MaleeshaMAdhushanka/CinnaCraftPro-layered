package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Dto.PackagingDto;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.Tm.SalesCartTm;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.PackagingDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackagingDAOImpl implements PackagingDAO {


    @Override
    public ArrayList<PackagingDto> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM packing ");

        ArrayList<PackagingDto> entityList = new ArrayList<>();

        while (resultSet.next()){
            entityList.add(new PackagingDto(
                   resultSet.getString("packId"),
                    resultSet.getString("typeId"),
                    resultSet.getString("description"),
                    resultSet.getInt("packageCount"),
                    resultSet.getDouble("price")
            ));

        }
        return entityList;
    }

    @Override
    public boolean save(PackagingDto dto) throws SQLException {

       return SQLUtil.crudUtil( "INSERT INTO packing VALUES(?, ?, ?, ?, ?) ",
               dto.getPackId(),
               dto.getTypeId(),
               dto.getDescription(),
               dto.getPackageCount(),
               dto.getPrice());

    }

    @Override
    public boolean update(PackagingDto dto) throws SQLException {


       return SQLUtil.crudUtil("UPDATE packing SET typeId=?, description=?,packageCount=?, price=? WHERE packId=?",

        dto.getTypeId(),
        dto.getDescription(),
        dto.getPackageCount(),
        dto.getPrice(),
        dto.getPackId());


    }

    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {


        return  SQLUtil.crudUtil("DELETE FROM packing WHERE packId=?",id);

    }

    @Override
    public String generateId() throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil("SELECT packId FROM  packing  ORDER BY packId DESC LIMIT 1 ");

        String currentPackId = null;

        if (resultSet.next()) {
            currentPackId = resultSet.getString(1);
            return splitPackId(currentPackId);
        }

        return  splitPackId(currentPackId);

    }

    private String splitPackId(String currentPackId) {
        if (currentPackId != null) {
            String[] split = currentPackId.split("P");
            int selectedId = Integer.parseInt(split[1]);


            if (selectedId < 9) {
                selectedId++;
                return "P00" + selectedId;
            } else if (selectedId < 99) {
                selectedId++;
                return "P0" + selectedId;
            } else {
                selectedId++;
                return "P" + selectedId;
            }
        }

        return "P001";

    }


    public PackagingDto search(String id) throws SQLException {


        ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM packing WHERE packId=?", id);

        if (resultSet.next()) {
            return new PackagingDto(
                    resultSet.getString("packId"),
                    resultSet.getString("typeId"),
                    resultSet.getString("description"),
                    resultSet.getInt("packageCount"),
                    resultSet.getDouble("price")
            );


        }
        return  null;
    }


    public List<PackagingDto> getAllPackaging(String cinnamonType) throws SQLException {


        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM  packing WHERE typeId=?", cinnamonType);
        List<PackagingDto> pacakagingList = new ArrayList<>();


        while (resultSet.next()){
            PackagingDto packaging = new PackagingDto(
            resultSet.getString("packId"),
            resultSet.getString("typeId"),
            resultSet.getString("description"),
            resultSet.getInt("packagingCount"),
            resultSet.getDouble("price")
            );

            pacakagingList.add(packaging);

        }

        return pacakagingList;
    }


    public String getPackId(String cinnamonTypeId, String packSize) throws SQLException {

      ResultSet resultSet = SQLUtil.crudUtil( "SELECT packId FROM  packing WHERE typeId=? AND description=?", cinnamonTypeId, packSize);

        resultSet.next();

        return resultSet.getString(1);

    }


    public boolean updatePackagingCount(List<PackingCountAmountDto> dtoList) throws SQLException {

        for (PackingCountAmountDto dto : dtoList) {
            boolean isUpdated = SQLUtil.crudUtil( "UPDATE packing SET packageCount= packageCount + ? WHERE packId=?", dto.getCount(), dto.getPackId());


            if (!isUpdated){
                return false;
            }
        }

        return true;
    }

    public boolean updatePackaging(List<SalesCartTm> tmList) throws SQLException {

        for (SalesCartTm tm : tmList) {

            if (!updatePackagingQty(tm)){
                return false;
            }

        }
        return true;
    }


    public boolean updatePackagingQty(SalesCartTm tm)  throws  SQLException{

        return SQLUtil.crudUtil( "UPDATE packing SET packageCount = packageCount - ? WHERE packId = ?", tm.getQty(), tm.getPackId());


    }


    public String getTypeId(String packId) throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil( "SELECT typeId FROM packing WHERE packId=?", packId);
        resultSet.next();

        return resultSet.getString(1);
    }


    public boolean updatedPack(String packId, String typeId, String size, double price) throws SQLException {

       return SQLUtil.crudUtil( "UPDATE packing SET typeId=?, description=?, price=? WHERE packId=?", typeId, size, price, packId);


    }
}