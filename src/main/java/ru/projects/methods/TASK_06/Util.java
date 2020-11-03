package ru.projects.methods.TASK_06;

public class Util {
    public static double F1(double x, double y) {
        return 3 * x * x - y + y * y - 3;
    }

    public static double F1poX(double x) {
        return 6 * x;
    }

    public static double F1poY(double y) {
        return 2 * y - 1;
    }


    public static double F2(double x, double y) {
        return x - Math.sqrt(y + 3) + 1;
    }

    public static double F2poX() {
        return 1;
    }

    public static double F2poY(double y) {
        return -(1 / (2 * Math.sqrt(y + 3)));
    }

    public static double matrix_J(double x, double y) {
        return F1poX(x) * F2poY(y) - F1poY(y) * F2poX();
    }

    public static double matrix_A1(double x, double y) {
        return F1(x, y) * F2poY(y) - F1poY(y) * F2(x, y);
    }

    public static double matrix_A2(double x, double y) {
        return F1poX(x) * F2(x, y) - F2poX() * F1(x, y);
    }
}
