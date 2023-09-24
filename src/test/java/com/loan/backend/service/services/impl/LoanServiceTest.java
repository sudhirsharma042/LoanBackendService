package com.loan.backend.service.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.backend.service.dao.CustomerRepository;
import com.loan.backend.service.dao.LoanRepository;
import com.loan.backend.service.dao.entity.CustomerEntity;
import com.loan.backend.service.dao.entity.LoanEntity;
import com.loan.backend.service.dto.AggregatedLoans;
import com.loan.backend.service.dto.Customer;
import com.loan.backend.service.dto.Loan;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

    @Mock
    private LoanRepository loanDao;

    @Mock
    private CustomerRepository customerDao;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private Page<LoanEntity> loanEntityPage;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    public void testGetLoans() {
        final Loan loan = new Loan();
        final LoanEntity loanEntity = objectMapper.convertValue(loan, LoanEntity.class);
        final Pageable pageable = PageRequest.of(0, 10);

        when(loanDao.findAll(pageable)).thenReturn(loanEntityPage);
        when(loanEntityPage.toList()).thenReturn(Arrays.asList(loanEntity));

        final List<Loan> loans = loanService.getLoans(0, 10);
        Assert.assertEquals(Arrays.asList(loan), loans);
    }

    @Test
    public void testGetLoansByCustomerId() {
        final Loan loan = new Loan();
        final Customer customer = new Customer();
        customer.setCustomerId(1);
        loan.setCustomer(customer);
        final LoanEntity loanEntity = objectMapper.convertValue(loan, LoanEntity.class);
        final CustomerEntity customerEntity = objectMapper.convertValue(customer, CustomerEntity.class);
        customerEntity.setCustomerId(1);
        customerEntity.setLoanEntities(Arrays.asList(loanEntity));
        loanEntity.setCustomer(customerEntity);

        when(customerDao.findById(1)).thenReturn(Optional.of(customerEntity));

        final List<Loan> loans = loanService.getLoansByCustomerId(1);
        Assert.assertEquals(Arrays.asList(loan), loans);
    }

    @Test
    public void testAddLoan() {
        final Loan loan = new Loan();
        final Customer customer = new Customer();
        customer.setCustomerId(1);
        loan.setCustomer(customer);
        loan.setPaymentDate(new Date());
        loan.setDueDate(new Date((new Date()).getTime() + 100000));
        final CustomerEntity customerEntity = objectMapper.convertValue(customer, CustomerEntity.class);
        customerEntity.setCustomerId(1);
        final LoanEntity loanEntity = objectMapper.convertValue(loan, LoanEntity.class);
        loanEntity.setCustomer(customerEntity);

        when(customerDao.findById(1)).thenReturn(Optional.of(customerEntity));
        when(loanDao.save(any(LoanEntity.class))).thenReturn(loanEntity);

        final Loan result = loanService.addLoan(loan);
        Assert.assertEquals(loan, result);
    }

    @Test
    public void testGetLoanById() {
        final Loan loan = new Loan();
        final Customer customer = new Customer();
        customer.setCustomerId(1);
        loan.setCustomer(customer);
        final CustomerEntity customerEntity = objectMapper.convertValue(customer, CustomerEntity.class);
        customerEntity.setCustomerId(1);
        final LoanEntity loanEntity = objectMapper.convertValue(loan, LoanEntity.class);
        loanEntity.setCustomer(customerEntity);

        when(loanDao.findById(1)).thenReturn(Optional.of(loanEntity));

        final Loan result = loanService.getLoanById(1);
        Assert.assertEquals(loan, result);
    }

    @Test
    public void testGetLoansByLenderId() {
        final Loan loan = new Loan();
        loan.setLenderId("LenderId");
        final LoanEntity loanEntity = objectMapper.convertValue(loan, LoanEntity.class);

        when(loanDao.findByLenderId(loan.getLenderId())).thenReturn(Arrays.asList(loanEntity));

        final List<Loan> result = loanService.getLoansByLenderId(loan.getLenderId());
        Assert.assertEquals(Arrays.asList(loan), result);
    }

    @Test
    public void testGetAggregatedLoansByLender() {
        final AggregatedLoans aggregatedLoan = new AggregatedLoans();

        when(loanDao.findLoansByLender()).thenReturn(Arrays.asList(aggregatedLoan));

        final List<AggregatedLoans> result = loanService.getAggregatedLoansByLender();
        Assert.assertEquals(Arrays.asList(aggregatedLoan), result);
    }

    @Test
    public void testGetAggregatedLoansByCustomer() {
        final AggregatedLoans aggregatedLoan = new AggregatedLoans();

        when(loanDao.findLoansByCustomer()).thenReturn(Arrays.asList(aggregatedLoan));

        final List<AggregatedLoans> result = loanService.getAggregatedLoansByCustomer();
        Assert.assertEquals(Arrays.asList(aggregatedLoan), result);
    }

    @Test
    public void testGetAggregatedLoansByInterest() {
        final AggregatedLoans aggregatedLoan = new AggregatedLoans();

        when(loanDao.findLoansByInterest()).thenReturn(Arrays.asList(aggregatedLoan));

        final List<AggregatedLoans> result = loanService.getAggregatedLoansByInterest();
        Assert.assertEquals(Arrays.asList(aggregatedLoan), result);
    }
}
