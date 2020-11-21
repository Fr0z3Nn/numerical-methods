package ru.projects.methods.TASK_11_12;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ru.projects.methods.TASK_11_12.derivative.Derivative;
import ru.projects.methods.TASK_11_12.integral.IntegralRange;
import ru.projects.methods.TASK_11_12.integral.IntegralSolver;
import ru.projects.methods.TASK_11_12.integral.IntegralUtil;
import ru.projects.methods.TASK_11_12.mnk.MNKSystem;

public class DrawController {
    @FXML
    public Button task_11;
    public Button task_12_1;
    public Button task_12_2;

    static double methodRygneRobengra(double F1, double F2, double p) {
        return F1 + (F1- F2) / (Math.pow(2, p) - 1);
    }

    @FXML
    public void initialize() {
        task_12_1.setOnMouseClicked(event -> {
            TextArea textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(10);
            StringBuilder result = new StringBuilder();

            Derivative derivative = new Derivative(2.0, new double[][]{{0.0, 0.0}, {1.0, 0.5}, {2.0, 1.7321}, {3.0, 3.0}, {4.0, 3.4641}});
            derivative.generateSystem();
            result.append("Производные\n\n");
            result.append(String.format("ПЕРВАЯ ПРОИЗВОДНАЯ: %.5f\n", derivative.findFIRSTDerivative()));
            result.append(String.format("ВТОРАЯ ПРОИЗВОДНАЯ: %.5f\n", derivative.findSECONDDerivative()));

            textArea.setText(result.toString());
            Scene scene = new Scene(textArea);
            Stage newWindow = new Stage();
            newWindow.setTitle("TEXT");
            newWindow.setScene(scene);
            newWindow.show();
        });
        task_12_2.setOnMouseClicked(event -> {
            TextArea textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(10);
            StringBuilder result = new StringBuilder();

            IntegralRange integralRange = new IntegralRange(-2,2, 1);
            IntegralSolver integralSolver = new IntegralSolver(integralRange);

            double exactValue = IntegralUtil.integralExactValue();
            result.append(String.format("ТОЧНОЕ ЗНАЧЕНИЕ: %.5f\n", exactValue));
            result.append(String.format("ШАГ: %.5f\n", integralRange.getH()));
            double left = integralSolver.methodLeftRectangles.solve();
            result.append(String.format("метод левых: %.5e  ПОГРЕШНОСТЬ: %.5e\n", left, exactValue - left));
            double right = integralSolver.methodRightRectangles.solve();
            result.append(String.format("метод правых: %.5e ПОГРЕШНОСТЬ: %.5e\n", right, exactValue - right));
            double middle = integralSolver.methodMiddleRectangles.solve();
            result.append(String.format("метод средних: %.5e ПОГРЕШНОСТЬ: %.5e\n", middle, exactValue - middle));
            double trapeze = integralSolver.methodTrapeze.solve();
            result.append(String.format("метод трапеций: %.5e ПОГРЕШНОСТЬ: %.5e\n", trapeze, exactValue - trapeze));
            double simpson = integralSolver.methodSimpson.solve();
            result.append(String.format("метод Симпсона: %.5e ПОГРЕШНОСТЬ: %.5e\n", simpson, exactValue - simpson));

            IntegralRange integralRange1 = new IntegralRange(-2,2,0.5);
            IntegralSolver integralSolver1 = new IntegralSolver(integralRange1);

            result.append(String.format("ШАГ: %.5f\n", integralRange1.getH()));
            double left1 = integralSolver1.methodLeftRectangles.solve();
            result.append(String.format("метод левых: %.5e  ПОГРЕШНОСТЬ: %.5e\n", left1, exactValue - left1));
            double right1 = integralSolver1.methodRightRectangles.solve();
            result.append(String.format("метод правых: %.5e ПОГРЕШНОСТЬ: %.5e\n", right1, exactValue - right1));
            double middle1 = integralSolver1.methodMiddleRectangles.solve();
            result.append(String.format("метод средних: %.5e ПОГРЕШНОСТЬ: %.5e\n", middle1, exactValue - middle1));
            double trapeze1 = integralSolver1.methodTrapeze.solve();
            result.append(String.format("метод трапеций: %.5e ПОГРЕШНОСТЬ: %.5e\n", trapeze1, exactValue - trapeze1));
            double simpson1 = integralSolver1.methodSimpson.solve();
            result.append(String.format("метод Симпсона: %.5e ПОГРЕШНОСТЬ: %.5e\n", simpson1, exactValue - simpson1));

            result.append("Внесенные уточнения:\n");
            double leftY = methodRygneRobengra(left1,left,1);
            result.append(String.format("метод левых: %.5e  ПОГРЕШНОСТЬ: %.5e\n", leftY, exactValue - leftY));
            double rightY = methodRygneRobengra(right1,right,1);
            result.append(String.format("метод правых: %.5e ПОГРЕШНОСТЬ: %.5e\n", rightY, exactValue - rightY));
            double middleY = methodRygneRobengra(middle1,middle,2);
            result.append(String.format("метод средних: %.5e ПОГРЕШНОСТЬ: %.5e\n", middleY, exactValue - middleY));
            double trapezeY = methodRygneRobengra(trapeze1,trapeze,2);
            result.append(String.format("метод трапеций: %.5e ПОГРЕШНОСТЬ: %.5e\n", trapezeY, exactValue - trapezeY));
            double simpsonY = methodRygneRobengra(simpson1,simpson,4);
            result.append(String.format("метод Симпсона: %.5e ПОГРЕШНОСТЬ: %.5e\n", simpsonY, exactValue - simpsonY));

            textArea.setText(result.toString());
            Scene scene = new Scene(textArea);
            Stage newWindow = new Stage();
            newWindow.setTitle("TEXT");
            newWindow.setScene(scene);
            newWindow.show();
        });
        task_11.setOnMouseClicked(event -> {
            AreaChart<Number, Number> areaChart = new AreaChart<>(new NumberAxis(), new NumberAxis());

            XYChart.Series<Number, Number> FUNC = new XYChart.Series<>();
            XYChart.Series<Number, Number> POLYNOMIAL1 = new XYChart.Series<>();
            XYChart.Series<Number, Number> POLYNOMIAL2 = new XYChart.Series<>();

            double[][] XandYFunc = new double[][]{{0.1, 100.0}, {0.5, 4.0}, {0.9, 1.2346}, {1.3, 0.59172}, {1.7, 0.34602}, {2.1, 0.22676}};

            MNKSystem mnkSystem = new MNKSystem(2, XandYFunc);

            for (double[] xy : XandYFunc){
                FUNC.getData().add(new XYChart.Data<>(xy[0],xy[1]));
            }

            for (double x = XandYFunc[0][0] - 0.5; x < XandYFunc[XandYFunc.length-1][0] + 0.5; x += 0.01){
                POLYNOMIAL1.getData().add(new XYChart.Data<>(x,mnkSystem.func1(x)));
                POLYNOMIAL2.getData().add(new XYChart.Data<>(x,mnkSystem.func2(x)));
            }

            System.out.println("Сумма квадратов ошибок:\n");
            System.out.printf("Для первого многочлена = %.5f\n",Math.pow(100-mnkSystem.func1(0.1),2)+Math.pow(4.0-mnkSystem.func1(0.5),2)+Math.pow(1.2346-mnkSystem.func1(0.9),2)+Math.pow(0.59172-mnkSystem.func1(1.3),2)+Math.pow(0.34602-mnkSystem.func1(1.7),2)+Math.pow(0.22676-mnkSystem.func1(2.1),2));
            System.out.printf("Для второго многочлена = %.5f\n",Math.pow(100-mnkSystem.func2(0.1),2)+Math.pow(4.0-mnkSystem.func2(0.5),2)+Math.pow(1.2346-mnkSystem.func2(0.9),2)+Math.pow(0.59172-mnkSystem.func2(1.3),2)+Math.pow(0.34602-mnkSystem.func2(1.7),2)+Math.pow(0.22676-mnkSystem.func2(2.1),2));


            FUNC.setName("FUNC");
            POLYNOMIAL1.setName("POLYNOMIAL1");
            POLYNOMIAL2.setName("POLYNOMIAL2");
            areaChart.getData().setAll(FUNC, POLYNOMIAL1, POLYNOMIAL2);
            Scene scene = new Scene(areaChart);
            Stage newWindow = new Stage();
            newWindow.setTitle("TASK_11");
            newWindow.setScene(scene);
            newWindow.show();
        });
    }
}


