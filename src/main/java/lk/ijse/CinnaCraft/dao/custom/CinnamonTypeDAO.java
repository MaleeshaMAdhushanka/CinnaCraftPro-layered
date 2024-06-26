package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Dto.CinnamonTypeDto;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.dao.CrudDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CinnamonTypeDAO extends CrudDAO<CinnamonTypeDto> {

    public String getCinnamonTypeId(String type) throws SQLException;

    public String getCinnamonType(String typeID) throws SQLException;

    public boolean updateCinnamonTypeAmount(CinnamonBookTypeDetailDto dto) throws SQLException;
    public  double getCinnamonAmount(String cinnamonType) throws  SQLException;

    public boolean updateAmount (double amount , String typeID) throws SQLException;
}
