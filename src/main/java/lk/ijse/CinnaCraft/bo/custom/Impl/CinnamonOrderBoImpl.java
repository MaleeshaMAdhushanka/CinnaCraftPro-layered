package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.PlaceCinnamonOrderDto;
import lk.ijse.CinnaCraft.Tm.SalesCartTm;
import lk.ijse.CinnaCraft.Util.TransactionUtil;
import lk.ijse.CinnaCraft.bo.custom.CinnamonOrderBo;
import lk.ijse.CinnaCraft.dao.custom.CinnamonOrderDAO;
import lk.ijse.CinnaCraft.dao.custom.CinnamonOrderDetailDAO;
import lk.ijse.CinnaCraft.dao.custom.PackagingDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonOrderDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonOrderDetailDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.PackagingDAOImpl;
import lk.ijse.CinnaCraft.entity.CinnamonOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CinnamonOrderBoImpl implements CinnamonOrderBo {

    PackagingDAO packagingDAO = new PackagingDAOImpl();

    CinnamonOrderDAO cinnamonOrderDAO = new CinnamonOrderDAOImpl();

    CinnamonOrderDetailDAO cinnamonOrderDetailDAO = new CinnamonOrderDetailDAOImpl();


    @Override
    public String generateNextOrderId() throws SQLException {
        return cinnamonOrderDAO.generateNextOrderId();
    }

    @Override
    public int getOrderCount(String date) throws SQLException {
        return cinnamonOrderDAO.getOrderCount(date);
    }


    public boolean saveOrderDetail(String Cinnamon_order_ID, List<SalesCartTm> tmList) throws SQLException{

        for (SalesCartTm salesCartTm : tmList){
            if (!saveOrderDetail(Cinnamon_order_ID,salesCartTm)) {
                return false;
            }
        }
        return true;

    }

    public boolean saveOrderDetail(String CinnamonOrderId, SalesCartTm salesCartTm) throws SQLException {


    return cinnamonOrderDetailDAO.saveOrderDetail(CinnamonOrderId, salesCartTm);



    }

    @Override
    public boolean placeOrder(PlaceCinnamonOrderDto dto) throws SQLException {
        boolean result =  false;

        try {


            TransactionUtil.autoCommitFalse();


            boolean isOrderSaved = cinnamonOrderDAO.saveOrder(new CinnamonOrder(dto.getCinnamon_order_ID(), dto.getCusID(), dto.getDate(), dto.getTotal(), dto.getTmList()));

            if (isOrderSaved) {
                boolean isUpdated = packagingDAO.updatePackaging(dto.getTmList());

                if (isUpdated) {
                    boolean isOrderDetailSaved = saveOrderDetail(dto.getCinnamon_order_ID(), dto.getTmList());

                    if (isOrderDetailSaved) {
                        TransactionUtil.commit();
                        result = true;
                    }
                }
            }
        }
        catch (SQLException e){
            TransactionUtil.rollback();
        }

        return result;

    }
}
