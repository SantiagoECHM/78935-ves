package com.pasteleria.gui.Menu;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//Panel inicial tras desbloqueo
public class ContenedorGeneral extends BorderPane {
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

    public ContenedorGeneral(Stage stage1){
        //Recibir el stage.
        this.stage=stage1;

        //Contenedores del costado de este menu.
        menuExpandible = new MenuExpandible();
        barraTema = new HBox();

        //Elementos que corresponden al caso de uso del tema.
        imvTemaIcon = new ImageView("SwitchOff.png");
        imvTemaIcon.setFitHeight(30);
        imvTemaIcon.setFitWidth(30);

        imvTemaIconOn = new ImageView("SwitchOn.png");
        imvTemaIconOn.setFitWidth(30);
        imvTemaIconOn.setFitHeight(30);
        btnTema = new Button("");
        btnTema.setGraphic(imvTemaIcon); //Default
        lblTema = new Label("Tema oscuro desactivado");


        //Manejador de pestañas de Issac xd, este va en el centro
        Tab tab = new Tab("Apps");
        StackPane layout = new StackPane();

        LanzadorSecciones aplicaciones = new LanzadorSecciones(tabPane, tab,stage, valorColor);
        layout.getChildren().add(aplicaciones);
        tab.setContent(layout);
        //tab.setClosable(false); Si esta linea no es util hoy, quizas mañana
        tabPane.getTabs().add(tab);

        Tab newTab = new Tab("+");
        tabPane.getTabs().add(newTab);

        newTab.setOnSelectionChanged(e->{
            Tab nuevoTab = new Tab("Apps"+tabPane.getTabs().size());
            //StackPane nuevoLayout = new StackPane(); Este tiene variacion con su predecesor
            LanzadorSecciones nuevasAplicaciones = new LanzadorSecciones(tabPane, nuevoTab, stage, valorColor);
            nuevoTab.setContent(nuevasAplicaciones);
            //Manejo de numeros
            tabPane.getTabs().add(tabPane.getTabs().size()-1, nuevoTab);
            tabPane.getSelectionModel().select(tabPane.getTabs().size()-2);
        });

        btnTema.setOnAction(evtm->{
            cambiarTema();
        });

        //Acomodo
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
