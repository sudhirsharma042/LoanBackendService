package com.loan.backend.service;

import com.loan.backend.service.dao.LoanRepository;
import com.loan.backend.service.dto.Loan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckDefaultedLoansTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private CheckDefaultedLoans checkDefaultedLoans;

    @Test
    public void testExecute() {
        final Loan loan = new Loan();
        when(loanRepository.findAllByDueDateBeforeAndCancelFalse(any(Date.class))).thenReturn(Arrays.asList(loan));

        checkDefaultedLoans.execute();
        verify(loanRepository, times(1)).findAllByDueDateBeforeAndCancelFalse(any());
    }
}
