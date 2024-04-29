package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.CustomerDto;
import lk.ijse.Dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    public String generateNextEmployeeId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT empID FROM employee ORDER BY empID DESC LIMIT 1";
        PreparedStatement pstm= connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        String currentEmployeeId = null;

        if (resultSet.next()){
            currentEmployeeId= resultSet.getString(1);
            return splitEmployeeId (currentEmployeeId);
        }
        return splitEmployeeId(currentEmployeeId);

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
    public boolean saveEmployee(EmployeeDto dto) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEmpID());
        pstm.setString(2, dto.getFirstName());
        pstm.setString(3, dto.getLastName());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getSex());
        pstm.setString(6, dto.getMobileNo());

        boolean isSaved = pstm.executeUpdate() > 0;

        return  isSaved;

    }

    public List<EmployeeDto> getAllEmployee()throws  SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);


        List<EmployeeDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();


        while (resultSet.next()) {
            String empID = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String Address = resultSet.getString(4);
            String Sex = resultSet.getString(5);
            String mobileNo = resultSet.getString(6);


            var dto = new EmployeeDto(empID, firstName, lastName, Address, Sex, mobileNo);
            dtoList.add(dto);
        }
        return dtoList;


    }

    public boolean deleteEmployee(String id) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();
         String sql = "DELETE FROM employee WHERE empID = ?";

         PreparedStatement pstm = connection.prepareStatement(sql);
         pstm.setString(1, id);

         return pstm.executeUpdate() > 0;

    }
    public EmployeeDto searchEmployee(String empID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();;

        String sql = "SELECT * FROM employee WHERE empID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, empID);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto = null;

        if (resultSet.next()){
            String empId = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String sex = resultSet.getString(5);
            String mobileNO = resultSet.getString(6);

            dto = new EmployeeDto(empId,firstName,lastName,address,sex,mobileNO);
        }

        return dto;
    }
    public boolean updateEmployee(EmployeeDto employeeDto) throws  SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE employee SET firstName = ? , lastName = ? , address = ?, sex = ?, mobileNo = ? WHERE empID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, employeeDto.getFirstName());
        pstm.setString(2, employeeDto.getLastName());
        pstm.setString(3, employeeDto.getAddress());
        pstm.setString(4, employeeDto.getSex());
        pstm.setString(5, employeeDto.getMobileNo());
        pstm.setString(6, employeeDto.getEmpID());

        return pstm.executeUpdate()> 0;

    }

}
