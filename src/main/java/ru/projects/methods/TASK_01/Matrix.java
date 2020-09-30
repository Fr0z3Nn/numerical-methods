package ru.projects.methods.TASK_01;

public class Matrix {
    private int dimension;
    private double[][] matrix;

    public Matrix(double[][] double_array) throws Exception {
        matrix = double_array;
        dimension = matrix.length;
        for (int i = 0; i < dimension; i++)
            if (matrix[i].length != dimension)
                throw new Exception("МАТРИЦА НЕ КВАДРАТНАЯ");
    }

    public double Determinant() throws Exception {
        if (dimension == 1)
            return matrix[0][0];
        // рекурсия
        double det = 0;
        for (int j = 0; j < dimension; j++) {
            if (j % 2 == 0)
                det += matrix[0][j] * GetSubmatrix(this, 0, j).Determinant();
            else
                det -= matrix[0][j] * GetSubmatrix(this, 0, j).Determinant();
        }
        return det;
    }

    public Matrix GetSubmatrix(Matrix m, int rowToExclude, int colToExclude) throws Exception {
        double[][] values = new double[m.dimension - 1][];
        for (int i = 0; i < m.dimension; i++) {
            if (i < m.dimension - 1)
                values[i] = new double[m.dimension - 1];
            for (int j = 0; j < m.dimension; j++)
                if (i != rowToExclude && j != colToExclude)
                    values[i < rowToExclude ? i : i - 1][j < colToExclude ? j : j - 1] = m.matrix[i][j];
        }
        return new Matrix(values);
    }
}
