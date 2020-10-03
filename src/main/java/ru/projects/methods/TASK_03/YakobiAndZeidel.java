package ru.projects.methods.TASK_03;

import java.util.Arrays;
import java.util.Scanner;

public class YakobiAndZeidel {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("МЕНЮ:\n 1.ПОДСЧЕТ 9ГО ВАРИАНТА\n 2.ВВОД СВОЕЙ МАТРИЦЫ");
        int choice = Integer.parseInt(scan.nextLine());
        int numberOfEquation;
        double[][] matrix;
        double[][] matrix1;
        double[] vector;
        double[] vector1;
        double accuracy;
        switch (choice) {
            case 1:
                matrix = new double[][]{{12, -3, -1, 3}, {5, 20, 9, 1}, {6, -3, -21, -7}, {8, -7, 3, -27}};
                vector = new double[]{-31, 90, 119, 71};
                matrix1 = new double[][]{{12, -3, -1, 3}, {5, 20, 9, 1}, {6, -3, -21, -7}, {8, -7, 3, -27}};
                vector1 = new double[]{-31, 90, 119, 71};
                accuracy = 0.01;
                break;
            case 2:
                System.out.print("Введите размер матрицы / кол-во уравнений: ");
                numberOfEquation = Integer.parseInt(scan.nextLine());
                matrix = new double[numberOfEquation][numberOfEquation];
                vector = new double[numberOfEquation];
                matrix1 = new double[numberOfEquation][numberOfEquation];
                vector1 = new double[numberOfEquation];
                System.out.print("Введите точность вычисления: ");
                accuracy = Double.parseDouble(scan.nextLine());
                //заполнение матрицы
                for (int i = 0; i < numberOfEquation; i++) {
                    System.out.print("Введите коэфициэнты " + i + " строки: ");
                    for (int j = 0; j < numberOfEquation; j++) {
                        matrix[i][j] = scan.nextDouble();
                        matrix1[i][j] = matrix[i][j];
                    }
                }
                //вектор заполнение
                System.out.print("Введите свободные коэффициенты: ");
                for (int i = 0; i < numberOfEquation; i++) {
                    vector[i] = scan.nextDouble();
                    vector1[i] = vector[i];
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }


        Yakobi yakobi = new Yakobi(matrix, vector, accuracy);
        yakobi.ExpressMatrixCoefficients();
        yakobi.createAugmentedMatrix();
        yakobi.findRoots();


        Zeidel zeidel = new Zeidel(matrix1, vector1, accuracy);
        zeidel.ExpressMatrixCoefficients();
        zeidel.createAugmentedMatrix();
        zeidel.findRoots();

        System.out.println("Метод Зейбеля потребовал на " + (yakobi.getNumberOfIteration() - zeidel.getNumberOfIteration()) + " итерации меньше");
    }
}
