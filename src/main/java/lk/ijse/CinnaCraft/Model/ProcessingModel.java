package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ProcessingModel {

  private final CinnamonBookTypeModel cinnamonBookTypeModel = new CinnamonBookTypeModel();


  private final CinnamonTypeModel cinnamonTypeModel = new CinnamonTypeModel();


  public boolean updateDetails(LocalDate date, List<CinnamonBookTypeDetailDto> dtoList) throws SQLException {

    boolean result = false;

    Connection connection = null;

     try {

      connection = DbConnection.getInstance().getConnection();
      connection.setAutoCommit(false);


      boolean isConfirmed = cinnamonBookTypeModel.ConfirmCinnamonBook(date);


       if (isConfirmed) {
         boolean isUpdated = cinnamonTypeModel.updateCinnamonTypeAmount(dtoList);

         if (isUpdated) {
          connection.commit();
          result = true;
         }
       }
     }catch (SQLException e){
      connection.rollback();
     }finally {
      connection.setAutoCommit(true);
    }
    return  result;


  }


}
