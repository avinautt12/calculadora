package com.example.calculadora;

public class Calculadora {

    private double result;
    private double tempValor;
    private String currentOperation;

    public Calculadora() {
        reset();
    }

    public void setValue(double value) {
        if (currentOperation.isEmpty()) {
            tempValor = value;
        } else {
            calculate(value);
        }
    }

    private void calculate(double value) {
        switch (currentOperation) {
            case "+":
                result = tempValor + value;
                break;
            case "-":
                result = tempValor - value;
                break;
            case "*":
                result = tempValor * value;
                break;
            case "/":
                if (value != 0) {
                    result = tempValor / value;
                } else {
                    result = 0;
                }
                break;
            default:
                return;
        }
        tempValor = result;
        currentOperation = "";
    }

    public double getResult() {
        return result;
    }

    public void performOperation(String operation) {
        if (currentOperation.isEmpty() && !operation.equals("=")) {
            currentOperation = operation;
            result = tempValor;
        } else if (operation.equals("=")) {
            calculate(tempValor);
            currentOperation = "";
        } else {
            calculate(tempValor);
            currentOperation = operation;
            tempValor = result;
        }
    }

    public void reset() {
        result = 0;
        tempValor = 0;
        currentOperation = "";
    }

    public void clearEntry() {
        reset();
    }
}
