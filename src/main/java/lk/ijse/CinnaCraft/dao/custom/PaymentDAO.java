package lk.ijse.CinnaCraft.dao.custom;


import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.entity.Payments;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payments> {
    List<Payments> getAllPaymentsDetails(String SupID) throws SQLException;


}
