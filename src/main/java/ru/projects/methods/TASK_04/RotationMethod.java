package ru.projects.methods.TASK_04;

import java.util.ArrayList;
import java.util.Scanner;

public class RotationMethod {
    private double accuracy;
    private double[] maxElementIJ;
    private ArrayList<double[][]> UmatrixList = new ArrayList<>();
    private ArrayList<double[][]> UmatrixTranspList = new ArrayList<>();
    private ArrayList<double[][]> matrixList = new ArrayList<>();

    private double[][] UFinalMultiMatrix;
    private int iteration = 0;
    private int len;

    public void solveMatrix() {
        double fallibility;
        do {
            findMaximumAboveTheMainDiagonal(iteration);
            createNewUMatrix(iteration);
            transpositionMatrix(iteration);
            matrixList.add(multiMatrix(UmatrixTranspList.get(iteration), multiMatrix(matrixList.get(iteration), UmatrixList.get(iteration))));
            fallibility = findFallibility(iteration);
            ++iteration;
        } while (fallibility > accuracy);
        UFinalMultiMatrix = findFinalUMatrix(0);
        printResult();
    }

    private double findFallibility(int iteration) {
        double sum = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (j > i) {
                    sum += Math.pow(matrixList.get(iteration + 1)[i][j], 2);
                }
            }
        }
        return Math.sqrt(sum);
    }

    private void printResult() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    System.out.printf("Собственное значение L%d: %.3f\n", i, matrixList.get(matrixList.size() - 1)[i][j]);
                    System.out.printf("Собственный вектор X%d:\n", i);
                    for (int m = 0; m < len; m++) {
                        System.out.printf("%.3f\n", UFinalMultiMatrix[m][i]);
                    }
                }
            }
        }
        System.out.printf("Количество итераций: %d", iteration);
    }

    private double[][] findFinalUMatrix(int start) {
        return start == UmatrixList.size() - 1 ? UmatrixList.get(start) :
                multiMatrix(UmatrixList.get(start), findFinalUMatrix(start + 1));
    }

    private void createNewUMatrix(int iteration) {
        double[][] Umatrix = new double[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    Umatrix[i][j] = 1.0;
                }
            }
        }
        int first = (int) maxElementIJ[1];
        int second = (int) maxElementIJ[2];
        double Fi = (1.0 / 2) * Math.atan(2 * maxElementIJ[0] / (matrixList.get(iteration)[first][first] - matrixList.get(iteration)[second][second]));
        double sinFI = Math.sin(Fi);
        double cosFI = Math.cos(Fi);
        Umatrix[first][first] = cosFI;
        Umatrix[second][second] = cosFI;
        Umatrix[first][second] = -sinFI;
        Umatrix[second][first] = sinFI;
        UmatrixList.add(Umatrix);
    }

    private void transpositionMatrix(int iteration) {
        double[][] UMatrixTransp = new double[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                UMatrixTransp[i][j] = UmatrixList.get(iteration)[j][i];
            }
        }
        UmatrixTranspList.add(UMatrixTransp);
    }

    private void findMaximumAboveTheMainDiagonal(int iteration) {
        maxElementIJ[0] = matrixList.get(iteration)[0][1];
        maxElementIJ[1] = 0;
        maxElementIJ[2] = 1;
        for (int i = 0; i < len; i++) {
            for (int j = 2; j < len; j++) {
                if (j > i && Math.abs(matrixList.get(iteration)[i][j]) > Math.abs(maxElementIJ[0])) {
                    maxElementIJ[0] = matrixList.get(iteration)[i][j];
                    maxElementIJ[1] = i;
                    maxElementIJ[2] = j;
                }
            }
        }

    }

    private double[][] multiMatrix(double[][] matrix1, double[][] matrix2) {
        double[][] multi = new double[len][len];
        for (int m = 0; m < len; m++) {
            for (int j = 0; j < len; j++) {
                for (int i = 0; i < len; i++) {
                    multi[m][j] += matrix1[m][i] * matrix2[i][j];
                }
            }
        }
        return multi;
    }

    public RotationMethod(double[][] matrix, double accuracy) {
        this.accuracy = accuracy;
        this.maxElementIJ = new double[3];
        matrixList.add(matrix);
        len = matrix.length;
        this.UFinalMultiMatrix = new double[len][len];
    }

}

class Test {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("МЕНЮ:\n 1.ПОДСЧЕТ 9ГО ВАРИАНТА\n 2.ВВОД СВОЕЙ МАТРИЦЫ");
        int choice = Integer.parseInt(scan.nextLine());
        int numberOfEquation;
        double[][] matrix;
        double accuracy;
        switch (choice) {
            case 1:
                matrix = new double[][]{{4, 7, -1}, {7, -9, -6}, {-1, -6, -4}};
                accuracy = 0.001;
                break;
            case 2:
                System.out.print("Введите размер матрицы:");
                numberOfEquation = Integer.parseInt(scan.nextLine());
                matrix = new double[numberOfEquation][numberOfEquation];
                System.out.print("Введите точность вычисления: ");
                accuracy = Double.parseDouble(scan.nextLine());

                for (int i = 0; i < numberOfEquation; i++) {
                    System.out.print("Введите коэфициэнты " + i + " строки: ");
                    for (int j = 0; j < numberOfEquation; j++) {
                        matrix[i][j] = scan.nextDouble();
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }

        RotationMethod rotationMethod = new RotationMethod(matrix, accuracy);
        rotationMethod.solveMatrix();
    }
}
