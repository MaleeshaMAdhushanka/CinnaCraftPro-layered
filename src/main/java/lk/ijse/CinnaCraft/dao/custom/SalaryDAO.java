package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;

import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.entity.Salary;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface SalaryDAO extends CrudDAO<Salary> {

     List<Salary> getPaymentDetails(String supplierId) throws SQLException;


    }
