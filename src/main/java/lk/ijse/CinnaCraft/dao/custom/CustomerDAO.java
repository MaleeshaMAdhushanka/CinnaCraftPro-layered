package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {

         String searchCustomerId (String cusNum) throws SQLException;
    }
