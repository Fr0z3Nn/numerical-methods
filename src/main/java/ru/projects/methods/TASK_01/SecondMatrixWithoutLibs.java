package ru.projects.methods.TASK_01;

import java.util.Arrays;
import java.util.Scanner;

public class SecondMatrixWithoutLibs {
    private static double[][] matrix;
    private static double[] vector;
    private static double[][] oneDiagonalMatrix;

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите размер матрицы / кол-во уравнений: ");
        int numberOfEquation = Integer.parseInt(scan.nextLine());
        matrix = new double[numberOfEquation][numberOfEquation];
        vector = new double[numberOfEquation];
        oneDiagonalMatrix = new double[numberOfEquation][numberOfEquation];
        //заполнение матрицы
        for (int i = 0; i < numberOfEquation; i++) {
            System.out.print("Введите коэфициэнты " + i + " строки: ");
            for (int j = 0; j < numberOfEquation; j++) {
                matrix[i][j] = scan.nextDouble();
                if (i == j) {
                    oneDiagonalMatrix[i][j] = 1.0;
                }
            }
        }
        //вектор заполнение
        System.out.print("Введите свободные коэффициенты: ");
        for (int i = 0; i < numberOfEquation; i++) {
            vector[i] = scan.nextDouble();
        }

        Matrix matrixToSolve = new Matrix(matrix, vector);
        System.out.printf("ОПРЕДЕЛИТЕЛЬ: %.2f\n", matrixToSolve.getDeterminant());
        System.out.println("КОРНИ УРАВНЕНИЯ:");
        printSolution(matrixToSolve.solveMatrixAndGetX());
        System.out.println("ОБРАТНАЯ МАТРИЦА:");
        printArray(matrixToSolve.getInverseMatrix());

    }

    static void printArray(double[][] array) {
        for (double[] row : array) {
            for (double item : row) {
                System.out.printf("%5.2f ", item);
            }
            System.out.println();
        }
    }

    static void printSolution(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("X" + (i + 1) + " = %.2f\n", array[i]);
        }
    }


}
