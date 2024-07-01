package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Dto.SupplierDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO  extends SuperBo {
     List<SupplierDto> getAllSuppliers() throws SQLException;


     boolean saveSupplier(SupplierDto dto) throws SQLException ;

     String generateNextSupplierId() throws SQLException ;


      boolean deleteSupplier(String id) throws SQLException;

     boolean updateSupplier(SupplierDto dto) throws SQLException ;

     SupplierDto searchSupplier(String SupID) throws SQLException ;
     String getSupplierName(String SupID) throws SQLException ;
}
