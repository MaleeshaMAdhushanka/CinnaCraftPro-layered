package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Dto.CinnamonTypeDto;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinnamonTypeModel {
    private final PackagingModel packagingModel = new PackagingModel();

    public List<CinnamonTypeDto> getAllCinnamonType() throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Cinnamon_types";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        List<CinnamonTypeDto> dtoList = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()){
            String typeID =  resultSet.getString(1);
            String type = resultSet.getString(2);
            double amount = resultSet.getDouble(3);



            CinnamonTypeDto  dto = new CinnamonTypeDto(typeID, type, amount);

            dtoList.add(dto);
        }
        return dtoList;
    }

    public String getCinnamonTypeId(String type) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT typeId FROM  Cinnamon_types WHERE type=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,type);

        ResultSet resultSet = pstm.executeQuery();

        resultSet.next();

        return resultSet.getString(1);

    }

    public String getCinnamonType(String typeID) throws SQLException{


        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT type FROM  Cinnamon_types WHERE typeID=?";

        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, typeID);

        ResultSet resultSet = pstm.executeQuery();

        resultSet.next();

        return resultSet.getString(1);

    }

    public boolean updateCinnamonTypeAmount(List<CinnamonBookTypeDetailDto> dtoList) throws SQLException{

       Connection connection =  DbConnection.getInstance().getConnection();

       String sql = "UPDATE Cinnamon_types SET amount = amount + ? WHERE typeID = ?";

       PreparedStatement pstm = connection.prepareStatement(sql);

       for(CinnamonBookTypeDetailDto dto : dtoList){
           pstm.setDouble(1, dto.getAmount());
           pstm.setString(2, dto.getTypeID());

           if (pstm.executeUpdate() <=0) {
               return false;
           }

       }
       return true;

    }
    public  double getCinnamonAmount(String cinnamonType) throws  SQLException{

        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT amount FROM Cinnamon_types WHERE type=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, cinnamonType);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0;

    }

    public boolean updateAmount (List<PackingCountAmountDto> dtoList) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Cinnamon_types SET amount = amount + ? WHERE  typeID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (PackingCountAmountDto dto : dtoList){

          String typeID = packagingModel.getTypeId(dto.getPackId());
            pstm.setDouble(1, dto.getAmount());
            pstm.setString(2, typeID);

            if (pstm.executeUpdate() <= 0){
                return false;
            }
        }
        return true;
    }



}
