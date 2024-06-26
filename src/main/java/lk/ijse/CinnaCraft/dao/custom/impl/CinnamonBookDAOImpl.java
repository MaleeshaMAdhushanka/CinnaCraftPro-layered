package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.CinnamonBookDAO;
import lk.ijse.CinnaCraft.entity.CinnamonBook;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CinnamonBookDAOImpl implements CinnamonBookDAO {

    @Override
    public ArrayList<CinnamonBook> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM Cinnamon_Book order by date");

        ArrayList<CinnamonBook> cinnamonBooks = new ArrayList<>();

        while (resultSet.next()) {
            cinnamonBooks.add(new CinnamonBook(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3)
            ));

        }
        return cinnamonBooks;

    }

    @Override
    public boolean save(CinnamonBook dto) throws SQLException {
        return SQLUtil.crudUtil("INSERT INTO Cinnamon_Book VALUES(?,?,?)",
                dto.getCinnamonBookId(),
                dto.getDailyAmount(),
                dto.getDate());

    }

    @Override
    public boolean update(CinnamonBook dto) throws SQLException {
        return false;
    }

    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }


    @Override
    public String generateId() throws SQLException {


        ResultSet resultSet = SQLUtil.crudUtil("SELECT CinnamonBookId  FROM Cinnamon_Book ORDER BY CinnamonBookId DESC LIMIT 1");

        String currentCinnamonBookId = null;

        if (resultSet.next()) {
            currentCinnamonBookId = resultSet.getString(1);
            return splitCinnamonBookId(currentCinnamonBookId);

        }
        return splitCinnamonBookId(currentCinnamonBookId);
    }

    private String splitCinnamonBookId(String currentCinnamonBookId) {

        if (currentCinnamonBookId != null) {
            String[] split = currentCinnamonBookId.split("CB");
            int selectedId = Integer.parseInt(split[1]);
            if (selectedId < 9) {
                selectedId++;
                return "CB00" + selectedId;
            } else if (selectedId < 99) {
                selectedId++;
                return "CB0" + selectedId;
            } else {
                selectedId++;
                return "CB" + selectedId;
            }
        }
        return "CB001";
    }

    @Override
    public CinnamonBook search(String id) throws SQLException {
        return null;
    }

    public boolean updateCinnamonBookAmount(String CinnamonBookId, double amount) throws SQLException {

       return SQLUtil.crudUtil( "UPDATE Cinnamon_Book SET dailyAmount = ? WHERE CinnamonBookId =?",
               amount,
               CinnamonBookId);


    }

    public String getCinnamonBookId(String date) throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil("SELECT CinnamonBookId FROM Cinnamon_Book WHERE date=?", date);

        resultSet.next();

        return resultSet.getString(1);

    }

    public String getCinnamonBookDate(String CinnamonBookId) throws SQLException {


       ResultSet resultSet = SQLUtil.crudUtil("SELECT date FROM Cinnamon_Book WHERE CinnamonBookId=?",CinnamonBookId);


        resultSet.next();

        return resultSet.getString(1);
    }

    public boolean searchDate(String date) throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM  Cinnamon_Book WHERE date=?", date);

        return resultSet.next();


    }

    public boolean createCinnamonBookRecord(String date) throws SQLException {

        return SQLUtil.crudUtil( "INSERT INTO Cinnamon_Book VALUES(?, ?, ?)",
         generateId(),
                0.0,
                date);

    }


    public double getAmount(String date) throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil( "SELECT dailyAmount FROM Cinnamon_Book WHERE date=?", date);

        if (resultSet.next()) {
           resultSet.getDouble(1);
        }
        return 0;
    }


}
