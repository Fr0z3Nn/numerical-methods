package ru.projects.methods.TASK_13_14;

import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;

public abstract class Method {

    protected double a = 3;
    protected double b = 4;
    protected double h = 0.1;

    protected ArrayList<Double> x = new ArrayList<>();
    protected ArrayList<Double> y = new ArrayList<>();
    protected ArrayList<Double> delta_y = new ArrayList<>();
    protected ArrayList<Double> z = new ArrayList<>();
    protected ArrayList<Double> delta_z = new ArrayList<>();

    protected Method() {
        y.add(2.0);
        z.add(2.0);
        delta_y.add(0.0);
        delta_z.add(0.0);
        for (; Precision.round(a, 3) <= b; a += h) {
            x.add(a);
        }
    }

    protected abstract void initialization();

    protected double istFunc(double x) {
        return Math.pow(x - 2, 3) + (1 / (x - 2));
    }

    protected double fFunc(double x, double y, double z) {
        return z;
    }

    protected double gFunc(double x, double y, double z) {
        return (Math.pow(x - 2, 3) * z + 3 * y) / (Math.pow(x - 2, 2));
    }

}
