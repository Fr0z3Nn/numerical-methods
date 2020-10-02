package ru.projects.methods.TASK_02;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class RunMethod {

    private static double[][] matrix;
    private static double[] vector;

    private static double[] PETA;
    private static double[] QUTA;
    private static double[] solution;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        //матрица
        System.out.println("МЕНЮ:\n 1.ПОДСЧЕТ 9ГО ВАРИАНТА\n 2.ВВОД СВОЕЙ МАТРИЦЫ");
        int choice = Integer.parseInt(scan.nextLine());
        switch (choice) {
            case 1:
                //TEST
                matrix = new double[][]{{8, -4, 0, 0, 0}, {-2, 12, -7, 0, 0}, {0, 2, -9, 1, 0}, {0, 0, -8, 17, -4}, {0, 0, 0, -7, 13}};
                vector = new double[]{32, 15, -10, 133, -76};
                PETA = new double[5];
                QUTA = new double[5];
                solution = new double[5];
                break;
            case 2:
                System.out.print("Введите размер матрицы / кол-во уравнений: ");
                //size
                int numberOfEquation = Integer.parseInt(scan.nextLine());
                //initialization
                matrix = new double[numberOfEquation][numberOfEquation];
                vector = new double[numberOfEquation];
                PETA = new double[numberOfEquation];
                QUTA = new double[numberOfEquation];
                solution = new double[numberOfEquation];
                //massive
                for (int i = 0; i < numberOfEquation; i++) {
                    System.out.print("Введите коэфициэнты " + i + " строки: ");
                    for (int j = 0; j < numberOfEquation; j++) {
                        matrix[i][j] = scan.nextDouble();
                    }
                }
                //vector
                System.out.print("Введите свободные коэффициенты: ");
                for (int i = 0; i < numberOfEquation; i++) {
                    vector[i] = scan.nextDouble();
                }
                break;
        }
        //find koef and solve matrix
        calculateKoef();
        calculateRootsEquation();
        printSolution(solution);

    }

    private static void PETAKoef() {
        for (int i = 0; i < matrix.length; i++) {
            if(i == 0){
                PETA[i] = (-(matrix[0][1])) / (matrix[0][0]);
            }else if(i == matrix.length - 1){
                PETA[i] = 0;
            }else{
                // P             C                   B               A
                PETA[i] = (-matrix[i][i + 1]) / (matrix[i][i] + matrix[i][i - 1] * PETA[i - 1]);
            }
        }
    }

    private static void QUTAKoef() {
        for (int i = 0; i < matrix.length; i++) {
            if(i == 0){
                QUTA[i] = vector[0] / matrix[0][0];
            }else{
                // Q            d           A                Q          B               A               P
                QUTA[i] = (vector[i] - matrix[i][i - 1] * QUTA[i - 1]) / (matrix[i][i] + matrix[i][i - 1] * PETA[i - 1]);
            }
        }
    }

    public static void calculateRootsEquation() {
        solution[matrix.length - 1] = QUTA[matrix.length - 1];
        for (int i = matrix.length - 2; i >= 0; i--) {
            solution[i] = QUTA[i] + PETA[i] * solution[i + 1];
        }
    }

    public static void calculateKoef() {
        PETAKoef();
        QUTAKoef();
    }

    static void printSolution(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("X" + (i + 1) + " = %.5f\n", array[i]);
        }
    }
}
