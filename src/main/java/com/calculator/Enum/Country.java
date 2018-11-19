package com.calculator.Enum;

import java.util.Arrays;

public enum Country {

    PLN("Polish Zloty", "PLN", 0.19f, 1200),
    GBP("Pound Sterling", "GBP", 0.25f, 600),
    EUR("Euro", "EUR", 0.2f, 800);

    private final String name;
    private final String code;
    private final float tax;
    private final float fixedCost;

    Country(String name, String code, float tax, float fixedCost) {
        this.name = name;
        this.code = code;
        this.tax = tax;
        this.fixedCost = fixedCost;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public float getTax() {
        return tax;
    }

    public float getFixedCost() {
        return fixedCost;
    }

    public static Country getCountryByCode(String code) {
        return Arrays.asList(values()).stream().filter(e -> e.code.equals(code)).findFirst().orElse(null);
    }

}
