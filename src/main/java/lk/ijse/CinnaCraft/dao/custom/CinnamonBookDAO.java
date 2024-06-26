package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.entity.CinnamonBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CinnamonBookDAO extends CrudDAO<CinnamonBook> {


     boolean updateCinnamonBookAmount(String CinnamonBookId, double amount) throws SQLException;

     String getCinnamonBookId(String date) throws SQLException;

     String getCinnamonBookDate(String CinnamonBookId) throws  SQLException;

     boolean searchDate(String date ) throws SQLException;

      boolean createCinnamonBookRecord(String date) throws  SQLException;

      double getAmount(String string) throws SQLException;



    }
