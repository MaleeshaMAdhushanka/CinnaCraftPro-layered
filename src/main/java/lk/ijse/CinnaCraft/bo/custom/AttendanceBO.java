package lk.ijse.CinnaCraft.bo.custom;

import lk.ijse.CinnaCraft.Dto.AttendanceDto;
import lk.ijse.CinnaCraft.bo.SuperBo;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AttendanceBO  extends SuperBo {


     String generateNextAttendanceId() throws SQLException;

     boolean markAttendance(AttendanceDto dto) throws SQLException;

     boolean searchAttendance(String empId, LocalDate date) throws SQLException;

     List<AttendanceDto > getAllAttendanceDetails(LocalDate date) throws SQLException;

     boolean deleteAttendance(String attendanceID) throws SQLException;

      void updateOutTime(String empId, LocalTime outTime, LocalDate currentDate) throws SQLException;


      int getWorkedHoursCount(String empId) throws SQLException;

     int getWorkedDaysCount(String empId) throws SQLException;

     boolean updatePayedStatus(String empId) throws SQLException;

     int getAttendanceCount(String date) throws SQLException;

      boolean searchOutTime(String empId, LocalDate date) throws SQLException;
}
