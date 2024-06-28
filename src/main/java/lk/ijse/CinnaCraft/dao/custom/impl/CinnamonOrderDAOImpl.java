package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.CinnamonBarkStockDAO;
import lk.ijse.CinnaCraft.dao.custom.CinnamonOrderDAO;
import lk.ijse.CinnaCraft.entity.CinnamonOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CinnamonOrderDAOImpl  implements CinnamonOrderDAO {
    @Override
    public String generateNextOrderId()throws SQLException {

     ResultSet resultSet = SQLUtil.crudUtil("SELECT Cinnamon_order_ID FROM Cinnamon_orders  ORDER BY Cinnamon_order_ID DESC LIMIT 1");
        String currentOrderId = null;

        if (resultSet.next()) {
            currentOrderId = resultSet.getString(1);
            return  splitOrderId(currentOrderId);
        }
        return splitOrderId(currentOrderId);

    }
    private String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String [] split = currentOrderId.split("O");
            int seletedId = Integer.parseInt(split[1]);

            if (seletedId < 9) {
                seletedId++;
                return "O00"+ seletedId;
            }else if (seletedId < 99){
                seletedId ++;
                return "O0" + seletedId;
            }else {
                seletedId++;
                return "O" + seletedId;
            }
        }
        return "O001";

    }

    @Override
    public  boolean saveOrder(CinnamonOrder entity) throws SQLException{

      return SQLUtil.crudUtil("INSERT INTO Cinnamon_orders VALUES(?, ?, ?)",
           entity.getCinnamon_order_ID(),
              entity.getCusID(),
              entity.getDate());
    }

    @Override
    public int getOrderCount(String date) throws SQLException{


       ResultSet resultSet = SQLUtil.crudUtil( "SELECT COUNT(Cinnamon_order_ID) FROM Cinnamon_orders WHERE date=?", date);
        int count = 0;

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;


    }


}
