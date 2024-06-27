package lk.ijse.CinnaCraft.Util;

import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtil {
    private  static Connection connection;

    static {
        try {
            connection = DbConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static  void autoCommitFalse() throws SQLException{
        connection.setAutoCommit(false);
    }
    public static void commit() throws SQLException{
        connection.commit();
        connection.setAutoCommit(true);
    }
    public static void rollback()throws  SQLException{
        connection.rollback();
        connection.setAutoCommit(true);
    }



}
