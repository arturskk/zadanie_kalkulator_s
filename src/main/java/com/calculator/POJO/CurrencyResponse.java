package com.calculator.POJO;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyResponse {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;
}