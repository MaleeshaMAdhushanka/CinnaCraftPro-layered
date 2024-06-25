package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDto;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.CinnamonBookTypeDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CinnamonBookTypeDAOImpl implements CinnamonBookTypeDAO {

    @Override
    public ArrayList<CinnamonBookTypeDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(CinnamonBookTypeDto dto) throws SQLException {

        return SQLUtil.crudUtil( "INSERT INTO Cinnamon_book_type_details VALUES(?,?,?,?,?)",

          dto.getCinnamonBookTypeId(),
          Date.valueOf(dto.getDate()),
          dto.getTypeId(),
          dto.getAmount(),
          dto.isConfirmed()
          );


    }

    @Override
    public boolean update(CinnamonBookTypeDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String  id) throws SQLException{

      return SQLUtil.crudUtil( "DELETE FROM  Cinnamon_book_type_details WHERE CinnamonBookTypeID =?", id);


    }
    @Override
    public String generateId() throws SQLException {

        ResultSet resultSet =SQLUtil.crudUtil("SELECT CinnamonBookTypeID FROM  Cinnamon_book_type_details  ORDER BY CinnamonBookTypeID DESC LIMIT 1 ");

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

    @Override
    public CinnamonBookTypeDto search(String id) throws SQLException {
        return null;
    }

    public List<CinnamonBookTypeDto> getAllTeaBookTypeDetails(String date) throws SQLException {

     ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM Cinnamon_book_type_details WHERE date = ?", date);

        ArrayList<CinnamonBookTypeDto> cinnamonBookTypeList = new ArrayList<>();

        while (resultSet.next()) {
          cinnamonBookTypeList.add( new CinnamonBookTypeDto(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getBoolean(5)
            ));


        }
        return  cinnamonBookTypeList;

    }



    //trans Action

    public boolean ConfirmCinnamonBook(LocalDate date) throws SQLException{

     return SQLUtil.crudUtil("UPDATE  Cinnamon_book_type_details SET isConfirmed = true WHERE date =?", date);

    }
    public List<CinnamonBookTypeDetailDto> getTotalAmount(LocalDate date) throws SQLException{


        ResultSet resultSet = SQLUtil.crudUtil("SELECT typeID,SUM(amount) FROM Cinnamon_book_type_details WHERE date = ? AND isConfirmed = false group by typeID", date);

        ArrayList<CinnamonBookTypeDetailDto> cinnamonBookTypeDetailsDtoList = new ArrayList<>();

        while(resultSet.next()){
            cinnamonBookTypeDetailsDtoList.add(new CinnamonBookTypeDetailDto(
           resultSet.getString(1),
            resultSet.getDouble(2)
            ));

        }
        return  cinnamonBookTypeDetailsDtoList;

    }


}
