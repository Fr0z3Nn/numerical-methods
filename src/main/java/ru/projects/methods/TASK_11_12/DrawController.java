package ru.projects.methods.TASK_11_12;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DrawController {
    @FXML
    public Button task_11;

    @FXML
    public void initialize() {
        task_11.setOnMouseClicked(event -> {
            AreaChart<Number, Number> areaChart = new AreaChart<>(new NumberAxis(), new NumberAxis());

            XYChart.Series<Number, Number> FUNC = new XYChart.Series<>();
            XYChart.Series<Number, Number> POLYNOMIAL1 = new XYChart.Series<>();
            XYChart.Series<Number, Number> POLYNOMIAL2 = new XYChart.Series<>();

            double[][] XandYFunc = new double[][]{{0.1,100.0},{0.5,4.0},{0.9,1.2346},{1.3,0.59172},{1.7,0.34602},{2.1,0.22676}};

            MNKSystem mnkSystem = new MNKSystem(2,XandYFunc);

            for (double[] xy : XandYFunc){
                FUNC.getData().add(new XYChart.Data<>(xy[0],xy[1]));
            }

            for (double x = XandYFunc[0][0] - 0.5; x < XandYFunc[XandYFunc.length-1][0] + 0.5; x += 0.01){
                POLYNOMIAL1.getData().add(new XYChart.Data<>(x,mnkSystem.func1(x)));
                POLYNOMIAL2.getData().add(new XYChart.Data<>(x,mnkSystem.func2(x)));
            }

            FUNC.setName("FUNC");
            POLYNOMIAL1.setName("POLYNOMIAL1");
            POLYNOMIAL2.setName("POLYNOMIAL2");
            areaChart.getData().setAll(FUNC,POLYNOMIAL1,POLYNOMIAL2);
            Scene scene = new Scene(areaChart);
            Stage newWindow = new Stage();
            newWindow.setTitle("TASK_11");
            newWindow.setScene(scene);
            newWindow.show();
        });
    }
}
