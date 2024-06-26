package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.PackingCountAmountDto;
import lk.ijse.CinnaCraft.dao.CrudDAO;
import lk.ijse.CinnaCraft.entity.PackingCountAmount;
import lk.ijse.CinnaCraft.entity.PackingDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PackagingDetailsDAO extends CrudDAO<PackingDetails> {



    public double getTotalDescreasedAmount(String CinnamonTypeId)  throws SQLException;

    public List<PackingDetails> loadAllPackagingDetails(LocalDate date) throws SQLException;

    public List<PackingCountAmount> getTotalCountAmount(LocalDate date) throws SQLException;

    public boolean confirmPackaging(LocalDate parse) throws SQLException;



}
