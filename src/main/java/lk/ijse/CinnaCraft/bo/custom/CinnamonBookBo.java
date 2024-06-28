package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CinnamonBookDto;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface CinnamonBookBo extends SuperBo {



      boolean updateCinnamonBookAmount(String CinnamonBookId, double amount) throws SQLException;

      String getCinnamonBookId(String date) throws SQLException;

      String getCinnamonBookDate(String CinnamonBookId) throws  SQLException;

      boolean searchDate(String date ) throws SQLException ;
       boolean createCinnamonBookRecord(String date) throws  SQLException;

      String generateNextCinnamonBookId() throws  SQLException ;


      double getAmount(String string) throws SQLException;
      List<CinnamonBookDto> getAllCinnamonBookDetails() throws SQLException;

}
