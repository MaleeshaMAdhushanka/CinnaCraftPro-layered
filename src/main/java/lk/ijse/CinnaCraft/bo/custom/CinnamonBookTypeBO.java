package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface CinnamonBookTypeBO extends SuperBo {


     String generateNextCinnamonBookTypeId() throws SQLException;

     boolean saveCinnamonBookType(CinnamonBookTypeDto dto) throws SQLException ;

     List<CinnamonBookTypeDto> getAllTeaBookTypeDetails(String date) throws SQLException ;
     boolean deleteCinnamonBookType(String  CinnamonBookTypeID) throws SQLException ;

    //trans Action

     boolean ConfirmCinnamonBook(LocalDate date) throws SQLException ;
     List<CinnamonBookTypeDetailDto> getTotalAmount(LocalDate date) throws SQLException ;
}
