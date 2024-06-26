package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.AttendanceDto;
import lk.ijse.CinnaCraft.bo.custom.AttendanceBO;
import lk.ijse.CinnaCraft.dao.custom.AttendanceDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.CinnaCraft.entity.Attendance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBOImpl implements AttendanceBO {
    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();

    @Override
    public String generateNextAttendanceId() throws SQLException {
        return attendanceDAO.generateId();
    }

    @Override
    public boolean markAttendance(AttendanceDto dto) throws SQLException {
        return  attendanceDAO.save(new Attendance(
                dto.getAttendanceId(),
                dto.getDate(),
                dto.getEmpId(),
                dto.getInTime(),
                dto.getOutTime(),
                dto.isPayed()
        ));
    }

    @Override
    public boolean searchAttendance(String empId, LocalDate date) throws SQLException {
        return attendanceDAO.searchAttendance(empId,date);
    }

    @Override
    public List<AttendanceDto> getAllAttendanceDetails(LocalDate date) throws SQLException {
      List<Attendance> allAttendance = attendanceDAO.getAllAttendanceDetails(date);
      List<AttendanceDto> allAttendanceDto = new ArrayList<>();
        for (Attendance  attendance:   allAttendance) {
            allAttendanceDto.add(new AttendanceDto(
                    attendance.getAttendanceId(),
                    attendance.getDate(),
                    attendance.getEmpId(),
                    attendance.getInTime(),
                    attendance.getOutTime(),
                    attendance.isPayed()

            ));
        }
        return allAttendanceDto;

    }

    @Override
    public boolean deleteAttendance(String attendanceID) throws SQLException {
        return attendanceDAO.delete(attendanceID);
    }

    @Override
    public void updateOutTime(String empId, LocalTime outTime, LocalDate currentDate) throws SQLException {
        attendanceDAO.updateOutTime(empId, outTime, currentDate);

    }

    @Override
    public int getWorkedHoursCount(String empId) throws SQLException {
       return attendanceDAO.getWorkedHoursCount(empId);
    }

    @Override
    public int getWorkedDaysCount(String empId) throws SQLException {
      return  attendanceDAO.getWorkedDaysCount(empId);

    }

    @Override
    public boolean updatePayedStatus(String empId) throws SQLException {
       return  attendanceDAO.updatePayedStatus(empId);
    }

    @Override
    public int getAttendanceCount(String date) throws SQLException {
        return attendanceDAO.getAttendanceCount(date);
    }

    @Override
    public boolean searchOutTime(String empId, LocalDate date) throws SQLException {
        return attendanceDAO.searchOutTime(empId, date);
    }
}
