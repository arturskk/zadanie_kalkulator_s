package com.calculator.Service;

import com.calculator.Enum.Country;
import com.calculator.Exception.NotFoundException;
import com.calculator.POJO.CurrencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class NbpClient {

    private RestTemplate restTemplate;

    @Autowired
    public NbpClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }
    /**
     * get currency rate for given country
     */
    public BigDecimal getCurrentExchangeRate(Country country) {
        if (country.equals(Country.PLN)) {
            return BigDecimal.valueOf(0.00);
        }
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://api.nbp.pl/api/exchangerates/rates/a/" + country.getCode();
        ResponseEntity<CurrencyResponse> response = restTemplate.getForEntity(fooResourceUrl, CurrencyResponse.class);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody().getRates() != null && response.getBody().getRates().get(0) != null) {
            return new BigDecimal(response.getBody().getRates().get(0).getMid());
        } else {
            throw new NotFoundException("Response is missing");
        }
    }
}