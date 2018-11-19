package com.calculator;

import com.calculator.Service.SalaryCalculatorService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Tests for Rest Api
 */
public class RestApiTest extends AppTestConfig {

    @Autowired
    SalaryCalculatorService salaryCalculatorService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testSalaryController() throws Exception {

       BigDecimal nettValue =  salaryCalculatorService.calculateNettSalary(BigDecimal.valueOf(1000),"GBP");

        MvcResult result = mockMvc.perform(get("/salary")
                .param("selectedCurrency", "GBP")
                .param("grossSalary","1000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),nettValue.toString());

    }

    @Test
    public void testCountriesController() throws Exception {

        MvcResult result = mockMvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(),"[\"PLN\",\"GBP\",\"EUR\"]");
    }

}
