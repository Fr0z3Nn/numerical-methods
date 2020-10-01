package ru.projects.methods.TASK_01.helper;


import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import ru.projects.methods.TASK_01.helper.LinearMatrixSolver;

import java.util.Scanner;

public class MatrixMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите размер матрицы / кол-во уравнений: ");
        //размер массива
        int numberOfEquation = Integer.parseInt(scan.nextLine());
        //инициализация
        double[][] matrix = new double[numberOfEquation][numberOfEquation];
        double[] vector = new double[numberOfEquation];
        //матрица
        for (int i = 0; i < numberOfEquation; i++) {
            System.out.print("Введите коэфициэнты " + i + " строки: ");
            for (int j = 0; j < numberOfEquation; j++) {
                matrix[i][j] = scan.nextDouble();
            }
        }
        //вектор
        System.out.print("Введите свободные коэффициенты: ");
        for (int i = 0; i < numberOfEquation; i++) {
            vector[i] = scan.nextDouble();
        }


        LinearMatrixSolver linearMatrixSolver = new LinearMatrixSolver(new Array2DRowRealMatrix(matrix), new ArrayRealVector(vector));

        System.out.println("\nИСХОДНАЯ МАТРИЦА: ");
        printArray(matrix);

        double[][] inverseMatrix = linearMatrixSolver.getInverseMatrix();
        System.out.println("\nОБРАТНАЯ МАТРИЦА:");
        printArray(inverseMatrix);

        double determinantMatrix = linearMatrixSolver.getDeterminantMatrix();
        System.out.printf("\nОПРЕДЕЛИТЕЛЬ МАТРИЦЫ: %.2f\n", determinantMatrix);

        RealVector solutionMatrix = linearMatrixSolver.getSolutionOfMatrix();
        System.out.println("\nКОРНИ УРАВНЕНИЯ:");
        System.out.println(solutionMatrix);
    }

    static void printArray(double[][] array) {
        for (double[] row : array) {
            for (double item : row) {
                System.out.printf("%5.2f ", item);
            }
            System.out.println();
        }
    }
}
