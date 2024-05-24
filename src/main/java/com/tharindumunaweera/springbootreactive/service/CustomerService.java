package com.tharindumunaweera.springbootreactive.service;

import com.tharindumunaweera.springbootreactive.dao.CustomerDao;
import com.tharindumunaweera.springbootreactive.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("total execution time difference" + (end - start));
        return customers;
    }
}
