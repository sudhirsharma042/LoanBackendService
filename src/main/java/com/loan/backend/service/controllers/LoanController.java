package com.loan.backend.service.controllers;

import com.loan.backend.service.dto.AggregatedLoans;
import com.loan.backend.service.dto.Loan;
import com.loan.backend.service.services.AbstractLoanService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
@CrossOrigin(origins = "*")
@Log4j2
public class LoanController {

    private final AbstractLoanService loanService;

    @Autowired
    public LoanController(AbstractLoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/getLoans")
    public ResponseEntity<List<Loan>> getLoans(@RequestParam("page") int pageNumber,
                                               @RequestParam("size") int pageSize) {
        return new ResponseEntity<>(loanService.getLoans(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Loan>> getAllLoans() {
        return new ResponseEntity<>(loanService.getAllLoans(), HttpStatus.OK);
    }

    @PostMapping("/loans/add")
    public ResponseEntity<Loan> addLoad(@RequestBody Loan loan) {
        return new ResponseEntity<>(loanService.addLoan(loan), HttpStatus.OK);
    }

    @GetMapping("/loans/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable int loanId) {
        return new ResponseEntity<>(loanService.getLoanById(loanId), HttpStatus.OK);
    }

    @GetMapping("/loans/customer/{customerId}")
    public ResponseEntity<List<Loan>> getLoansByCustomerId(@PathVariable int customerId) {
        return new ResponseEntity<>(loanService.getLoansByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/loans/lender/{lenderId}")
    public ResponseEntity<List<Loan>> getLoansByLenderId(@PathVariable String lenderId) {
        return new ResponseEntity<>(loanService.getLoansByLenderId(lenderId), HttpStatus.OK);
    }

    @GetMapping("/loans/aggregate/lender")
    public ResponseEntity<List<AggregatedLoans>> getAggregatedLoansByLender() {
        return new ResponseEntity<>(loanService.getAggregatedLoansByLender(), HttpStatus.OK);
    }

    @GetMapping("/loans/aggregate/customer")
    public ResponseEntity<List<AggregatedLoans>> getAggregatedLoansByCustomer() {
        return new ResponseEntity<>(loanService.getAggregatedLoansByCustomer(), HttpStatus.OK);
    }

    @GetMapping("/loans/aggregate/interest")
    public ResponseEntity<List<AggregatedLoans>> getAggregatedLoansByInterest() {
        return new ResponseEntity<>(loanService.getAggregatedLoansByInterest(), HttpStatus.OK);
    }
}
