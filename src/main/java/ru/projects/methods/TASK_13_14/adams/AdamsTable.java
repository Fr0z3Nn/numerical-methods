package ru.projects.methods.TASK_13_14.adams;

import org.apache.commons.math3.util.Precision;

public class AdamsTable {
    private int i;
    private double h;
    private double x;
    private double y;
    private double yd;
    private double ydd;
    private double z;

    private String delta_z;
    private String delta_y;
    private double istY;
    private String accuracy;

    public AdamsTable(int i, double h, double x, double y,double yd,double ydd, double z, double delta_z, double delta_y, double istY, double accuracy) {
        this.i = i;
        this.h = h;
        this.x = Precision.round(x,4);
        this.y = Precision.round(y,4);
        this.yd = Precision.round(yd,4);
        this.ydd = Precision.round(ydd,4);
        this.z = Precision.round(z,4);
        if(delta_z == 0){
            this.delta_z = "-----";
        }else{
            this.delta_z = String.valueOf(Precision.round(delta_z,4));
        }

        if(delta_y == 0){
            this.delta_y = "-----";
        }else{
            this.delta_y = String.valueOf(Precision.round(delta_y,4));
        }

        this.istY = Precision.round(istY,4);
        this.accuracy = String.format("%.3e",accuracy);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getYd() {
        return yd;
    }

    public void setYd(double yd) {
        this.yd = yd;
    }

    public double getYdd() {
        return ydd;
    }

    public void setYdd(double ydd) {
        this.ydd = ydd;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getDelta_z() {
        return delta_z;
    }

    public void setDelta_z(String delta_z) {
        this.delta_z = delta_z;
    }

    public String getDelta_y() {
        return delta_y;
    }

    public void setDelta_y(String delta_y) {
        this.delta_y = delta_y;
    }

    public double getIstY() {
        return istY;
    }

    public void setIstY(double istY) {
        this.istY = istY;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }
}
