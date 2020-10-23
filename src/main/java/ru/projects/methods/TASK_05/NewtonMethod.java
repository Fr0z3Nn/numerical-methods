package ru.projects.methods.TASK_05;

public class NewtonMethod {
    private double accuracy;
    private double leftA;
    private double rightB;

    public NewtonMethod(double accuracy, double leftA, double rightB) {
        this.accuracy = accuracy;
        this.leftA = leftA;
        this.rightB = rightB;
    }

    private double fN(double x) {
        return x * x * x + x * x - 2 * x - 1;
    }

    private double fP(double x) {
        return 3 * x * x + 2 * x - 2 ;
    }

    private double fPP(double x) {
        return 6 * x + 2;
    }

    public String solve() {
        StringBuilder result = new StringBuilder();
        double zeroApproximation = (rightB + leftA) / 2;
        result.append(" k               x                f(x)                f'(x)              -f(x)/f'(x)\n");
        int k = 0;
        double divideArgument;
        double epsilon;
        do {
            double first = fN(zeroApproximation);
            double second = fP(zeroApproximation);
            divideArgument = -first / second;
            result.append(String.format(" %d        %.4f       %.4f         %.4f      %.4f\n", k, zeroApproximation, first, second, divideArgument));
            k++;
            zeroApproximation += divideArgument;
            epsilon = Math.abs(divideArgument);

        } while (epsilon >= accuracy);

        result.append(String.format(" %d        %.4f", k, zeroApproximation));

        return result.toString();
    }
}
