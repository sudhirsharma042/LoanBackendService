package com.loan.backend.service.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.backend.service.dao.CustomerRepository;
import com.loan.backend.service.dao.entity.CustomerEntity;
import com.loan.backend.service.dto.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerDao;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private Page<CustomerEntity> customerEntityPage;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testAddCustomer() {
        final Customer customer = new Customer();
        customer.setEmail("TestEmail");
        final CustomerEntity customerEntity = objectMapper.convertValue(customer, CustomerEntity.class);

        when(customerDao.checkCustomer(customer.getEmail())).thenReturn(null);
        when(customerDao.save(any(CustomerEntity.class))).thenReturn(customerEntity);

        final Customer c = customerService.addCustomer(customer);
        Assert.assertEquals(customer, c);
    }

    @Test
    public void testUpdateCustomer() {
        final Customer customer = new Customer();
        customer.setCustomerId(1);
        final CustomerEntity customerEntity = objectMapper.convertValue(customer, CustomerEntity.class);

        when(customerDao.findById(customer.getCustomerId())).thenReturn(Optional.of(customerEntity));
        when(customerDao.save(customerEntity)).thenReturn(customerEntity);

        final Customer c = customerService.updateCustomer(customer);
        Assert.assertEquals(customer, c);
    }

    @Test
    public void testGetCustomers() {
        final Customer customer = new Customer();
        final CustomerEntity customerEntity = objectMapper.convertValue(customer, CustomerEntity.class);
        final Pageable pageable = PageRequest.of(0, 10);

        when(customerDao.findAll(pageable)).thenReturn(customerEntityPage);
        when(customerEntityPage.toList()).thenReturn(Arrays.asList(customerEntity));

        final List<Customer> c = customerService.getCustomers(0, 10);
        Assert.assertEquals(Arrays.asList(customer), c);
    }

    @Test
    public void testGetCustomerById() {
        final Customer customer = new Customer();
        customer.setCustomerId(1);
        final CustomerEntity customerEntity = objectMapper.convertValue(customer, CustomerEntity.class);
        customerEntity.setCustomerId(1);

        when(customerDao.findById(1)).thenReturn(Optional.of(customerEntity));

        final Customer c = customerService.getCustomerById(1);
        Assert.assertEquals(customer, c);
    }
}
