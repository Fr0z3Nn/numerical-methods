package ru.projects.methods.TASK_09_10;

import org.apache.commons.math3.linear.*;

import java.util.Arrays;

public class CubeSpline {
    private double[] X = new double[5];

    private double[] func = new double[5];

    private double[] h = new double[5];

    private double[] cKOEF = new double[4];
    private double[] aKOEF = new double[4];
    private double[] bKOEF = new double[4];
    private double[] dKOEF = new double[4];

    public CubeSpline(double x0, double x1, double x2, double x3, double x4, double func0, double func1, double func2, double func3, double func4) {
        X[0] = x0;
        X[1] = x1;
        X[2] = x2;
        X[3] = x3;
        X[4] = x4;

        this.func[0] = func0;
        this.func[1] = func1;
        this.func[2] = func2;
        this.func[3] = func3;
        this.func[4] = func4;

        this.h[1] = x1 - x0;
        this.h[2] = x2 - x1;
        this.h[3] = x3 - x2;
        this.h[4] = x4 - x3;

        initialization();
    }

    public double calculateFuncInCoordinates(double x,int i){
        return aKOEF[i] + bKOEF[i] * (x - X[i]) + cKOEF[i] * Math.pow((x - X[i]),2) + dKOEF[i] * Math.pow((x - X[i]),3);
    }

    private void initialization() {
        double[][] matrixData = {{2 * (h[1] + h[2]), h[2], 0}, {h[2], 2 * (h[2] + h[3]), h[3]}, {0, h[3], 2 * (h[3] + h[4])}};
        RealMatrix m = MatrixUtils.createRealMatrix(matrixData);
        DecompositionSolver solver = new LUDecomposition(m).getSolver();
        RealVector constants = new ArrayRealVector(new double[]{firstStrokeKoef(), secondStrokeKoef(), thirdStrokeKoef()});
        RealVector solution = solver.solve(constants);
        double[] t = solution.toArray();
        //C koef initialization
        for (int i = 0; i < cKOEF.length; i++) {
            if (i == 0) {
                cKOEF[i] = 0;
            } else {
                cKOEF[i] = t[i - 1];
            }
        }

        //A koef initialization
        for (int i = 0; i < aKOEF.length; i++) {
            aKOEF[i] = func[i];
        }

        //B koef initialization
        for (int i = 0; i < aKOEF.length - 1; i++) {
            bKOEF[i] = ((func[i + 1] - func[i]) / h[i + 1]) - ((cKOEF[i + 1] + 2 * cKOEF[i]) * h[i + 1]) / 3;
        }
        bKOEF[3] = ((func[4] - func[3]) / h[4]) - (2 * h[4] * cKOEF[3]) / 3;

        //D koef initialization
        for (int i = 0; i < dKOEF.length - 1; i++) {
            dKOEF[i] = (cKOEF[i + 1] - cKOEF[i]) / (3 * h[i + 1]);
        }
        dKOEF[3] = -cKOEF[3] / (3 * h[4]);
    }

    private double firstStrokeKoef() {
        return 3 * (((func[2] - func[1]) / h[2]) - ((func[1] - func[0]) / h[1]));
    }

    private double secondStrokeKoef() {
        return 3 * (((func[3] - func[2]) / h[3]) - ((func[2] - func[1]) / h[2]));
    }

    private double thirdStrokeKoef() {
        return 3 * (((func[4] - func[3]) / h[4]) - ((func[3] - func[2]) / h[3]));
    }

    public double[] getH() {
        return h;
    }

    public double[] getcKOEF() {
        return cKOEF;
    }

    public double[] getaKOEF() {
        return aKOEF;
    }

    public double[] getbKOEF() {
        return bKOEF;
    }

    public double[] getdKOEF() {
        return dKOEF;
    }

    public double[] getX() {
        return X;
    }
}
