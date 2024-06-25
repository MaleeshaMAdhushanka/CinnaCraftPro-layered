package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.SalaryDto;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.SalaryDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {

    @Override
    public ArrayList<SalaryDto> getAll() throws SQLException {
        return null;
    }

   @Override
    public boolean save(SalaryDto dto) throws SQLException {


        return SQLUtil.crudUtil("INSERT INTO  salary VALUES(?, ?, ?, ?, ?)",
                dto.getSalaryId(),
                dto.getEmpID(),
                dto.getAmount(),
                dto.getDateCount(),
                Date.valueOf(dto.getDate()));


    }

    @Override
    public boolean update(SalaryDto dto) throws SQLException {
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


    public String generateId() throws SQLException {


        ResultSet resultSet = SQLUtil.crudUtil("SELECT salaryID FROM salary ORDER BY salaryID DESC LIMIT 1");

        String currentSalaryId = null;

        if (resultSet.next()) {
            currentSalaryId = resultSet.getString(1);

            return splitSalaryId(currentSalaryId);
        }

        return splitSalaryId(currentSalaryId);


    }

    private String splitSalaryId(String currentSalaryId) {

        if (currentSalaryId != null) {

            String[] split = currentSalaryId.split("S");
            int selectedId = Integer.parseInt(split[1]);

            if (selectedId < 9) {
                selectedId++;
                return "S00" + selectedId;
            } else if (selectedId < 99) {
                selectedId++;
                return "S0" + selectedId;
            } else {
                selectedId++;
                return "S" + selectedId;
            }

        }
        return "S001";
    }

    @Override
    public SalaryDto search(String id) throws SQLException {
        return null;
    }

    public List<SalaryDto> getPaymentDetails(String supplierId) throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM salary WHERE empID=?", supplierId);

        List<SalaryDto> salaryList = new ArrayList<>();

        while (resultSet.next()) {
            SalaryDto salary = new SalaryDto(
                    resultSet.getString("salaryId"),
                    resultSet.getString("empID"),
                    resultSet.getDouble("amount"),
                    resultSet.getInt("dateCount"),
                    resultSet.getDate("Date").toLocalDate());

            salaryList.add(salary);

        }
        return salaryList;

    }
}
