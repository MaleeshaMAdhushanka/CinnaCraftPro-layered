package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Dto.CinnamonBarkStock;
import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class AddCinnamonBarkTransactionModel {


    private final CinnamonBarkStockModel cinnamonBarkStockModel = new CinnamonBarkStockModel();
    private final CinnamonBookModel cinnamonBookModel = new CinnamonBookModel();

    public boolean saveCinnamonBarkStock(CinnamonBarkStock cinnamonBarkStockDto, String CinnamonBookId) throws SQLException {

            boolean result = false;
            Connection connection = null;

         try{
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSaved = cinnamonBarkStockModel.saveCinnamonBarkStock(cinnamonBarkStockDto);

            if (isSaved) {

                double dailyAmount = cinnamonBarkStockModel.getTotalAmount(CinnamonBookId);

                boolean isUpdated = cinnamonBookModel.updateCinnamonBookAmount(CinnamonBookId, dailyAmount);

                if (isUpdated) {
                    connection.commit();
                    result = true;

                }

            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;


    }
}



