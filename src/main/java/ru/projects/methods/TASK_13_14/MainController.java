package ru.projects.methods.TASK_13_14;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.projects.methods.TASK_09_10.UtilLagrange;
import ru.projects.methods.TASK_09_10.UtilNewton;

import java.util.ArrayList;

public class MainController {
    @FXML
    public Button graph13;
    @FXML
    public Button euler13;
    @FXML
    public void initialize(){

        ArrayList<EulerTable> eulerList = new Euler().createCells();

        graph13.setOnMouseClicked(event -> {
            AreaChart<Number, Number> areaChart = new AreaChart<>(new NumberAxis(), new NumberAxis());

            XYChart.Series<Number, Number> seriesFunc = new XYChart.Series<>();
            XYChart.Series<Number, Number> seriesEuler = new XYChart.Series<>();

                    for (int x = 0; x < eulerList.size() - 1; x ++) {
                        seriesFunc.getData().add(new XYChart.Data<>(eulerList.get(x).getX(), eulerList.get(x).getIstY()));
                        seriesEuler.getData().add(new XYChart.Data<>(eulerList.get(x).getX(), eulerList.get(x).getY()));
                    }


            seriesFunc.setName("FUNC_IST");
            seriesEuler.setName("EULER");
            areaChart.getData().setAll(seriesFunc, seriesEuler);
            Scene scene = new Scene(areaChart);
            Stage newWindow = new Stage();
            newWindow.setTitle("GRAPHICS");
            newWindow.setScene(scene);
            newWindow.show();
        });


        euler13.setOnMouseClicked(event -> {
            TableView<EulerTable> table = new TableView<>();

            TableColumn<EulerTable, Integer> iColumn = new TableColumn<>("i");
            TableColumn<EulerTable, Double> xColumn = new TableColumn<>("x");
            TableColumn<EulerTable, Double> yColumn = new TableColumn<>("y");
            TableColumn<EulerTable, Double> zColumn = new TableColumn<>("z");
            TableColumn<EulerTable, String> delta_zColumn = new TableColumn<>("delta_z");
            TableColumn<EulerTable, String> delta_yColumn = new TableColumn<>("delta_y");
            TableColumn<EulerTable, Double> istYColumn = new TableColumn<>("istY");
            TableColumn<EulerTable, Double> accuracyColumn = new TableColumn<>("accuracy");

            iColumn.setCellValueFactory(new PropertyValueFactory<>("i"));
            xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
            yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
            zColumn.setCellValueFactory(new PropertyValueFactory<>("z"));
            delta_zColumn.setCellValueFactory(new PropertyValueFactory<>("delta_z"));
            delta_yColumn.setCellValueFactory(new PropertyValueFactory<>("delta_y"));
            istYColumn.setCellValueFactory(new PropertyValueFactory<>("istY"));
            accuracyColumn.setCellValueFactory(new PropertyValueFactory<>("accuracy"));

            ObservableList<EulerTable> list = FXCollections.observableArrayList();
            list.setAll(eulerList);
            table.setItems(list);

            table.getColumns().addAll(iColumn,xColumn,yColumn,zColumn,delta_zColumn,delta_yColumn,istYColumn,accuracyColumn);

            StackPane root = new StackPane();
            root.setPadding(new Insets(5));
            root.getChildren().add(table);

            Scene scene = new Scene(root);
            Stage newWindow = new Stage();

            newWindow.setTitle("Эйлера таблица");
            newWindow.setScene(scene);
            newWindow.show();
        });
    }
}
