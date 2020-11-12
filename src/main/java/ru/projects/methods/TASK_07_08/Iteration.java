package ru.projects.methods.TASK_07_08;

import java.util.HashMap;
import java.util.Map;

import static ru.projects.methods.TASK_07_08.Util.*;



public class Iteration extends Method {

    public Iteration(double x1, double x2, double y1, double y2, double accuracy) {
        super(x1, x2, y1, y2, accuracy);
    }

  /*  public String solve(){
        double q = findMaxNorm();
        return String.valueOf(q);
    }
*/
    private double findMaxNorm(){
        double q = Double.MIN_NORMAL;
        for (double x = X1; x <= X2; x += 0.001) {
            for (double y = Y1; y <= Y2; y += 0.001) {
                if(Math.abs(FI1poX(x,y)) + Math.abs(FI1poY(x,y)) > q || Math.abs(FI2poX(x,y)) + Math.abs(FI2poY(x,y)) > q ){
                q = Math.max(Math.abs(FI1poX(x,y)) + Math.abs(FI1poY(x,y)) , Math.abs(FI2poX(x,y)) + Math.abs(FI2poY(x,y)));
                }
            }
        }
        return q;
    }
    public String solve() {
        //if (!canWeCalculateNextStep()) return "Условие сходимости не выполнено. Значения функции не попадают в интервал";
        if (!isNormaMatrixLessThanOne()){ return "ЧЛЕНИЩЕ";}
        double q = findMaxNorm();
        double zero_X = 1.5;
        double zero_Y = 1.5;
        double next_X;
        double next_Y;
        double epsilon;
        System.out.println(124);
        do{
            next_X = F1(zero_X,zero_Y);
            next_Y = F2(zero_X,zero_Y);

            epsilon = q / (1 - q) * Math.max(Math.abs(next_X - zero_X), Math.abs(next_Y - zero_Y));

            zero_X = next_X;
            zero_Y = next_Y;

        }while (epsilon > accuracy);

        return String.format("X = %.4f\n Y = %.4f",zero_X,zero_Y);


    }

    public boolean isNormaMatrixLessThanOne(){
        for (double x = X1; x <= X2; x += accuracy) {
            for (double y = Y1; y <= Y2; y += accuracy) {

                System.out.print("F1poX + F1poY  ");
                System.out.println(Math.abs(FI1poX(x,y)) + Math.abs(FI1poY(x,y)));
                System.out.print("F2poX + F2poY   \n");
                System.out.println(x);
                System.out.println(y);
                System.out.println(FI2poX(x, y));
                System.out.println(Math.abs(FI2poX(x,y)));
                System.out.println(Math.abs(FI2poY(x, y)));
                System.out.println("//////////////////////////");

                if ( Math.abs(FI1poX(x,y)) + Math.abs(FI1poY(x,y)) <= 1 && Math.abs(FI2poX(x,y)) + Math.abs(FI2poY(x,y)) <= 1) {
                    return true;
                }




            }
        }
       return false;
    }

   

}
class Test {
    public static void main(String[] args) {
        Iteration iteration = new Iteration(0, 0.3, -1.3, -1.1, 0.01);
        System.out.println(iteration.solve());
    }
}

