package com.example.demo.controller;

import com.example.demo.service.CommissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CommissionController {

    private final CommissionService commissionService;

    @GetMapping("/commission")
    public BigDecimal getCommission(@RequestParam BigDecimal moneySum) {
        return commissionService.getCommission(moneySum);
    }
}
