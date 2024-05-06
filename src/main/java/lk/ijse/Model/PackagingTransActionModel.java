package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.PackingCountAmountDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PackagingTransActionModel {


    private final PackagingDetailsModel packagingDetailsModel = new PackagingDetailsModel();
    private final PackagingModel packagingModel = new PackagingModel();

    private final CinnamonTypeModel cinnamonTypeModel = new CinnamonTypeModel();


    public boolean confirmPackaging(LocalDate date, List<PackingCountAmountDto> dtoList) throws SQLException{


        boolean result = false;

        Connection connection = null;

        try{

        connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean isConfirmed = packagingDetailsModel.confirmPackaging(date);

        if (isConfirmed) {
            boolean iSaved =  packagingModel.updatePackagingCount(dtoList);
            if (iSaved) {
                boolean isUpdated = cinnamonTypeModel.updateAmount(dtoList);

                if (isUpdated) {
                    connection.commit();
                    result = true;
                }

            }
        }
    }catch (SQLException e){
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return result;

    }


}
