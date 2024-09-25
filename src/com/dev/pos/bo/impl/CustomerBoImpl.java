package com.dev.pos.bo.impl;

import com.dev.pos.bo.custom.CustomerBo;
import com.dev.pos.dao.DaoFactory;
import com.dev.pos.dao.custom.CustomerDao;
import com.dev.pos.dto.CustomerDto;
import com.dev.pos.entity.Customer;
import com.dev.pos.util.Enum.DaoType;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    private final CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws Exception {
        return customerDao.save(new Customer(
                customerDto.getEmail(),
                customerDto.getName(),
                customerDto.getContact(),
                customerDto.getSalary()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws Exception {
        return customerDao.update(new Customer(
                customerDto.getEmail(),
                customerDto.getName(),
                customerDto.getContact(),
                customerDto.getSalary()
        ));
    }

    @Override
    public boolean deleteCustomer(String email) throws Exception {
        return customerDao.delete(email);
    }

    @Override
    public CustomerDto findCustomer(String email) throws Exception {
        Customer customer = customerDao.find(email);
        if (customer != null) {
            return new CustomerDto(
                    customer.getEmail(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getSalary()
            );
        }
        return null;
    }

    @Override
    public List<CustomerDto> searchCustomer(String value) throws Exception {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customerDao.search(value)) {
            customerDtoList.add(new CustomerDto(
                    customer.getEmail(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getSalary()
            ));
        }
        return customerDtoList;
    }

    @Override
    public List<CustomerDto> findAllCustomer() throws Exception {
        List<Customer> allCustomers = customerDao.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : allCustomers) {
            customerDtoList.add(new CustomerDto(
                    customer.getEmail(),
                    customer.getName(),
                    customer.getContact(),
                    customer.getSalary()
            ));
        }
        return customerDtoList;
    }
}
