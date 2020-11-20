package ru.projects.methods.TASK_11_12.integral;

public class IntegralUserConsole {
    public static void main(String[] args) {
        IntegralRange integralRange = new IntegralRange(-1,1,0.25);
        IntegralSolver integralSolver = new IntegralSolver(integralRange);
        System.out.println(integralSolver.methodLeftRectangles.solve());
        System.out.println(integralSolver.methodRightRectangles.solve());
        System.out.println(integralSolver.methodMiddleRectangles.solve());
        System.out.println(integralSolver.methodTrapeze.solve());
        System.out.println(integralSolver.methodSimpson.solve());

    }

}


