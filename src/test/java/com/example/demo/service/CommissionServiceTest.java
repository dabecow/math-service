package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommissionServiceTest {

    private final CommissionService commissionService;

    @Value("${commission.minimal}")
    private BigDecimal minCommission;
    @Value("${commission.percentage}")
    private BigDecimal commissionPercentage;

    public CommissionServiceTest() {
        this.commissionService = new CommissionService();
    }

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(commissionService, "minCommission", minCommission);
        ReflectionTestUtils.setField(commissionService, "commissionPercentage", commissionPercentage);
    }

    @Test
    void getCommission_whenBelowOrEqualsMinimal_equalsSum() {
        BigDecimal commission = commissionService.getCommission(minCommission);
        Assertions.assertEquals(minCommission, commission);
    }

    @Test
    void getCommission_whenAboveMinimal_equalsByFormula() {
        BigDecimal sum = minCommission.divide(commissionPercentage, 2, RoundingMode.FLOOR).multiply(BigDecimal.valueOf(110));
        BigDecimal expectedCommission = sum.multiply(commissionPercentage);
        BigDecimal commission = commissionService.getCommission(sum);
        Assertions.assertEquals(expectedCommission, commission);
    }
}
