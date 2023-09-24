package com.loan.backend.service.controllers;

import com.loan.backend.service.dao.entity.CustomerEntity;
import com.loan.backend.service.dto.Customer;
import com.loan.backend.service.services.AbstractCustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
@Log4j2
public class CustomerController {

    private final AbstractCustomerService customerService;

    @Autowired
    public CustomerController(AbstractCustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam("page") int pageNumber,
                                                       @RequestParam("size") int pageSize) {
        return new ResponseEntity<>(customerService.getCustomers(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }
}
