package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.CinnamonBookDto;
import lk.ijse.CinnaCraft.bo.custom.CinnamonBookBo;
import lk.ijse.CinnaCraft.dao.DAOFactory;
import lk.ijse.CinnaCraft.dao.custom.CinnamonBookDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnamonBookDAOImpl;
import lk.ijse.CinnaCraft.entity.CinnamonBook;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinnamonBookBOImpl implements CinnamonBookBo {
    CinnamonBookDAO cinnamonBookDAO = (CinnamonBookDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CINNAMON_BOOK);


    @Override
    public boolean updateCinnamonBookAmount(String CinnamonBookId, double amount) throws SQLException {
        return cinnamonBookDAO.updateCinnamonBookAmount(CinnamonBookId, amount);
    }

    @Override
    public String getCinnamonBookId(String date) throws SQLException {
        return cinnamonBookDAO.getCinnamonBookId(date);
    }

    @Override
    public String getCinnamonBookDate(String CinnamonBookId) throws SQLException {
        return cinnamonBookDAO.getCinnamonBookDate(CinnamonBookId);
    }

    @Override
    public boolean searchDate(String date) throws SQLException {
        return cinnamonBookDAO.searchDate(date);
    }

    @Override
    public boolean createCinnamonBookRecord(String date) throws SQLException {
        return cinnamonBookDAO.createCinnamonBookRecord(date);
    }

    @Override
    public String generateNextCinnamonBookId() throws SQLException {
        return cinnamonBookDAO.generateId();
    }

    @Override
    public double getAmount(String string) throws SQLException {
        return cinnamonBookDAO.getAmount(string);
    }

    @Override
    public List<CinnamonBookDto> getAllCinnamonBookDetails() throws SQLException {
        List<CinnamonBook> allCinnamonBookDetails = cinnamonBookDAO.getAll();
        List<CinnamonBookDto> allCinnamonBookDto = new ArrayList<>();
        for (CinnamonBook cinnamonBook: allCinnamonBookDetails) {
            allCinnamonBookDto.add(new CinnamonBookDto(
                    cinnamonBook.getCinnamonBookId(),
                    cinnamonBook.getDailyAmount(),
                    cinnamonBook.getDate()
            ));
        }
        return allCinnamonBookDto;
    }
}
