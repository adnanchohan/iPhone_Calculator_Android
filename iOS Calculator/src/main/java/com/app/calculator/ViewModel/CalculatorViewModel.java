package com.app.calculator.ViewModel;

import androidx.lifecycle.ViewModel;

import com.app.calculator.Model.CalculatorLogic;

public class CalculatorViewModel extends ViewModel {

    private final CalculatorLogic calculatorLogic = new CalculatorLogic();

    public double performAddition(double a, double b){
        return calculatorLogic.addition(a,b);
    }
    public double performSubtraction(double a, double b){
        return calculatorLogic.subtract(a,b);
    }
    public double performDivision(double a, double b){
        return calculatorLogic.divide(a,b);
    }
    public double performMultiplication(double a, double b){
        return calculatorLogic.multiply(a,b);
    }
}
