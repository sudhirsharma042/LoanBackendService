package com.loan.backend.service.controllers;

import com.loan.backend.service.dto.AggregatedLoans;
import com.loan.backend.service.dto.Loan;
import com.loan.backend.service.services.AbstractLoanService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanControllerTest {

    @Mock
    private AbstractLoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @Test
    public void testGetLoans() {
        final Loan loan = new Loan();
        when(loanService.getLoans(0, 10)).thenReturn(Arrays.asList(loan));
        final ResponseEntity<List<Loan>> responseEntity = loanController.getLoans(0, 10);
        Assert.assertEquals(Arrays.asList(loan), responseEntity.getBody());
    }

    @Test
    public void testGetAllLoans() {
        final Loan loan = new Loan();
        when(loanService.getAllLoans()).thenReturn(Arrays.asList(loan));
        final ResponseEntity<List<Loan>> responseEntity = loanController.getAllLoans();
        Assert.assertEquals(Arrays.asList(loan), responseEntity.getBody());
    }

    @Test
    public void testAddLoad() {
        final Loan loan = new Loan();
        when(loanService.addLoan(loan)).thenReturn(loan);
        final ResponseEntity<Loan> responseEntity = loanController.addLoad(loan);
        Assert.assertEquals(loan, responseEntity.getBody());
    }

    @Test
    public void testGetLoanById() {
        final Loan loan = new Loan();
        when(loanService.getLoanById(1)).thenReturn(loan);
        final ResponseEntity<Loan> responseEntity = loanController.getLoanById(1);
        Assert.assertEquals(loan, responseEntity.getBody());
    }

    @Test
    public void testGetLoansByCustomerId() {
        final Loan loan = new Loan();
        when(loanService.getLoansByCustomerId(1)).thenReturn(Arrays.asList(loan));
        final ResponseEntity<List<Loan>> responseEntity = loanController.getLoansByCustomerId(1);
        Assert.assertEquals(Arrays.asList(loan), responseEntity.getBody());
    }

    @Test
    public void testGetLoansByLenderId() {
        final Loan loan = new Loan();
        when(loanService.getLoansByLenderId("LenderId")).thenReturn(Arrays.asList(loan));
        final ResponseEntity<List<Loan>> responseEntity = loanController.getLoansByLenderId("LenderId");
        Assert.assertEquals(Arrays.asList(loan), responseEntity.getBody());
    }

    @Test
    public void testGetAggregatedLoansByLender() {
        final AggregatedLoans aggregatedLoans = new AggregatedLoans("LenderId", 1, 1, 1, 1);
        when(loanService.getAggregatedLoansByLender()).thenReturn(Arrays.asList(aggregatedLoans));
        final ResponseEntity<List<AggregatedLoans>> responseEntity = loanController.getAggregatedLoansByLender();
        Assert.assertEquals(Arrays.asList(aggregatedLoans), responseEntity.getBody());
    }

    @Test
    public void testGetAggregatedLoansByCustomer() {
        final AggregatedLoans aggregatedLoans = new AggregatedLoans("LenderId", 1, 1, 1, 1);
        when(loanService.getAggregatedLoansByCustomer()).thenReturn(Arrays.asList(aggregatedLoans));
        final ResponseEntity<List<AggregatedLoans>> responseEntity = loanController.getAggregatedLoansByCustomer();
        Assert.assertEquals(Arrays.asList(aggregatedLoans), responseEntity.getBody());
    }

    @Test
    public void testGetAggregatedLoansByInterest() {
        final AggregatedLoans aggregatedLoans = new AggregatedLoans("LenderId", 1, 1, 1, 1);
        when(loanService.getAggregatedLoansByInterest()).thenReturn(Arrays.asList(aggregatedLoans));
        final ResponseEntity<List<AggregatedLoans>> responseEntity = loanController.getAggregatedLoansByInterest();
        Assert.assertEquals(Arrays.asList(aggregatedLoans), responseEntity.getBody());
    }
}
