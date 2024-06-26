package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.entity.CinnamonBookType;
import lk.ijse.CinnaCraft.entity.CinnamonBookTypeDetail;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface CinnamonBookTypeDAO extends CrudDAO<CinnamonBookType> {

    public List<CinnamonBookType> getAllTeaBookTypeDetails(String date) throws SQLException;
    public boolean ConfirmCinnamonBook(LocalDate date) throws SQLException;
    public List<CinnamonBookTypeDetail> getTotalAmount(LocalDate date) throws SQLException;
}
