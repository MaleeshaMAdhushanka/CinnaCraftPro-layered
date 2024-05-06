package lk.ijse.Model;

import lk.ijse.Db.DbConnection;
import lk.ijse.Dto.SalaryDto;

import java.sql.Connection;
import java.sql.SQLException;

public class SalaryTransactionModel {


    private final SalaryModel salaryModel = new SalaryModel();
    private final AttendanceModel attendanceModel = new AttendanceModel();

    public boolean addSalary(SalaryDto dto) throws SQLException {

        boolean result = false;
        Connection connection= null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);


            boolean isSalarySaved = salaryModel.addSdalary(dto);

            if (isSalarySaved) {
                boolean isUpdated = attendanceModel.updatePayedStatus(dto.getEmpID());


                if (isUpdated) {
                    connection.commit();
                    result = true;
                }
            }

        } catch (SQLException e) {
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }

        return  result;


    }


}
