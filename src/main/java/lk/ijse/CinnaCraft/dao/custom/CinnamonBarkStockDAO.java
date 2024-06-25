package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Dto.CinnamonBarkStockDto;
import lk.ijse.CinnaCraft.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface CinnamonBarkStockDAO extends CrudDAO<CinnamonBarkStockDto> {


     double getTotalAmount(String CinnamonBookID) throws SQLException;

     List<CinnamonBarkStockDto> getAllStockDetails(String dateBookId) throws SQLException;

     double getTotalCinnamonBarkSuppliedAmount(String SupID) throws SQLException;

     boolean updatePayedStatus(String SupID) throws SQLException;

}
