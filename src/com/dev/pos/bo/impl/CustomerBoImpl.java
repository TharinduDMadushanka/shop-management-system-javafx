package com.dev.pos.bo.impl;

import com.dev.pos.bo.custom.CustomerBo;
import com.dev.pos.dao.DaoFactory;
import com.dev.pos.dao.custom.CustomerDao;
import com.dev.pos.dto.CustomerDto;
import com.dev.pos.entity.Customer;
import com.dev.pos.util.Enum.DaoType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto customerDTO) throws Exception {
        return customerDao.save(new Customer(
                customerDTO.getEmail(),
                customerDTO.getName(),
                customerDTO.getContact(),
                customerDTO.getSalary()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDTO) throws Exception {
        return customerDao.update(new Customer(
                customerDTO.getEmail(),
                customerDTO.getName(),
                customerDTO.getContact(),
                customerDTO.getSalary()
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
        List<CustomerDto> customerDTOList = new ArrayList<>();
        for (Customer c : customerDao.search(value)) {
            customerDTOList.add(new CustomerDto(
                    c.getEmail(),
                    c.getName(),
                    c.getContact(),
                    c.getSalary()
            ));
        }
        return customerDTOList;
    }

    @Override
    public List<CustomerDto> findAllCustomer() throws Exception {
        List<Customer> allCustomers = customerDao.findAll();
        List<CustomerDto> customerDTOs = new ArrayList<>();
        for (Customer c : allCustomers) {
            customerDTOs.add(new CustomerDto(
                    c.getEmail(),
                    c.getName(),
                    c.getContact(),
                    c.getSalary()
            ));
        }
        return customerDTOs;
    }
}
