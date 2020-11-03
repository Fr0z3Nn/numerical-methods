package ru.projects.methods.TASK_06;

public class Method {
    protected double X1;
    protected double X2;
    protected double Y1;
    protected double Y2;
    protected double accuracy;

    public Method(double x1, double x2, double y1, double y2, double accuracy) {
        X1 = x1;
        X2 = x2;
        Y1 = y1;
        Y2 = y2;
        this.accuracy = accuracy;
    }

    public String solve(){
        return "Ошибка";
    }
}
