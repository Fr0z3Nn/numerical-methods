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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.projects.methods.TASK_13_14.euler.Euler;
import ru.projects.methods.TASK_13_14.euler.EulerTable;
import ru.projects.methods.TASK_13_14.euler_koshi.EulerKoshi;
import ru.projects.methods.TASK_13_14.euler_koshi.EulerKoshiTable;

import java.util.ArrayList;


public class MainController {
    @FXML
    public Button graph13;
    @FXML
    public Button euler13;
    @FXML
    public Button eulerkoshi13;

    @FXML
    public void initialize() {

        ArrayList<EulerKoshiTable> eulerKoshiListH1 = new EulerKoshi(0.1).createCells();
        ArrayList<EulerKoshiTable> eulerKoshiListH2 = new EulerKoshi(0.05).createCells();
        ArrayList<EulerKoshiTable> eulerKoshiList = new ArrayList<>();
        eulerKoshiList.addAll(eulerKoshiListH1);
        eulerKoshiList.addAll(eulerKoshiListH2);

        ArrayList<EulerTable> eulerListH1 = new Euler(0.1).createCells();
        ArrayList<EulerTable> eulerListH2 = new Euler(0.05).createCells();
        ArrayList<EulerTable> eulerList = new ArrayList<>();
        eulerList.addAll(eulerListH1);
        eulerList.addAll(eulerListH2);

        graph13.setOnMouseClicked(event -> {
            AreaChart<Number, Number> areaChart = new AreaChart<>(new NumberAxis(), new NumberAxis());

            XYChart.Series<Number, Number> seriesFunc = new XYChart.Series<>();
            XYChart.Series<Number, Number> seriesEuler = new XYChart.Series<>();
            XYChart.Series<Number, Number> seriesEulerKoshi = new XYChart.Series<>();

            for (int x = 0; x <= eulerListH1.size() - 1; x++) {
                seriesFunc.getData().add(new XYChart.Data<>(eulerListH1.get(x).getX(), eulerListH1.get(x).getIstY()));
                seriesEuler.getData().add(new XYChart.Data<>(eulerListH1.get(x).getX(), eulerListH1.get(x).getY()));
                seriesEulerKoshi.getData().add(new XYChart.Data<>(eulerKoshiListH1.get(x).getX(), eulerKoshiListH1.get(x).getY()));
            }


            seriesFunc.setName("FUNC_IST");
            seriesEuler.setName("EULER");
            seriesEulerKoshi.setName("EULER-KOSHI");
            areaChart.getData().setAll(seriesFunc, seriesEuler,seriesEulerKoshi);
            Scene scene = new Scene(areaChart);
            Stage newWindow = new Stage();
            newWindow.setTitle("GRAPHICS");
            newWindow.setScene(scene);
            newWindow.show();
        });


        euler13.setOnMouseClicked(event -> {

            TableView<EulerTable> table = new TableView<>();

            TableColumn<EulerTable, Integer> iColumn = new TableColumn<>("i");
            TableColumn<EulerTable, Integer> hColumn = new TableColumn<>("h");
            TableColumn<EulerTable, Double> xColumn = new TableColumn<>("x");
            TableColumn<EulerTable, Double> yColumn = new TableColumn<>("y");
            TableColumn<EulerTable, Double> zColumn = new TableColumn<>("z");
            TableColumn<EulerTable, String> delta_zColumn = new TableColumn<>("delta_z");
            TableColumn<EulerTable, String> delta_yColumn = new TableColumn<>("delta_y");
            TableColumn<EulerTable, Double> istYColumn = new TableColumn<>("istY");
            TableColumn<EulerTable, Double> accuracyColumn = new TableColumn<>("accuracy");

            iColumn.setCellValueFactory(new PropertyValueFactory<>("i"));
            hColumn.setCellValueFactory(new PropertyValueFactory<>("h"));
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

            table.getColumns().addAll(iColumn, hColumn, xColumn, yColumn, zColumn, delta_zColumn, delta_yColumn, istYColumn, accuracyColumn);

            StackPane root = new StackPane();
            root.setPadding(new Insets(5));
            root.getChildren().add(table);

            Scene scene = new Scene(root);
            Stage newWindow = new Stage();

            newWindow.setTitle("Эйлера таблица");
            newWindow.setScene(scene);
            newWindow.show();

            /////RYNGE ROBERG

            StringBuilder res = new StringBuilder();

            for (int i = 0; i <= eulerListH1.size() - 1; i++) {

                double F1 = eulerListH1.get(i).getY();
                double F2 = eulerListH2.get(2*i).getY();
                double RR = F2 + ((F2 - F1) / (Math.pow(2, 2) - 1));
                res.append(String.format("x - %.3f  y - %.3f    acc - %.3f\n", eulerList.get(i).getX(), RR, Math.abs(RR - eulerListH1.get(i).getIstY())));

            }


            TextArea textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(10);

            textArea.setText(res.toString());

            Scene scene1 = new Scene(textArea);
            Stage newWindow1 = new Stage();
            newWindow1.setTitle("Rynge-Roberg");
            newWindow1.setScene(scene1);
            newWindow1.show();
        });

        eulerkoshi13.setOnMouseClicked(event -> {

            TableView<EulerKoshiTable> table = new TableView<>();

            TableColumn<EulerKoshiTable, Integer> iColumn = new TableColumn<>("i");
            TableColumn<EulerKoshiTable, Integer> hColumn = new TableColumn<>("h");
            TableColumn<EulerKoshiTable, Double> xColumn = new TableColumn<>("x");
            TableColumn<EulerKoshiTable, Double> yColumn = new TableColumn<>("y");
            TableColumn<EulerKoshiTable, Double> zColumn = new TableColumn<>("z");
            TableColumn<EulerKoshiTable, Double> yrColumn = new TableColumn<>("yr");
            TableColumn<EulerKoshiTable, Double> zrColumn = new TableColumn<>("zr");
            TableColumn<EulerKoshiTable, String> delta_zColumn = new TableColumn<>("delta_z");
            TableColumn<EulerKoshiTable, String> delta_yColumn = new TableColumn<>("delta_y");
            TableColumn<EulerKoshiTable, Double> istYColumn = new TableColumn<>("istY");
            TableColumn<EulerKoshiTable, Double> accuracyColumn = new TableColumn<>("accuracy");

            iColumn.setCellValueFactory(new PropertyValueFactory<>("i"));
            hColumn.setCellValueFactory(new PropertyValueFactory<>("h"));
            xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
            yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
            zColumn.setCellValueFactory(new PropertyValueFactory<>("z"));
            yrColumn.setCellValueFactory(new PropertyValueFactory<>("yr"));
            zrColumn.setCellValueFactory(new PropertyValueFactory<>("zr"));
            delta_zColumn.setCellValueFactory(new PropertyValueFactory<>("delta_z"));
            delta_yColumn.setCellValueFactory(new PropertyValueFactory<>("delta_y"));
            istYColumn.setCellValueFactory(new PropertyValueFactory<>("istY"));
            accuracyColumn.setCellValueFactory(new PropertyValueFactory<>("accuracy"));

            ObservableList<EulerKoshiTable> list = FXCollections.observableArrayList();
            list.setAll(eulerKoshiList);
            table.setItems(list);

            table.getColumns().addAll(iColumn, hColumn, xColumn, yColumn, zColumn, yrColumn,zrColumn, delta_zColumn, delta_yColumn, istYColumn, accuracyColumn);

            StackPane root = new StackPane();
            root.setPadding(new Insets(5));
            root.getChildren().add(table);

            Scene scene = new Scene(root);
            Stage newWindow = new Stage();

            newWindow.setTitle("Эйлера-Коши таблица");
            newWindow.setScene(scene);
            newWindow.show();

            /////RYNGE ROBERG

            StringBuilder res = new StringBuilder();

            for (int i = 0; i <= eulerKoshiListH1.size() - 1; i++) {

                double F1 = eulerKoshiListH1.get(i).getY();
                System.out.println(F1);
                double F2 = eulerKoshiListH2.get(2*i).getY();
                System.out.println(F2);
                double RR = F2 + ((F2 - F1) / (Math.pow(2, 3) - 1));
                res.append(String.format("x - %.3f  y - %.3f    acc - %.3f\n", eulerKoshiListH1.get(i).getX(), RR, Math.abs(RR - eulerKoshiListH1.get(i).getIstY())));

            }

            TextArea textArea = new TextArea();
            textArea.setPrefColumnCount(50);
            textArea.setPrefRowCount(10);

            textArea.setText(res.toString());

            Scene scene1 = new Scene(textArea);
            Stage newWindow1 = new Stage();
            newWindow1.setTitle("Rynge-Roberg");
            newWindow1.setScene(scene1);
            newWindow1.show();
        });
        }
}
