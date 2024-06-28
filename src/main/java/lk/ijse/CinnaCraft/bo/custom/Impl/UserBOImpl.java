package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.UserDto;
import lk.ijse.CinnaCraft.bo.custom.UserBO;
import lk.ijse.CinnaCraft.dao.custom.UserDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.UserDAOImpl;
import lk.ijse.CinnaCraft.entity.User;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    public static String userName;

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean saveUser(UserDto dto) throws SQLException {
        return  userDAO.save(new User(
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail()
        ));
    }

    @Override
    public boolean searchUser(String userName) throws SQLException {
        return userDAO.searchUser(userName);
    }

    @Override
    public boolean searchEmailAndUsername(String userName, String email) throws SQLException {
        return userDAO.searchEmailAndUsername(userName, email);
    }

    @Override
    public boolean updatePassword(String userName, String password) throws SQLException {
        return userDAO.updatePassword(userName, password);
    }

    @Override
    public boolean searchEmail(String email) throws SQLException {
        return userDAO.searchEmail(email);
    }

    @Override
    public boolean searchUsernameAndPassword(String userName, String password) throws SQLException {
        return userDAO.searchUsernameAndPassword(userName, password);
    }
}
