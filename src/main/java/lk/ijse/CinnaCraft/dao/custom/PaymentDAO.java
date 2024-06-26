package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Dto.PaymentsDto;
import lk.ijse.CinnaCraft.dao.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends CrudDAO<PaymentsDto> {
    List<PaymentsDto> getAllPaymentsDetails(String SupID) throws SQLException;


}
