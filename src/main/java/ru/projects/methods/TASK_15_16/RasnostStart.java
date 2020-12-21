package ru.projects.methods.TASK_15_16;

import java.util.ArrayList;

public class RasnostStart {
    public static void main(String[] args) {
        Raznost raznost1 = new Raznost(Math.PI / 60);
        raznost1.raznost();

        Raznost raznost2 = new Raznost(Math.PI / 120);
        raznost2.raznost();
        // RYNGE
        ArrayList<Double> listX1 = raznost1.getX();
        ArrayList<Double> listY1 = raznost1.getY();
        ArrayList<Double> listY2 = raznost2.getY();

        System.out.println("МЕТОД РУНГЕ-РОМБЕРГА");
        for (int i = 0; i < listX1.size(); i++) {
            System.out.print(String.format("x = %.2f ", listX1.get(i)));
            double rynge = listY1.get(i) + (listY1.get(i) - listY2.get(2*i)) / (Math.pow(0.5, 4) - 1);
            System.out.print(String.format("y = %.2f ",  rynge));
            System.out.println(String.format("EPS = %.4e ", Math.abs(raznost1.func(listX1.get(i)) - rynge)));
        }
    }
}
