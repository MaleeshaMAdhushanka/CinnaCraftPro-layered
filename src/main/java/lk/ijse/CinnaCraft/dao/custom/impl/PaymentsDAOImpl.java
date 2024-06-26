package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Dto.PaymentsDto;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.PaymentDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentsDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<PaymentsDto> getAll() throws SQLException {
        return null;
    }


    @Override
    public boolean save(PaymentsDto dto) throws SQLException {
        return  SQLUtil.crudUtil("INSERT INTO payments VALUES(?,?,?,?,?)",
                dto.getPaymentId(),
                dto.getSupId(),
                dto.getAmount(),
                dto.getPayment(),
                Date.valueOf(dto.getDate())
        );
    }

    @Override
    public boolean update(PaymentsDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String generateId() throws SQLException {
        ResultSet resultSet = SQLUtil.crudUtil("SELECT paymentId FROM payments ORDER BY paymentId DESC LIMIT 1");
        String currentPaymentId = null;
        if (resultSet.next()) {
            currentPaymentId = resultSet.getString(1);
            return  splitPaymentId(currentPaymentId);
        }
        return splitPaymentId(currentPaymentId);
    }

    private String splitPaymentId(String currentPaymentId) {
        if (currentPaymentId != null) {
            String[] split = currentPaymentId.split("P");
            int selectedId = Integer.parseInt(split[1]);
            if (selectedId < 9) {
                selectedId++;
                return "P00" + selectedId;
            } else if (selectedId < 99) {
                selectedId++;
                return "P0" + selectedId;
            } else {
                selectedId++;
                return "P" + selectedId;
            }
        }
        return "P001";
    }

    @Override
    public PaymentsDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public List<PaymentsDto> getAllPaymentsDetails(String SupID) throws SQLException {
        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM payments WHERE SupID=?",SupID);
        List<PaymentsDto> dtoList = new ArrayList<>();
        while(resultSet.next()){
            PaymentsDto dto = new PaymentsDto(
                    resultSet.getString("paymentID "),
                    resultSet.getString("SupID"),
                    resultSet.getDouble("amount"),
                    resultSet.getDouble("payment"),
                    resultSet.getDate("date").toLocalDate());
            dtoList.add(dto);

        }
        return  dtoList;
    }
}
