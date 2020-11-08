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

    public static double FI1(double x, double y) {
        //return 0.3 - 0.1 * x * x - 0.2 * y * y;
        //return Math.sqrt(3 + y - y * y);
        return Math.sqrt(y + 3) - 1;
        //return Math.sqrt((y - y * y + 3) / 3);
    }

    public static double FI1poX(double x, double y) {


        //return 0;
        return 0;
    }

    public static double FI1poY(double x, double y) {


        return 1. / (2 * Math.sqrt(y + 3));
        //return (Math.sqrt(3) * (1 - 2 * y)) / (6 * Math.sqrt(y - y * y + 3));
    }

    public static double FI2(double x, double y) {
        //return 0.7 - 0.2 * x * x + 0.1 * x * y;
        //return Math.pow(x + 1, 2) - 3;
        //return 3 * x * x + y * y - 3;
        //return Math.sqrt(y + 3 - 3 * x * x);
        //return (3 * x * x - 3) / (1 - y);
        //return x * x + 2 * x - 2;
        //return (x+2) * (x+2) - 6;
        //return (x*x-2) *(x*x -2) - 6;
        //return Math.sqrt(y + 3) - 1;
        //return (3-3*x*x)/(y-1);
        //return (3 - 3 * x * x) / y - 1;
        //return Math.sqrt(4-y-3*x*x)+1;
        //return ((y+2)/(x*x+2*x))+y;
        return (-(3*x*x - 3) / 2) - (((y-1)*(y-1))/2);
    }

    public static double FI2poX(double x, double y) {


        //return 6 * x;
        //return -(6*x)/(2*Math.sqrt(y+3-3*x*x));
        //return 2 * x + 2;
        //return 2*x;
        //return 0;
        //return -6*x;
        //return -6*x;
        //return (-6*x)/(2 * Math.sqrt(4 - y - 3*x*x));
        //return (1/(x*x + 2*x)) + 1;
        //return -6*x/y-1;
        return -3*x;
    }

    public static double FI2poY(double x, double y) {


        //return 2 * y;
        //return 1./(2*Math.sqrt(y+3-3*x*x));
        //return 0;
        //return 1. / (2 * Math.sqrt(y + 3));
        //return -1/((y-1)*(y-1));
        //return  -1./(x*x);
        //return -1./(2 * Math.sqrt(4 - y - 3*x*x));
        //return ((y+2)*(-2*x-2))/((x*x+2*x)*(x*x+2*x));
        //return -(3-3*x*x)/((y-1)*(y-1));
        return -y + 1;
    }
}
