package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Dto.UserDto;
import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserModel {

    public static String userName;

    public boolean saveUser(UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO user VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getUsername());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3, dto.getEmail());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;


    }


    public boolean searchUser(String userName) throws SQLException {
      Connection connection =  DbConnection.getInstance().getConnection();
      String sql = "SELECT * FROM user WHERE username= ?";
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1, userName);

      return  pstm.executeQuery().next();
    }

    public boolean searchEmailAndUsername(String userName, String email)throws SQLException {

       Connection connection =  DbConnection.getInstance().getConnection();
       String sql = "SELECT * FROM user WHERE username=? AND  email=?";

       PreparedStatement pstm = connection.prepareStatement(sql);

       pstm.setString(1, userName);
       pstm.setString(2, email);

       return pstm.executeQuery().next();


    }

    public boolean updatePassword(String userName, String password) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE user SET password=? WHERE username=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, password);
        pstm.setString(2, userName);

        return  pstm.executeUpdate() > 0;
    } 

    public boolean searchEmail(String email) throws  SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM user WHERE email=?";

        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, email);

        return pstm.executeQuery().next();
    }

    public boolean searchUsernameAndPassword(String userName, String password) throws SQLException{
       Connection connection =  DbConnection.getInstance().getConnection();

       String  sql = "SELECT * FROM user WHERE username=? AND password=?";

      PreparedStatement pstm =  connection.prepareStatement(sql);

      pstm.setString(1, userName);
      pstm.setString(2, password);

      return  pstm.executeQuery().next();
    }
}
