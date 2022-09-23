package com.example.msbeerservice.services;

import com.example.msbeerservice.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID id) {
        return CustomerDto.builder()
                .id(randomUUID())
                .name("Cristi")
                .build();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return CustomerDto.builder().id(randomUUID()).build();
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        log.debug("Updating a customer...");
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Deleting a customer...");
    }
}
