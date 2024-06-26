package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Dto.CinnamonTypeDto;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.CinnamonTypeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinnamonTypeDAOImpl  implements CinnamonTypeDAO {

    @Override
    public ArrayList<CinnamonTypeDto> getAll() throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM Cinnamon_types");

        ArrayList<CinnamonTypeDto> dtoList = new ArrayList<>();
        while (resultSet.next()){
            CinnamonTypeDto dto = new CinnamonTypeDto(
            resultSet.getString("typeID"),
            resultSet.getString("type"),
            resultSet.getDouble("amount"));

            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(CinnamonTypeDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(CinnamonTypeDto dto) throws SQLException {
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
        return "";
    }

    @Override
    public CinnamonTypeDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public String getCinnamonTypeId(String type) throws SQLException{

         ResultSet resultSet = SQLUtil.crudUtil("SELECT typeId FROM  Cinnamon_types WHERE type=?", type);

         resultSet.next();

         return resultSet.getString(1);

    }

    @Override
    public String getCinnamonType(String typeID) throws SQLException{

        ResultSet resultSet = SQLUtil.crudUtil( "SELECT type FROM  Cinnamon_types WHERE typeID=?", typeID);

        resultSet.next();

        return resultSet.getString(1);

    }


    @Override
    public boolean updateCinnamonTypeAmount(CinnamonBookTypeDetailDto dto) throws SQLException{

       return SQLUtil.crudUtil( "UPDATE Cinnamon_types SET amount = amount + ? WHERE typeID = ?",

            dto.getAmount(),
            dto.getTypeID());

    }

    @Override
    public  double getCinnamonAmount(String cinnamonType) throws  SQLException{

       ResultSet resultSet = SQLUtil.crudUtil( "SELECT amount FROM Cinnamon_types WHERE type=?", cinnamonType);

        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0;

    }

    @Override

    public boolean updateAmount (double amount, String typeID) throws SQLException{

        return SQLUtil.crudUtil( "UPDATE Cinnamon_types SET amount = amount + ? WHERE  typeID = ?",amount, typeID);

    }
}
