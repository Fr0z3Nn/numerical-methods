package ru.projects.methods.TASK_07_08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.projects.methods.TASK_07_08.Util.*;



public class Iteration extends Method {

    private static StringBuilder result = new StringBuilder();
    private List<Double> x = new ArrayList<>();
    private List<Double> y = new ArrayList<>();
    double x0;
    double y0;
    double eps;

    public Iteration(double x1, double x2, double y1, double y2, double accuracy) {
        super(x1, x2, y1, y2, accuracy);
         x0 = (X2+X1) / 2;
         y0 = (Y2+Y1) / 2;
         eps = accuracy;
    }

    public String solve(){
        return result.toString();
    }

    public void solveSecondRoot(){
        // Начальное значение
        result.append("НАХОЖДЕНИЕ ВТОРОГО КОРНЯ:\n\n");
        result.append("В качестве первого приближения примем:\n x = 0.3 x[0 ; 0.6]\n y = -1 y[-1.3 ; -0.7]\n\n");

        double x0 = (X2+X1) / 2;
        double y0 = (Y2+Y1) / 2;
        double eps = 1e-10;



        x.add(x0);
        y.add(y0);
        x.add(PHI1R2(x0,y0));
        y.add(PHI2R2(x0,y0));

        int i=1;
        // ИТЕРАЦИИ
        while (Math.abs(x.get(i)-x.get(i-1)) > eps && Math.abs(y.get(i)-y.get(i-1)) > eps){
            x.add(PHI1R2(x.get(i),y.get(i)));
            y.add(PHI2R2(x.get(i),y.get(i)));
            if (i > 500){
                result.append("Плохая функция\n");
                break;
            }
            result.append(String.format("Итерация №%d, решение: ( x = %.8f, y = %.8f)\n\n",i++,x.get(i),y.get(i)));
        }
        result.append(String.format("ИТОГОВОЕ РЕШЕНИЕ: ( x = %.8f, y = %.8f)\n\n",x.get(i),y.get(i)));
    }


    public void solveFirstRoot() {
        // Начальное значение
        result.append("НАХОЖДЕНИЕ ПЕРВОГО КОРНЯ:\n\n");
        result.append("В качестве первого приближения примем:\n x = 0.8 x[0.4; 1.2]\n y = 0.8 y[0.4 ; 1.2]\n\n");

        double x0 = (X2+X1) / 2;
        double y0 = (Y2+Y1) / 2;
        double eps = 1e-10;

        x.add(x0);
        y.add(y0);
        x.add(PHI1(x0,y0));
        y.add(PHI2(x0,y0));

        int i=1;
        // ИТЕРАЦИИ
        while (Math.abs(x.get(i)-x.get(i-1)) > eps && Math.abs(y.get(i)-y.get(i-1)) > eps){
            x.add(PHI1(x.get(i),y.get(i)));
            y.add(PHI2(x.get(i),y.get(i)));
            if (i > 500){
                result.append("Плохая функция\n");
                break;
            }
            result.append(String.format("Итерация №%d, решение: ( x = %.8f, y = %.8f)\n\n",i++,x.get(i),y.get(i)));
        }
        result.append(String.format("ИТОГОВОЕ РЕШЕНИЕ: ( x = %.8f, y = %.8f)\n\n",x.get(i),y.get(i)));
    }
}


class Test {
    public static void main(String[] args) {
        Iteration iteration = new Iteration(0, 0.6, -1.3, -0.7, 0.00001);
        iteration.solveSecondRoot();
        System.out.println(iteration.solve());
    }
}

