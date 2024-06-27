package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.Dto.PackingDetailsDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PackagingDetailsBO extends SuperBo {

     String generateNextPackId() throws SQLException ;

     boolean addPackingDetails(PackingDetailsDto dto) throws  SQLException ;


     double getTotalDescreasedAmount(String CinnamonTypeId)  throws SQLException ;
     List<PackingDetailsDto> loadAllPackagingDetails(LocalDate date) throws SQLException ;

     boolean deletePackageDetails(String packageDetailsId) throws SQLException ;

     List<PackingCountAmountDto> getTotalCountAmount(LocalDate parse) throws SQLException ;

     boolean confirmPackaging(LocalDate date) throws SQLException;

     boolean confirmPackaging(LocalDate date, List<PackingCountAmountDto> dtoList) throws SQLException;
}
