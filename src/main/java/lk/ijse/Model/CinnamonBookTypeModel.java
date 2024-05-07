package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.Dto.CinnamonBookTypeDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CinnamonBookTypeModel {





    public String generateNextCinnamonBookTypeId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT CinnamonBookTypeID FROM  Cinnamon_book_type_details  ORDER BY CinnamonBookTypeID DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        String currentCinnamonBookTypeId = null;

        if (resultSet.next()) {
            currentCinnamonBookTypeId = resultSet.getString(1);

            return  splitCinnamonBookTypeId(currentCinnamonBookTypeId);
        }
        return splitCinnamonBookTypeId(currentCinnamonBookTypeId);



    }

    private String splitCinnamonBookTypeId(String currentCinnamonBookTypeId) {

        if (currentCinnamonBookTypeId != null){
            String[] split = currentCinnamonBookTypeId.split("CBT-");
            int selectedId = Integer.parseInt(split[1]);
            if (selectedId < 9){
                selectedId++;
                return "CBT-00"+selectedId;
            }else if (selectedId < 99){
                selectedId++;
                return "CBT-00"+selectedId;
            }else {
                selectedId++;
                return "CBT-"+selectedId;
            }

        }

        return "CBT-001";
    }
    public boolean saveCinnamonBookType(CinnamonBookTypeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Cinnamon_book_type_details VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getCinnamonBookTypeId());
        pstm.setDate(2, Date.valueOf(dto.getDate()));
        pstm.setString(3,dto.getTypeId());
        pstm.setDouble(4,dto.getAmount());
        pstm.setBoolean(5,dto.isConfirmed());

        return pstm.executeUpdate()>0;

    }


    public List<CinnamonBookTypeDto> getAllTeaBookTypeDetails(String date) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Cinnamon_book_type_details WHERE date = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, date);

        ResultSet resultSet = pstm.executeQuery();

        List<CinnamonBookTypeDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            CinnamonBookTypeDto dto = new CinnamonBookTypeDto(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getBoolean(5)
            );

            dtoList.add(dto);
        }
         return  dtoList;

    }
    public boolean deleteCinnamonBookType(String  CinnamonBookTypeID) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM  Cinnamon_book_type_details WHERE CinnamonBookTypeID =?";

        PreparedStatement  pstm = connection.prepareStatement(sql);

        pstm.setString(1, CinnamonBookTypeID);

        return pstm.executeUpdate()>0;

    }

    //trans Action

    public boolean ConfirmCinnamonBook(LocalDate date) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE  Cinnamon_book_type_details SET isConfirmed = true WHERE date =?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setDate(1, Date.valueOf(date));

        return  pstm.executeUpdate() >0;


    }
    public List<CinnamonBookTypeDetailDto> getTotalAmount(LocalDate date) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT typeID,SUM(amount) FROM Cinnamon_book_type_details WHERE date = ? AND isConfirmed = false group by typeID";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setDate(1, Date.valueOf(date));

        List<CinnamonBookTypeDetailDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();


        while(resultSet.next()){
            String typeID = resultSet.getString(1);
            double amount = resultSet.getDouble(2);

            CinnamonBookTypeDetailDto dto = new CinnamonBookTypeDetailDto(typeID, amount);
            dtoList.add(dto);
        }
        return  dtoList;

    }





}


