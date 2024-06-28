package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDto;
import lk.ijse.CinnaCraft.bo.custom.CinnamonBookBo;
import lk.ijse.CinnaCraft.bo.custom.CinnamonBookTypeBO;
import lk.ijse.CinnaCraft.dao.custom.CinnamonBookTypeDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonBookTypeDAOImpl;
import lk.ijse.CinnaCraft.entity.CinnamonBookType;
import lk.ijse.CinnaCraft.entity.CinnamonBookTypeDetail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CinnamonBookTypeBoImpl  implements CinnamonBookTypeBO {
    CinnamonBookTypeDAO cinnamonBookTypeDAO = new CinnamonBookTypeDAOImpl();


    @Override
    public String generateNextCinnamonBookTypeId() throws SQLException {
        return cinnamonBookTypeDAO.generateId();
    }

    @Override
    public boolean saveCinnamonBookType(CinnamonBookTypeDto dto) throws SQLException {
        return cinnamonBookTypeDAO.save(new CinnamonBookType(
                dto.getCinnamonBookTypeId(),
                dto.getDate(),
                dto.getTypeId(),
                dto.getAmount(),
                dto.isConfirmed()
        ));
    }

    @Override
    public List<CinnamonBookTypeDto> getAllTeaBookTypeDetails(String date) throws SQLException {

        List<CinnamonBookType> allCinnamonBookTypeDetails = cinnamonBookTypeDAO.getAllTeaBookTypeDetails(date);
        List<CinnamonBookTypeDto> cinnamonBookTypeDtoList = new ArrayList<>();

        for (CinnamonBookType cinnamonBookType: allCinnamonBookTypeDetails) {
            cinnamonBookTypeDtoList.add(new CinnamonBookTypeDto(
                    cinnamonBookType.getCinnamonBookTypeId(),
                    cinnamonBookType.getDate(),
                    cinnamonBookType.getTypeId(),
                    cinnamonBookType.getAmount(),
                    cinnamonBookType.isConfirmed()
            ));
        }
        return cinnamonBookTypeDtoList;
    }

    @Override
    public boolean deleteCinnamonBookType(String CinnamonBookTypeID) throws SQLException {
        return cinnamonBookTypeDAO.delete(CinnamonBookTypeID);
    }

    @Override
    public boolean ConfirmCinnamonBook(LocalDate date) throws SQLException {
        return cinnamonBookTypeDAO.ConfirmCinnamonBook(date);
    }

    @Override
    public List<CinnamonBookTypeDetailDto> getTotalAmount(LocalDate date) throws SQLException {

        List<CinnamonBookTypeDetail> totalAmount = cinnamonBookTypeDAO.getTotalAmount(date);
        List<CinnamonBookTypeDetailDto> cinnamonBookTypeDtoList = new ArrayList<>();
        for (CinnamonBookTypeDetail cinnamonBookTypeDetail: totalAmount) {
            cinnamonBookTypeDtoList.add(new CinnamonBookTypeDetailDto(
                    cinnamonBookTypeDetail.getTypeID(),
                    cinnamonBookTypeDetail.getAmount()

            ));

        }
        return cinnamonBookTypeDtoList;
    }
}
