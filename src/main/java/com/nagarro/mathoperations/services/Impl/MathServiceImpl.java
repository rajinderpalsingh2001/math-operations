package com.nagarro.mathoperations.services.Impl;

import org.springframework.stereotype.Service;

import com.nagarro.mathoperations.services.MathService;

@Service
public class MathServiceImpl implements MathService {

    @Override
    public Long sum(Long a, Long b) {
        return a+b;
    }

    @Override
    public Long multiply(Long a, Long b) {
        return a*b;
    }

    @Override
    public Long subtract(Long a, Long b) {
        return a-b;
    }

    @Override
    public Double divide(Long a, Long b) {
        return (double) a/b;
    }
    
}
