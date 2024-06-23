package lk.ijse.CinnaCraft.Model;

import javafx.scene.control.Alert;
import lk.ijse.CinnaCraft.Dto.PlaceFertilizerOrderDto;
import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceFertilizerOrderModel {

    private final FertilizerOrderModel fertilizerOrderModel = new FertilizerOrderModel();

    private final FertilizerModel fertilizerModel = new FertilizerModel();

    private final FertilizerOrderDetailModel fertilizerOrderDetailModel = new FertilizerOrderDetailModel();


    public boolean placeFertilizerOrder(PlaceFertilizerOrderDto dto) throws SQLException {

        boolean result = false;
        Connection connection = null;

        try{
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = fertilizerOrderModel.saveFertilizerOrder(dto); //true false

            if(isOrderSaved) {
                boolean isUpdated = fertilizerModel.updateFertilizer(dto.getTmList()); //

                if (isUpdated){
                    boolean isFertilizerOrderDetailSaved = fertilizerOrderDetailModel.saveFertilizerOrderDetail(dto.getFertilizerOrderId(),dto.getTmList()); //true
                    if (isFertilizerOrderDetailSaved){
                        connection.commit();
                        result = true;

                    }
                }
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}
