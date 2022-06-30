package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CommissionService {

    @Value("${commission.minimal}")
    private BigDecimal minCommission;
    @Value("${commission.percentage}")
    private BigDecimal commissionPercentage;

    public BigDecimal getCommission(BigDecimal moneySum) {
        if (moneySum.compareTo(minCommission) <= 0)
            return moneySum;

        BigDecimal result = moneySum.multiply(commissionPercentage);
        if (result.compareTo(minCommission) <= 0)
            return BigDecimal.valueOf(250);
        else
            return result;
    }
}
