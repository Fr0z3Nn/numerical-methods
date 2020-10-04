package ru.projects.methods.TASK_03;

import java.util.Arrays;

public class Zeidel extends Yakobi{

    public Zeidel(double[][] matrix, double[] vector, double accuracy) {
        super(matrix, vector, accuracy);
    }

    @Override
    public void findRoots() {
        double epsilon;
        double delta;
        double matrixNorm = normMatrix(augmentedMatrix);
        double vectorNorm = normVector(augmentedMatrix);

        System.out.println("МЕТОД ЗЕЙДЕЛЯ: ");

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
                vector[i] = sum;

            }


            printSolution(vector);

            delta = normOfTwoVectors(solution, vector);
            epsilon = matrixNorm/(1 - matrixNorm) * delta;
            System.out.printf("%2.5f %2.8f\n", epsilon, delta);

            solution = Arrays.copyOf(vector,vector.length);

            super.numberOfIteration++;
        }while (epsilon > accuracy);

        System.out.printf("КОЛИЧЕСТВО ИТЕРАЦИЙ: %d\n\n", super.numberOfIteration);
    }
}
