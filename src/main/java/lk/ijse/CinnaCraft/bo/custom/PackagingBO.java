package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.PackagingDto;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.Tm.SalesCartTm;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PackagingBO extends SuperBo {
     List<PackagingDto> getAllPackaging (String cinnamonType) throws SQLException;


     String getPackId(String cinnamonTypeId, String packSize) throws  SQLException ;

     PackagingDto searchPackaging(String packId) throws SQLException ;
     boolean updatePackagingCount(List<PackingCountAmountDto> dtoList) throws SQLException ;

     String getTypeId(String packId) throws SQLException ;
     boolean updatePackaging(List<SalesCartTm> tmList) throws SQLException;

     boolean updatePackagingQty(SalesCartTm tm)  throws  SQLException ;

     List<PackagingDto> getAllPackaging() throws SQLException ;

     String generateNextPackId() throws SQLException ;

     boolean deletePackage(String packId) throws SQLException ;
      boolean addPackage(PackagingDto dto) throws  SQLException ;

     boolean updatedPack(String packId, String typeId, String size, double price) throws  SQLException ;

//     boolean updatePackageCount (String packId,int count) throws SQLException ;

}
