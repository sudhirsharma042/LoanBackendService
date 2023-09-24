package com.loan.backend.service;

import com.loan.backend.service.dao.LoanRepository;
import com.loan.backend.service.dto.Loan;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class CheckDefaultedLoans {

    private final LoanRepository loanRepository;

    @Autowired
    public CheckDefaultedLoans(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    //Schedule to run every day
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    @Async
    public void execute() {
        final List<Loan> defaultedLoans = loanRepository.findAllByDueDateBeforeAndCancelFalse(new Date());
        log.info("Defaulted Loan - {} ", defaultedLoans);
        //TODO to add logic for already alerted defaulted loans or some other process.
    }
}
