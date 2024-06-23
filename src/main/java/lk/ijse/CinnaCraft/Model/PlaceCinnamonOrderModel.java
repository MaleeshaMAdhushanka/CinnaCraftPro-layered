package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Dto.PlaceCinnamonOrderDto;
import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceCinnamonOrderModel {


    private final PackagingModel packagingModel = new PackagingModel();

    private final CinnamonOrderDetailModel cinnamonOrderDetailModel = new CinnamonOrderDetailModel();


    public boolean placeOrder(PlaceCinnamonOrderDto dto) throws SQLException {

        boolean result = false;
        Connection connection = null;

        try {

            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = CinnamonOrderModel.saveOrder(dto.getCinnamon_order_ID(), dto.getCusID(), dto.getDate());

            System.out.println(isOrderSaved);

            if (isOrderSaved) {
                boolean isUpdated = packagingModel.updatePackaging(dto.getTmList());

                System.out.println(isUpdated);

                if (isUpdated) {
                    boolean isOrderDetailSaved = cinnamonOrderDetailModel.saveOrderDetail(dto.getCinnamon_order_ID(), dto.getTmList());
                    if (isOrderDetailSaved) {
                        connection.commit();
                        result = true;
                    }
                }
            }
        }
          catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}

