package ru.projects.methods.TASK_06_07;

public class IterationMethod extends Method{

    public IterationMethod(double accuracy, double leftA, double rightB) {
        super(accuracy, leftA, rightB);
    }

    // f1(x) = 3sqrt(2x+1-x^2)
    private double f1N(double x) {
        return Math.cbrt(2 * x + 1 - x * x);
    }

    // f2(x) = (x^3+x^2-1)/2
    private double f2N(double x) {
        return (Math.pow(x, 3) + Math.pow(x, 2) - 1) / 2;
    }

    // f'1(x) = (2/3 - (2/3)x) / (1 - x^2 + 2x)^(2/3)
    private double f1P(double x) {
        return (2.0 / 3 - (2 * x / 3)) / Math.cbrt(Math.pow((1 - x * x + 2 * x), 2));
    }

    FuncCalculate f1ABS = x ->{
        double max = Double.MIN_NORMAL;
        for (double i = leftA; i <= x;){
            if(Math.abs(f1P(i)) > max) max = Math.abs(f1P(i));
            i += accuracy;
        }
        return max;
    };

    // f'2(x) = 3 (x^2/2) + x
    private double f2P(double x) {
        return 3 * (x * x / 2) + x;
    }

    FuncCalculate f2ABS = x -> {
        double max = Double.MIN_NORMAL;
        for (double i = leftA; i <= x; ) {
            if (Math.abs(f2P(i)) > max) max = Math.abs(f2P(i));
            i += accuracy;
        }
        return max;
    };

    private boolean isNotСonverges(int num) {
        boolean res = false;
        switch (num) {
            case 1:
                for (double x = leftA; x <= rightB; ) {
                    if (!(f1N(x) <= rightB && f1N(x) >= leftA &&
                            f1P(x) < 1)) res = true;
                    x += accuracy;
                }
                break;
            case 2:
                for (double x = leftA; x <= rightB; ) {
                    if (!(f2N(x) <= rightB && f2N(x) >= leftA &&
                            f2P(x) < 1)) res = true;
                    x += accuracy;
                }
                break;
        }
        return res;
    }

    public int findFunction() {
        if (f1N(leftA) <= rightB && f1N(leftA) >= leftA &&
                f1P(leftA) < 1 && f1P(rightB) < 1 &&
                f1N(rightB) <= rightB && f1N(rightB) >= leftA) return 1;
        if (f2N(leftA) <= rightB && f2N(leftA) >= leftA &&
                f2P(leftA) < 1 && f2P(rightB) < 1 &&
                f2N(rightB) <= rightB && f2N(rightB) >= leftA) return 2;
        return 0;
    }

    public String solve() {
        StringBuilder result = new StringBuilder();
        result.append(" k         X          ε\n");
        int whatFunctionUses = findFunction();
        if (isNotСonverges(whatFunctionUses)) return "Условие сходимости не выполняется, выберете другой интервал";
        double zeroApproximation = leftA;
        int k = 0;
        result.append(String.format(" %d     %.4f    ----\n", k, zeroApproximation));
        double q;
        double epsilon;
        double nextApproximation;
        switch (whatFunctionUses) {
            case 1:
                q = f1ABS.calculate(rightB);
                do {
                    nextApproximation = zeroApproximation;
                    zeroApproximation = f1N(zeroApproximation);
                    epsilon = q / (1 - q) * Math.abs(zeroApproximation - nextApproximation);
                    k++;
                    result.append(String.format(" %d     %.4f    %.4f\n", k, zeroApproximation, epsilon));
                } while (epsilon > accuracy);
                break;
            case 2:
                q = f2ABS.calculate(rightB);
                do {
                    nextApproximation = zeroApproximation;
                    zeroApproximation = f2N(zeroApproximation);
                    epsilon = q / (1 - q) * Math.abs(zeroApproximation - nextApproximation);
                    k++;
                    result.append(String.format(" %d     %.4f    %.4f\n", k, zeroApproximation, epsilon));
                } while (epsilon > accuracy);
                break;
            case 0:
                return "Условие сходимости не выполняется, выберете другой интервал";
        }
        return result.toString();
    }
}
