package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface UserDAO  extends CrudDAO<User> {

     boolean searchUser(String userName) throws SQLException;

     boolean searchEmailAndUsername(String userName, String email)throws SQLException;

     boolean updatePassword(String userName, String password) throws SQLException;

     boolean searchEmail(String email) throws  SQLException;

     boolean searchUsernameAndPassword(String userName, String password) throws SQLException;

}
