package lk.ijse.CinnaCraft.dao.custom;


import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.entity.CinnamonBarkStock;

import java.sql.SQLException;
import java.util.List;

public interface CinnamonBarkStockDAO extends CrudDAO<CinnamonBarkStock> {


     double getTotalAmount(String CinnamonBookID) throws SQLException;

     List<CinnamonBarkStock> getAllStockDetails(String dateBookId) throws SQLException;

     double getTotalCinnamonBarkSuppliedAmount(String SupID) throws SQLException;

     boolean updatePayedStatus(String SupID) throws SQLException;

}
