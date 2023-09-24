package com.loan.backend.service.services;

import java.util.List;

import com.loan.backend.service.dao.entity.CustomerEntity;
import com.loan.backend.service.dto.Customer;
import lombok.NonNull;

public interface AbstractCustomerService {

    Customer addCustomer(@NonNull final Customer customer);

    Customer updateCustomer(@NonNull final Customer customer);

    List<Customer> getCustomers(final int pageNumber, final int pageSize);

    Customer getCustomerById(final int customerId);
}
