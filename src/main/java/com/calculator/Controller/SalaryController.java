package com.calculator.Controller;

import com.calculator.Service.SalaryCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
/**
 * Rest api end points
 */
@RestController
public class SalaryController {

    private SalaryCalculatorService salaryCalculatorService;

    @Autowired
    public SalaryController(SalaryCalculatorService salaryCalculatorService) {
        this.salaryCalculatorService = salaryCalculatorService;
    }

    /**
     * Search by provided gross daily salary and country
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/salary"}, method = RequestMethod.GET)
    public final ResponseEntity<BigDecimal> calculateSalary(@RequestParam("selectedCurrency") String selectedCurrency, @RequestParam("grossSalary") BigDecimal grossSalary) {
        BigDecimal nettSalary = salaryCalculatorService.calculateNettSalary(grossSalary, selectedCurrency);
        return new ResponseEntity<>(nettSalary, HttpStatus.OK);
    }
}
