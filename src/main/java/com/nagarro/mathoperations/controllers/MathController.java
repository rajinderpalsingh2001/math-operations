package com.nagarro.mathoperations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.mathoperations.constants.MathOperationConstants;
import com.nagarro.mathoperations.models.MathResponse;
import com.nagarro.mathoperations.services.MathService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    MathService mathService;

    @GetMapping("/sum")
    ResponseEntity<?> sum(@RequestParam Long a, @RequestParam Long b) {
        Long res = mathService.sum(a, b);
        MathResponse response = new MathResponse(res.toString(), MathOperationConstants.SUM.getValue());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/subtract") 
    ResponseEntity<?> subtract(@RequestParam Long a, @RequestParam Long b) {
        Long result = mathService.subtract(a, b);
        MathResponse response = new MathResponse(result.toString(), MathOperationConstants.SUBTRACT.getValue());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/multiply") 
    ResponseEntity<?> multiply(@RequestParam Long a, @RequestParam Long b) {
        Long result = mathService.multiply(a, b);
        MathResponse response = new MathResponse(result.toString(), MathOperationConstants.MULTIPLY.getValue());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/divide") 
    ResponseEntity<?> divide(@RequestParam Long a, @RequestParam Long b) {
        if (b == 0) {
            return ResponseEntity.badRequest().body("Division by zero"); 
        }
        Double result = mathService.divide(a, b); 
        MathResponse response = new MathResponse(result.toString(), MathOperationConstants.DIVIDE.getValue());
        return ResponseEntity.ok().body(response);
    }   
}
