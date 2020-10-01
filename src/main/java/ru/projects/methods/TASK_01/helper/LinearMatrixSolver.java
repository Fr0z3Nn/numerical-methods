package ru.projects.methods.TASK_01.helper;

import org.apache.commons.math3.linear.*;

import java.util.Arrays;

public class LinearMatrixSolver {
    RealMatrix matrix;
    RealVector vector;
    LUDecomposition solver;

    public LinearMatrixSolver(RealMatrix matrix, RealVector vector) {
        this.matrix = matrix;
        this.vector = vector;
        this.solver = new LUDecomposition(matrix);
    }

    public RealVector getSolutionOfMatrix(){
        return solver.getSolver().solve(vector);
    }

    public double[][] getInverseMatrix(){
        return solver.getSolver().getInverse().getData();
    }

    public double getDeterminantMatrix(){
        return solver.getDeterminant();
    }


}
