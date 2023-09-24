package com.loan.backend.service.dao;

import com.loan.backend.service.dao.entity.LoanEntity;
import com.loan.backend.service.dto.AggregatedLoans;
import com.loan.backend.service.dto.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<LoanEntity, Integer> {

    List<LoanEntity> findByLenderId(String lenderId);

    @Query(value = "select new com.loan.backend.service.dto.AggregatedLoans(" +
            "l.lenderId, -1, sum(l.remainingAmt), l.interestPerDay, l.penaltyPerDay) " +
            "from LoanEntity l " +
            "group by l.lenderId, l.interestPerDay, l.penaltyPerDay ", nativeQuery = false)
    List<AggregatedLoans> findLoansByLender();

    @Query(value = "select new com.loan.backend.service.dto.AggregatedLoans(" +
            "'TempLenderId', l.customer.id, sum(l.remainingAmt), l.interestPerDay, l.penaltyPerDay) " +
            "from LoanEntity l " +
            "JOIN  l.customer " +
            "group by l.customer.id, l.interestPerDay, l.penaltyPerDay ", nativeQuery = false)
    List<AggregatedLoans> findLoansByCustomer();

    @Query(value = "select new com.loan.backend.service.dto.AggregatedLoans(" +
            "'TempLenderId', -1, sum(l.remainingAmt), l.interestPerDay, l.penaltyPerDay) " +
            "from LoanEntity l " +
            "group by l.interestPerDay, l.penaltyPerDay ", nativeQuery = false)
    List<AggregatedLoans> findLoansByInterest();

    List<Loan> findAllByDueDateBeforeAndCancelFalse(Date today);
}
