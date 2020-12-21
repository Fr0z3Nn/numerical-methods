package ru.projects.methods.TASK_15_16;

import java.util.ArrayList;

public class StartShooting {

    public static void main(String[] args) {
        double n1 = 1;
        double n2 = 0.8;
        Shooting shooting1 = new Shooting(Math.PI / 60, n1, n2);
        shooting1.rynge();
        Shooting shooting2 = new Shooting(Math.PI / 120, n1, n2);
        shooting2.rynge();

        //rynge
        ArrayList<Double> listX1 = shooting1.getX();
        ArrayList<Double> listY1 = shooting1.getY();
        ArrayList<Double> listY2 = shooting2.getY();

        System.out.println("МЕТОД РУНГЕ-РОМБЕРГА");
        for (int i = 0; i < listX1.size(); i++) {
            System.out.print(String.format("x = %.2f ", listX1.get(i)));
            double rynge = listY1.get(i) + (listY1.get(i) - listY2.get(2*i)) / (Math.pow(0.5, 4) - 1);
            System.out.print(String.format("y = %.2f ",  rynge));
            System.out.println(String.format("EPS = %.2f ", Math.abs(shooting1.func(listX1.get(i)) - rynge)));
        }
    }
}
