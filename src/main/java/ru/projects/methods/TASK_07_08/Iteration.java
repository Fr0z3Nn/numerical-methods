package ru.projects.methods.TASK_07_08;

import java.util.HashMap;
import java.util.Map;

import static ru.projects.methods.TASK_07_08.Util.*;



public class Iteration extends Method {
    double q = Double.MAX_VALUE;
    public Iteration(double x1, double x2, double y1, double y2, double accuracy) {
        super(x1, x2, y1, y2, accuracy);
    }

    public String solve() {
        if (!canWeCalculateNextStep()) return "Условие сходимости не выполнено. Значения функции не попадают в интервал";
        return isNormaMatrixLessThanOne() ? "пенис" : "Огромный пенис";
        /*double zero_X = (X1 + X2) / 2;
        double zero_Y = (Y1 + Y2) / 2;
        double next_X;
        double next_Y;
        double epsilon;
        do{
            next_X = F1(zero_X,zero_Y);
            next_Y = F2(zero_X,zero_Y);

            epsilon = q / (1 - q) * Math.max(Math.abs(next_X - zero_X), Math.abs(next_Y - zero_Y));

            zero_X = next_X;
            zero_Y = next_Y;

        }while (epsilon > accuracy);

        return String.format("X = %.4f\n Y = %.4f",zero_X,zero_Y);*/


    }

    public boolean isNormaMatrixLessThanOne(){
        for (double x = X1; x <= X2; x += accuracy) {
            for (double y = Y1; y <= Y2; y += accuracy) {

                if (Math.abs(FI1poX(x,y)) + Math.abs(FI1poY(x,y)) > q) q = Math.abs(FI1poX(x,y)) + Math.abs(FI1poY(x,y));
                if(Math.abs(FI2poX(x,y)) + Math.abs(FI2poY(x,y)) > q) q = Math.abs(FI2poX(x,y)) + Math.abs(FI2poY(x,y));

                System.out.print("F1poX + F1poY  ");
                System.out.println(Math.abs(FI1poX(x,y)) + Math.abs(FI1poY(x,y)));
                System.out.print("F2poX + F2poY   ");
                System.out.println(Math.abs(FI2poX(x,y)) + Math.abs(FI2poY(x,y)));
                System.out.println("//////////////////////////");

                if ( Math.abs(FI1poX(x,y)) + Math.abs(FI1poY(x,y)) < 1 && Math.abs(FI2poX(x,y)) + Math.abs(FI2poY(x,y)) < 1) {
                    return true;
                }




            }
        }
       return false;
    }

    public boolean canWeCalculateNextStep() {
        for (double x = X1; x <= X2; x += accuracy) {
            for (double y = Y1; y <= Y2; y += accuracy) {
                /*System.out.print("F1  ");
                System.out.println(FI1(x, y));
                System.out.print("F2  ");
                System.out.println(FI2(x, y));*/
               if (FI1(x, y) > X1 &&  FI1(x, y) < X2 && FI2(x, y) > Y1 && FI2(x, y) < Y2) {
                   //X1 = x;
                   //Y1 = y;
                    return true;
                }

            }
        }
        return false;
    }
}

class Test {
    public static void main(String[] args) {
       Iteration iteration = new Iteration(0.8, 1.2, 0.8, 1.2, 0.1);
       System.out.println(iteration.solve());
    }
}

