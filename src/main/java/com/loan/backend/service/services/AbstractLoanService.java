package com.loan.backend.service.services;

import java.util.List;

import com.loan.backend.service.dao.entity.LoanEntity;
import com.loan.backend.service.dto.AggregatedLoans;
import com.loan.backend.service.dto.Loan;
import lombok.NonNull;

public interface AbstractLoanService {

    List<Loan> getLoans(int pageNumber, int pageSize);

    List<Loan> getAllLoans();

    List<Loan> getLoansByCustomerId(int customerId);

    Loan addLoan(@NonNull final Loan loanEntity);

    Loan getLoanById(int loanId);

    List<Loan> getLoansByLenderId(String lenderId);

    List<AggregatedLoans> getAggregatedLoansByLender();

    List<AggregatedLoans> getAggregatedLoansByCustomer();

    List<AggregatedLoans> getAggregatedLoansByInterest();
}
