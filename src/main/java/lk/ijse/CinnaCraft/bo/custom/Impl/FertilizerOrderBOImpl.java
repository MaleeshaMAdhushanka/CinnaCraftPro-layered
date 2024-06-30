package lk.ijse.CinnaCraft.bo.custom.Impl;

import javafx.scene.control.Alert;
import lk.ijse.CinnaCraft.Dto.PlaceFertilizerOrderDto;
import lk.ijse.CinnaCraft.Util.TransactionUtil;
import lk.ijse.CinnaCraft.bo.custom.FertilizerOrderBo;
import lk.ijse.CinnaCraft.dao.DAOFactory;
import lk.ijse.CinnaCraft.dao.custom.FertilizerDAO;
import lk.ijse.CinnaCraft.dao.custom.FertilizerOrderDAO;
import lk.ijse.CinnaCraft.dao.custom.FertilizerOrderDetailDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.FertilizerDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.FertilizerOrderDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.FertilizerOrderDetailDAOImpl;
import lk.ijse.CinnaCraft.entity.Fertilizer;
import lk.ijse.CinnaCraft.entity.FertilizerOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class FertilizerOrderBOImpl implements FertilizerOrderBo {

    private FertilizerDAO fertilizerDAO = (FertilizerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.FERTILIZER);

   private FertilizerOrderDAO fertilizerOrderDAO = (FertilizerOrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.FERTILIZER_ORDER);

   private   FertilizerOrderDetailDAO fertilizerOrderDetailDAO = (FertilizerOrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.FERTILIZER_ORDER_DETAIL);


    @Override
    public String generateNextFertilizerOrderId() throws SQLException {
        return fertilizerOrderDAO.generateId();
    }

    @Override
    public boolean saveFertilizerOrder(PlaceFertilizerOrderDto dto) throws SQLException {
        return fertilizerOrderDAO.save(new FertilizerOrder(
                dto.getFertilizerOrderId(),
                dto.getCustomerId(),
                dto.getDate()
        ));
    }

    @Override
    public boolean placeFertilizerOrder(PlaceFertilizerOrderDto dto) throws SQLException {
        boolean result = false;


        try {

            TransactionUtil.autoCommitFalse();

            boolean isOrderSaved = saveFertilizerOrder(dto);

            if (isOrderSaved) {
                boolean isUpdated = fertilizerDAO.updateFertilizer(dto.getTmList());

                if (isUpdated) {
                    boolean isFertilizerOrderDetailSaved = fertilizerOrderDetailDAO.saveFertilizerOrderDetail(dto.getFertilizerOrderId(), dto.getTmList());
                    if (isFertilizerOrderDetailSaved) {
                        TransactionUtil.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            TransactionUtil.rollback();
        }
        return result;
    }
}






