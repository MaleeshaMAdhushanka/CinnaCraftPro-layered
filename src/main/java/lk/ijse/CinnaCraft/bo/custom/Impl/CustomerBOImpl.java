package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.CustomerDto;
import lk.ijse.CinnaCraft.bo.custom.CustomerBO;
import lk.ijse.CinnaCraft.dao.custom.CustomerDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.CinnaCraft.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl  implements CustomerBO {

    CustomerDAO customerDAO = new CustomerDAOImpl();


    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException {
        ArrayList<Customer> allCustomer = customerDAO.getAll();
        ArrayList<CustomerDto> allCustomerDto = new ArrayList<>();
        for (Customer customer: allCustomer) {
            allCustomerDto.add(new CustomerDto(
                    customer.getCusId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getAddress(),
                    customer.getEmail(),
                    customer.getMobileNo()
            ));
        }
        return allCustomerDto;

    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.save(new Customer(
                dto.getCusId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getMobileNo()

        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.update(new Customer(
                dto.getCusId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getMobileNo()
        ));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNextCustomerId() throws SQLException {
        return customerDAO.generateId();
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException {
        Customer customer = customerDAO.search(id);
        if (customer != null) {
            return new CustomerDto(
                    customer.getCusId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getMobileNo()
            );
        }
        return null;
    }

    @Override
    public String searchCustomerId(String name) throws SQLException {
        return customerDAO.searchCustomerId(name);
    }
}
