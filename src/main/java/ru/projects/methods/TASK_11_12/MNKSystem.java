package ru.projects.methods.TASK_11_12;

import org.apache.commons.math3.linear.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class MNKSystem {

    private int len;
    private double[][] numbersForGeneration;

    private double[][] matrixOfKoef;
    private double[] vectorOfMatrixKoef;
    private Map<Integer, List<Double>> mapOfMNKSystem_XY = new HashMap<>();
    private double[] A0andA1ForFirstFunc = new double[2];
    private double[] A0andA1andA2ForSecondFunc = new double[3];

    public MNKSystem(int numberOfPolynomial, double[][] numbersForGeneration) {
        this.len = numberOfPolynomial + 1;
        matrixOfKoef = new double[len][len];
        vectorOfMatrixKoef = new double[len];
        this.numbersForGeneration = numbersForGeneration;
        initialization();
    }

    public double func1(double x){
        return A0andA1ForFirstFunc[0] + A0andA1ForFirstFunc[1] * x;
    }

    public double func2(double x){
        return A0andA1andA2ForSecondFunc[0] + A0andA1andA2ForSecondFunc[1] * x + A0andA1andA2ForSecondFunc[2] * x * x;
    }

    private void initialization(){
        generateMNKSystem();
        createMatrixOfKoef();
        createVectorOfKoef();
        findA0andA1forFirstFunc();
        findA0andA1andA2forSecondFunc();
    }

    private void findA0andA1forFirstFunc() {
        double[][] matrixData = new double[len - 1][len - 1];
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1; j++) {
                matrixData[i][j] = matrixOfKoef[i][j];
            }
        }
        RealMatrix m = MatrixUtils.createRealMatrix(matrixData);
        DecompositionSolver solver = new LUDecomposition(m).getSolver();
        double[] vectorData = new double[len - 1];
        for (int i = 0; i < len - 1; i++) {
            vectorData[i] = vectorOfMatrixKoef[i];
        }
        RealVector constants = new ArrayRealVector(vectorData);
        RealVector solution = solver.solve(constants);
        A0andA1ForFirstFunc = solution.toArray();
    }

    private void findA0andA1andA2forSecondFunc() {
        RealMatrix m = MatrixUtils.createRealMatrix(matrixOfKoef);
        DecompositionSolver solver = new LUDecomposition(m).getSolver();
        RealVector constants = new ArrayRealVector(vectorOfMatrixKoef);
        RealVector solution = solver.solve(constants);
        A0andA1andA2ForSecondFunc = solution.toArray();
    }

    private void createMatrixOfKoef() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i != 0 || j != 0) {
                    matrixOfKoef[i][j] = (double) mapOfMNKSystem_XY.size();
                }
                if (i >= j) {
                    matrixOfKoef[i][j] = matrixOfKoef[j][i] = sumOfSeriesInDegree(i + j);
                }
            }
        }
    }

    private void createVectorOfKoef(){
        for (int i = 0; i < len; i++) {
            vectorOfMatrixKoef[i] = sumOfNumbersInVectorInDegree(i);
        }
    }

    private double sumOfNumbersInVectorInDegree(int degree){
        // x == 0           y == 1
        if(degree == 0){
            return sumOfSeriesXorY(1, 1);
        }
        return mapOfMNKSystem_XY
                .entrySet()
                .stream()
                .flatMapToDouble(n -> DoubleStream.of(n.getValue().get(1) * Math.pow(n.getValue().get(0), degree)))
                .sum();

    }

    private double sumOfSeriesInDegree(int degree) {
        return sumOfSeriesXorY(0, degree);
    }

    private void generateMNKSystem() {
        AtomicInteger count = new AtomicInteger();
        Arrays.stream(numbersForGeneration).forEach(n -> {
            int m = new Integer(String.valueOf(count));
            mapOfMNKSystem_XY.put(
                    count.getAndIncrement(),
                    new ArrayList<>(Arrays.asList(numbersForGeneration[m][0], numbersForGeneration[m][1]))
            );
        });
    }

    private double sumOfSeriesXorY(int xORy, int degree) {
        return mapOfMNKSystem_XY
                .values()
                .stream()
                .map(n -> Math.pow(n.get(xORy), degree))
                .flatMapToDouble(DoubleStream::of).sum();
    }

    public void print() {
        Stream.of(mapOfMNKSystem_XY).forEach(System.out::println);
        System.out.println(Arrays.deepToString(matrixOfKoef));
        System.out.println(Arrays.toString(vectorOfMatrixKoef));
        System.out.println(Arrays.toString(A0andA1ForFirstFunc));
        System.out.println(Arrays.toString(A0andA1andA2ForSecondFunc));
    }
}

class Test{
    public static void main(String[] args) {
        MNKSystem mnkSystem = new MNKSystem(2,new double[][]{{0.0,0.0},{1.7,1.3038},{3.4,1.8439},{5.1,2.2583},{6.8,2.6077},{8.5,2.9155}});

        mnkSystem.print();
    }}