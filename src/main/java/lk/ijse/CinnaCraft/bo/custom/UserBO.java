package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.UserDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface UserBO  extends SuperBo {


     boolean saveUser(UserDto dto) throws SQLException;


     boolean searchUser(String userName) throws SQLException ;

     boolean searchEmailAndUsername(String userName, String email)throws SQLException ;

     boolean updatePassword(String userName, String password) throws SQLException ;

     boolean searchEmail(String email) throws  SQLException ;

     boolean searchUsernameAndPassword(String userName, String password) throws SQLException ;
}
