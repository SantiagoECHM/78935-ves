package com.pasteleria.gui.Pedido;

import com.itextpdf.layout.borders.Border;
import com.pasteleria.CONT.ClientesController;
import com.pasteleria.CONT.DireccionesController;
import com.pasteleria.CONT.VentasController;
import com.pasteleria.MOD.ClienteEntidad;
import com.pasteleria.MOD.TieneEntidad;
import com.pasteleria.MOD.VentaEntidad;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.awt.*;


public class PantallaDatosPedido extends BorderPane {
    private Stage ventanaPedidos;

    private TieneEntidad pedido;
    private ClienteEntidad cliente;
    private VentasController ventasControl;
    private int seleccion;
    private TablaPedidos tablaPedidos;

    private GridPane datosCliente;
    private Label lNombre;
    private Label lApellidoM;
    private Label lTel1;
    private Label lApellidoP;
    private Label lFecha;
    private Label lHora;
    private Label lAnticipo;
    private Label lTotal;
    private Label lIdCliente;

    private VentaEntidad ventaEntidad;

    public PantallaDatosPedido(Stage ventanaPedidos, TieneEntidad pedido, int seleccion, TablaPedidos tablaPedidos) {
        this.ventanaPedidos = ventanaPedidos;
        this.pedido = pedido;
        this.seleccion = seleccion;
        this.tablaPedidos = tablaPedidos;
        this.cliente = new ClienteEntidad();
        this.ventaEntidad = new VentaEntidad();

        getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        inicializarComponentes();
        //llenarComponentes();
    }

    private void inicializarComponentes() {
        datosCliente = new GridPane();
        cliente = new ClientesController().BuscarID(pedido.getVentaIdVenta());
        ventaEntidad = new VentasController().BuscarPorID(pedido.getVentaIdVenta());

        lIdCliente = new Label("ID: "+cliente.getIdCliente());

        lNombre = new Label("Cliente: "+cliente.getNombreCliente()+" "+cliente.getPrimerApellido()+" "+cliente.getSegundoApellido());

        lTel1 = new Label("Teléfono: "+cliente.getTelefono());

        lFecha = new Label("Fecha de entrega: "+ventaEntidad.getFechaEntrega());

        lHora = new Label("Hora de entrega: "+ventaEntidad.getHoraEntrega());

        lAnticipo = new Label("Anticipo: "+ventaEntidad.getAnticipo());

        lTotal = new Label("Total: "+ventaEntidad.getTotal());

        datosCliente.setStyle("-fx-background-color: #dfe6e9");
        datosCliente.setVgap(30);
        datosCliente.setHgap(10);
        datosCliente.setPrefWidth(500);

        datosCliente.add(lIdCliente,1,3);

        datosCliente.add(lNombre,1,4);

        datosCliente.add(lTel1,1,5);

        datosCliente.add(lFecha, 1, 6);

        datosCliente.add(lHora, 1, 7);

        datosCliente.add(lAnticipo, 1, 8);

        datosCliente.add(lTotal, 2, 9);

        this.setLeft(datosCliente);
    }
}
