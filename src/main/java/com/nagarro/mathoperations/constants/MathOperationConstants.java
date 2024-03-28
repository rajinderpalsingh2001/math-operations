package com.nagarro.mathoperations.constants;

public enum MathOperationConstants {
    SUM("sum"),
    SUBTRACT("subtract"),
    DIVIDE("divide"),
    MULTIPLY("multiply");

    private final String value; 

    MathOperationConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
