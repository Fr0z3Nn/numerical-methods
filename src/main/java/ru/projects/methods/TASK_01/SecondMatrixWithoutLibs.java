package ru.projects.methods.TASK_01;

import java.util.Arrays;
import java.util.Scanner;

public class SecondMatrixWithoutLibs {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите размер матрицы / кол-во уравнений: ");
        //размер массива
        int numberOfEquation = Integer.parseInt(scan.nextLine());
        //инициализация
        double[][] matrix = new double[numberOfEquation][numberOfEquation];
        double[] vector = new double[numberOfEquation];
        double[][] oneDiagonalMatrix = new double[numberOfEquation][numberOfEquation];
        //матрица
        for (int i = 0; i < numberOfEquation; i++) {
            System.out.print("Введите коэфициэнты " + i + " строки: ");
            for (int j = 0; j < numberOfEquation; j++) {
                matrix[i][j] = scan.nextDouble();
                if (i == j) {
                    oneDiagonalMatrix[i][j] = 1.0;
                }
            }
        }


        //вектор
        System.out.print("Введите свободные коэффициенты: ");
        for (int i = 0; i < numberOfEquation; i++) {
            vector[i] = scan.nextDouble();
        }

        System.out.println("ИЗНАЧАЛЬНЫЕ ДАННЫЕ");
        System.out.println("МАТРИЦА:");
        printArray(matrix);
        System.out.println("ЕДИНИЧНАЯ МАТРИЦА:");
        printArray(oneDiagonalMatrix);
        System.out.println("ВЕКТОР:");
        System.out.println(Arrays.toString(vector));
        //диагональная
        for (int j = 0; j < matrix.length; j++) {
            for (int i = j + 1; i < matrix.length; i++) {
                double koef = matrix[i][j] / matrix[j][j];
                for (int m = 0; m < matrix.length; m++) {
                    matrix[i][m] -= koef * matrix[j][m];
                    oneDiagonalMatrix[i][m] -= koef * oneDiagonalMatrix[j][m];
                }
                vector[i] -= koef * vector[j];
            }
        }
        double[] solution = new double[numberOfEquation];
        System.out.println("ПОСЛЕ ДИАГОНАЛЬНОГО ПРЕОБРАЗОВАНИЯ");
        System.out.println("МАТРИЦА:");
        printArray(matrix);
        System.out.println("ЕДИНИЧНАЯ МАТРИЦА:");
        printArray(oneDiagonalMatrix);
        System.out.println("ВЕКТОР:");
        System.out.println(Arrays.toString(vector));


        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    vector[i] -= solution[j] * matrix[i][j];
                }
            }
            solution[i] = vector[i] / matrix[i][i];
        }

        System.out.println("КОРНИ УРАВНЕНИЯ:");
        printSolution(solution);
        Matrix matrixToDeterminant = new Matrix(matrix);
        System.out.printf("ОПРЕДЕЛИТЕЛЬ: %.2f\n", matrixToDeterminant.Determinant());
        //reverse all
        double[][] reverseMatrix = new double[numberOfEquation][numberOfEquation];
        double[][] reverseOneDiagonalMatrix = new double[numberOfEquation][numberOfEquation];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                reverseMatrix[i][j] = matrix[matrix.length - 1 - i][matrix.length - 1 - j];
                reverseOneDiagonalMatrix[i][j] = oneDiagonalMatrix[oneDiagonalMatrix.length - 1 - i][oneDiagonalMatrix.length - 1 - j];
            }
        }
        System.out.println("РЕВЕРС МАТРИЦ");
        printArray(reverseMatrix);
        printArray(reverseOneDiagonalMatrix);

        for (int j = 0; j < reverseMatrix.length; j++) {
            for (int i = j + 1; i < reverseMatrix.length; i++) {
                double koef = reverseMatrix[i][j] / reverseMatrix[j][j];
                for (int m = 0; m < reverseMatrix.length; m++) {
                    reverseMatrix[i][m] -= koef * reverseMatrix[j][m];
                    reverseOneDiagonalMatrix[i][m] -= koef * reverseOneDiagonalMatrix[j][m];
                }
            }
        }
        System.out.println("РЕВЕРС МАТРИЦ ДИАГОНАЛЬ");
        printArray(reverseMatrix);
        printArray(reverseOneDiagonalMatrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i == j){
                    for(int m = 0; m<matrix.length; m++){
                        reverseOneDiagonalMatrix[i][m] /= reverseMatrix[i][j];
                    }
                }
            }
        }

        System.out.println("РЕВЕРС ОБРАТНОЙ МАТРИЦЫ");
        printArray(reverseOneDiagonalMatrix);

        double[][] inverseMatrix = new double[numberOfEquation][numberOfEquation];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                inverseMatrix[i][j] = reverseOneDiagonalMatrix[reverseOneDiagonalMatrix.length - 1 - i][reverseOneDiagonalMatrix.length - 1 - j];
            }
        }

        System.out.println("ОБРАТНАЯ МАТРИЦА");
        printArray(inverseMatrix);

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
