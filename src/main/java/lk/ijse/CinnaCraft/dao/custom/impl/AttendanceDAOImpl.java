package lk.ijse.CinnaCraft.dao.custom.impl;

import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.AttendanceDAO;
import lk.ijse.CinnaCraft.entity.Attendance;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

public class AttendanceDAOImpl implements AttendanceDAO {

    @Override
    public ArrayList<Attendance> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Attendance entity) throws SQLException {
        return SQLUtil.crudUtil("INSERT INTO attendance VALUES(?, ?, ?, ?, ?, ?)", entity.getAttendanceId(), entity.getDate(), entity.getEmpId(), entity.getInTime(), entity.getOutTime(), entity.isPayed());
    }

    @Override
    public boolean update(Attendance entity) throws SQLException {
        return SQLUtil.crudUtil("UPDATE attendance SET outTime=? WHERE empId=? AND date=?", entity.getOutTime(), entity.getEmpId(), entity.getDate());
    }

    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.crudUtil("DELETE FROM attendance WHERE attendanceID=?", id);
    }


    @Override
    public Attendance search(String id) throws SQLException {
        return null;
    }

   @Override
    public String generateId() throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil("SELECT attendanceID FROM attendance  ORDER BY attendanceID DESC LIMIT 1");


        String curretAttendanceId = null;

        if (resultSet.next()) {
            curretAttendanceId = resultSet.getString(1);
            return splitAtttendanceId(curretAttendanceId);

        }
        return splitAtttendanceId(curretAttendanceId);

    }


    private String splitAtttendanceId(String curretAttendanceId) {
        if (curretAttendanceId != null) {
            String[] split = curretAttendanceId.split("A");
            int selectedId = Integer.parseInt(split[1]);

            if (selectedId < 9) {
                selectedId++;
                return "A00" + selectedId;
            } else if (selectedId < 99) {
                selectedId++;
                return "A0" + selectedId;
            } else {
                selectedId++;
                return "A" + selectedId;
            }
        }
        return "A001";
    }



    @Override
    public boolean searchAttendance(String empId, LocalDate date) throws SQLException {
        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM  attendance  WHERE  empId=? AND  date=?", empId, date);
        return  resultSet.next();

    }

    @Override
    public List<Attendance> getAllAttendanceDetails(LocalDate date) throws SQLException {
        ResultSet resultSet = SQLUtil.crudUtil("SELECT * FROM attendance WHERE date=?", date);

        List<Attendance> attendanceList = new ArrayList<>();


        //The Value Can Either Be null or not null
//            Time outTime = resultSet.getTime(5);
//            LocalTime checkNullOutTime = null;
//
//            if (outTime!=null) {
//                checkNullOutTime= outTime.toLocalTime();
//            }

        while (resultSet.next()) {
            Attendance attendance = new Attendance(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate()
                    , resultSet.getString(3)
                    , resultSet.getTime(4).toLocalTime()
                    , (resultSet.getObject(5, LocalTime.class) != null) ? resultSet.getTime(5).toLocalTime() : null,
                    resultSet.getBoolean(6));

            attendanceList.add(attendance);
        }
        return attendanceList;
    }





    @Override
    public int getWorkedHoursCount(String empId) throws SQLException {
        ResultSet resultSet = SQLUtil.crudUtil( "SELECT SUM(TIME_TO_SEC(TIMEDIFF(outTime,inTime)))/3600 FROM attendance WHERE empId=? AND isPayed= false", empId);

        if (resultSet.next()) {
            return  resultSet.getInt(1);

        }
        return 0;
    }

    @Override
    public int getWorkedDaysCount(String empId) throws SQLException {
        ResultSet resultSet = SQLUtil.crudUtil("SELECT COUNT(*) FROM attendance WHERE empId=? AND isPayed= false", empId);

        if (resultSet .next()) {
            return  resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean updatePayedStatus(String empId) throws SQLException {
        return SQLUtil.crudUtil("UPDATE attendance SET isPayed=true WHERE empId=?  AND isPayed=false", empId);
    }

    @Override
    public int getAttendanceCount(String date) throws SQLException {
        ResultSet resultSet = SQLUtil.crudUtil( "SELECT COUNT(*) FROM attendance WHERE date=?", date);

        int count =0;

        if (resultSet.next()) {
            count = resultSet.getInt(1);

        }
        return count;
    }

    @Override
    public boolean searchOutTime(String empId, LocalDate date) throws SQLException {
        ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM attendance WHERE empId=?  AND date=? AND outTime IS NOT NULL", empId, date);
        return  resultSet.next();

    }
    public  void updateOutTime(String empId, LocalTime outTime, LocalDate currentDate) throws SQLException{

        SQLUtil.crudUtil("UPDATE attendance SET outTime=? WHERE empId=? AND date=?", outTime,empId ,currentDate);


    }
}
