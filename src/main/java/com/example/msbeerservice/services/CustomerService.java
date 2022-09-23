package com.example.msbeerservice.services;

import com.example.msbeerservice.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustomerById(UUID id);
    CustomerDto createCustomer(CustomerDto customerDto);
    void updateCustomer(CustomerDto customerDto);
    void deleteById(UUID id);
}
