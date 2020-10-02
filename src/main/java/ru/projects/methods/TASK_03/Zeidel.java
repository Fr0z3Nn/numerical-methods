package ru.projects.methods.TASK_03;

import java.util.Arrays;

public class Zeidel extends Yakobi{
    public Zeidel(double[][] matrix, double[] vector, double accuracy) {
        super(matrix, vector, accuracy);
    }

    @Override
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
                vector[i] = sum;

            }


            printSolution(vector);

            epsilon = Math.pow(matrixNorm,numberOfIteration)/(1 - matrixNorm) * vectorNorm;
            System.out.printf("%2.5f %2.8f\n", epsilon,normOfTwoVectors(solution, vector));

            solution = Arrays.copyOf(vector,vector.length);

            numberOfIteration++;
        }while (epsilon > accuracy);

        System.out.printf("КОЛИЧЕСТВО ИТЕРАЦИЙ: %d", numberOfIteration);
    }
}
