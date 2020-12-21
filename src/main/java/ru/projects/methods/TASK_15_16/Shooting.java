package ru.projects.methods.TASK_15_16;

import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;

public class Shooting {

    static int count = 0;

    private double eps = 0.0001;
    List<Double> F = new ArrayList<>();
    List<Double> Betta = new ArrayList<>();
    List<Double> n = new ArrayList<>();
    private double BettaK = 2.5 - 0.5 * Math.log(3);

    private double h;

    ArrayList<Double> x = new ArrayList<>();

    ArrayList<Double> y = null;
    ArrayList<Double> z = null;

    public void rynge() {
        int r = 0;
        System.out.println(String.format("h = %.2f", h));
        while (F.get(r) >= eps) {
            ArrayList<Double> K1 = new ArrayList<>();
            ArrayList<Double> K2 = new ArrayList<>();
            ArrayList<Double> K3 = new ArrayList<>();
            ArrayList<Double> K4 = new ArrayList<>();
            ArrayList<Double> L1 = new ArrayList<>();
            ArrayList<Double> L2 = new ArrayList<>();
            ArrayList<Double> L3 = new ArrayList<>();
            ArrayList<Double> L4 = new ArrayList<>();
            y = new ArrayList<>();
            y.add(2.0);
            z = new ArrayList<>();
            z.add(n.get(r));
            ArrayList<Double> deltaY = new ArrayList<>();
            ArrayList<Double> deltaZ = new ArrayList<>();

            System.out.println(String.format("k = %2d", r));

            for (int i = 0; i < x.size(); i++) {
                K1.add(h * fFunc(x.get(i), y.get(i), z.get(i)));
                L1.add(h * gFunc(x.get(i), y.get(i), z.get(i)));
                K2.add(h * fFunc(x.get(i) + 0.5 * h, y.get(i) + 0.5 * K1.get(i), z.get(i) + 0.5 * L1.get(i)));
                L2.add(h * gFunc(x.get(i) + 0.5 * h, y.get(i) + 0.5 * K1.get(i), z.get(i) + 0.5 * L1.get(i)));
                K3.add(h * fFunc(x.get(i) + 0.5 * h, y.get(i) + 0.5 * K2.get(i), z.get(i) + 0.5 * L2.get(i)));
                L3.add(h * gFunc(x.get(i) + 0.5 * h, y.get(i) + 0.5 * K2.get(i), z.get(i) + 0.5 * L2.get(i)));
                K4.add(h * fFunc(x.get(i) + h, y.get(i) + K3.get(i), z.get(i) + L3.get(i)));
                L4.add(h * gFunc(x.get(i) + h, y.get(i) + K3.get(i), z.get(i) + L3.get(i)));
                deltaY.add((K1.get(i) + 2 * K2.get(i) + 2 * K3.get(i) + K4.get(i)) / 6);
                deltaZ.add((L1.get(i) + 2 * L2.get(i) + 2 * L3.get(i) + L4.get(i)) / 6);
                System.out.print(String.format("x = %.2f ", x.get(i)));
                y.add(y.get(i) + deltaY.get(i));
                System.out.print(String.format("y = %.2f ", y.get(i)));
                z.add(z.get(i) + deltaZ.get(i));
                System.out.print(String.format("y_ист = %.2f ", func(x.get(i))));
                System.out.print(String.format("z = %.2f ", z.get(i)));
                System.out.println(String.format("EPS = %.2f ", Math.abs(func(x.get(i)) - y.get(i))));
            }
            Betta.add(y.get(y.size() - 1));
            F.add(Math.abs(Betta.get(r) - BettaK));
            System.out.println(String.format("|Ф| = %.6f", F.get(r)));
            if (r > 0) {
                n.add(n.get(r) - (n.get(r) - n.get(r - 1)) / (Betta.get(r) - Betta.get(r - 1)) * (Betta.get(r) - BettaK));
            }
            r++;
            System.out.println();
        }


    }

    public Shooting(double h, double n1, double n2) {
        this.h = h;
        n.add(n1);
        n.add(n2);
        F.add(eps);
        for (double i = 0; Precision.round(i, 7) <= Precision.round(Math.PI / 6, 7); i += Precision.round(h, 7)) {
            x.add(Precision.round(i, 7));
        }
        if (count++ == 0) {
            x.add(Math.PI / 6);
        }

    }

    public double func(double x) {
        return Math.sin(x) + 2 - Math.sin(x) * Math.log((1 + Math.sin(x)) / (1 - Math.sin(x)));
    }

    private double fFunc(double x, double y, double z) {
        return z;
    }

    private double gFunc(double x, double y, double z) {
        return Math.tan(z) - 2 * y;
    }

    public ArrayList<Double> getY() {
        return y;
    }

    public ArrayList<Double> getZ() {
        return z;
    }

    public ArrayList<Double> getX() {
        return x;
    }

    public void setX(ArrayList<Double> x) {
        this.x = x;
    }
}

