package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Dto.CinnamonTypeDto;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface CinnamonTypeBO  extends SuperBo {


    public List<CinnamonTypeDto> getAllCinnamonType() throws SQLException;

    public String getCinnamonTypeId(String type) throws SQLException ;

    public String getCinnamonType(String typeID) throws SQLException ;

    public  double getCinnamonAmount(String cinnamonType) throws  SQLException ;

    public boolean updateAmount (List<PackingCountAmountDto> dtoList) throws SQLException ;

}
