package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.CinnamonBarkStockDto;
import lk.ijse.CinnaCraft.Util.TransactionUtil;
import lk.ijse.CinnaCraft.bo.custom.CinnamonBarkStockBO;
import lk.ijse.CinnaCraft.dao.DAOFactory;
import lk.ijse.CinnaCraft.dao.custom.CinnamonBarkStockDAO;
import lk.ijse.CinnaCraft.dao.custom.CinnamonBookDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonBarkStockDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonBookDAOImpl;
import lk.ijse.CinnaCraft.entity.CinnamonBarkStock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinnamonBarkStockBOImpl implements CinnamonBarkStockBO {

    CinnamonBarkStockDAO cinnamonBarkStockDAO = (CinnamonBarkStockDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CINNAMON_BARK_STOCK);

    CinnamonBookDAO cinnamonBookDAO = (CinnamonBookDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CINNAMON_BOOK);


    @Override
    public String generateNextCinnamonBarkStockId() throws SQLException {
        return cinnamonBarkStockDAO.generateId();
    }

    @Override
    public boolean saveCinnamonBarkStock(CinnamonBarkStockDto dto) throws SQLException {
       return cinnamonBarkStockDAO.save(new CinnamonBarkStock(
               dto.getCinnamonStockID(),
               dto.getSupID(),
               dto.getCinnamonBookID(),
               dto.getAmount(),
               dto.isPayed()
             ));
    }

    @Override
    public double getTotalAmount(String CinnamonBookID) throws SQLException {
        return cinnamonBarkStockDAO.getTotalAmount(CinnamonBookID);
    }

    @Override
    public List<CinnamonBarkStockDto> getAllStockDetails(String dateBookId) throws SQLException {
        List<CinnamonBarkStock> all = cinnamonBarkStockDAO.getAllStockDetails(dateBookId);
        List<CinnamonBarkStockDto> dtoList = new ArrayList<>();
        for (CinnamonBarkStock cinnamonBarkStock: all) {
            dtoList.add(new CinnamonBarkStockDto(
                    cinnamonBarkStock.getCinnamonStockID(),
                    cinnamonBarkStock.getSupID(),
                    cinnamonBarkStock.getCinnamonBookID(),
                    cinnamonBarkStock.getAmount(),
                    cinnamonBarkStock.isPayed()
            ));
        }
        return  dtoList;
    }

    @Override
    public CinnamonBarkStockDto searchCinnamonBarkStock(String text) throws SQLException {
        CinnamonBarkStock search = cinnamonBarkStockDAO.search(text);
        if (search != null) {
            return  new CinnamonBarkStockDto(
                    search.getCinnamonStockID(),
                    search.getSupID(),
                    search.getCinnamonBookID(),
                    search.getAmount(),
                    search.isPayed()
            );
        }
        return null;
    }

    @Override
    public boolean deleteCinnamonBarkStock(String CinnamonStockID) throws SQLException {
        return cinnamonBarkStockDAO.delete(CinnamonStockID);
    }

    @Override
    public boolean updateCinnamonBarkStock(CinnamonBarkStockDto cinnamonBarkStockDto) throws SQLException {
        return cinnamonBarkStockDAO.update(new CinnamonBarkStock(
                cinnamonBarkStockDto.getCinnamonStockID(),
                cinnamonBarkStockDto.getSupID(),
                cinnamonBarkStockDto.getCinnamonBookID(),
                cinnamonBarkStockDto.getAmount(),
                cinnamonBarkStockDto.isPayed()

        ));
    }

    @Override
    public double getTotalCinnamonBarkSuppliedAmount(String SupID) throws SQLException {
        return cinnamonBarkStockDAO.getTotalCinnamonBarkSuppliedAmount(SupID);
    }

    @Override
    public boolean updatePayedStatus(String SupID) throws SQLException {
        return cinnamonBarkStockDAO.updatePayedStatus(SupID);
    }

    @Override
    public boolean addCinnamonBarkStock(CinnamonBarkStockDto cinnamonBarkStockDto, String CinnamonBookId) throws SQLException {


        boolean result = false;

        try {


            TransactionUtil.autoCommitFalse();

            boolean isSaved = saveCinnamonBarkStock(cinnamonBarkStockDto);

            if (isSaved) {
                double dailyAmount = getTotalAmount(CinnamonBookId);

                boolean isUpdated = cinnamonBookDAO.updateCinnamonBookAmount(CinnamonBookId, dailyAmount);
                if (isUpdated) {
                    TransactionUtil.commit();
                    result = true;
                }
            }
        }catch (SQLException e){
            TransactionUtil.rollback();
        }
        return true;
    }
}
