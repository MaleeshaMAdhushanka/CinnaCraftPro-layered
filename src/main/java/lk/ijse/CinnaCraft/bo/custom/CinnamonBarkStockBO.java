package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Dto.CinnamonBarkStockDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface CinnamonBarkStockBO extends SuperBo {

     String generateNextCinnamonBarkStockId() throws SQLException ;

     boolean saveCinnamonBarkStock(CinnamonBarkStockDto dto) throws SQLException ;

     double getTotalAmount(String CinnamonBookID) throws SQLException ;

     List<CinnamonBarkStockDto> getAllStockDetails(String dateBookId) throws SQLException ;

     CinnamonBarkStockDto searchCinnamonBarkStock(String text) throws SQLException ;

     boolean deleteCinnamonBarkStock(String CinnamonStockID) throws SQLException ;

     boolean updateCinnamonBarkStock(CinnamonBarkStockDto cinnamonBarkStockDto) throws SQLException ;

     double getTotalCinnamonBarkSuppliedAmount(String SupID) throws SQLException ;

     boolean updatePayedStatus(String SupID) throws SQLException ;

      boolean addCinnamonBarkStock(CinnamonBarkStockDto cinnamonBarkStockDto, String CinnamonBookId) throws SQLException;
}
