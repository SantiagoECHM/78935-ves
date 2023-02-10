package com.pasteleria.gui.Graficas;


import com.pasteleria.MOD.TrabajadorEntidad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class GraficaPastelTrabajadores extends BorderPane {


    public GraficaPastelTrabajadores(Stage Stage){

        DaoLenguaje daol = new DaoLenguaje();
        List<TrabajadorEntidad> trabajador = daol.mostrarTrabajadores();

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        int cont = 2;
        for (TrabajadorEntidad l: trabajador){
            data.add(new PieChart.Data(l.getNombre(), cont));
            cont ++;
        }

        PieChart pie = new PieChart(data);
        pie.setTitle("Grafica de pastel de trabajadores");
        //pie.setLegendSide(Side.LEFT);
        //pie.setTitleSide(Side.BOTTOM);
        //pie.setLabelLineLength(60);
        pie.setLabelsVisible(true);

        StackPane root = new StackPane(pie);
        Scene scene = new Scene(root, 800, 600);

        Stage.setTitle("Grafica de pastel");
        Stage.setScene(scene);
        Stage.show();
    }

}


