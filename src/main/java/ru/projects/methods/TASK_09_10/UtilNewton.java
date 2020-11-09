package ru.projects.methods.TASK_09_10;

import java.util.Arrays;

public class UtilNewton {
    private double X0;
    private double X1;
    private double X2;
    private double X3;

    private double[] funcVar = new double[4];
    private double[] funcVarX1 = new double[3];
    private double[] funcVarX2 = new double[2];
    private double[] funcVarX3 = new double[1];


    public UtilNewton(double x0, double x1, double x2, double x3) {
        X0 = x0;
        X1 = x1;
        X2 = x2;
        X3 = x3;
        calculate();
    }

    public double Func(double x){
        return 1/x + x;
    }

    public double calculateMeNewton(double x){
        return funcVar[0] + funcVarX1[0]*(x-X0) + funcVarX2[0]*(x-X0)*(x-X1) + funcVarX3[0]*(x-X0)*(x-X1)*(x-X2) ;
    }

    public String showMePolynomialNewton(){
        return String.format("P3(x) = %.3f + %.3f(x - %.2f) + %.3f(x - %.2f)(x - %.2f) + %.3f(x - %.2f)(x - %.2f)(x - %.2f)",funcVar[0],funcVarX1[0],X0,funcVarX2[0],X0,X1,funcVarX3[0],X0,X1,X2);
    }

    private void calculate(){
        calculateFisrt();
        calculateSecond();
        calculateThird();
        calculateFourth();
    }
    private void calculateFisrt(){
        funcVar[0] = Func(X0);
        funcVar[1] = Func(X1);
        funcVar[2] = Func(X2);
        funcVar[3] = Func(X3);
    }

    private void calculateSecond(){
        funcVarX1[0] = (funcVar[1] - funcVar[0]) / (X1 - X0);
        funcVarX1[1] = (funcVar[2] - funcVar[1]) / (X2-X1);
        funcVarX1[2] = (funcVar[3] - funcVar[2]) / (X3-X2);
    }

    private void calculateThird(){
        funcVarX2[0] = (funcVarX1[1] - funcVarX1[0]) / (X2-X0);
        funcVarX2[1] = (funcVarX1[2] - funcVarX1[1]) / (X3-X1);
    }

    private void calculateFourth(){
        funcVarX3[0] = (funcVarX2[1] - funcVarX2[0]) / (X3-X0);
    }

}

