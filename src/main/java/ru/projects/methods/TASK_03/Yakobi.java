package ru.projects.methods.TASK_03;

import java.util.Arrays;

public class Yakobi {
    protected double[][] matrix;
    protected double[] vector;
    protected double accuracy;
    protected double[][] augmentedMatrix;
    protected double[] solution;
    protected int numberOfIteration = 1;

    public Yakobi(double[][] matrix, double[] vector, double accuracy) {
        this.matrix = matrix;
        this.vector = vector;
        this.accuracy = accuracy;
        augmentedMatrix = new double[matrix.length][matrix.length + 1];
    }

    public int getNumberOfIteration() {
        return numberOfIteration;
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

        double epsilon;
        double delta;
        double matrixNorm = normMatrix(augmentedMatrix);
        double vectorNorm = normVector(augmentedMatrix);

        System.out.println("МЕТОД ЯКОБИ: ");

        System.out.printf("НОРМА МАТРИЦЫ: %2.3f\n", matrixNorm);
        System.out.println(matrixNorm < 1 ? "Итерационный процесс будет сводиться к точному решению\n" : "Итерационный процесс к точному решению сводится не будет\n");

        printHead();

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


            delta = normOfTwoVectors(solution, vector);
            epsilon = matrixNorm/(1 - matrixNorm) * delta;
            System.out.printf("%2.5f %2.8f\n", epsilon, delta);

            vector = Arrays.copyOf(solution,solution.length);

            numberOfIteration++;
        }while (epsilon >= accuracy);

        System.out.printf("КОЛИЧЕСТВО ИТЕРАЦИЙ: %d\n\n", numberOfIteration);




    }
    public void printHead(){
        for(int i = 1; i < matrix.length + 1; i++){
            System.out.printf("%5s ","X" + i );
        }
        System.out.printf("%7s %7s","ε", "δ");
        System.out.println();

    }
    public void printSolution(double[] array) {
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
