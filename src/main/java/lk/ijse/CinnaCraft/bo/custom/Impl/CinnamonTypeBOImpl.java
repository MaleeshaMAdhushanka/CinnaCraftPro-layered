package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.CinnamonTypeDto;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.bo.custom.CinnamonTypeBO;
import lk.ijse.CinnaCraft.dao.custom.CinnamonTypeDAO;
import lk.ijse.CinnaCraft.dao.custom.PackagingDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonTypeDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.PackagingDAOImpl;
import lk.ijse.CinnaCraft.entity.CinnamonType;
import lk.ijse.CinnaCraft.entity.PackingCountAmount;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinnamonTypeBOImpl  implements CinnamonTypeBO {
    CinnamonTypeDAO cinnamonTypeDAO = new CinnamonTypeDAOImpl();
    PackagingDAO packagingDAO = new PackagingDAOImpl();

    @Override
    public List<CinnamonTypeDto> getAllCinnamonType() throws SQLException {
        List<CinnamonType> allCinnamonTypes = cinnamonTypeDAO.getAll();
        List<CinnamonTypeDto> cinnamonTypeDtoList = new ArrayList<>();

        for (CinnamonType cinnamonType: allCinnamonTypes) {
            cinnamonTypeDtoList.add(new CinnamonTypeDto(
                    cinnamonType.getTypeId(),
                    cinnamonType.getType(),
                    cinnamonType.getAmount()
            ));
        }
        return cinnamonTypeDtoList;
    }

    @Override
    public String getCinnamonTypeId(String type) throws SQLException {
        return cinnamonTypeDAO.getCinnamonTypeId(type);
    }

    @Override
    public String getCinnamonType(String typeID) throws SQLException {
        return cinnamonTypeDAO.getCinnamonType(typeID);
    }

    @Override
    public double getCinnamonAmount(String cinnamonType) throws SQLException {
        return  cinnamonTypeDAO.getCinnamonAmount(cinnamonType);
    }

    @Override
    public boolean updateAmount(List<PackingCountAmountDto> dtoList) throws SQLException {
        List<PackingCountAmount> packagingCountAmountList = new ArrayList<>();
        for (PackingCountAmountDto dto : dtoList) {
            packagingCountAmountList.add(new PackingCountAmount(
                    dto.getPackId(),
                    dto.getCount(),
                    dto.getAmount()
            ));
        }

        for (PackingCountAmount packagingCountAmount : packagingCountAmountList) {
            String typeId = packagingDAO.getTypeId(packagingCountAmount.getPackId());
            boolean isUpdated=cinnamonTypeDAO.updateAmount(packagingCountAmount.getAmount(),typeId);
            if(!isUpdated){
                return false;
            }
        }
        return true;
    }
}



