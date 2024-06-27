package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Dto.PaymentsDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface PaymentsBO extends SuperBo {
    String generateNextPaymentId() throws SQLException;
    boolean savePayments(PaymentsDto dto) throws SQLException;
    boolean addPayment(PaymentsDto dto) throws SQLException;
    List<PaymentsDto> getAllPaymentsDetails(String supId) throws SQLException;
}
