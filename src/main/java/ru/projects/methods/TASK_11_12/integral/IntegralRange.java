package ru.projects.methods.TASK_11_12.integral;

public class IntegralRange {
    private double X0;
    private double XK;
    private double h;


    public IntegralRange(double x0, double XK, double h) {
        X0 = x0;
        this.XK = XK;
        this.h = h;
    }

    public double getX0() {
        return X0;
    }

    public double getXK() {
        return XK;
    }

    public double getH() {
        return h;
    }

}
