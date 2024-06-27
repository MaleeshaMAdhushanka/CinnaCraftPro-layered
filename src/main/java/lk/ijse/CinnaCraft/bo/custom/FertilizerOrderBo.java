package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.PlaceFertilizerOrderDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.*;

public interface FertilizerOrderBo  extends SuperBo {
    public String generateNextFertilizerOrderId() throws SQLException;

    public boolean saveFertilizerOrder(PlaceFertilizerOrderDto dto) throws SQLException;
    public boolean placeFertilizerOrder(PlaceFertilizerOrderDto dto) throws SQLException;
}
