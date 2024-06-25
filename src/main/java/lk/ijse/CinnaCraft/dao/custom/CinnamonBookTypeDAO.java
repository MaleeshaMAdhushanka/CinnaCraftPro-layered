package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDetailDto;
import lk.ijse.CinnaCraft.Dto.CinnamonBookTypeDto;
import lk.ijse.CinnaCraft.dao.CrudDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface CinnamonBookTypeDAO extends CrudDAO<CinnamonBookTypeDto> {

    public List<CinnamonBookTypeDto> getAllTeaBookTypeDetails(String date) throws SQLException;
    public boolean ConfirmCinnamonBook(LocalDate date) throws SQLException;
    public List<CinnamonBookTypeDetailDto> getTotalAmount(LocalDate date) throws SQLException;
}
