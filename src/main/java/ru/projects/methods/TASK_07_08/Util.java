package ru.projects.methods.TASK_07_08;


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

    //SECOND
    public static double PHI1NOR(double x, double y){
        return 3 * x * x - y + y * y - 3;
    }

    public static double PHI1(double x, double y) {
        return Math.sqrt((1. / 3) * (3 - y * y + y));
    }

    public static double PHI2(double x, double y) {
        return (x + 1) * (x + 1) - 3;
    }

    public static double PHI2NOR(double x, double y){
        return x - Math.sqrt(y+3) + 1;
    }

    public static double PHI1R2(double x, double y) {
        return x - 0.1 * (3 * x * x - y + y * y - 3);
    }

    public static double PHI2R2(double x, double y) {
        return y - 0.1 * (x - Math.sqrt(y + 3) + 1);
    }
}
