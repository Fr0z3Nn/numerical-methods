package ru.projects.methods.TASK_15_16;

import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.Arrays;

public class Raznost {
    private double h;
    static int count = 0;
    ArrayList<Double> x = new ArrayList<>();
    ArrayList<Double> y = new ArrayList<>();

    public Raznost(double h) {
        this.h = h;
        for (double i = 0; Precision.round(i, 7) <= Precision.round(Math.PI / 6, 7); i += Precision.round(h, 7)) {
            x.add(Precision.round(i, 7));
        }
        if (count++ == 0) {
            x.add(Math.PI / 6);
        }
        len = x.size();
    }

    private int len;

    public void raznost(){
       double[][] arr = new double[len][len];
       double[] vector = new double[len];
       arr[0][0] = 1;
       vector[0] = 2;
       y.add(func(0));
        for (int j = 1; j < x.size() - 1; j++) {
                arr[j][j-1] = 1 - (pFunc(x.get(j))*h)/2;
                arr[j][j] = -2 + (Math.pow(h,2)*qFunc(x.get(j)));
                arr[j][j+1] = 1 + (pFunc(x.get(j))*h)/2;
                y.add(func(x.get(j)));
        }
        arr[len-1][len-1] = 1;
        vector[len-1] = 2.5 - 0.5 * Math.log(3);
        y.add(func(Math.PI/6));


        RealMatrix m = MatrixUtils.createRealMatrix(arr);
        DecompositionSolver solver = new LUDecomposition(m).getSolver();
        RealVector constants = new ArrayRealVector(vector);
        RealVector solution = solver.solve(constants);
        double[] YRoots = solution.toArray();

        System.out.printf("h = %.2f\n", h);
        for (int i = 0; i < len; i++) {
            System.out.printf("x = %.2f ", x.get(i));
            System.out.printf("y_raz = %.2f ", y.get(i));
            System.out.printf("y_ист = %.2f ", YRoots[i]);
            System.out.printf("eps = %.4e \n",Math.abs(YRoots[i]-y.get(i)));
        }
    }

    public double func(double x) {
        return Math.sin(x) + 2 - Math.sin(x) * Math.log((1 + Math.sin(x)) / (1 - Math.sin(x)));
    }

    private double pFunc(double x) {
        return -Math.tan(x);
    }

    private double qFunc(double x) {
        return 2;
    }

    private double fFunc(double x) {
        return 0;
    }

    public ArrayList<Double> getX() {
        return x;
    }

    public ArrayList<Double> getY() {
        return y;
    }
}

