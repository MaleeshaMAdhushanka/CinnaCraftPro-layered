package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.PlaceFertilizerOrderDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.*;

public interface FertilizerOrderBo  extends SuperBo {
     String generateNextFertilizerOrderId() throws SQLException;

     boolean saveFertilizerOrder(PlaceFertilizerOrderDto dto) throws SQLException;
     boolean placeFertilizerOrder(PlaceFertilizerOrderDto dto) throws SQLException;
}
