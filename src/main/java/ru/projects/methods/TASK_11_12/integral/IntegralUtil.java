package ru.projects.methods.TASK_11_12.integral;

public class IntegralUtil {
    public static double func(double x) {
        //return x / (Math.pow(3 * x + 4, 2));
        return x * Math.sqrt(49 - x*x);
    }
    public static double integralExactValue() {
        return 0;
        //return ;
    }
}
