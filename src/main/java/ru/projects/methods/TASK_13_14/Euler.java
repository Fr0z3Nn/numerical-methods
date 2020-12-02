package ru.projects.methods.TASK_13_14;


public class Euler extends Method {

    public Euler() {
        super();
        initialization();
    }

    public void initialization() {

        for (int i = 0; i <= x.size() - 1; i++) {
            z.add(z.get(i) + h * gFunc(x.get(i), y.get(i), z.get(i)));
            delta_z.add(h * gFunc(x.get(i), y.get(i), z.get(i)));
            y.add(y.get(i) + h * fFunc(y.get(i), y.get(i), y.get(i)));
            delta_y.add(h * fFunc(y.get(i), y.get(i), y.get(i)));
        }

    }

    public String addResult() {

        StringBuilder res = new StringBuilder();

        for (int i = 0; i <= x.size() - 1; i++) {
            res.append(String.format("%.3f  %.3f  %.3f  %.3f  %.3f  %.3f  %.3f  %.3f\n", (double) i, x.get(i), y.get(i), z.get(i), delta_z.get(i), delta_y.get(i), istFunc(x.get(i)), Math.abs(istFunc(x.get(i)) - y.get(i))));
        }

        return res.toString();
    }


}
