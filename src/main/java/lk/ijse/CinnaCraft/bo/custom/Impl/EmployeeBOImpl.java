package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.EmployeeDto;
import lk.ijse.CinnaCraft.bo.custom.EmployeeBO;
import lk.ijse.CinnaCraft.dao.DAOFactory;
import lk.ijse.CinnaCraft.dao.custom.EmployeeDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.CinnaCraft.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);


    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        return employeeDAO.save(new Employee(
                dto.getEmpID(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAddress(),
                dto.getSex(),
                dto.getMobileNo()
        ));
    }

    @Override
    public boolean updateEmployee(EmployeeDto employee) throws SQLException {
        return employeeDAO.update(new Employee(
                employee.getEmpID(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAddress(),
                employee.getSex(),
                employee.getMobileNo()
        ));
    }

    @Override
    public String generateNextEmployeeId() throws SQLException {
        return employeeDAO.generateId();
    }

    @Override
    public EmployeeDto searchEmployee(String empID) throws SQLException {
        Employee employee =  employeeDAO.search(empID);
        return new EmployeeDto(
                employee.getEmpID(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAddress(),
                employee.getSex(),
                employee.getMobileNo()
        );
    }

    @Override
    public List<EmployeeDto> getAllEmployee() throws SQLException {
        List<Employee> employees = employeeDAO.getAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDtos.add(new EmployeeDto(
                    employee.getEmpID(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getAddress(),
                    employee.getSex(),
                    employee.getMobileNo()
            ));

        }
        return employeeDtos;
    }

    @Override
    public boolean deleteEmployee(String empID) throws SQLException {
        return employeeDAO.delete(empID);
    }
}
