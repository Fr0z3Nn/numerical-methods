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
        printHead();
        double epsilon;
        double delta;
        int numberOfIteration = 1;
        double matrixNorm = normMatrix(augmentedMatrix);
        double vectorNorm = normVector(augmentedMatrix);

        printSolution(solution);
        System.out.println();

        do {
            for (int i = 0; i < augmentedMatrix.length; i++) {
                double sum = 0.0;
                for (int j = 0; j < augmentedMatrix.length; j++) {
                    sum += vector[j] * augmentedMatrix[i][j];
                }
                sum += augmentedMatrix[i][augmentedMatrix[i].length - 1];
                solution[i] = sum;

            }


            printSolution(solution);

            epsilon = Math.pow(matrixNorm,numberOfIteration)/(1 - matrixNorm) * vectorNorm;
            System.out.printf("%2.5f %2.8f\n", epsilon,normOfTwoVectors(solution, vector));

            vector = Arrays.copyOf(solution,solution.length);

            numberOfIteration++;
        }while (epsilon > accuracy);

        System.out.printf("КОЛИЧЕСТВО ИТЕРАЦИЙ: %d", numberOfIteration);




    }
    private void printHead(){
        for(int i = 1; i < matrix.length + 1; i++){
            System.out.printf("%5s ","X" + i );
        }
        System.out.printf("%7s %7s","ε", "δ");
        System.out.println();

    }
    private void printSolution(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%2.3f ", array[i]);
        }

    }
    public double normOfTwoVectors(double[] vectorNew, double[] vectorOld){
        double max = Double.MIN_NORMAL;
        for(int i = 0; i<vectorNew.length; i ++){
            double tryStayMax = Math.abs(vectorOld[i] - vectorNew[i]);
            max = Math.max(tryStayMax, max);
        }
        return max;
    }

    public double normMatrix(double[][] array){
        return Math.min(normMatrix1(array),normMatrix2(array));
    }

    public double normVector(double[][] array){
        double max = Double.MIN_NORMAL;
        for(int i = 0; i<array.length; i ++){
            double tryStayMax = Math.abs(array[i][array[i].length - 1]);
            max = Math.max(tryStayMax, max);
        }
        return max;
    }

    private double normMatrix1(double[][] array){
        double max = Double.MIN_NORMAL;
        for (int i = 0; i < array.length; i++) {
            double tryStayMax = 0;
            for (int j = 0; j < array.length; j++) {
                tryStayMax += Math.abs(array[i][j]);
            }
            if(tryStayMax > max) {
                max = tryStayMax;
            }
        }
        return max;
    }

    private double normMatrix2(double[][] array){
        double max = Double.MIN_NORMAL;
        for (int i = 0; i < array.length; i++) {
            double tryStayMax = 0;
            for (int j = 0; j < array.length; j++) {
                tryStayMax += Math.abs(array[j][i]);
            }
            if(tryStayMax > max) {
                max = tryStayMax;
            }
        }
        return max;
    }

}
