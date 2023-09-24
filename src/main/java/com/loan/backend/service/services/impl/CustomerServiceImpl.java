package com.loan.backend.service.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.backend.service.dto.Customer;
import com.loan.backend.service.exceptions.CustomerAlreadyRegisteredException;
import com.loan.backend.service.exceptions.CustomerNotFoundException;
import com.loan.backend.service.dao.entity.CustomerEntity;
import com.loan.backend.service.services.AbstractCustomerService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.loan.backend.service.dao.CustomerRepository;

@Service
@Primary
@Log4j2
public class CustomerServiceImpl implements AbstractCustomerService {

    private final CustomerRepository customerDao;

    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerServiceImpl(@NonNull final CustomerRepository customerDao) {
        this.customerDao = customerDao;
        objectMapper = new ObjectMapper();
    }

    @Override
    public Customer addCustomer(@NonNull final Customer c) {
        final CustomerEntity customerEntity = customerDao.checkCustomer(c.getEmail());
        if (customerEntity != null) {
            throw new CustomerAlreadyRegisteredException("Customer Already Registered: " + customerEntity.getCustomerId());
        }
        final CustomerEntity customer = customerDao
                .save(objectMapper.convertValue(c, CustomerEntity.class));
        return objectMapper.convertValue(customer, Customer.class);
    }

    @Override
    public Customer updateCustomer(@NonNull final Customer c) {
        final CustomerEntity customerEntity = customerDao.findById(c.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + c.getCustomerId()));
        BeanUtils.copyProperties(c, customerEntity);
        final CustomerEntity customer = customerDao.save(customerEntity);
        return objectMapper.convertValue(customer, Customer.class);
    }

    @Override
    public List<Customer> getCustomers(final int pageNumber, final int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        final List<CustomerEntity> customerEntities = customerDao.findAll(pageable).toList();
        return customerEntities.stream()
                .map(customerEntity ->
                        objectMapper.convertValue(customerEntity, Customer.class))
                .collect(Collectors.toList());
    }

    @Override
    public Customer getCustomerById(final int customerId) {
        final CustomerEntity customerEntity = customerDao.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
        log.debug("Customer Found: " + customerId);
        return objectMapper.convertValue(customerEntity, Customer.class);
    }
}
