package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.CinnamonBarkStockDto;
import lk.ijse.Dto.CinnamonBookDto;

import java.sql.Connection;
import java.sql.SQLException;

public class AddCinnamonBarkTransactionModel {

    private final CinnamonBarkStockModel cinnamonBarkStockModel = new CinnamonBarkStockModel();
    private final CinnamonBookModel cinnamonBookModel = new CinnamonBookModel();
    public boolean saveCinnamonBarkStock(CinnamonBarkStockDto cinnamonBarkStockDto, String CinnamonBookId) throws SQLException {

        boolean result = false;
        Connection connection =null;

        connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);



    }


}
