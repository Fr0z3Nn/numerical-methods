package ru.projects.methods.TASK_06;

import static ru.projects.methods.TASK_06.Util.*;

public class Newton extends Method {

    public Newton(double x1, double x2, double y1, double y2, double accuracy) {
        super(x1, x2, y1, y2, accuracy);
    }

    public String solve() {
        StringBuilder builder = new StringBuilder();
        int iteration = 0;
        double zero_X = (X2 + X1) / 2;
        double zero_Y = (Y2 + Y1) / 2;
        double epsilon;
        do {


            double next_X = zero_X - (matrix_A1(zero_X, zero_Y) / matrix_J(zero_X, zero_Y));
            double next_Y = zero_Y - (matrix_A2(zero_X, zero_Y) / matrix_J(zero_X, zero_Y));

            epsilon = Math.max(Math.abs(next_X - zero_X), Math.abs(next_Y - next_X));

            zero_X = next_X;
            zero_Y = next_Y;

            iteration++;
            builder.append(String.format("|                   |      %2.5f    |      %2.5f    |      %2.5f    |      %2.5f    |                        |                       |                       |                  |\n", zero_X, F1(zero_X, zero_Y), F1poX(zero_X), F1poY(zero_Y)));
            builder.append(String.format("|         %d        | -------------- | -------------- | -------------- | --------------  |      %2.5f    |    %.5f    |      %2.5f    | %.7f|\n", iteration, matrix_A1(zero_X, zero_Y), matrix_A2(zero_X, zero_Y), matrix_J(zero_X, zero_Y), epsilon));
            builder.append(String.format("|                   |      %2.5f    |      %2.5f    |      %2.5f    |      %2.5f   |                        |                      |                       |                  |\n", zero_Y, F2(zero_X, zero_Y), F2poX(), F2poY(zero_Y)));
            builder.append("|-----------------------------------------------------------------------------------------------------------------------------------------|\n");
        } while (epsilon > accuracy);

        builder.append(String.format("X = %.10f\nY = %.10f\nε = %.10f", zero_X,zero_Y,epsilon));


        return builder.toString();
    }


}
