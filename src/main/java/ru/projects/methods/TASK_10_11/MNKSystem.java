package ru.projects.methods.TASK_10_11;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MNKSystem {

    private  int len;
    private Double[][] matrixOfKoef;
    private Double[] vectorOfMatrixKoef;

    public MNKSystem(int numberOfPolynomial) {
        this.len = numberOfPolynomial + 1;
        matrixOfKoef = new Double[len][len];
        vectorOfMatrixKoef = new Double[len];
    }

    private Map<Integer,List<Double>> mapOfMNKSystem_XY = new HashMap<>();


    public void generateMNKSystem(Double[][] pair){
        AtomicInteger count = new AtomicInteger();

        Arrays.stream(pair).forEach(n -> {
            int m = new Integer(String.valueOf(count));
            mapOfMNKSystem_XY.put(
                    count.getAndIncrement(),
                    new ArrayList<>(Arrays.asList(pair[m][0],pair[m][1]))
            );
        });
    }

   public void createMatrixOfKoef(){
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i != 0 || j != 0){
                    matrixOfKoef[i][j] = (double)mapOfMNKSystem_XY.size();
                }
                if ( i >= j){
                    matrixOfKoef[i][j] = matrixOfKoef[j][i] = sumOfSeriesInDegree(i+j);
                }
            }
        }
    }

    public void createVectorOfKoef(){
        for (int i = 0; i < len; i++) {
            vectorOfMatrixKoef[i] = sumOfNumbersInVectorInDegree(i);
        }
    }

    public double sumOfNumbersInVectorInDegree(int degree){
        if(degree == 0){
            return sumOfSeriesXorY(1,1);
        }
        return mapOfMNKSystem_XY
                .entrySet()
                .stream()
                .flatMapToDouble(n-> DoubleStream.of(n.getValue().get(1) * Math.pow(n.getValue().get(0),degree)))
                .sum();

    }

    public double sumOfSeriesInDegree(int degree){
        return sumOfSeriesXorY(0,degree);
    }

    public double sumOfSeriesXorY(int xORy, int degree){
        return mapOfMNKSystem_XY
                .values()
                .stream()
                .map(n -> Math.pow(n.get(xORy),degree))
                .flatMapToDouble(DoubleStream::of).sum();
    }

    public void print(){
        Stream.of(mapOfMNKSystem_XY).forEach(System.out::println);
        System.out.println(Arrays.deepToString(matrixOfKoef));
        System.out.println(Arrays.toString(vectorOfMatrixKoef));
    }
}

class Test{
    public static void main(String[] args) {
        MNKSystem mnkSystem = new MNKSystem(2);
        mnkSystem.generateMNKSystem(new Double[][]{{0.0,0.0},{1.7,1.3038},{3.4,1.8439},{5.1,2.2583},{6.8,2.6077},{8.5,2.9155}});
        mnkSystem.createMatrixOfKoef();
        mnkSystem.createVectorOfKoef();
        mnkSystem.print();
    }}