package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.PackingCountAmountDto;
import lk.ijse.Dto.PackingDetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PackagingDetailsModel {


    public String generateNextPackId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT packagingDetailsId FROM  packaging_details ORDER BY  packagingDetailsId DESC LIMIT 1";
           PreparedStatement pstm =  connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        String currentPackingId = null;

        if (resultSet.next()) {
            currentPackingId = resultSet.getString(1);
            return splitPackingId(currentPackingId);

        }
        return splitPackingId(currentPackingId);

    }

    private String splitPackingId(String currentPackingId){
        if (currentPackingId != null) {
            String [] split = currentPackingId.split("PD");
           int selectedId = Integer.parseInt(split[1]);

            if (selectedId < 9) {
                selectedId++;
                return "PD00" + selectedId;
            }else if(selectedId < 99){
                selectedId++;
                return "PD0" + selectedId;
            }else {
                selectedId++;
                return "PD" + selectedId;
            }

        }
        return "PD001";
    }

    public boolean addPackingDetails(PackingDetailsDto dto) throws  SQLException{

        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INO packaging_details VALUES(?, ?, ?, ?, ?,?) ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPackagingDetailsId());
        pstm.setString(2, String.valueOf(java.sql.Date.valueOf(dto.getDate())));
        pstm.setString(3, dto.getPackId());
        pstm.setString(4, String.valueOf(dto.getCount()));
        pstm.setDouble(5, dto.getAmount());
        pstm.setBoolean(6, dto.isConfirmed());

       return pstm.executeUpdate()>0;

    }


    public double getTotalDescreasedAmount(String CinnamonTypeId)  throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT SUM(amount) FROM packaging_details WHERE packId IN(SELECT packId FROM  packing WHERE typeId=?) AND isConfirmed =0 ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, CinnamonTypeId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return  resultSet.getDouble(1);
        }
        return  0;

    }
    public List<PackingDetailsDto> loadAllPackagingDetails(LocalDate date) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM packaging_details WHERE date=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setDate(1, java.sql.Date.valueOf(date));

        ResultSet resultSet = pstm.executeQuery();

        List<PackingDetailsDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            PackingDetailsDto dto = new PackingDetailsDto(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getBoolean(6)
            );

            dtoList.add(dto);

        }

          return dtoList;
         }

    public boolean deletePackageDetails(String packageDetailsId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM packaging_details WHERE packagingDetailsId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,packageDetailsId);

        return pstm.executeUpdate()>0;

    }
    public List<PackingCountAmountDto> getTotalCountAmount(LocalDate parse) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT packId,SUM(count),SUM(amount) FROM packaging_details WHERE date=? AND isConfirmed=0 GROUP BY packId";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setDate(1, java.sql.Date.valueOf(parse));

        List<PackingCountAmountDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            PackingCountAmountDto dto = new PackingCountAmountDto(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getDouble(3)
            );
            dtoList.add(dto);
        }
        return dtoList;

    }

    public boolean confirmPackaging(LocalDate parse) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE packaging_details SET isConfirmed=1 WHERE date=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setDate(1,java.sql.Date.valueOf(parse));

        return pstm.executeUpdate()>0;

    }





}
