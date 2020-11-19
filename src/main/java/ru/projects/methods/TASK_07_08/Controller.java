package ru.projects.methods.TASK_07_08;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    @FXML
    public TextField X1;
    @FXML
    public TextField X2;
    @FXML
    public TextField Y1;
    @FXML
    public TextField Y2;
    @FXML
    public TextArea areaResult;
    @FXML
    public ImageView imageToShow;


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

        findRoot.setOnMouseClicked(event -> {

            if (newton.isSelected()) {
                double accuracy = Double.parseDouble(chooseAccuracy.getText());
                double x1 = Double.parseDouble(X1.getText());
                double x2 = Double.parseDouble(X2.getText());
                double y1 = Double.parseDouble(Y1.getText());
                double y2 = Double.parseDouble(Y2.getText());
                Method  method = new Newton(x1, x2, y1, y2, accuracy);
            Image img = new Image("/img/Newton.png");
                imageToShow.setImage(img);
                areaResult.setText(method.solve());
                }
            if (iteration.isSelected()){
                imageToShow.setImage(null);
                Iteration iteration1 = new Iteration(0.4,1.2,0.4,1.2,1e-10);
                Iteration iteration2 = new Iteration(0,0.6,-1.3,-0.7,1e-10);
                iteration1.solveFirstRoot();
                iteration2.solveSecondRoot();
                areaResult.setText(iteration1.solve());
            }
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
