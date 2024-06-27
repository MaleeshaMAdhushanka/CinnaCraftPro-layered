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
    public List<SupplierDto> getAllSuppliers() throws SQLException;


    public boolean saveSupplier(SupplierDto dto) throws SQLException ;

    public String generateNextSupplierId() throws SQLException ;


    public  boolean deleteSupplier(String supplierid) throws SQLException;

    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException ;

    public SupplierDto searchSupplier(String supId) throws SQLException ;
    public String getSupplierName(String supId) throws SQLException ;
}
