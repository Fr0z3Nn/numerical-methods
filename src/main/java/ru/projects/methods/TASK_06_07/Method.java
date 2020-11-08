package ru.projects.methods.TASK_06_07;

public abstract class Method {
    protected double accuracy;
    protected  double leftA;
    protected  double rightB;

    public Method(double accuracy, double leftA, double rightB) {
        this.accuracy = accuracy;
        this.leftA = leftA;
        this.rightB = rightB;
    }

    abstract String solve();
}
