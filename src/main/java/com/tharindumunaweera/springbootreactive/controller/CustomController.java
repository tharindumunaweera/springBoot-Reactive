package com.tharindumunaweera.springbootreactive.controller;

import com.tharindumunaweera.springbootreactive.dto.Customer;
import com.tharindumunaweera.springbootreactive.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomController {

    @Autowired
    private CustomerService service;

    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers() {
        return service.loadAllCustomers();
    }
}
