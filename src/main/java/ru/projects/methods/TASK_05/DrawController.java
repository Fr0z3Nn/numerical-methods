package ru.projects.methods.TASK_05;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class DrawController {
    @FXML
    public AreaChart<Double, Double> areaChart;
    @FXML
    public Button drawGraph;
    @FXML
    public TextArea areaResult;
    @FXML
    public CheckBox iteration;
    @FXML
    public CheckBox newton;
    @FXML
    public TextField start;
    @FXML
    public TextField end;
    @FXML
    public TextField chooseAccuracy;
    @FXML
    public Button findRoot;
    @FXML
    public TextField A;
    @FXML
    public TextField B;

    @FXML
    public void initialize() {

        drawGraph.setOnMouseClicked( mouseEvent -> {
            XYChart.Series<Double, Double> seriesCube = new XYChart.Series<>();
            XYChart.Series<Double, Double> seriesSquare = new XYChart.Series<>();
            double x = Double.parseDouble(start.getText());
            while (x < Double.parseDouble(end.getText())){
                seriesCube.getData().add(new XYChart.Data<>(x, Math.pow(x, 3)));
                seriesSquare.getData().add(new XYChart.Data<>( x, -Math.pow(x, 2) + 2 * x + 1));
                x += 0.1;
            }

            seriesCube.setName("y = x^3");
            seriesSquare.setName("y = -x^2+2x+1");
            areaChart.getData().setAll(seriesCube, seriesSquare);
        });

        findRoot.setOnMouseClicked(mouseEvent -> {
            double leftA;
            double rightB;
            double accuracy;
            try {
                leftA = Double.parseDouble(A.getText());
                rightB = Double.parseDouble(B.getText());
                accuracy = Double.parseDouble(chooseAccuracy.getText());
                if (accuracy == 0) {
                    areaResult.setText("Точность не может быть 0");
                    return;
                }
            }catch (Exception e){
                areaResult.setText("Введите все данные для расчёта");
                return;
            }
            if(!iteration.isSelected() && !newton.isSelected()){
                areaResult.setText("Выберите способ расчёта");
                return;
            }
            if (iteration.isSelected()){
                IterationMethod iterationMethod = new IterationMethod(accuracy,leftA,rightB);
                areaResult.setText(iterationMethod.solve());
            }
            if (newton.isSelected()){
                NewtonMethod newtonMethod = new NewtonMethod(accuracy,leftA,rightB);
                areaResult.setText(newtonMethod.solve());
            }

        });
        //CHECKBOX LOGIC
        iteration.setOnAction(event -> {
            if (iteration.isSelected()) {
                newton.setSelected(false);
            }
        });

        newton.setOnAction(event -> {
            if (newton.isSelected()) {
                iteration.setSelected(false);
            }
        });
    }
}
