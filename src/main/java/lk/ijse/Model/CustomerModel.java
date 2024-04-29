package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {


    public String generateNextCustomerId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT CusId FROM Customer ORDER BY CusId DESC LIMIT 1";
        PreparedStatement pstm= connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        String currentCustomerId = null;

        if (resultSet.next()){
            currentCustomerId = resultSet.getString(1);
            return  splitCustomerId(currentCustomerId);
        }
        return splitCustomerId(currentCustomerId);

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

    public boolean saveCustomer(CustomerDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Customer VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getCusId());
        pstm.setString(2, dto.getFirstName());
        pstm.setString(3, dto.getLastName());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getEmail());
        pstm.setString(6, dto.getMobileNo());

        boolean isSaved = pstm.executeUpdate()>0;

        return isSaved;

    }

    public List<CustomerDto> getAllCustomers() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Customer";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<CustomerDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();


        while (resultSet.next()) {
            String cusId= resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String mobileNo = resultSet.getString(6);

            var dto = new CustomerDto(cusId,firstName,lastName,address,email,mobileNo);
            dtoList.add(dto);
        }

        return dtoList;
    }



    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Customer WHERE CusId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public CustomerDto searchCustomer(String cusId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Customer WHERE CusId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,cusId);

        ResultSet resultSet = pstm.executeQuery();

        CustomerDto dto=null;

        if (resultSet.next()){
            String cus_Id = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String mobileNO = resultSet.getString(6);

            dto = new CustomerDto(cus_Id,firstName,lastName,address,email,mobileNO);
        }

        return dto;
    }

    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Customer SET firstName = ? , lastName = ? , address = ?, email = ?, mobileNo = ? WHERE CusId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, customerDto.getFirstName());
        pstm.setString(2, customerDto.getLastName());
        pstm.setString(3, customerDto.getAddress());
        pstm.setString(4, customerDto.getEmail());
        pstm.setString(5, customerDto.getMobileNo());
        pstm.setString(6, customerDto.getCusId());

        return pstm.executeUpdate()>0;

    }





}
