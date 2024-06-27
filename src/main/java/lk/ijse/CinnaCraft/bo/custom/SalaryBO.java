package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.SalaryDto;
import lk.ijse.CinnaCraft.bo.SuperBo;
import lk.ijse.CinnaCraft.entity.Salary;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface SalaryBO extends SuperBo {
    public boolean addSalary(SalaryDto dto) throws SQLException ;

    public String generateNextSalaryId() throws SQLException ;

    public List<SalaryDto> getPaymentDetails(String supplierId) throws SQLException;

    public boolean saveSalary(SalaryDto dto) throws SQLException;
    }
