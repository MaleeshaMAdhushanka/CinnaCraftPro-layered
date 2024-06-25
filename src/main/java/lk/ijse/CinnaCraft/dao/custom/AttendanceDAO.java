package lk.ijse.CinnaCraft.dao.custom;

import lk.ijse.CinnaCraft.Db.DbConnection;
import lk.ijse.CinnaCraft.Dto.AttendanceDto;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.CrudDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface AttendanceDAO  extends CrudDAO<AttendanceDto> {

    List<AttendanceDto> getAllAttendanceDetails(LocalDate date) throws SQLException;

//     boolean markAttendance(AttendanceDto dto) throws SQLException;

     boolean searchAttendance(String empId, LocalDate date) throws SQLException;

//     boolean deleteAttendance(String attendanceID) throws SQLException;

     void updateOutTime(String empId, LocalTime outTime, LocalDate currentDate) throws SQLException;

     int getWorkedHoursCount(String empId) throws SQLException;

     int getWorkedDaysCount(String empId) throws SQLException;

     boolean updatePayedStatus(String empId) throws SQLException;

     int getAttendanceCount(String date) throws SQLException;

     boolean searchOutTime(String empId, LocalDate date) throws SQLException;


    }
