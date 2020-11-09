package ru.projects.methods.TASK_09_10;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TaskController {
    @FXML
    public Button drawgraph;
    @FXML
    public Button showtable;
    @FXML
    public CheckBox checkNewton;
    @FXML
    public CheckBox checkLagr;
    @FXML
    public CheckBox checkvar1;
    @FXML
    public CheckBox checkvar2;

    @FXML
    public void initialize() {
        drawgraph.setOnMouseClicked(event -> {
            AreaChart<Number, Number> areaChart = new AreaChart<>(new NumberAxis(), new NumberAxis());

            XYChart.Series<Number, Number> seriesFunc = new XYChart.Series<>();
            XYChart.Series<Number, Number> seriesLagrn = new XYChart.Series<>();

            if (checkLagr.isSelected()) {

                if(checkvar1.isSelected()){
                    UtilLagrange variant1 = new UtilLagrange(0.1,0.5,0.9,1.3);
                    for (double x = 0.1; x < 1.5; x += 0.01) {
                        seriesFunc.getData().add(new XYChart.Data<>(x, variant1.Func(x)));
                        seriesLagrn.getData().add(new XYChart.Data<>(x, variant1.calculateLagrange(x)));
                    }
                }

                if (checkvar2.isSelected()) {
                    UtilLagrange variant2 = new UtilLagrange(0.1,0.5,1.1,1.3);
                    for (double x = 0.1; x < 1.5; x += 0.01) {
                        seriesFunc.getData().add(new XYChart.Data<>(x, variant2.Func(x)));
                        seriesLagrn.getData().add(new XYChart.Data<>(x, variant2.calculateLagrange(x)));
                    }
                }
            }

            if (checkNewton.isSelected()){

                if (checkvar1.isSelected()){
                    UtilNewton variant1 = new UtilNewton(0.1,0.5,0.9,1.3);
                    for (double x = 0.1; x < 1.5; x += 0.01) {
                        seriesFunc.getData().add(new XYChart.Data<>(x, variant1.Func(x)));
                        seriesLagrn.getData().add(new XYChart.Data<>(x, variant1.calculateMeNewton(x)));
                    }
                }
                if (checkvar2.isSelected()){
                    UtilNewton variant2 = new UtilNewton(0.1,0.5,1.1,1.3);
                    for (double x = 0.1; x < 1.5; x += 0.01) {
                        seriesFunc.getData().add(new XYChart.Data<>(x, variant2.Func(x)));
                        seriesLagrn.getData().add(new XYChart.Data<>(x, variant2.calculateMeNewton(x)));
                    }
                }
            }

            seriesFunc.setName("FUNC");
            seriesLagrn.setName("LAGRANGE");
            areaChart.getData().setAll(seriesFunc, seriesLagrn);
            Scene scene = new Scene(areaChart);
            Stage newWindow = new Stage();
            newWindow.setTitle("GRAPHICS");
            newWindow.setScene(scene);
            newWindow.show();
        });

        showtable.setOnMouseClicked(event -> {
            TextArea textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(10);
            StringBuilder result = new StringBuilder();

            if (checkLagr.isSelected()) {
                if(checkvar1.isSelected()){
                    UtilLagrange variant1 = new UtilLagrange(0.1,0.5,0.9,1.3);
                    for (double x = 0.1; x < 1.5; x += 0.01) {
                        result.append("X= ").append(String.format("%.3f ",x));
                        result.append(" f(x) = ").append(String.format("%.3f ",variant1.Func(x)));
                        result.append(" L3(x) = ").append(String.format("%.3f ",variant1.calculateLagrange(x)));
                        result.append("ΔL3 = ").append(String.format("%.6f\n",variant1.calculateLagrange(x) - variant1.Func(x)));
                    }
                    result.append("ПОГРЕШНОСТЬ ИНТЕРПОЛЯЦИИ: ").append(String.format("%.3f",variant1.calculateLagrange(0.8)- variant1.Func(0.8)));
                }

                if (checkvar2.isSelected()) {
                    UtilLagrange variant2 = new UtilLagrange(0.1,0.5,1.1,1.3);
                    for (double x = 0.1; x < 1.5; x += 0.01) {
                        result.append("X= ").append(String.format("%.3f ",x));
                        result.append(" f(x) = ").append(String.format("%.3f ",variant2.Func(x)));
                        result.append(" L3(x) = ").append(String.format("%.3f ",variant2.calculateLagrange(x)));
                        result.append("ΔL3 = ").append(String.format("%.6f\n",variant2.calculateLagrange(x) - variant2.Func(x)));
                    }
                    result.append("ПОГРЕШНОСТЬ ИНТЕРПОЛЯЦИИ: ").append(String.format("%.3f",variant2.calculateLagrange(0.8)- variant2.Func(0.8)));
                }
            }

            if (checkNewton.isSelected()){

                if (checkvar1.isSelected()){
                    UtilNewton variant1 = new UtilNewton(0.1,0.5,0.9,1.3);
                    for (double x = 0.1; x < 1.5; x += 0.01) {
                        result.append("X= ").append(String.format("%.3f ",x));
                        result.append(" f(x) = ").append(String.format("%.3f ",variant1.Func(x)));
                        result.append(" P3(x) = ").append(String.format("%.3f ",variant1.calculateMeNewton(x)));
                        result.append("ΔP3 = ").append(String.format("%.6f\n",variant1.calculateMeNewton(x) - variant1.Func(x)));
                    }
                    result.append("ПОГРЕШНОСТЬ ИНТЕРПОЛЯЦИИ: ").append(String.format("%.3f",variant1.calculateMeNewton(0.8)- variant1.Func(0.8)));
                }
                if (checkvar2.isSelected()){
                    UtilNewton variant2 = new UtilNewton(0.1,0.5,1.1,1.3);
                    for (double x = 0.1; x < 1.5; x += 0.01) {
                        result.append("X= ").append(String.format("%.3f ",x));
                        result.append(" f(x) = ").append(String.format("%.3f ",variant2.Func(x)));
                        result.append(" P3(x) = ").append(String.format("%.3f ",variant2.calculateMeNewton(x)));
                        result.append("ΔP3 = ").append(String.format("%.6f\n",variant2.calculateMeNewton(x) - variant2.Func(x)));
                    }
                    result.append("ПОГРЕШНОСТЬ ИНТЕРПОЛЯЦИИ: ").append(String.format("%.3f",variant2.calculateMeNewton(0.8)- variant2.Func(0.8)));
                }
            }
            textArea.setText(result.toString());
            Scene scene = new Scene(textArea);
            Stage newWindow = new Stage();
            newWindow.setTitle("TEXT");
            newWindow.setScene(scene);
            newWindow.show();
        });

        //METGOD CHECKERS
        checkNewton.setOnAction(event ->

        {
            if (checkNewton.isSelected()) {
                checkLagr.setSelected(false);
            }
        });

        checkLagr.setOnAction(event ->

        {
            if (checkLagr.isSelected()) {
                checkNewton.setSelected(false);
            }
        });

        // VARIANT CHECKERS
        checkvar1.setOnAction(event ->

        {
            if (checkvar1.isSelected()) {
                checkvar2.setSelected(false);
            }
        });

        checkvar2.setOnAction(event ->

        {
            if (checkvar2.isSelected()) {
                checkvar1.setSelected(false);
            }
        });
    }
}
