package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.UserDto;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {


    @Override
    public ArrayList<UserDto> getAll() throws SQLException {
        return null;
    }


    @Override
    public boolean save(UserDto dto) throws SQLException {
        return SQLUtil.crudUtil( "INSERT INTO user VALUES(?, ?, ?)",

        dto.getUsername(),
        dto.getPassword(),
        dto.getEmail()
        );

    }

    @Override
    public boolean update(UserDto dto) throws SQLException {
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
        return "";
    }

    @Override
    public UserDto search(String id) throws SQLException {
        return null;
    }

    public boolean searchUser(String userName) throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM user WHERE username= ?", userName);

        return  resultSet.next();
    }

    public boolean searchEmailAndUsername(String userName, String email)throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM user WHERE username=? AND  email=?", userName, email);
       return  resultSet.next();


    }

    public boolean updatePassword(String userName, String password) throws SQLException{


       return SQLUtil.crudUtil( "UPDATE user SET password=? WHERE username=?", userName, password);


    }

    public boolean searchEmail(String email) throws  SQLException{

        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM user WHERE email=?", email);
        return resultSet.next();

    }

    public boolean searchUsernameAndPassword(String userName, String password) throws SQLException{
        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM user WHERE username=? AND password=?",userName, password);
        return resultSet.next();

    }

}
