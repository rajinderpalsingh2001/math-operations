package com.nagarro.mathoperations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nagarro.mathoperations.controllers.MathController;

@SpringBootTest
class MathoperationsApplicationTests {
	@Autowired
    private MathController mathController;

    @Test
    void contextLoads() {
    }

    @Test
    void testControllerWiring() {
        assertNotNull(mathController);
    }

}
