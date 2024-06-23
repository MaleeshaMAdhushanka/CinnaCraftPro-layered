package lk.ijse.CinnaCraft.Model;

import lk.ijse.CinnaCraft.Dto.AttendanceDto;
import lk.ijse.CinnaCraft.Db.DbConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceModel {


    public String generateNextAttendanceId() throws SQLException {
        Connection connection =  DbConnection.getInstance().getConnection();

        String sql = "SELECT attendanceID FROM attendance  ORDER BY attendanceID DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();


        String curretAttendanceId = null;

        if (resultSet.next()) {
            curretAttendanceId = resultSet.getString(1);
            return splitAtttendanceId(curretAttendanceId);

        }
        return  splitAtttendanceId(curretAttendanceId);

    }

    private String splitAtttendanceId(String curretAttendanceId) {
        if (curretAttendanceId != null) {
            String[] split = curretAttendanceId.split("A");
            int selectedId= Integer.parseInt(split[1]);

            if (selectedId < 9) {
                selectedId++;
                return "A00" + selectedId;
            }else if (selectedId< 99) {
                selectedId++;
                return "A0" + selectedId;
            }else {
                selectedId++;
                return"A"+ selectedId;
            }
        }
        return "A001";
    }


    public boolean markAttendance(AttendanceDto dto) throws SQLException {
     Connection connection = DbConnection.getInstance().getConnection();

     String sql = "INSERT INTO attendance VALUES(?, ?, ?, ?, ?, ?)";

     PreparedStatement pstm = connection.prepareStatement(sql);

       pstm.setString(1, dto.getAttendanceId());
       pstm.setDate(2, Date.valueOf(dto.getDate()));
       pstm.setString(3,dto.getEmpId());
        pstm.setTime(4, Time.valueOf(dto.getInTime()));
        pstm.setTime(5, null);
        pstm.setBoolean(6,dto.isPayed());

        return  pstm.executeUpdate()>0;




    }

    public boolean searchAttendance(String empId, LocalDate date) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM  attendance  WHERE  empId=? AND  date=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, empId);
        pstm.setDate(2, Date.valueOf(date));

        ResultSet resultSet = pstm.executeQuery();

        return resultSet.next();

    }


    public List<AttendanceDto > getAllAttendanceDetails(LocalDate date) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM attendance WHERE date=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDate(1, Date.valueOf(date));

        ResultSet resultSet = pstm.executeQuery();

        List<AttendanceDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
             String attedanceId = resultSet.getString(1);
             LocalDate attendanceDate = resultSet.getDate(2).toLocalDate();
              String empId =  resultSet.getString(3);
             LocalTime inTime = resultSet.getTime(4).toLocalTime();


            //the value can either be null or not null

            Time outTime = resultSet.getTime(5);

            boolean isPayed = resultSet.getBoolean(6);

            LocalTime checkNullOutTime = null;

            if ( outTime != null) {
                checkNullOutTime = outTime.toLocalTime();
            }

            AttendanceDto dto = new AttendanceDto(attedanceId, attendanceDate, empId, inTime, checkNullOutTime, isPayed );

            dtoList.add(dto);
        }
        return  dtoList;

    }

    public boolean deleteAttendance(String attendanceID) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM attendance WHERE attendanceID=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, attendanceID);

        return  pstm.executeUpdate()>0;

    }

    public  void updateOutTime(String empId, LocalTime outTime, LocalDate currentDate) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE attendance SET outTime=? WHERE empId=? AND date=?";

        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setTime(1, Time.valueOf(outTime));
        pstm.setString(2, empId);
        pstm.setDate(3,Date.valueOf(currentDate));

        pstm.executeUpdate();

    }


    public  int getWorkedHoursCount(String empId) throws SQLException{

        Connection connection = DbConnection.getInstance().getConnection();

          String sql= "SELECT SUM(TIME_TO_SEC(TIMEDIFF(outTime,inTime)))/3600 FROM attendance WHERE empId=? AND isPayed= false";
          PreparedStatement pstm = connection.prepareStatement(sql);

          pstm.setString(1, empId);

          ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return  resultSet.getInt(1);

        }
        return 0;
    }

    public int getWorkedDaysCount(String empId) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM attendance WHERE empId=? AND isPayed= false";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, empId);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet .next()) {
            return  resultSet.getInt(1);
        }
        return 0;
    }

    public boolean updatePayedStatus(String empId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE attendance SET isPayed=true WHERE empId=?  AND isPayed=false";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, empId);

        return pstm.executeUpdate()> 0;

    }

    public int getAttendanceCount(String date) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM attendance WHERE date=?";


        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, date);

        ResultSet resultSet = pstm.executeQuery();

        int count =0;

        if (resultSet.next()) {
            count = resultSet.getInt(1);

        }
        return count;

    }

    public  boolean searchOutTime(String empId, LocalDate date) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM attendance WHERE empId=?  AND date=? AND outTime IS NOT NULL";

        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, empId);

        pstm.setDate(2, Date.valueOf(date));

        ResultSet resultSet = pstm.executeQuery();

        return  resultSet.next();
    }
}
