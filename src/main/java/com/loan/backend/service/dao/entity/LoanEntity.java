package com.loan.backend.service.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "loan")
@Getter
@Setter
@NoArgsConstructor
public class LoanEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_id")
    private int loanId;

    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @JsonIgnoreProperties("loanEntities")
    private CustomerEntity customer;

    @Column(name = "lender_id", nullable = false)
    private String lenderId;

    @Column(name = "loan_amount")
    private double loanAmt;

    @Column(name = "remaining_amount")
    private double remainingAmt;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "interest_per_day")
    private float interestPerDay;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "penalty_per_day")
    private float penaltyPerDay;

    @Column(name = "cancel")
    private boolean cancel;
}
