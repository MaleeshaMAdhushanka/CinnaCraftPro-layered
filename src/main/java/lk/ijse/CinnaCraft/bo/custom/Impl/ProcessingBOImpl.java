package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Util.TransactionUtil;
import lk.ijse.CinnaCraft.bo.custom.ProcessingBO;
import lk.ijse.CinnaCraft.dao.DAOFactory;
import lk.ijse.CinnaCraft.dao.custom.CinnamonBookTypeDAO;
import lk.ijse.CinnaCraft.dao.custom.CinnamonTypeDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonBookTypeDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonTypeDAOImpl;
import lk.ijse.CinnaCraft.entity.CinnamonBookTypeDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessingBOImpl implements ProcessingBO {

      CinnamonBookTypeDAO cinnamonBookTypeDAO = (CinnamonBookTypeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CINNAMON_BOOK_TYPE);

      CinnamonTypeDAO cinnamonTypeDAO = (CinnamonTypeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CINNAMON_TYPE);
    @Override
    public boolean updateDetails(LocalDate date, List<CinnamonBookTypeDetailDto> dtoList) throws SQLException {


        boolean result = false;
            try{
                TransactionUtil.autoCommitFalse();

                boolean isConfirmed = cinnamonBookTypeDAO.ConfirmCinnamonBook(date);

                if (isConfirmed){
                    boolean isUpdated = updateCinnamonTypeAmount(dtoList);

                    if (isUpdated){
                        TransactionUtil.commit();
                        result = true;
                    }
                }
            }catch (SQLException e){
                TransactionUtil.rollback();
            }

            return result;
        }

        public boolean updateCinnamonTypeAmount(List<CinnamonBookTypeDetailDto> dtoList) throws SQLException {
            List<CinnamonBookTypeDetail> cinnamonBookTypeDetailsList = new ArrayList<>();
            for (CinnamonBookTypeDetailDto dto : dtoList) {
                cinnamonBookTypeDetailsList.add(new CinnamonBookTypeDetail(
                        dto.getTypeID(),dto.getAmount()
                ));
            }
            for (CinnamonBookTypeDetail cinnamonBookTypeDetail : cinnamonBookTypeDetailsList){
                boolean isUpdated=cinnamonTypeDAO.updateCinnamonTypeAmount(cinnamonBookTypeDetail);
                if(!isUpdated){
                    return false;
                }
            }
            return true;
        }



    }
