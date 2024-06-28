package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.Dto.PackingDetailsDto;
import lk.ijse.CinnaCraft.Util.TransactionUtil;
import lk.ijse.CinnaCraft.bo.custom.CinnamonTypeBO;
import lk.ijse.CinnaCraft.bo.custom.PackagingDetailsBO;
import lk.ijse.CinnaCraft.dao.custom.CinnamonTypeDAO;
import lk.ijse.CinnaCraft.dao.custom.PackagingDAO;
import lk.ijse.CinnaCraft.dao.custom.PackagingDetailsDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonTypeDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.PackagingDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.PackagingDetailsDAOImpl;
import lk.ijse.CinnaCraft.entity.PackingCountAmount;
import lk.ijse.CinnaCraft.entity.PackingDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PackagingDetailsBOImpl implements PackagingDetailsBO {

    PackagingDAO packagingDAO = new PackagingDAOImpl();
    PackagingDetailsDAO packagingDetailsDAO = new PackagingDetailsDAOImpl();
   CinnamonTypeBO cinnamonTypeDAO = new CinnamonTypeBOImpl();

    @Override
    public String generateNextPackId() throws SQLException {
        return packagingDetailsDAO.generateId();
    }

    @Override
    public boolean addPackingDetails(PackingDetailsDto dto) throws SQLException {
       return packagingDetailsDAO.save(new PackingDetails(
               dto.getPackagingDetailsId(),
               dto.getDate(),
               dto.getPackId(),
               dto.getCount(),
               dto.getAmount(),
               dto.isConfirmed(),
               dto.getTotal()));
    }

    @Override
    public double getTotalDescreasedAmount(String CinnamonTypeId) throws SQLException {
        return packagingDetailsDAO.getTotalDescreasedAmount(CinnamonTypeId);
    }

    @Override
    public List<PackingDetailsDto> loadAllPackagingDetails(LocalDate date) throws SQLException {
        List<PackingDetails> allPackagingDetails = packagingDetailsDAO.loadAllPackagingDetails(date);
        List<PackingDetailsDto> packagingDetailsDtoList = new ArrayList<>();

        for (PackingDetails packingDetails : allPackagingDetails ) {
            packagingDetailsDtoList.add(new PackingDetailsDto(
                    packingDetails.getPackagingDetailsId(),
                    packingDetails.getDate(),
                    packingDetails.getPackId(),
                    packingDetails.getCount(),
                    packingDetails.getAmount(),
                    packingDetails.isConfirmed(),
                    packingDetails.getTotal()
            ));
        }
        return packagingDetailsDtoList;

    }
     @Override
     public  boolean deletePackageDetails(String packageDetailsId) throws SQLException{
        return packagingDetailsDAO.delete(packageDetailsId);

     }

    @Override
    public List<PackingCountAmountDto> getTotalCountAmount(LocalDate parse) throws SQLException {
        List<PackingCountAmount> allTotalCountAmount = packagingDetailsDAO.getTotalCountAmount(parse);
        List<PackingCountAmountDto> packagingCountAmountDtoList = new ArrayList<>();

        for (PackingCountAmount packingCountAmount: allTotalCountAmount ){
            packagingCountAmountDtoList.add( new PackingCountAmountDto(
                    packingCountAmount.getPackId(),
                    packingCountAmount.getCount(),
                    packingCountAmount.getAmount()
            ));
        }
        return packagingCountAmountDtoList;
    }

    @Override
    public boolean confirmPackaging(LocalDate date) throws SQLException {
        return packagingDetailsDAO.confirmPackaging(date);
    }

    @Override
    public boolean confirmPackaging(LocalDate date, List<PackingCountAmountDto> dtoList) throws SQLException {


        boolean result = false;

        try {

            TransactionUtil.autoCommitFalse();

            boolean isConfirmed = confirmPackaging(date);

            if (isConfirmed) {
                boolean isSaved = packagingDAO.updatePackagingCount(dtoList);
                if (isSaved) {
                    boolean isUpdate = cinnamonTypeDAO.updateAmount(dtoList);
                    if (isUpdate) {
                        TransactionUtil.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            TransactionUtil.rollback();
        }
        return result;

    }
}
