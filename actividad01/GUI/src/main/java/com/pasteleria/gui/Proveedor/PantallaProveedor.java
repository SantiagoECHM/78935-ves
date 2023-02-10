package com.pasteleria.gui.Proveedor;

import com.pasteleria.MOD2.CONT.ProveedorController;
import com.pasteleria.MOD2.MOD.EntidadProveedor;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PantallaProveedor extends BorderPane {
    private Label lTitulo;
    private Button bAgregarProveedor;
    private HBox barra;
    private TableView<EntidadProveedor> tablaProveedor;
    private TextField filtro;
    private FilteredList<EntidadProveedor> listaFiltro;

    private ObservableList<EntidadProveedor> proveedores;

    private TablaProveedor tablaProveedorHelper;
    TabPane tabPane;
    Tab tab;


    //private ObservableList<Pastel> pasteles;

    public PantallaProveedor(TabPane tabPane, Tab tab) {
        getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        this.tabPane = tabPane;
        this.tab = tab;
        this.inicializarComponentes();
    }

    private void inicializarComponentes() {
        //Crea label del titulo
        this.lTitulo = new Label("Proveedores");
        //Le da estilo
        this.lTitulo.getStyleClass().add("label-titulo");
        //Tamanio del label de titulo
        this.lTitulo.setPrefHeight(70.0);
        //crea la lista helper
        List<EntidadProveedor> listaProveedorHelper = new ArrayList<EntidadProveedor>();
        //crear controlador
        ProveedorController proveedorControl = new ProveedorController();
        //agregar los elementos del controlador al la lista helper
        listaProveedorHelper = proveedorControl.ListarTodos();
        //agregar la lista helper a la tabla helper
        tablaProveedorHelper = new TablaProveedor(listaProveedorHelper);
        //a la tablaview agregarle la tabla helper
        this.tablaProveedor = tablaProveedorHelper.getTablaProveedor();

        //metodo para crear ventana de editar entidad
        tablaProveedor.setOnMouseClicked((evtm) -> {
            if (evtm.getClickCount() == 2) {
                EntidadProveedor usuarioSeleccionado = (EntidadProveedor)tablaProveedor.getSelectionModel().getSelectedItem();
                int seleccion = tablaProveedor.getSelectionModel().getSelectedIndex();
                editarProveedor(usuarioSeleccionado, seleccion);
            }

        });

        //crear boton agregar
        this.bAgregarProveedor = new Button("Agregar Proveedor");
        this.bAgregarProveedor.getStyleClass().add("cssBoton");

        //metodo para ventana de crear entidad
        this.bAgregarProveedor.setOnAction((evtm) -> {
            this.registrarProveedor();
        });

        //textfield para el filtro
        filtro=new TextField();
        //crear la lista del filtro agregando la lista de la tabla
        listaFiltro = new FilteredList<EntidadProveedor>(tablaProveedorHelper.getListaProveedores(), b->true);
        //agregar listener al textfield
        filtro.textProperty().addListener((observable, oldvalue, newValue)->{
            listaFiltro.setPredicate(proveedorEntidad -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFiltrer = newValue.toLowerCase();
                if(lowerCaseFiltrer.contains(Integer.toString(proveedorEntidad.getIdProveedor()))){
                    return true;
                }
                if(proveedorEntidad.getNomEmpresa().toLowerCase().contains(lowerCaseFiltrer)){
                    return true;
                }
                if(proveedorEntidad.getCorreo().toLowerCase().contains(lowerCaseFiltrer)){
                    return true;
                }
                if(proveedorEntidad.getTelefono().toLowerCase().contains(lowerCaseFiltrer)){
                    return true;
                }

                return false;
            });
        } );

        //envia la lista a la tabla
        SortedList<EntidadProveedor> sortedData = new SortedList<EntidadProveedor>(listaFiltro);
        sortedData.comparatorProperty().bind(tablaProveedor.comparatorProperty());
        tablaProveedor.setItems(sortedData);

        //crear la barra superior y agregarlos al pane
        this.barra = new HBox();
        this.barra.setSpacing(200.0);
        this.barra.setAlignment(Pos.CENTER);
        this.barra.getChildren().addAll(lTitulo, bAgregarProveedor, filtro);
        this.setTop(barra);
        this.setLeft(tablaProveedor);
    }

    private void editarProveedor(EntidadProveedor usuarioSeleccionado, int seleccion) {
        Stage ventanaProveedor = new Stage();
        Pane root = new PantallaAgregarProveedores(ventanaProveedor,  tablaProveedorHelper);
        Scene escena = new Scene(root, 600, 550);
        ventanaProveedor.setScene(escena);
        ventanaProveedor.setResizable(false);
        ventanaProveedor.setTitle("Editar Proveedor");
        ventanaProveedor.show();
    }

    private void registrarProveedor(){
        Stage ventanaProveedor = new Stage();
        Pane root = new PantallaAgregarProveedores(ventanaProveedor, tablaProveedorHelper);
        Scene escena = new Scene(root, 600, 550);
        ventanaProveedor.setScene(escena);
        ventanaProveedor.setResizable(false);
        ventanaProveedor.setTitle("Agregar Trabajador");
        ventanaProveedor.showAndWait();

    }


}