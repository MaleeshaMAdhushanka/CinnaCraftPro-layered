package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.SalaryDto;
import lk.ijse.CinnaCraft.Util.TransactionUtil;
import lk.ijse.CinnaCraft.bo.custom.SalaryBO;
import lk.ijse.CinnaCraft.dao.custom.AttendanceDAO;
import lk.ijse.CinnaCraft.dao.custom.SalaryDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.AttendanceDAOImpl;
import lk.ijse.CinnaCraft.dao.custom.impl.SalaryDAOImpl;
import lk.ijse.CinnaCraft.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {

    SalaryDAO salaryDAO =  new SalaryDAOImpl();
    AttendanceDAO attendanceDAO = new AttendanceDAOImpl();


    @Override
    public boolean addSalary(SalaryDto dto) throws SQLException {
        return salaryDAO.save(new Salary(
                dto.getSalaryId(),
                dto.getEmpID(),
                dto.getAmount(),
                dto.getDateCount(),
                dto.getDate()
        ));
    }

    @Override
    public String generateNextSalaryId() throws SQLException {
        return salaryDAO.generateId();
    }

    @Override
    public List<SalaryDto> getPaymentDetails(String supplierId) throws SQLException {
        List<Salary> salaryList = salaryDAO.getPaymentDetails(supplierId);
        List<SalaryDto> dtoList = new ArrayList<>();
        for (Salary salary: salaryList) {
            dtoList.add(new SalaryDto(
                    salary.getSalaryId(),
                    salary.getEmpID(),
                    salary.getAmount(),
                    salary.getDateCount(),
                    salary.getDate()
            ));
        }
        return  dtoList;
    }

    @Override
    public boolean saveSalary(SalaryDto dto) throws SQLException {
        boolean result = true;

        try {
            TransactionUtil.autoCommitFalse();


            boolean isSalarySaved = addSalary(dto);

            if (isSalarySaved) {
                boolean isUpdated = attendanceDAO.updatePayedStatus(dto.getEmpID());

                if (isUpdated) {
                    TransactionUtil.commit();
                    result = true;
                }

            }
        }catch (SQLException e){
            TransactionUtil.rollback();
        }
        return  result;
    }

}
