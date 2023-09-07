package com.app.calculator.ViewModel;

import androidx.lifecycle.ViewModel;

import com.app.calculator.Model.CalculatorLogic;

public class CalculatorViewModel extends ViewModel {

    private final CalculatorLogic calculatorLogic = new CalculatorLogic();

    public int performAddition(int a, int b){
        return calculatorLogic.addition(a,b);
    }
}
