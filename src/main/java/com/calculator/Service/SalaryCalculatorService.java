package com.calculator.Service;

import com.calculator.Enum.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class SalaryCalculatorService {

    private NbpClient nbpClient;

    private final BigDecimal workdaysInMonth = new BigDecimal("22");

    @Autowired
    public SalaryCalculatorService(NbpClient nbpClient) {
        this.nbpClient = nbpClient;
    }

    public BigDecimal calculateNettSalary(BigDecimal dailyGrossAmount, String country) {
        BigDecimal nettSalary = BigDecimal.valueOf(0.00);
        BigDecimal grossMontlhy = dailyGrossAmount.multiply(workdaysInMonth);
        Country countryEnum = Country.getCountryByCode(country);
        if (countryEnum.equals(Country.PLN)) {
            nettSalary = (grossMontlhy.multiply((BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(countryEnum.getTax()))))).subtract(BigDecimal.valueOf(countryEnum.getFixedCost())).setScale(2, RoundingMode.HALF_UP);
        } else {
            BigDecimal currencRate = nbpClient.getCurrentExchangeRate(countryEnum);
            nettSalary = (grossMontlhy.multiply((BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(countryEnum.getTax()))))).subtract(BigDecimal.valueOf(countryEnum.getFixedCost())).multiply(currencRate).setScale(2, RoundingMode.HALF_UP);
        }
        return nettSalary;
    }
}
