package ru.projects.methods.TASK_06;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;

public class Controller {
    @FXML
    public Button findRoot;
    @FXML
    public Button drawGraph;


    @FXML
    public CheckBox iteration;
    @FXML
    public CheckBox newton;
    @FXML
    public TextField chooseAccuracy;
    @FXML
    public static ScatterChart<Number, Number> scatter;


    public void initialize() {

        drawGraph.setOnMouseClicked(mouseEvent -> {
            scatter = new ScatterChart<>(new NumberAxis(), new NumberAxis());

            XYChart.Series<Number, Number> FIRST = new XYChart.Series<>();

            for (double y = -1.3; y < 2.3; y += 0.01) {
                FIRST.getData().add(new XYChart.Data<>(Math.sqrt((y - y * y + 3) / 3), y));
            }
            FIRST.setName("3x^2 - y + y^2 - 3 = 0");

            XYChart.Series<Number, Number> SECOND = new XYChart.Series<>();

            for (double y = -2; y < 3; y += 0.01) {
                SECOND.getData().add(new XYChart.Data<>(Math.sqrt(y + 3) - 1, y));
            }
            SECOND.setName("x - √(y + 3) + 1 = 0");

            scatter.getData().addAll(FIRST, SECOND);
            Scene scene = new Scene(scatter);
            Stage newWindow = new Stage();
            newWindow.setTitle("Second Stage");
            newWindow.setScene(scene);
            newWindow.show();

        });

        //CHECKBOX LOGIC
        iteration.setOnAction(event ->

        {
            if (iteration.isSelected()) {
                newton.setSelected(false);
            }
        });

        newton.setOnAction(event ->

        {
            if (newton.isSelected()) {
                iteration.setSelected(false);
            }
        });
    }
}
