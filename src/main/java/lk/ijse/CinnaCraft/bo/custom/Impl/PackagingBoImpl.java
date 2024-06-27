package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.PackagingDto;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.Tm.SalesCartTm;
import lk.ijse.CinnaCraft.bo.custom.PackagingBO;
import lk.ijse.CinnaCraft.dao.custom.PackagingDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.PackagingDAOImpl;
import lk.ijse.CinnaCraft.entity.Packaging;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackagingBoImpl implements PackagingBO {

    PackagingDAO packagingDAO = new PackagingDAOImpl();

    @Override
    public List<PackagingDto> getAllPackaging(String cinnamonType) throws SQLException {
        List<Packaging> packagingList = packagingDAO.getAllPackaging(cinnamonType);
        List<PackagingDto> dtoList = new ArrayList<>();
        for (Packaging packaging : packagingList) {
            dtoList.add(new PackagingDto(
                    packaging.getPackId(),
                    packaging.getTypeId(),
                    packaging.getDescription(),
                    packaging.getPackageCount(),
                    packaging.getPrice()
            ));
        }
        return dtoList;
    }

    @Override
    public String getPackId(String cinnamonTypeId, String packSize) throws SQLException {
        return  packagingDAO.getPackId(cinnamonTypeId, packSize);
    }

    @Override
    public PackagingDto searchPackaging(String packId) throws SQLException {
        Packaging packaging = packagingDAO.search(packId);
        if (packaging != null) {
            return  new PackagingDto(
                    packaging.getPackId(),
                    packaging.getTypeId(),
                    packaging.getDescription(),
                    packaging.getPackageCount(),
                    packaging.getPrice());
        }
        return  null;
    }

    @Override
    public boolean updatePackagingCount(List<PackingCountAmountDto> dtoList) throws SQLException {
       return packagingDAO.updatePackagingCount(dtoList);
    }

    @Override
    public String getTypeId(String packId) throws SQLException {
        return packagingDAO.getTypeId(packId);
    }

    @Override
    public boolean updatePackaging(List<SalesCartTm> tmList) throws SQLException {
        return packagingDAO.updatePackaging(tmList);
    }

    @Override
    public boolean updatePackagingQty(SalesCartTm tm) throws SQLException {
        return packagingDAO.updatePackagingQty(tm);
    }

    @Override
    public List<PackagingDto> getAllPackaging() throws SQLException {
        List<Packaging> packagingList = packagingDAO.getAll();
        List<PackagingDto> dtoList = new ArrayList<>();
        for (Packaging packaging : packagingList) {
            dtoList.add(new PackagingDto(
                    packaging.getPackId(),
                    packaging.getTypeId(),
                    packaging.getDescription(),
                    packaging.getPackageCount(),
                    packaging.getPrice()
            ));
        }
        return dtoList;
    }

    @Override
    public String generateNextPackId() throws SQLException {
        return packagingDAO.generateId();
    }

    @Override
    public boolean deletePackage(String packId) throws SQLException {
        return packagingDAO.delete(packId);
    }

    @Override
    public boolean addPackage(PackagingDto dto) throws SQLException {
        return packagingDAO.save(new Packaging(
                dto.getPackId(),
                dto.getTypeId(),
                dto.getDescription(),
                dto.getPackageCount(),
                dto.getPrice()
        ));
    }

    @Override
    public boolean updatedPack(String packId, String typeId, String size, double price) throws SQLException {
        return packagingDAO.updatedPack(packId, typeId, size, price);
    }


}

