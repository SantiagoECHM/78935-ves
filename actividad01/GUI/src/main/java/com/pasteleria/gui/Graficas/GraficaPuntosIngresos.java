package com.pasteleria.gui.Graficas;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
public class GraficaPuntosIngresos extends BorderPane {
    public GraficaPuntosIngresos(Stage Stage) {
        //Creating the X and Y axes
        NumberAxis xAxis = new NumberAxis(10, 26, 2);
        CategoryAxis yAxis = new CategoryAxis();
        //Setting labels to the axes
        yAxis.setLabel("Años");
        xAxis.setLabel("Num");
        //Creating the Scatter chart
        ScatterChart puntos = new ScatterChart(xAxis, yAxis);
        //Preparing data for the scatter chart
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data(1142, "año 1"));
        series.getData().add(new XYChart.Data(1164, "año 2"));
        series.getData().add(new XYChart.Data(1194, "año 3"));
        series.getData().add(new XYChart.Data(1522, "año 4"));
        series.getData().add(new XYChart.Data(1850, "año 5"));
        series.getData().add(new XYChart.Data(2211, "año 6"));
        series.getData().add(new XYChart.Data(19.4, "año 7"));
        series.getData().add(new XYChart.Data(25.1, "año 8"));
        series.getData().add(new XYChart.Data(23.4, "año 9"));
        series.getData().add(new XYChart.Data(18.1, "año 10"));
        series.getData().add(new XYChart.Data(22.6, "año 11"));
        series.getData().add(new XYChart.Data(17.2, "año 12"));
        //Setting the data to scatter chart
        puntos.getData().addAll(series);
        //Setting title to the scatter chart
        //Setting name to the series
        series.setName("Grafica de puntos de Ingresos");
        //Creating a stack pane to hold the chart
        StackPane root = new StackPane(puntos);
        //Setting the Scene
        Scene scene = new Scene(root, 800, 600);
        Stage.setTitle("Grafica de puntos");
        Stage.setScene(scene);
        Stage.show();
    }
}