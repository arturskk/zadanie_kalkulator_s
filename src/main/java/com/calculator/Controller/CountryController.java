package com.calculator.Controller;

import com.calculator.Enum.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {
    /**
     * get available currencies
     */
    @GetMapping(value = "/countries")
    public List<String> getAvailableCurrencies() {

        List<String> currencies = new ArrayList<>();
        for (Country country : Country.values()) {
            currencies.add(country.getCode());
        }
        return currencies;
    }
}