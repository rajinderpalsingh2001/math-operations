package com.nagarro.mathoperations.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.mathoperations.constants.MathOperationConstants;
import com.nagarro.mathoperations.controllers.MathController;
import com.nagarro.mathoperations.models.MathResponse;
import com.nagarro.mathoperations.services.MathService;

@WebMvcTest(controllers = MathController.class)
@ExtendWith(SpringExtension.class)
public class MathControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MathService mathService;

    MathResponse mockMathResponse;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
    }

    @ParameterizedTest
    @CsvSource({
            "10, 5, 15",
            "-2, 8, 6",
            "0, 3, 3",
            "3, 0, 3",
            Long.MAX_VALUE - 1 + ", 2, " + Long.MAX_VALUE // Large numbers (edge case)
    })
    void testSum(Long a, Long b, Long expectedSum) throws Exception {
        when(mathService.sum(a, b)).thenReturn(expectedSum);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/math/sum")
                .param("a", a.toString())
                .param("b", b.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        Long ans = Long.parseLong(objectMapper.readTree(responseBody).get("solution").asText());

        assertEquals(expectedSum, ans);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 5, 5",
            "-2, 8, -10",
            "0, 3, -3",
            "3, 0, 3",
            Long.MAX_VALUE - 1 + ", 2, " + Long.MAX_VALUE // Large numbers (edge case)
    })
    void testSubtract(Long a, Long b, Long expectedDifference) throws Exception {
        when(mathService.subtract(a, b)).thenReturn(expectedDifference);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/math/subtract")
                .param("a", a.toString())
                .param("b", b.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        Long ans = Long.parseLong(objectMapper.readTree(responseBody).get("solution").asText());

        assertEquals(expectedDifference, ans);
    }


    @ParameterizedTest
    @CsvSource({
            "10, 5, 50",
            "-2, 8, -16",
            "0, 3, 0",
            "-3, -3, 9",
            Long.MAX_VALUE - 1 + ", 2, " + Long.MAX_VALUE // Large numbers (edge case)
    })
    void testMultiply(Long a, Long b, Long expectedProduct) throws Exception {
        when(mathService.multiply(a, b)).thenReturn(expectedProduct);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/math/multiply")
                .param("a", a.toString())
                .param("b", b.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        Long ans = Long.parseLong(objectMapper.readTree(responseBody).get("solution").asText());

        assertEquals(expectedProduct, ans);
    }


    @ParameterizedTest
    @CsvSource({
            "10, 2, 5.0",      // Standard division
            "5, 0, BAD_REQUEST" // Division by zero
    })
    void testDivide(Long a, Long b, String expectedResult) throws Exception {
        if (b == 0) {
            mockMvc.perform(MockMvcRequestBuilders.get("/math/divide")
                .param("a", a.toString())
                .param("b", b.toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        return; 
        }
        Double actualResult = Double.parseDouble(expectedResult);
        when(mathService.divide(a, b)).thenReturn(actualResult);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/math/divide")
                .param("a", a.toString())
                .param("b", b.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        Double ans = Double.parseDouble(objectMapper.readTree(responseBody).get("solution").asText());

        assertEquals(actualResult, ans);
    }



}
