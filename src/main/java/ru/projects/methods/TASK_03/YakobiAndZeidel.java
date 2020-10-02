package ru.projects.methods.TASK_03;

import java.util.Scanner;

public class YakobiAndZeidel {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("МЕНЮ:\n 1.ПОДСЧЕТ 9ГО ВАРИАНТА\n 2.ВВОД СВОЕЙ МАТРИЦЫ");
        int choice = Integer.parseInt(scan.nextLine());
        int numberOfEquation;
        double[][] matrix;
        double[] vector;
        double accuracy;
        switch (choice) {
            case 1:
                matrix = new double[][]{{20, 4, -8}, {-3, 15, 5}, {6, 3, -18}};
                vector = new double[]{1, -2, 3};
                accuracy = 0.01;
                break;
            case 2:
                System.out.print("Введите размер матрицы / кол-во уравнений: ");
                numberOfEquation = Integer.parseInt(scan.nextLine());
                matrix = new double[numberOfEquation][numberOfEquation];
                vector = new double[numberOfEquation];
                System.out.print("Введите точность вычисления: ");
                accuracy = Double.parseDouble(scan.nextLine());
                //заполнение матрицы
                for (int i = 0; i < numberOfEquation; i++) {
                    System.out.print("Введите коэфициэнты " + i + " строки: ");
                    for (int j = 0; j < numberOfEquation; j++) {
                        matrix[i][j] = scan.nextDouble();
                    }
                }
                //вектор заполнение
                System.out.print("Введите свободные коэффициенты: ");
                for (int i = 0; i < numberOfEquation; i++) {
                    vector[i] = scan.nextDouble();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }

        Yakobi yakobi = new Yakobi(matrix, vector, accuracy);
        yakobi.ExpressMatrixCoefficients();
        yakobi.createAugmentedMatrix();
        yakobi.findRoots();

        Zeidel zeidel = new Zeidel(matrix, vector, accuracy);
        zeidel.ExpressMatrixCoefficients();
        zeidel.createAugmentedMatrix();
        zeidel.findRoots();
    }
}
