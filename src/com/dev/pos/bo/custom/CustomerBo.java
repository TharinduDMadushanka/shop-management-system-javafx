package com.dev.pos.bo.custom;

import com.dev.pos.bo.SuperBO;
import com.dev.pos.dto.CustomerDto;

import java.util.List;


public interface CustomerBo extends SuperBO {

    public boolean saveCustomer(CustomerDto customerDTO)throws Exception;
    public boolean updateCustomer(CustomerDto customerDTO)throws Exception;
    public boolean deleteCustomer(String email)throws Exception;
    public CustomerDto findCustomer(String email)throws Exception;
    public List<CustomerDto> searchCustomer(String value)throws Exception;
    public List<CustomerDto> findAllCustomer()throws Exception;

}
