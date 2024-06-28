package lk.ijse.CinnaCraft.bo;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Dto.CinnamonTypeDto;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CinnamonTypeBO  extends SuperBo{


    public List<CinnamonTypeDto> getAllCinnamonType() throws SQLException;

    public String getCinnamonTypeId(String type) throws SQLException ;

    public String getCinnamonType(String typeID) throws SQLException ;

    public  double getCinnamonAmount(String cinnamonType) throws  SQLException ;

    public boolean updateAmount (List<PackingCountAmountDto> dtoList) throws SQLException ;

}
