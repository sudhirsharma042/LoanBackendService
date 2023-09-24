package com.loan.backend.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Loan implements Serializable {

    private int loanId;

    private String lenderId;

    private double loanAmt;

    private double remainingAmt;

    private Date paymentDate;

    private float interestPerDay;

    private Date dueDate;

    private float penaltyPerDay;

    private boolean cancel;

    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanId == loan.loanId && Double.compare(loan.loanAmt, loanAmt) == 0 && Double.compare(loan.remainingAmt, remainingAmt) == 0 && Float.compare(loan.interestPerDay, interestPerDay) == 0 && Float.compare(loan.penaltyPerDay, penaltyPerDay) == 0 && cancel == loan.cancel && Objects.equals(lenderId, loan.lenderId) && Objects.equals(paymentDate, loan.paymentDate) && Objects.equals(dueDate, loan.dueDate) && Objects.equals(customer, loan.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, lenderId, loanAmt, remainingAmt, paymentDate, interestPerDay, dueDate, penaltyPerDay, cancel, customer);
    }
}
