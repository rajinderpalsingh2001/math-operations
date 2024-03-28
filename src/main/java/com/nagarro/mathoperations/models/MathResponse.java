package com.nagarro.mathoperations.models;

public class MathResponse {
    String solution;
    String operation;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public MathResponse(String solution, String operation){
        this.solution = solution;
        this.operation = operation;
    }

    public String getSolution() {
        return solution;
    }
    public void setSolution(String solution) {
        this.solution = solution;
    }
}
