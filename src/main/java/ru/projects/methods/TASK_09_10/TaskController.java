package ru.projects.methods.TASK_09_10;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
            if (checkLagr.isSelected()) {
                AreaChart<Number, Number> areaChart = new AreaChart<>(new NumberAxis(), new NumberAxis());

                XYChart.Series<Number, Number> seriesFunc = new XYChart.Series<>();
                XYChart.Series<Number, Number> seriesLagrn = new XYChart.Series<>();

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
                seriesFunc.setName("FUNC");
                seriesLagrn.setName("LAGRANGE");
                areaChart.getData().setAll(seriesFunc, seriesLagrn);
                Scene scene = new Scene(areaChart);
                Stage newWindow = new Stage();
                newWindow.setTitle("LAGRANGE");
                newWindow.setScene(scene);
                newWindow.show();

            }
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
