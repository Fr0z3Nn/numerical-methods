package ru.projects.methods.TASK_01;

import java.util.Arrays;

public class Matrix {
    private int dimension;
    private double[][] matrix;
    private double[] vector;
    private double[][] oneDiagonalMatrix;
    private double[] copyOfVectorToZ;
    private double[][] matrixOfKoefForLU;


    public Matrix(double[][] doubleArray, double[] vector) throws Exception {
        this.matrix = doubleArray;
        this.dimension = matrix.length;
        this.vector = vector;
        this.copyOfVectorToZ = Arrays.copyOf(vector,dimension);
        this.oneDiagonalMatrix = new double[dimension][dimension];
        this.matrixOfKoefForLU = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++)
            if (matrix[i].length != dimension){
                throw new Exception("NOT SQUARE");
            }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == j) {
                    oneDiagonalMatrix[i][j] = 1.0;
                    matrixOfKoefForLU[i][j] = 1.0;
                }
            }
        }
        toDiagonalMatrix();
    }
    public double[] solveMatrixAndGetX(){
        double[] solution = new double[dimension];
        return getDoublesForSolution(solution, vector);
    }

    public double[] LUSolveMatrixAndGetX(){
        double[] solution = new double[dimension];
        double[] vectorZ = new double[dimension];
        for (int i = 0; i < matrixOfKoefForLU.length; i++) {
            for (int j = 0; j < matrixOfKoefForLU.length; j++) {
                if (i != j) {
                    copyOfVectorToZ[i] -= vectorZ[j] * matrixOfKoefForLU[i][j];
                }
            }
            vectorZ[i] = copyOfVectorToZ[i];
        }

        return getDoublesForSolution(solution, vectorZ);
    }

    private double[] getDoublesForSolution(double[] solution, double[] vector) {
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    vector[i] -= solution[j] * matrix[i][j];
                }
            }
            solution[i] = vector[i] / matrix[i][i];
        }
        return solution;
    }

    public void toDiagonalMatrix(){
        for (int j = 0; j < dimension; j++) {
            for (int i = j + 1; i < dimension; i++) {
                double koef = matrix[i][j] / matrix[j][j];
                matrixOfKoefForLU[i][j] = koef;
                for (int m = 0; m < dimension; m++) {
                    matrix[i][m] -= koef * matrix[j][m];
                    oneDiagonalMatrix[i][m] -= koef * oneDiagonalMatrix[j][m];
                }
                vector[i] -= koef * vector[j];
            }
        }
    }

    public double[][] getInverseMatrix(){
        double[][] reverseMatrix = new double[dimension][dimension];
        double[][] reverseOneDiagonalMatrix = new double[dimension][dimension];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                reverseMatrix[i][j] = matrix[matrix.length - 1 - i][matrix.length - 1 - j];
                reverseOneDiagonalMatrix[i][j] = oneDiagonalMatrix[oneDiagonalMatrix.length - 1 - i][oneDiagonalMatrix.length - 1 - j];
            }
        }

        for (int j = 0; j < reverseMatrix.length; j++) {
            for (int i = j + 1; i < reverseMatrix.length; i++) {
                double koef = reverseMatrix[i][j] / reverseMatrix[j][j];
                for (int m = 0; m < reverseMatrix.length; m++) {
                    reverseMatrix[i][m] -= koef * reverseMatrix[j][m];
                    reverseOneDiagonalMatrix[i][m] -= koef * reverseOneDiagonalMatrix[j][m];
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    for (int m = 0; m < matrix.length; m++) {
                        reverseOneDiagonalMatrix[i][m] /= reverseMatrix[i][j];
                    }
                }
            }
        }

        double[][] inverseMatrix = new double[dimension][dimension];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                inverseMatrix[i][j] = reverseOneDiagonalMatrix[reverseOneDiagonalMatrix.length - 1 - i][reverseOneDiagonalMatrix.length - 1 - j];
            }
        }

        return inverseMatrix;
    }

    public double getLUDeterminant(){
        double det = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j){
                    det *= matrix[i][j];
                }
            }
        }
        return det;
    }
    public double getDeterminant() throws Exception {
        if (dimension == 1)
            return matrix[0][0];
        // рекурсия
        double det = 0;
        for (int j = 0; j < dimension; j++) {
            if (j % 2 == 0)
                det += matrix[0][j] * GetSubMatrix(this, 0, j).getDeterminant();
            else
                det -= matrix[0][j] * GetSubMatrix(this, 0, j).getDeterminant();
        }
        return det;
    }

    public Matrix GetSubMatrix(Matrix m, int rowToExclude, int colToExclude) throws Exception {
        double[][] values = new double[m.dimension - 1][];
        for (int i = 0; i < m.dimension; i++) {
            if (i < m.dimension - 1)
                values[i] = new double[m.dimension - 1];
            for (int j = 0; j < m.dimension; j++)
                if (i != rowToExclude && j != colToExclude)
                    values[i < rowToExclude ? i : i - 1][j < colToExclude ? j : j - 1] = m.matrix[i][j];
        }
        return new Matrix(values,vector);
    }

    public double[][] getMatrix() {
        return matrix;
    }

}
