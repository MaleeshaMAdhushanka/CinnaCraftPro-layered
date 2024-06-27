package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CustomerDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBo {

    ArrayList<CustomerDto> getAllCustomers() throws SQLException ;

    boolean saveCustomer(CustomerDto dto) throws SQLException;

    boolean updateCustomer(CustomerDto dto) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;

     String generateNextCustomerId() throws SQLException;

     CustomerDto searchCustomer(String id) throws SQLException;

     String searchCustomerId(String name) throws SQLException ;





}
