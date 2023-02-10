package com.pasteleria.gui.Menu;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ContenedorGeneralAdmin extends BorderPane {
    //Componentes generales
    HBox barraTema;
    MenuExpandible menuExpandible;

    TabPane tabPane = new TabPane();
    Stage stage;

    Button btnTema;
    ImageView imvTemaIcon;
    ImageView imvTemaIconOn;
    Label lblTema;
    boolean temaOscuro = false;
    String valorColor="F0E3F0";

    public ContenedorGeneralAdmin(Stage stage1){
        //Recibir stage
        this.stage=stage1;

        //Contenedores extra
        menuExpandible = new MenuExpandible();
        barraTema = new HBox();

        //Elementos para el tema
        imvTemaIcon = new ImageView("SwitchOff.png");
        imvTemaIcon.setFitHeight(30);
        imvTemaIcon.setFitWidth(30);

        imvTemaIconOn = new ImageView("SwitchOn.png");
        imvTemaIconOn.setFitWidth(30);
        imvTemaIconOn.setFitHeight(30);
        btnTema = new Button("");
        btnTema.setGraphic(imvTemaIcon); //Default
        lblTema = new Label("Tema oscuro desactivado");

        //Manejador de pestañas estilo navegador web
        Tab tab = new Tab("Panel de control");
        StackPane layout = new StackPane();

        LanzadorSeccionesAdmin aplicaciones = new LanzadorSeccionesAdmin(tabPane, tab,stage, valorColor);
        layout.getChildren().add(aplicaciones);
        tab.setContent(layout);
        //tab.setClosable(false);
        tabPane.getTabs().add(tab);

        Tab newTab = new Tab("+");
        tabPane.getTabs().add(newTab);

        newTab.setOnSelectionChanged(e->{
            Tab nuevoTab = new Tab("Apps"+tabPane.getTabs().size());
            StackPane nuevoLayout = new StackPane();
            LanzadorSeccionesAdmin nuevasAplicaciones = new LanzadorSeccionesAdmin(tabPane, nuevoTab, stage, valorColor);
            nuevoTab.setContent(nuevasAplicaciones);
            tabPane.getTabs().add(tabPane.getTabs().size()-1, nuevoTab);
            tabPane.getSelectionModel().select(tabPane.getTabs().size()-2);
        });

        btnTema.setOnAction(evtm->{
            cambiarTema();
        });

        //Organizacion
        barraTema.getChildren().addAll(btnTema, lblTema);
        setLeft(menuExpandible);
        setBottom(barraTema);
        setCenter(tabPane);
    }

    private void manejoSalir(){
        Alert alertaSalir = new Alert(Alert.AlertType.CONFIRMATION);
        alertaSalir.setTitle("Advertencia");
        alertaSalir.setContentText("¿Esta seguro que quiere salir?");
        ButtonType respuesta = alertaSalir.showAndWait().orElse(ButtonType.OK);
        if (ButtonType.OK.equals(respuesta))
            System.exit(0);
    }

    private void cambiarTema(){
        if (!temaOscuro){
            btnTema.setGraphic(imvTemaIcon);
            lblTema.setText("Tema oscuro desactivado");
            valorColor = "F0E3F0";
        }else{
            btnTema.setGraphic(imvTemaIconOn);
            lblTema.setText("Tema oscuro activado");
            valorColor = "#5e65a1";
        }
        temaOscuro = !temaOscuro;
    }
}
