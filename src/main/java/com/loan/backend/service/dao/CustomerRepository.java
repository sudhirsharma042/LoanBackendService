package com.loan.backend.service.dao;

import com.loan.backend.service.dao.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Integer> {

    Page<CustomerEntity> findAll(Pageable pageable);

    @Query("select c from CustomerEntity c where c.email=?1")
    CustomerEntity checkCustomer(String email);
}
