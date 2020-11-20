package ru.projects.methods.TASK_11_12.derivative;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Derivative {
    // where calculate derivative
    private double X;
    private double[][] XYdots;

    private Map<Integer, List<Double>> mapOfMNKSystem_XY = new HashMap<>();

    private  double y1;
    private  double y2;
    private  double y3;
    private  double x1;
    private  double x2;
    private  double x3;

    public Derivative(double x, double[][] XYdots) {
        X = x;
        this.XYdots = XYdots;
        initialization();
        y1 = mapOfMNKSystem_XY.get(1).get(1);
        y2 = mapOfMNKSystem_XY.get(2).get(1);
        y3 = mapOfMNKSystem_XY.get(3).get(1);
        x1 = mapOfMNKSystem_XY.get(1).get(0);
        x2 = mapOfMNKSystem_XY.get(2).get(0);
        x3 = mapOfMNKSystem_XY.get(3).get(0);

    }

    private void initialization(){
        generateSystem();
    }

    public double findFIRSTDerivative() {
        double sum1 = (y2 - y1) / (x2 - x1);
        double sum2 = (y3 - y2) / (x3 - x2);
        double sum3 = x3 - x1;
        return sum1 + ((sum2 - sum1) / sum3) * (2 * X - x1 - x2);
    }

    public double findSECONDDerivative() {
        double sum1 = (y3 - y2) / (x3 - x2);
        double sum2 = (y2 - y1) / (x2 - x1);
        double sum3 = x3 - x1;
        return 2 * (sum1 - sum2) / sum3;
    }

    public void generateSystem() {
        AtomicInteger count = new AtomicInteger();
        Arrays.stream(XYdots).forEach(n -> {
            int m = new Integer(String.valueOf(count));
            mapOfMNKSystem_XY.put(
                    count.getAndIncrement(),
                    new ArrayList<>(Arrays.asList(XYdots[m][0], XYdots[m][1]))
            );
        });
    }

}
