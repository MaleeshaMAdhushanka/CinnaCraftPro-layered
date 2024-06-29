package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.SupplierDto;
import lk.ijse.CinnaCraft.bo.custom.SupplierBO;
import lk.ijse.CinnaCraft.dao.DAOFactory;
import lk.ijse.CinnaCraft.dao.custom.SupplierDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.CinnaCraft.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBoImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.save(new Supplier(
                dto.getSupID(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAddress(),
                dto.getBank(),
                dto.getBankNo(),
                dto.getMobileNo()
        ));
    }

    @Override
    public String generateNextSupplierId() throws SQLException {
        return supplierDAO.generateId();
    }

    @Override
    public boolean deleteSupplier(String supplierid) throws SQLException {
        return supplierDAO.delete(supplierid);
    }

    @Override
    public List<SupplierDto> getAllSuppliers() throws SQLException {
        List<Supplier> supplierList = supplierDAO.getAll();
        List<SupplierDto> dtoList = new ArrayList<>();
        for (Supplier supplier: supplierList) {
            dtoList.add(new SupplierDto(
                    supplier.getSupID(),
                    supplier.getFirstName(),
                    supplier.getLastName(),
                    supplier.getAddress(),
                    supplier.getBank(),
                    supplier.getBankNo(),
                    supplier.getMobileNo()
            ));
        }
        return dtoList;
    }

    @Override
    public SupplierDto searchSupplier(String SupID) throws SQLException {
        Supplier supplier = supplierDAO.search(SupID);
        if (supplier != null) {
            return  new SupplierDto(
                    supplier.getSupID(),
                    supplier.getFirstName(),
                    supplier.getLastName(),
                    supplier.getAddress(),
                    supplier.getBank(),
                    supplier.getBankNo(),
                    supplier.getMobileNo()
            );
        }
        return null;
    }

    @Override
    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException {
        return  supplierDAO.update(new Supplier(
                supplierDto.getSupID(),
                supplierDto.getFirstName(),
                supplierDto.getLastName(),
                supplierDto.getAddress(),
                supplierDto.getBank(),
                supplierDto.getBankNo(),
                supplierDto.getMobileNo()
        ));
    }

    @Override
    public String getSupplierName(String SupID) throws SQLException {
        return supplierDAO.getSupplierName(SupID);
    }
}
