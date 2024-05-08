package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.PackagingDto;
import lk.ijse.Dto.PackingCountAmountDto;
import lk.ijse.Tm.SalesCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackagingModel {



    public List<PackagingDto> getAllPackaging (String cinnamonTypeId) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM  packing WHERE typeId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, cinnamonTypeId);

        List<PackagingDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        PackagingDto dto = null;


          while (resultSet.next()){
         String packId = resultSet.getString(1);
          String typeId = resultSet.getString(2);
          String description = resultSet.getString(3);
           int count = resultSet.getInt(4);
          double price =  resultSet.getDouble(5);

          dto = new PackagingDto(packId, typeId, description, count, price);

          dtoList.add(dto);

        }

          return  dtoList;

    }


    public String getPackId(String cinnamonTypeId, String packSize) throws  SQLException{
        Connection connection = DbConnection.getInstance().getConnection();


        String sql  = "SELECT packId FROM  packing WHERE typeId=? AND description=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, cinnamonTypeId);
        pstm.setString(2, packSize);


        ResultSet resultSet = pstm.executeQuery();

        resultSet.next();

       return resultSet.getString(1);

    }

    public PackagingDto searchPackaging(String packId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM packing WHERE packId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, packId);

        ResultSet resultSet = pstm.executeQuery();

        PackagingDto dto =  null;

        if (resultSet.next()) {
           String id =   resultSet.getString(1);
           String typeId = resultSet.getString(2);
           String description =  resultSet.getString(3);
           double price = resultSet.getDouble(4);
            int count = resultSet.getInt(5);


           dto = new PackagingDto(id, typeId, description, count, price);

        }
        return  dto;

    }
    public boolean updatePackagingCount(List<PackingCountAmountDto> dtoList) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE packing SET packageCount= packageCount + ? WHERE packId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        for (PackingCountAmountDto dto : dtoList) {

            pstm.setInt(1,dto.getCount());
            pstm.setString(2,dto.getPackId());

            if (pstm.executeUpdate()<=0){
                return false;
            }
        }

        return true;
    }

    public String getTypeId(String packId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT typeId FROM packing WHERE packId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,packId);

        ResultSet resultSet = pstm.executeQuery();

        resultSet.next();

        return resultSet.getString(1);

    }
    public boolean updatePackaging(List<SalesCartTm> tmList) throws SQLException {

        for (SalesCartTm tm : tmList) {

            if (!updatePackagingQty(tm)){
                return false;
            }


        }
        return true;
    }

    private boolean updatePackagingQty(SalesCartTm tm)  throws  SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE packing SET packageCount = packageCount - ? WHERE packId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, tm.getQty());
        pstm.setString(2, tm.getPackId());

        return  pstm.executeUpdate()>0;
    }

    public List<PackagingDto> getAllPackaging() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM packing ";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<PackagingDto> dtoList = new ArrayList<>();

        PackagingDto dto = null;

        while (resultSet.next()){
          String packId = resultSet.getString(1);
          String typeId = resultSet.getString(2);
          String description = resultSet.getString(3);
          double price = resultSet.getDouble(4);
          int count =resultSet.getInt(5);
          dto = new PackagingDto(packId, typeId, description, count, price);

          dtoList.add(dto);
        }
        return dtoList;
    }





    public String generateNextPackId() throws SQLException {

        Connection connection =  DbConnection.getInstance().getConnection();

        String sql = "SELECT packId FROM  packing  ORDER BY packId DESC LIMIT 1 ";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

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

    public boolean deletePackage(String packId) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM packing WHERE packId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, packId);

        return pstm.executeUpdate()>0;


    }
    public  boolean addPackage(PackagingDto dto) throws  SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO packing VALUES(?, ?, ?, ?, ?) ";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getPackId());
        pstm.setString(2, dto.getTypeId());
        pstm.setString(3, dto.getDescription());
        pstm.setInt(4, dto.getPackageCount());
        pstm.setDouble(5, dto.getPrice());

       return pstm.executeUpdate()>0;
    }

    public boolean updatedPack(String packId, String typeId, String size, double price) throws  SQLException{


        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE packing SET typeId=?, description=?, price=? WHERE packId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);;

        pstm.setString(1, typeId);
        pstm.setString(2, size);
        pstm.setDouble(3, price);
        pstm.setString(4, packId);

        return  pstm.executeUpdate()>0;
    }


    public boolean updatePackageCount (String packId,int count) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE packing SET packageCount=? WHERE packId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);;

        pstm.setInt(1,count);
        pstm.setString(2,packId);
        return pstm.executeUpdate()>0;
    }

}
