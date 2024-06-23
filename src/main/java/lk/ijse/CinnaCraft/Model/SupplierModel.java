package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Dto.SupplierDto;
import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {


    public List<SupplierDto> getAllSuppliers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Cinnamon_Supplier";

        PreparedStatement pstm = connection.prepareStatement(sql);

        List<SupplierDto> dtoList = new ArrayList<>();


        ResultSet resultSet = pstm.executeQuery();


        while (resultSet.next()) {
            String supId = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String bank = resultSet.getString(5);
            String bankNo = resultSet.getString(6);
            String mobileNo = resultSet.getString(7);

            var dto = new SupplierDto(supId, firstName, lastName, address, bank, bankNo, mobileNo);
            dtoList.add(dto);
        }

        return dtoList;
    }


    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Cinnamon_Supplier VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSupId());
        pstm.setString(2, dto.getFirstName());
        pstm.setString(3, dto.getLastName());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getBank());
        pstm.setString(6, dto.getBankNo());
        pstm.setString(7, dto.getMobileNo());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public String generateNextSupplierId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT SupId FROM Cinnamon_Supplier  ORDER BY SupId DESC LIMIT 1";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        String currentSupplierId = null;

        if (resultSet.next()) {
            currentSupplierId = resultSet.getString(1);
            return splitSupplierId(currentSupplierId);
        }

        return splitSupplierId(currentSupplierId);

    }


    private String splitSupplierId(String currentSupplierId) {

        if (currentSupplierId != null) {
            String[] split = currentSupplierId.split("S");
            int selectedId = Integer.parseInt(split[1]);

            if (selectedId < 9) {
                selectedId++;
                return "S00" + selectedId;
            } else if (selectedId < 99) {
                selectedId++;
                return "S0" + selectedId;
            } else {
                selectedId++;
                return "S" + selectedId;
            }
        }
        return "S001";
    }

    public static boolean deleteSupplier(String supplierid) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM Cinnamon_Supplier WHERE SupId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplierid);

        return pstm.executeUpdate() > 0;
    }

    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Cinnamon_Supplier SET SupID = ? , firstName = ? , lastName = ?, address = ?, bank = ?, bankNo = ?, mobileNo = ?  WHERE CusId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplierDto.getFirstName());
        pstm.setString(2, supplierDto.getLastName());
        pstm.setString(3, supplierDto.getAddress());
        pstm.setString(4, supplierDto.getBank());
        pstm.setString(5, supplierDto.getBankNo());
        pstm.setString(6, supplierDto.getMobileNo());
        pstm.setString(7, supplierDto.getSupId());

        return pstm.executeUpdate() > 0;

    }

    public SupplierDto searchSupplier(String supId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Cinnamon_Supplier WHERE SupId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, supId);

        ResultSet resultSet = pstm.executeQuery();

        SupplierDto dto = null;

        if (resultSet.next()) {
            String supID = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String bank = resultSet.getString(5);
            String bankNo = resultSet.getString(6);
            String mobileNo = resultSet.getString(7);

            dto = new SupplierDto(supID, firstName, lastName, address, bank, bankNo, mobileNo);
        }

        return dto;
    }
    public String getSupplierName(String supId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT firstName FROM Cinnamon_Supplier WHERE SupId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supId);

        ResultSet resultSet = pstm.executeQuery();

        String name = null;

        if (resultSet.next()) {
            name = resultSet.getString(1);
        }

        return name;
    }

}
