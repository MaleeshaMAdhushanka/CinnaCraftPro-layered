package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.Tm.SalesCartTm;
import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.entity.Packaging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PackagingDAO extends CrudDAO<Packaging> {
     List<Packaging> getAllPackaging (String cinnamonTypeId) throws SQLException;

     String getPackId(String cinnamonTypeId, String packSize) throws  SQLException;

     boolean updatePackagingCount(List<PackingCountAmountDto> dtoList) throws SQLException;


     String getTypeId(String packId) throws SQLException;


     boolean updatePackaging(List<SalesCartTm> tmList) throws SQLException;


     boolean updatePackagingQty(SalesCartTm tm)  throws  SQLException;

     boolean updatedPack(String packId, String typeId, String size, double price) throws  SQLException;


}

