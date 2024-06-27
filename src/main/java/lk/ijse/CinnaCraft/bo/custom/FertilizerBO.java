package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.FertilizerDto;
import lk.ijse.CinnaCraft.Tm.FertilizeSalesCartTm;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface FertilizerBO extends SuperBo {
    public String generateNextFertilizerId() throws SQLException ;

    public  boolean saveFertilizer(FertilizerDto dto) throws  SQLException ;

    public List<FertilizerDto> getAllFertilizer() throws SQLException ;

    FertilizerDto getFertilizer(String fertilizerId) throws SQLException;
    public boolean deleteFertilizer(String fertilizerId) throws  SQLException;
    public boolean updateFertilizer(FertilizerDto dto) throws SQLException ;

    public FertilizerDto searchFertilizer(String fertilizerId) throws SQLException ;

    public boolean updateFertilizer(List<FertilizeSalesCartTm> tmList) throws SQLException ;

     boolean updateQty(FertilizeSalesCartTm cartTm) throws SQLException ;
}
