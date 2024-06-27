package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.PaymentsDto;
import lk.ijse.CinnaCraft.Util.TransactionUtil;
import lk.ijse.CinnaCraft.bo.custom.PaymentsBO;
import lk.ijse.CinnaCraft.dao.custom.CinnamonBarkStockDAO;
import lk.ijse.CinnaCraft.dao.custom.PaymentDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonBarkStockDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.PaymentsDAOImpl;
import lk.ijse.CinnaCraft.entity.Payments;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentsBOImpl implements PaymentsBO {

    PaymentDAO paymentDAO = new PaymentsDAOImpl();
    CinnamonBarkStockDAO cinnamonBarkStockDAO = new CinnamonBarkStockDAOImpl();
    @Override
    public String generateNextPaymentId() throws SQLException {
        return paymentDAO.generateId();
    }

    @Override
    public boolean savePayments(PaymentsDto dto) throws SQLException {
        return paymentDAO.save(new Payments(
                dto.getPaymentId(),
                dto.getSupId(),
                dto.getAmount(),
                dto.getPayment(),
                dto.getDate()
        ));
    }

    @Override
    public List<PaymentsDto> getAllPaymentsDetails(String supId) throws SQLException {
        List<Payments> all = paymentDAO.getAllPaymentsDetails(supId);
        List<PaymentsDto> dtoList = new ArrayList<>();
        for (Payments payments: all) {
            dtoList.add(new PaymentsDto(
                    payments.getPaymentId(),
                    payments.getSupId(),
                    payments.getAmount(),
                    payments.getPayment(),
                    payments.getDate()
            ));
        }
        return dtoList;
    }

    @Override
    public boolean addPayment(PaymentsDto dto) throws SQLException {
        boolean result = false;


        try {

            TransactionUtil.autoCommitFalse();

            boolean isPaymentSaved = savePayments(dto);

            if (isPaymentSaved) {
                boolean isUpdated = cinnamonBarkStockDAO.updatePayedStatus(dto.getSupId());

                if (isUpdated) {
                    TransactionUtil.commit();
                    result = true;
                }
            }
        }catch (SQLException e){
            TransactionUtil.rollback();
        }
        return  result;
    }
}
