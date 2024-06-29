package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.SupplierDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO  extends SuperBo {
     List<SupplierDto> getAllSuppliers() throws SQLException;


     boolean saveSupplier(SupplierDto dto) throws SQLException ;

     String generateNextSupplierId() throws SQLException ;


      boolean deleteSupplier(String supplierid) throws SQLException;

     boolean updateSupplier(SupplierDto supplierDto) throws SQLException ;

     SupplierDto searchSupplier(String SupID) throws SQLException ;
     String getSupplierName(String SupID) throws SQLException ;
}
