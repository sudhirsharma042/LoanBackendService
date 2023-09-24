package com.loan.backend.service.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.loan.backend.service.dao.CustomerRepository;
import com.loan.backend.service.dao.LoanRepository;
import com.loan.backend.service.dao.entity.CustomerEntity;
import com.loan.backend.service.dao.entity.LoanEntity;
import com.loan.backend.service.dto.AggregatedLoans;
import com.loan.backend.service.dto.Loan;
import com.loan.backend.service.exceptions.CustomerNotFoundException;
import com.loan.backend.service.exceptions.LoanNotFoundException;
import com.loan.backend.service.exceptions.ValidationFailureException;
import com.loan.backend.service.services.AbstractLoanService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION;

@Service
@Primary
@Log4j2
public class LoanServiceImpl implements AbstractLoanService {

    private final LoanRepository loanDao;

    private final CustomerRepository customerDao;

    private final ObjectMapper objectMapper;

    @Autowired
    public LoanServiceImpl(@NonNull final LoanRepository loanDao,
                           @NonNull final CustomerRepository customerDao) {
        this.loanDao = loanDao;
        this.customerDao = customerDao;
        objectMapper =  new ObjectMapper().configure(DEFAULT_VIEW_INCLUSION, true);
    }

    @Override
    public List<Loan> getLoans(final int pageNumber, final int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        final List<LoanEntity> loanEntities = loanDao.findAll(pageable).toList();
        return loanEntities.stream()
                .map(loanEntity ->
                        objectMapper.convertValue(loanEntity, Loan.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> getAllLoans() {
        final List<LoanEntity> loanEntities = Lists.newArrayList(loanDao.findAll());
        return loanEntities.stream()
                .map(loanEntity ->
                        objectMapper.convertValue(loanEntity, Loan.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> getLoansByCustomerId(final int customerId) {
        final CustomerEntity customerEntity = customerDao.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
        final List<LoanEntity> loanEntities = customerEntity.getLoanEntities();
        return loanEntities.stream()
                .map(loanEntity ->
                        objectMapper.convertValue(loanEntity, Loan.class))
                .collect(Collectors.toList());
    }

    @Override
    public Loan addLoan(@NonNull final Loan loan) {
        if (loan.getPaymentDate().before(loan.getDueDate())) {
            final int customerId = loan.getCustomer().getCustomerId();
            final CustomerEntity customerEntity = customerDao.findById(customerId)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
            LoanEntity loanEntity = objectMapper.convertValue(loan, LoanEntity.class);
            customerEntity.addLoan(loanEntity);
            loanEntity = loanDao.save(loanEntity);
            return objectMapper.convertValue(loanEntity, Loan.class);
        }
        throw new ValidationFailureException("The payment date can’t be greater than the Due Date.");
    }

    @Override
    public Loan getLoanById(int loanId) {
        final LoanEntity loanEntity = loanDao.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
        return objectMapper.convertValue(loanEntity, Loan.class);
    }

    @Override
    public List<Loan> getLoansByLenderId(String lenderId) {
        final List<LoanEntity> loanEntities = loanDao.findByLenderId(lenderId);
        return loanEntities.stream()
                .map(loanEntity ->
                        objectMapper.convertValue(loanEntity, Loan.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AggregatedLoans> getAggregatedLoansByLender() {
        return loanDao.findLoansByLender();
    }

    @Override
    public List<AggregatedLoans> getAggregatedLoansByCustomer() {
        return loanDao.findLoansByCustomer();
    }

    @Override
    public List<AggregatedLoans> getAggregatedLoansByInterest() {
        return loanDao.findLoansByInterest();
    }
}
