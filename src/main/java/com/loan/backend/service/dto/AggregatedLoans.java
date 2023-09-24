package com.loan.backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedLoans {

    private String lenderId;

    private int customerId;

    private double remainingAmt;

    private float interestPerDay;

    private float penaltyPerDay;
}
