package com.pasteleria.gui.Menu;

import com.pasteleria.gui.Graficas.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuGraficas extends BorderPane {
    Background fondoMenuGraficas;
    TilePane contenedorIconos = null;

    Label etiGraficaBarras;
    Label descGraficaBarras;
    Label etiGraficaBarras2;
    Label descGraficaBarras2;
    Label etiGraficaBarras3;
    Label descGraficaBarras3;
    Label etiGraficaPuntos;
    Label descGraficaPuntos;
    Label etiGraficaPastel;
    Label descGraficaPastel;

    VBox contGraficasBarras;
    VBox contGraficasBarras2;
    VBox contGraficasBarras3;
    VBox contGraficasPuntos;
    VBox contGraficasPastel;

    ImageView imvGraficaPuntos;
    ImageView imvGraficaPastel;
    ImageView imvGraficaBarras;
    ImageView imvGraficaBarras2;
    ImageView imvGraficaBarras3;

    Button btnGraficasBarras;
    Button btnGraficasBarras2;
    Button btnGraficasBarras3;
    Button btnGraficasPastel;
    Button btnGraficasPuntos;

    TabPane tabPane;
    Tab tab;
    String varColor;

    public MenuGraficas(TabPane tabPane, Tab tab, String color){
        this.tabPane = tabPane;
        this.tab = tab;
        this.varColor = color;
        inicializarComponentes(varColor);
    }

    private void inicializarComponentes(String color) {
        fondoMenuGraficas = new Background(new BackgroundFill(Color.valueOf("F0E3F0"), new CornerRadii(0), new Insets(0)));
        contenedorIconos = new TilePane();
        contenedorIconos.setVgap(12);
        contenedorIconos.setHgap(12);
        contenedorIconos.setPadding(new Insets(10));
        contenedorIconos.setPrefColumns(3);

        //Contenedor de imagenes
        imvGraficaBarras = new ImageView("barras.png");
        imvGraficaBarras.setFitWidth(200);
        imvGraficaBarras.setFitHeight(200);

        imvGraficaBarras2 = new ImageView("barras.png");
        imvGraficaBarras2.setFitWidth(200);
        imvGraficaBarras2.setFitHeight(200);

        imvGraficaBarras3 = new ImageView("barras.png");
        imvGraficaBarras3.setFitWidth(200);
        imvGraficaBarras3.setFitHeight(200);

        imvGraficaPastel = new ImageView("pastel.png");
        imvGraficaPastel.setFitWidth(200);
        imvGraficaPastel.setFitHeight(200);

        imvGraficaPuntos = new ImageView("puntos.png");
        imvGraficaPuntos.setFitWidth(200);
        imvGraficaPuntos.setFitHeight(200);

        //Etiquetas
        etiGraficaBarras = new Label("Grafica de barras ingresos mensuales");
        etiGraficaBarras2 = new Label("Gráfica de numero de pedidos mensuales");
        etiGraficaBarras3 = new Label("Grafica de barras de ventas mensuales");
        etiGraficaPastel = new Label("Grafica de pastel de trabajadores");
        etiGraficaPuntos = new Label("Grafica de puntos de ingresos anuales");

        //Botones
        btnGraficasBarras = new Button("");
        btnGraficasBarras.setGraphic(imvGraficaBarras);
        btnGraficasBarras.setMaxWidth(200);
        btnGraficasBarras.setMinWidth(200);
        btnGraficasBarras.setOnAction(e -> {
            GraficaBarrasIngresos graficaBarrasI = new GraficaBarrasIngresos(new Stage());
        });

        btnGraficasBarras2 = new Button("");
        btnGraficasBarras2.setGraphic(imvGraficaBarras2);
        btnGraficasBarras2.setMaxWidth(200);
        btnGraficasBarras2.setMinWidth(200);
        btnGraficasBarras2.setOnAction(e -> {
            GraficaBarrasPedidos graficaBarrasP = new GraficaBarrasPedidos(new Stage());
        });

        btnGraficasBarras3 = new Button("");
        btnGraficasBarras3.setGraphic(imvGraficaBarras3);
        btnGraficasBarras3.setMaxWidth(200);
        btnGraficasBarras3.setMinWidth(200);
        btnGraficasBarras3.setOnAction(e -> {
            GraficaBarrasVentas graficaBarrasV = new GraficaBarrasVentas(new Stage());
        });

        btnGraficasPastel = new Button("");
        btnGraficasPastel.setGraphic(imvGraficaPastel);
        btnGraficasPastel.setMaxWidth(200);
        btnGraficasPastel.setMinWidth(200);
        btnGraficasPastel.setOnAction(e ->{
            GraficaPastelTrabajadores graficaPastel = new GraficaPastelTrabajadores(new Stage());
        });

        btnGraficasPuntos = new Button("");
        btnGraficasPuntos.setGraphic(imvGraficaPuntos);
        btnGraficasPuntos.setMaxWidth(200);
        btnGraficasPuntos.setMinWidth(200);
        btnGraficasPuntos.setOnAction(e->{
            GraficaPuntosIngresos graficaPuntos = new GraficaPuntosIngresos(new Stage());
        });

        contGraficasBarras = new VBox();
        contGraficasBarras.setAlignment(Pos.TOP_CENTER);
        contGraficasBarras.getChildren().addAll(btnGraficasBarras, etiGraficaBarras);

        contGraficasBarras2 = new VBox();
        contGraficasBarras2.setAlignment(Pos.TOP_CENTER);
        contGraficasBarras2.getChildren().addAll(btnGraficasBarras2, etiGraficaBarras2);

        contGraficasBarras3 = new VBox();
        contGraficasBarras3.setAlignment(Pos.TOP_CENTER);
        contGraficasBarras3.getChildren().addAll(btnGraficasBarras3, etiGraficaBarras3);

        contGraficasPastel = new VBox();
        contGraficasPastel.setAlignment(Pos.TOP_CENTER);
        contGraficasPastel.getChildren().addAll(btnGraficasPastel, etiGraficaPastel);

        contGraficasPuntos = new VBox();
        contGraficasPuntos.setAlignment(Pos.TOP_CENTER);
        contGraficasPuntos.getChildren().addAll(btnGraficasPuntos, etiGraficaPuntos);

        contenedorIconos.setAlignment(Pos.CENTER);
        contenedorIconos.getChildren().addAll(contGraficasBarras, contGraficasPastel, contGraficasBarras2, contGraficasPuntos, contGraficasBarras3);
        setBackground(fondoMenuGraficas);
        setCenter(contenedorIconos);
    }


}
