package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.bo.custom.CinnaCraftBo;
import lk.ijse.CinnaCraft.dao.DAOFactory;
import lk.ijse.CinnaCraft.dao.custom.CinnaCraftDetailDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.CinnaCraftDetailDAOImpl;

import java.sql.SQLException;

public class CinnaCraftDetailBOImpl implements CinnaCraftBo {

    CinnaCraftDetailDAO cinnaCraftDetailDAO = (CinnaCraftDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CINNACRAFT_DETAIL);
    @Override
    public double getHourlyRate() throws SQLException {
        return cinnaCraftDetailDAO.getHourlyRate();
    }

    @Override
    public double getOTRate() throws SQLException {
        return cinnaCraftDetailDAO.getOTRate();
    }

    @Override
    public double getCinnamonBrakPrice() throws SQLException {
        return cinnaCraftDetailDAO.getCinnamonBrakPrice();

    }

    @Override
    public boolean updateCinnamonBrakPrice(double price) throws SQLException {
        return cinnaCraftDetailDAO.updateCinnamonBrakPrice(price);
    }
    @Override
    public boolean updateHourlyRateAndOt ( double hourlyRate, double oT) throws SQLException {
        return cinnaCraftDetailDAO.updateHourlyRateAndOt(hourlyRate, oT);
    }



}
