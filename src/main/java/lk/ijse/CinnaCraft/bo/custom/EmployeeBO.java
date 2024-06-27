package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.EmployeeDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBo {

     boolean saveEmployee(EmployeeDto dto) throws SQLException;
     String generateNextEmployeeId() throws SQLException;
     boolean updateEmployee(EmployeeDto employee) throws  SQLException;
     EmployeeDto searchEmployee(String empID) throws SQLException ;

     List<EmployeeDto> getAllEmployee()throws  SQLException ;

     boolean deleteEmployee(String empID) throws SQLException ;



}
