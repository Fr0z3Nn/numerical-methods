package ru.projects.methods.TASK_06;

import static ru.projects.methods.TASK_06.Util.*;

public class Newton extends Method {

    public Newton(double x1, double x2, double y1, double y2, double accuracy) {
        super(x1, x2, y1, y2, accuracy);
    }

    public String solve() {
        if(Y1 < -1.3 || Y2 > 2.3){
            return "Введите допустимый Y:[-1.3;2.3]";
        }
        if(X1 < 0 || X2 > 1000){
            return "Введите допустимый X:[0;1000]";
        }
        StringBuilder builder = new StringBuilder();
        int iteration = 0;
        double zero_X = (X1 + X2) / 2;
        double zero_Y = (Y1 + Y2) / 2;
        double next_X;
        double next_Y;
        double epsilon;
        do {

            next_X = zero_X - (matrix_A1(zero_X, zero_Y) / matrix_J(zero_X, zero_Y));
            next_Y = zero_Y - (matrix_A2(zero_X, zero_Y) / matrix_J(zero_X, zero_Y));

            epsilon = Math.max(Math.abs(next_X - zero_X), Math.abs(next_Y - zero_Y));

            zero_X = next_X;
            zero_Y = next_Y;

            iteration++;

            builder.append(String.format("|                   |      %2.5f    |      %2.5f    |      %2.5f    |      %2.5f    |                        |                       |                       |                  |\n", zero_X, F1(zero_X, zero_Y), F1poX(zero_X), F1poY(zero_Y)));
            builder.append(String.format("|         %d        | -------------- | -------------- | -------------- | --------------  |      %2.5f    |    %.5f    |      %2.5f    | %.7f|\n", iteration, matrix_A1(zero_X, zero_Y), matrix_A2(zero_X, zero_Y), matrix_J(zero_X, zero_Y), epsilon));
            builder.append(String.format("|                   |      %2.5f    |      %2.5f    |      %2.5f    |      %2.5f   |                        |                      |                       |                  |\n", zero_Y, F2(zero_X, zero_Y), F2poX(), F2poY(zero_Y)));
            builder.append("|-----------------------------------------------------------------------------------------------------------------------------------------|\n");

        } while (epsilon > accuracy);

        if(next_X < X1 || next_X > X2 || next_Y < Y1 || next_Y > Y2 || Double.isNaN(next_X) || Double.isNaN(next_Y)){
            return "Корней в данном квадрате нет или функции сходтся к решению, нележащему в данном квадрате.\n Выберете другой квадрат.";
        }

        builder.append(String.format("X = %.10f\nY = %.10f\nε = %.10f", zero_X,zero_Y,epsilon));


        return builder.toString();
    }


}
