package ru.projects.methods.TASK_13_14.euler;


import ru.projects.methods.TASK_13_14.Method;

import java.util.ArrayList;

public class Euler extends Method {

    public Euler(double h) {
        super(h);
        initialization();
    }

    public void initialization() {

        for (int i = 0; i < x.size(); i++) {

            double g = gFunc(x.get(i), y.get(i), z.get(i));
            double f = fFunc(x.get(i), y.get(i), z.get(i));

            z.add(z.get(i) + h * g);
            delta_z.add(h * g);
            y.add(y.get(i) + h * f);
            delta_y.add(h * f);

        }

    }

    public ArrayList<EulerTable> createCells() {

        ArrayList<EulerTable> forTable = new ArrayList<>();

        for (int i = 0; i < x.size(); i++) {
            forTable.add(new EulerTable(i, h, x.get(i), y.get(i), z.get(i), delta_z.get(i), delta_y.get(i), istFunc(x.get(i)), Math.abs(istFunc(x.get(i)) - y.get(i))));
            }

        return forTable;
    }


}
