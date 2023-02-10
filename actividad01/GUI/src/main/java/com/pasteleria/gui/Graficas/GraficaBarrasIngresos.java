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

public class GraficaBarrasIngresos extends BorderPane {


        public GraficaBarrasIngresos(Stage Stage) {


            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Meses");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Ingresos");

            BarChart chart = new BarChart(xAxis, yAxis);
            chart.setTitle("Gráfica de Ingresos mensuales");
            chart.setData(getDataHSeries());

            StackPane root = new StackPane(chart);
            //root.getChildren().add(chart);

            Scene scene = new Scene(root, 800, 600);

            Stage.setTitle("Gráfica de barras");
            Stage.setScene(scene);
            Stage.show();
        }

        public static ObservableList<XYChart.Series<String, Number>> getDataHSeries() {
            //DaoLenguaje daol = new DaoLenguaje();
            //List<VentaEntidad> venta = daol.mostrarIngresos();
            XYChart.Series<String, Number> autos = new XYChart.Series<>();
            autos.setName("Ingresos");

                    autos.getData().addAll(

                            new XYChart.Data<>("Enero", 1258),
                            new XYChart.Data<>("Febrero", 938),
                            new XYChart.Data<>("Marzo", 1154),
                            new XYChart.Data<>("Abril", 1883),
                            new XYChart.Data<>("Mayo", 1550),
                            new XYChart.Data<>("Junio", 1361),
                            new XYChart.Data<>("Julio", 843),
                            new XYChart.Data<>("Agosto", 792),
                            new XYChart.Data<>("Septiembre", 1112),
                            new XYChart.Data<>("Octubre", 858),
                            new XYChart.Data<>("Noviembre", 735),
                            new XYChart.Data<>("Diciembre", 944));

            System.out.println(autos);
            ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
            data.addAll(autos);
            System.out.println(data);

            return data;
        }
}


