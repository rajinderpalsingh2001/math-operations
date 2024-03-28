package com.nagarro.mathoperations.services;
import org.springframework.stereotype.Service;

@Service
public interface MathService {
    Long sum(Long a, Long b);
    Long multiply(Long a, Long b);
    Long subtract(Long a, Long b);
    Double divide(Long a, Long b);
}
