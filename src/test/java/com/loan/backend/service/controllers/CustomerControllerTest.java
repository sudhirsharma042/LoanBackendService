package com.loan.backend.service.controllers;

import com.loan.backend.service.services.AbstractCustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.loan.backend.service.dto.Customer;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    private AbstractCustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    public void testAddCustomer() {
        final Customer customer = new Customer();
        when(customerService.addCustomer(customer)).thenReturn(customer);
        final ResponseEntity<Customer> customerResponseEntity = customerController.addCustomer(customer);
        Assert.assertEquals(customer, customerResponseEntity.getBody());
    }

    @Test
    public void testUpdateCustomer() {
        final Customer customer = new Customer();
        when(customerService.updateCustomer(customer)).thenReturn(customer);
        final ResponseEntity<Customer> customerResponseEntity = customerController.updateCustomer(customer);
        Assert.assertEquals(customer, customerResponseEntity.getBody());
    }

    @Test
    public void testGetCustomers() {
        final Customer customer = new Customer();
        when(customerService.getCustomers(0, 10)).thenReturn(Arrays.asList(customer));
        final ResponseEntity<List<Customer>> customerResponseEntity =
                customerController.getCustomers(0, 10);
        Assert.assertEquals(Arrays.asList(customer), customerResponseEntity.getBody());
    }

    @Test
    public void testGetCustomer() {
        final Customer customer = new Customer();
        when(customerService.getCustomerById(1)).thenReturn(customer);
        final ResponseEntity<Customer> customerResponseEntity = customerController.getCustomer(1);
        Assert.assertEquals(customer, customerResponseEntity.getBody());
    }
}
