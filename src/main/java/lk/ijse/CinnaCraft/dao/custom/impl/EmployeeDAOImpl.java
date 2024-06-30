package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.EmployeeDAO;
import lk.ijse.CinnaCraft.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {



    @Override
    public ArrayList<Employee> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM employee");
        ArrayList<Employee> allEmployee = new ArrayList<>();



        while (resultSet.next()) {
            Employee dto = new Employee(
                    resultSet.getString("empID"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("Address"),
                    resultSet.getString("Sex"),
                    resultSet.getString("mobileNo"));
            allEmployee .add(dto);

        }
        return allEmployee;


    }
     @Override
    public boolean save(Employee dto) throws SQLException{

      return SQLUtil.crudUtil("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)", dto.getEmpID(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getSex(), dto.getMobileNo());


    }

    @Override
    public boolean update(Employee dto) throws  SQLException{


         return  SQLUtil.crudUtil("UPDATE employee SET firstName = ? , lastName = ? , address = ?, sex = ?,  mobileNo = ? WHERE empID = ?", dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getSex(), dto.getMobileNo(), dto.getEmpID());

    }

    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }
    public boolean delete(String id) throws SQLException{

          return  SQLUtil.crudUtil("DELETE FROM employee WHERE empID = ?", id);

    }

    @Override
    public String generateId() throws SQLException {
    ResultSet resultSet = SQLUtil.crudUtil( "SELECT empID FROM employee ORDER BY empID DESC LIMIT 1");

        String currentEmployeeId = null;

        if (resultSet.next()){
            currentEmployeeId= resultSet.getString(1);
            return splitEmployeeId (currentEmployeeId);
        }
        return splitEmployeeId(currentEmployeeId);

    }

    @Override
    public Employee search(String id) throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM employee WHERE empID = ?", id);

        Employee entity = null;

        if (resultSet.next()){
            entity = new Employee(resultSet.getString("empID"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("address"),
                    resultSet.getString("sex"),
                    resultSet.getString("mobileNo")
            );
        }

        return entity;
    }
    private String splitEmployeeId(String currentEmployeeId) {

        if (currentEmployeeId!=null){
            String [] split = currentEmployeeId.split("E");
            int selectedId = Integer.parseInt(split[1]);

            if (selectedId<9) {
                selectedId++;
                return "E00" + selectedId;
            }else if (selectedId<99) {
                selectedId++;
                return "E0" + selectedId;
            }else {
                selectedId++;
                return "E" + selectedId;
            }
        }
        return "E001";
    }




}
