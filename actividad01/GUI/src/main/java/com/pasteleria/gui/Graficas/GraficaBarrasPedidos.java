package com.pasteleria.gui.Graficas;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GraficaBarrasPedidos extends BorderPane {


    public GraficaBarrasPedidos(Stage Stage) {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Meses");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Numero pedidos");

        BarChart chart = new BarChart(xAxis, yAxis);
        chart.setTitle("Gráfica de numero de pedidos mensuales");
        chart.setData(getDataHSeries());

        StackPane root = new StackPane();
        root.getChildren().add(chart);

        Scene scene = new Scene(root, 640, 427);

        Stage.setTitle("Grafica de barras");
        Stage.setScene(scene);
        Stage.show();
    }

    public static ObservableList<XYChart.Series<String, Number>> getDataHSeries() {

        XYChart.Series<String, Number> autos = new XYChart.Series<>();
        autos.setName("Ingresos");
        autos.getData().addAll(
                new XYChart.Data<>("Enero", 35),
                new XYChart.Data<>("Febrero", 58),
                new XYChart.Data<>("Marzo", 54),
                new XYChart.Data<>("Abril", 38),
                new XYChart.Data<>("Mayo", 50),
                new XYChart.Data<>("Junio", 40),
                new XYChart.Data<>("Julio", 62),
                new XYChart.Data<>("Agosto", 83),
                new XYChart.Data<>("Septiembre", 74),
                new XYChart.Data<>("Octubre", 98),
                new XYChart.Data<>("Noviembre", 23),
                new XYChart.Data<>("Diciembre", 42)
        );

        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
        data.addAll(autos);

        return data;
    }
}
