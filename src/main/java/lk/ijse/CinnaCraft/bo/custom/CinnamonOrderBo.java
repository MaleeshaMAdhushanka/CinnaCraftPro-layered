package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.PlaceCinnamonOrderDto;
import lk.ijse.CinnaCraft.Tm.SalesCartTm;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface CinnamonOrderBo extends SuperBo {


      String generateNextOrderId()throws SQLException;

      int getOrderCount(String date) throws SQLException ;

     boolean placeOrder(PlaceCinnamonOrderDto dto) throws SQLException ;





}
