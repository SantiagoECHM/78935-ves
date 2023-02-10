package com.pasteleria.gui.Menu;


import com.pasteleria.gui.Proveedor.PantallaProveedor;
import com.pasteleria.gui.BD.PantallaExportacion;
import com.pasteleria.gui.Trabajador.PantallaTrabajador;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LanzadorSeccionesAdmin extends BorderPane {
    Background fondoDashboard;
    TilePane contenedorIconos = null;

    Font tipoLetraTitulo;
    Font tipoLetraDescripcion;

    Label etiEntregas;
    Label descEntregas;

    Label etiMercancia;
    Label descMercancia;

    Label etiProveedor;
    Label descProveedor;

    Label etiDirecciones;
    Label descDirecciones;

    Label etiBD;
    Label descBD;

    Label etiGraficas;
    Label descGraficas;

    VBox contBD;
    VBox contEntregas;
    VBox contMercancias;
    VBox contProveedores;
    VBox contDirecciones;
    VBox contGraficas;

    ImageView imvEntregas;
    ImageView imvMercancias;
    ImageView imvProveedores;
    ImageView imvDirecciones;
    ImageView imvBD;
    ImageView imvGraficas;

    Button btnEntregas;
    Button btnMercancias;
    Button btnProveedores;
    Button btnDirecciones;
    Button btnExportarBD;
    Button btnGraficas;

    TabPane tabPane;
    Tab tab;
    Stage stage;

    String valCol;

    public LanzadorSeccionesAdmin(){
        inicializarComponentes(tabPane, tab);
    }

    public LanzadorSeccionesAdmin(TabPane tabPane, Tab tab, Stage stage){
        this.stage=stage;
        this.tabPane = tabPane;
        this.tab = tab;
        inicializarComponentes(tabPane, tab);
    }

    public LanzadorSeccionesAdmin(TabPane tabPane, Tab tab, Stage stage, String color){
        this.valCol = color;
        this.stage=stage;
        this.tabPane = tabPane;
        this.tab = tab;
        inicializarComponentes(tabPane, tab, valCol);
    }

    public void inicializarComponentes(TabPane tabPane, Tab tab){
        //getStylesheets().add(getClass().getResource("gui/styles.css").toExternalForm());
        //Muestra del grid
        contenedorIconos = new TilePane();
        contenedorIconos.setVgap(12);
        contenedorIconos.setHgap(12);
        contenedorIconos.setPadding(new Insets(10));
        contenedorIconos.setPrefColumns(3);

        tipoLetraTitulo = Font.font("Poppins", FontWeight.BOLD, 16);
        tipoLetraDescripcion = Font.font("NanumGothic", 12);

        //Imagenes
        imvEntregas = new ImageView("Entrega.png");
        imvEntregas.setFitWidth(200);
        imvEntregas.setFitHeight(200);

        imvMercancias = new ImageView("Inventario.png");
        imvMercancias.setFitWidth(200);
        imvMercancias.setFitHeight(200);

        imvProveedores = new ImageView("Agenda.png");
        imvProveedores.setFitWidth(200);
        imvProveedores.setFitHeight(200);

        imvDirecciones = new ImageView("Location.png");
        imvDirecciones.setFitWidth(200);
        imvDirecciones.setFitHeight(200);

        imvBD = new ImageView("Database.png");
        imvBD.setFitWidth(200);
        imvBD.setFitHeight(200);

        imvGraficas = new ImageView("Graficas.png");
        imvGraficas.setFitWidth(200);
        imvGraficas.setFitHeight(200);

        //Etiquetas
        etiEntregas = new Label("Entregas");
        descEntregas = new Label("Ver pedidos que se van a enviar");

        etiMercancia=new Label("Mercancias");
        descMercancia=new Label("Ver los productos disponibles");

        etiProveedor=new Label("Proveedores");
        descProveedor=new Label ("Directorio de socios");

        etiDirecciones=new Label("Direcciones");
        descDirecciones=new Label("Direcciones de empresas");

        etiBD = new Label("Exportar BD");
        descBD = new Label("Crear un respaldo en Excel");

        etiGraficas = new Label("Graficas");
        descGraficas = new Label ("Mostar graficas");

        //Botones
        //Cada onAction debe de mandar al nuevo contenedor donde se encuentra el panel.
        btnEntregas = new Button("");
        btnEntregas.setGraphic(imvEntregas);
        btnEntregas.setMaxWidth(200);
        btnEntregas.setMinWidth(200);
        btnEntregas.getStyleClass().add("btnLanzador");
        btnEntregas.setOnAction(e ->{
            tab.setText("Entregas");
        });

        btnMercancias = new Button("");
        btnMercancias.setGraphic(imvMercancias);
        btnMercancias.setMaxWidth(200);
        btnMercancias.setMinWidth(200);
        btnMercancias.getStyleClass().add("btnLanzador");
        btnMercancias.setOnAction(e ->{
//            PantallaTrabajador panelTrabajadores = new PantallaTrabajador(tabPane, tab);
//            tab.setContent(panelTrabajadores);
            tab.setText("Mercancias");
        });

        btnProveedores = new Button("");
        btnProveedores.setGraphic(imvProveedores);
        btnProveedores.setMaxWidth(200);
        btnProveedores.setMinWidth(200);
        btnProveedores.getStyleClass().add("btnLanzador");
        btnProveedores.setOnAction(evtm->{
            PantallaProveedor pantallaProveedor = new PantallaProveedor(tabPane, tab);
            tab.setContent(pantallaProveedor);
            tab.setText("Proveedores");
        });

        btnDirecciones = new Button("");
        btnDirecciones.setGraphic(imvDirecciones);
        btnDirecciones.setMaxWidth(200);
        btnDirecciones.setMinWidth(200);
        btnDirecciones.getStyleClass().add("btnLanzador");
        btnDirecciones.setOnAction(e->{
            tab.setText("Direcciones");
        });

        btnExportarBD = new Button("");
        btnExportarBD.setGraphic(imvBD);
        btnExportarBD.setMaxWidth(200);
        btnExportarBD.setMinWidth(200);
        btnExportarBD.getStyleClass().add("btnLanzador");
        btnExportarBD.setOnAction(e->{

        });

        btnGraficas = new Button("");
        btnGraficas.setGraphic(imvGraficas);
        btnGraficas.setMinWidth(200);
        btnGraficas.setMaxWidth(200);
        btnGraficas.setOnAction(e->{
            //MenuGraficas menuGraficas = new MenuGraficas(tabPane, tab);
            //tab.setContent(menuGraficas);
            //tab.setText("Graficas");
        });

        contEntregas = new VBox();
        contEntregas.setAlignment(Pos.TOP_CENTER);
        contEntregas.getChildren().addAll(btnEntregas, etiEntregas, descEntregas);

        contMercancias = new VBox();
        contMercancias.setAlignment(Pos.TOP_CENTER);
        contMercancias.getChildren().addAll(btnMercancias, etiMercancia, descMercancia);

        contProveedores = new VBox();
        contProveedores.setAlignment(Pos.TOP_CENTER);
        contProveedores.getChildren().addAll(btnProveedores, etiProveedor, descProveedor);

        contDirecciones = new VBox();
        contDirecciones.setAlignment(Pos.TOP_CENTER);
        contDirecciones.getChildren().addAll(btnDirecciones, etiDirecciones, descDirecciones);

        contenedorIconos.setAlignment(Pos.CENTER);
        contenedorIconos.getChildren().addAll(contEntregas, contMercancias, contProveedores, contDirecciones);
        setBackground(fondoDashboard);
        setCenter(contenedorIconos);
    }

    public void inicializarComponentes(TabPane tabPane, Tab tab, String color){
        fondoDashboard = new Background(new BackgroundFill(Color.valueOf(color), new CornerRadii(0), new Insets(0)));
        //Muestra del grid
        contenedorIconos = new TilePane();
        contenedorIconos.setVgap(12);
        contenedorIconos.setHgap(12);
        contenedorIconos.setPadding(new Insets(10));
        contenedorIconos.setPrefColumns(3);

        tipoLetraTitulo = Font.font("Poppins", FontWeight.BOLD, 16);
        tipoLetraDescripcion = Font.font("NanumGothic", 12);

        //Imagenes
        imvEntregas = new ImageView("Entrega.png");
        imvEntregas.setFitWidth(200);
        imvEntregas.setFitHeight(200);

        imvMercancias = new ImageView("Inventario.png");
        imvMercancias.setFitWidth(200);
        imvMercancias.setFitHeight(200);

        imvProveedores = new ImageView("Agenda.png");
        imvProveedores.setFitWidth(200);
        imvProveedores.setFitHeight(200);

        imvDirecciones = new ImageView("Location.png");
        imvDirecciones.setFitWidth(200);
        imvDirecciones.setFitHeight(200);

        imvBD = new ImageView("Database.png");
        imvBD.setFitWidth(200);
        imvBD.setFitHeight(200);

        imvGraficas = new ImageView("Graficas.png");
        imvGraficas.setFitWidth(200);
        imvGraficas.setFitHeight(200);

        //Etiquetas
        etiEntregas = new Label("Entregas");
        descEntregas = new Label("Ver pedidos que se van a enviar");

        etiMercancia=new Label("Mercancias");
        descMercancia=new Label("Ver los productos disponibles");

        etiProveedor=new Label("Proveedores");
        descProveedor=new Label ("Directorio de socios");

        etiDirecciones=new Label("Direcciones");
        descDirecciones=new Label("Direcciones de empresas");

        etiBD = new Label("Exportar BD");
        descBD = new Label("Crear un respaldo en Excel");

        etiGraficas = new Label("Graficas");
        descGraficas = new Label ("Mostar graficas");

        //Botones
        //Cada onAction debe de mandar al nuevo contenedor donde se encuentra el panel.
        btnEntregas = new Button("");
        btnEntregas.setGraphic(imvEntregas);
        btnEntregas.setMaxWidth(200);
        btnEntregas.setMinWidth(200);
        btnEntregas.getStyleClass().add("btnLanzador");
        btnEntregas.setOnAction(e ->{
            tab.setText("Entregas");
        });

        btnMercancias = new Button("");
        btnMercancias.setGraphic(imvMercancias);
        btnMercancias.setMaxWidth(200);
        btnMercancias.setMinWidth(200);
        btnMercancias.getStyleClass().add("btnLanzador");
        btnMercancias.setOnAction(e ->{
            PantallaTrabajador panelTrabajadores = new PantallaTrabajador(tabPane, tab);
            tab.setContent(panelTrabajadores);
            tab.setText("Mercancias");
        });

        btnProveedores = new Button("");
        btnProveedores.setGraphic(imvProveedores);
        btnProveedores.setMaxWidth(200);
        btnProveedores.setMinWidth(200);
        btnProveedores.getStyleClass().add("btnLanzador");
        btnProveedores.setOnAction(e->{
            tab.setText("Proveedores");
            PantallaProveedor pantallaProveedor = new PantallaProveedor(tabPane, tab);
            tab.setContent(pantallaProveedor);
        });

        btnDirecciones = new Button("");
        btnDirecciones.setGraphic(imvDirecciones);
        btnDirecciones.setMaxWidth(200);
        btnDirecciones.setMinWidth(200);
        btnDirecciones.getStyleClass().add("btnLanzador");
        btnDirecciones.setOnAction(e->{
            tab.setText("Direcciones");
        });

        btnExportarBD = new Button("");
        btnExportarBD.setGraphic(imvBD);
        btnExportarBD.setMaxWidth(200);
        btnExportarBD.setMinWidth(200);
        btnExportarBD.getStyleClass().add("btnLanzador");
        btnExportarBD.setOnAction(e->{
            PantallaExportacion exportacion = new PantallaExportacion(tabPane, tab);
            tab.setContent(exportacion);
            tab.setText("Exportar BD");
        });

        btnGraficas = new Button("");
        btnGraficas.setGraphic(imvGraficas);
        btnGraficas.setMinWidth(200);
        btnGraficas.setMaxWidth(200);
        btnGraficas.setOnAction(e->{
            MenuGraficas menuGraficas = new MenuGraficas(tabPane, tab, color);
            tab.setContent(menuGraficas);
            tab.setText("Graficas");
        });

        contEntregas = new VBox();
        contEntregas.setAlignment(Pos.TOP_CENTER);
        contEntregas.getChildren().addAll(btnEntregas, etiEntregas, descEntregas);

        contMercancias = new VBox();
        contMercancias.setAlignment(Pos.TOP_CENTER);
        contMercancias.getChildren().addAll(btnMercancias, etiMercancia, descMercancia);

        contProveedores = new VBox();
        contProveedores.setAlignment(Pos.TOP_CENTER);
        contProveedores.getChildren().addAll(btnProveedores, etiProveedor, descProveedor);

        contDirecciones = new VBox();
        contDirecciones.setAlignment(Pos.TOP_CENTER);
        contDirecciones.getChildren().addAll(btnDirecciones, etiDirecciones, descDirecciones);

        contGraficas = new VBox();
        contGraficas.setAlignment(Pos.TOP_CENTER);
        contGraficas.getChildren().addAll(btnGraficas, etiGraficas, descGraficas);

        contBD = new VBox();
        contBD.setAlignment(Pos.TOP_CENTER);
        contBD.getChildren().addAll(btnExportarBD, etiBD, descBD);

        contenedorIconos.setAlignment(Pos.CENTER);
        contenedorIconos.getChildren().addAll(contEntregas, contMercancias, contProveedores, contDirecciones, contGraficas, contBD);
        setBackground(fondoDashboard);
        setCenter(contenedorIconos);
    }
}
