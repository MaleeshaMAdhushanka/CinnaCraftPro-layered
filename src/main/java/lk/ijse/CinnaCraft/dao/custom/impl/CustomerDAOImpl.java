package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.CustomerDAO;
import lk.ijse.CinnaCraft.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM Customer");
        ArrayList<Customer> allCustomer = new ArrayList<>();

        while (resultSet.next()) {
            Customer  entity= new Customer(
            resultSet.getString("cusId"),
            resultSet.getString("firstName"),
            resultSet.getString("lastName"),
            resultSet.getString("address"),
            resultSet.getString("email"),
            resultSet.getString("mobileNo")
              );
            allCustomer.add(entity);

        }
        return  allCustomer;
    }

    @Override
    public boolean save(Customer entity) throws SQLException {
        return SQLUtil.crudUtil("INSERT INTO Customer VALUES(?, ?, ?, ?, ?, ?)", entity.getCusId(), entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getEmail(), entity.getMobileNo());

    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        return SQLUtil.crudUtil("UPDATE Customer SET firstName = ? , lastName = ? , address = ?, email = ?, mobileNo = ? WHERE CusId = ?", entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getEmail(), entity.getMobileNo(), entity.getCusId());
    }

    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.crudUtil( "DELETE FROM Customer WHERE CusId = ?", id);
    }

    @Override
    public String generateId() throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil( "SELECT CusId FROM Customer ORDER BY CusId DESC LIMIT 1");
        String currentCustomerId = null;

        if (resultSet.next()){
            currentCustomerId = resultSet.getString(1);
            return  splitCustomerId(currentCustomerId);
        }
        return splitCustomerId(currentCustomerId);
    }

    @Override
    public Customer search(String id) throws SQLException {

     ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM Customer WHERE CusId = ?", id);

        Customer entity  =null;

        if (resultSet.next()){
            entity = new Customer(
             resultSet.getString("cusId"),
              resultSet.getString("firstName"),
             resultSet.getString("lastName"),
             resultSet.getString("address"),
             resultSet.getString("email"),
             resultSet.getString("mobileNo")
            );

        }
        return entity;
    }

    @Override
    public String searchCustomerId(String cusNum) throws SQLException {


      ResultSet resultSet = SQLUtil.crudUtil("SELECT CusId FROM Customer WHERE mobileNo =?", cusNum);
        resultSet.next();

        return resultSet.getString("cusId");
    }

    private String splitCustomerId(String currentCustomerId) {

        if (currentCustomerId!=null){
            String [] split = currentCustomerId.split("C");
            int selectedId = Integer.parseInt(split[1]);

            if (selectedId<9){
                selectedId++;
                return "C00"+selectedId;
            }else if (selectedId<99){
                selectedId++;
                return "C0"+selectedId;
            }else {
                selectedId++;
                return "C"+selectedId;
            }
        }


        return "C001";
    }
}
