package com.pasteleria.gui.Pedido;

import com.pasteleria.CONT.PastelVentaController;
import com.pasteleria.MOD.TieneEntidad;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class PantallaVerPedidos extends BorderPane {
    private FilteredList listaFiltro;
    private TextField filtro;
    private TabPane tabPane;
    private Tab tab;

    private TieneEntidad pedido;

    private Label titulo;

    private List<TieneEntidad> listaPedidoHelper;

    private TablaPedidos tablaPedidosPHelper;

    private TableView tablaPedidosP;

    private HBox barra;

    public PantallaVerPedidos(TabPane tabPane, Tab tab) {
        getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        this.tabPane = tabPane;
        this.tab = tab;
        this.inicializarComponentes();
    }
    private void inicializarComponentes(){
        this.pedido = new TieneEntidad();
        this.titulo = new Label("Pedidos");
        this.titulo.getStyleClass().add("label-titulo");
        this.titulo.setPrefHeight(70.0);

        PastelVentaController pastelVentaController = new PastelVentaController();
        listaPedidoHelper = pastelVentaController.ListarPasteles();

        tablaPedidosPHelper = new TablaPedidos(listaPedidoHelper);

        this.tablaPedidosP=tablaPedidosPHelper.getTablaPedidosP();
        tablaPedidosP.setMaxWidth(700);
        tablaPedidosP.setOnMouseClicked(evtm->{
            if(evtm.getClickCount()==2){
                TieneEntidad pedidoSeleccionado = (TieneEntidad) tablaPedidosP.getSelectionModel().getSelectedItem();
                int seleccion = tablaPedidosP.getSelectionModel().getSelectedIndex();
                verDatos(pedidoSeleccionado, seleccion);
            }
        });

        filtro=new TextField();
        listaFiltro = new FilteredList<TieneEntidad>(tablaPedidosPHelper.getListaPedidosP(), b->true);
        filtro.textProperty().addListener((observable, oldvalue, newValue)->{
           listaFiltro.setPredicate(pedidoP -> {
               if (newValue == null || newValue.isEmpty()){
                   return true;
               }
               String lowerCaseFilter = newValue.toLowerCase();
               if(lowerCaseFilter.contains(Integer.toString(pedido.getPastelIdPastel()))){
                   return true;
               }
               if(lowerCaseFilter.contains(Integer.toString(pedido.getVentaIdVenta()))){
                   return true;
               }
               if(lowerCaseFilter.contains(Integer.toString(pedido.getCantidad()))){
                   return true;
               }
               return false;
           });
        } );
        SortedList<TieneEntidad> sortedData = new SortedList<TieneEntidad>(listaFiltro);
        sortedData.comparatorProperty().bind(tablaPedidosP.comparatorProperty());
        tablaPedidosP.setItems(sortedData);

        this.barra = new HBox();
        this.barra.setSpacing(200);
        this.barra.setAlignment(Pos.CENTER);
        this.barra.getChildren().addAll(titulo, filtro);

        this.setTop(barra);
        this.setCenter(tablaPedidosP);
    }

    private void verDatos(TieneEntidad pedidoSeleccionado, int seleccion) {
        Stage ventanaPedidos = new Stage();
        Pane root = new PantallaDatosPedido(ventanaPedidos, pedidoSeleccionado, seleccion, tablaPedidosPHelper);
        Scene escena = new Scene(root, 1200, 800);
        escena.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        ventanaPedidos.setScene(escena);
        ventanaPedidos.setResizable(false);
        ventanaPedidos.setTitle("Cliente");
        ventanaPedidos.show();
    }
}
