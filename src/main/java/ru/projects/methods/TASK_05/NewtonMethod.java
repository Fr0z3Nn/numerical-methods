package ru.projects.methods.TASK_05;

public class NewtonMethod extends Method{

    public NewtonMethod(double accuracy, double leftA, double rightB) {
        super(accuracy, leftA, rightB);
    }

    private double fN(double x) {
        return x * x * x + x * x - 2 * x - 1;
    }

    private double fP(double x) {
        return 3 * x * x + 2 * x - 2;
    }

    private double fPP(double x) {
        return 6 * x + 2;
    }

    private double Сonverges() {
        System.out.println(fN(leftA));
        System.out.println(fP(leftA));
        System.out.println(fPP(leftA));
        System.out.println("///////");
        System.out.println(fN(rightB));
        System.out.println(fP(rightB));
        System.out.println(fPP(rightB));
        System.out.println("----------------------------");

        if (fN(leftA) * fN(rightB) >= 0) return 0;

        for (double x = leftA; x <= rightB; ) {
            if (fN(x) * fPP(x) > 0) return x;
            x += accuracy;
        }
        return 0;
    }

    public String solve() {
        double zeroApproximation = Сonverges();
        if (zeroApproximation == 0) return "Условие сходимости не выполняется, выберете другой интервал";
        StringBuilder result = new StringBuilder();
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
