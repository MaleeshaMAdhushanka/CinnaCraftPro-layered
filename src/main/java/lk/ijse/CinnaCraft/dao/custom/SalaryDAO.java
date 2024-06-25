package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.SalaryDto;
import lk.ijse.CinnaCraft.dao.CrudDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface SalaryDAO extends CrudDAO<SalaryDto> {

     List<SalaryDto> getPaymentDetails(String supplierId) throws SQLException;


    }
