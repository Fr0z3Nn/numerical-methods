package ru.projects.methods.TASK_03;

import java.util.Arrays;

public class Yakobi {
    private double[][] matrix;
    private double[] vector;
    private double accuracy;
    private double[][] augmentedMatrix;
    private double[] solution;

    public Yakobi(double[][] matrix, double[] vector, double accuracy) {
        this.matrix = matrix;
        this.vector = vector;
        this.accuracy = accuracy;
        augmentedMatrix = new double[matrix.length][matrix.length + 1];
    }

    public void ExpressMatrixCoefficients() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    double delitel = matrix[i][i];
                    for (int m = 0; m < matrix.length; m++) {
                        matrix[i][m] /= delitel;
                    }
                    matrix[i][i] = 0;
                    vector[i] /= delitel;
                }
            }
        }
        solution = Arrays.copyOf(vector, vector.length);
    }

    public void createAugmentedMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                augmentedMatrix[i][j] = -matrix[i][j];
            }
        }
        for (int m = 0; m < vector.length; m++) {
            augmentedMatrix[m][augmentedMatrix.length] = vector[m];
        }
    }

    public void findRoots() {
        printSolution(solution);
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < matrix.length; j++) {
                sum += solution[j] * matrix[i][j];
            }
            solution[i] = sum;
        }
        printSolution(solution);
    }

    private void printSolution(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("X" + (i + 1) + " = %.2f\n", array[i]);
        }
    }
}
