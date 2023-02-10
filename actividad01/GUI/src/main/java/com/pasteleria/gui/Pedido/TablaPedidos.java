package com.pasteleria.gui.Pedido;

import com.pasteleria.MOD.ClienteEntidad;
import com.pasteleria.MOD.TieneEntidad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.Table;
import javax.swing.text.Position;
import java.util.List;

public class TablaPedidos {
    private TableView<TieneEntidad> tablaPedidosP;
    private ObservableList<TieneEntidad> pedidosP = FXCollections.observableArrayList();
    private TieneEntidad pedidoP;

    public TablaPedidos(List<TieneEntidad> listaPedidosP){
        this.tablaPedidosP = new TableView<>();
        this.pedidosP.addAll(listaPedidosP);
        this.tablaPedidosP = new TableView<>();
        this.tablaPedidosP.getStyleClass().add("table-cell");
        this.tablaPedidosP.setPrefWidth(300.0);
        this.crearTabla();
    }

    private void crearTabla() {
        this.tablaPedidosP.setItems(pedidosP);

        TableColumn<TieneEntidad, String> colEstado = new TableColumn<>("Estado");
        TableColumn<TieneEntidad, Integer> colCantidad = new TableColumn<>("Cantidad");
        TableColumn<TieneEntidad, Integer> colIDpastel = new TableColumn<>("ID Pastel");
        TableColumn<TieneEntidad, Integer> colIDventa = new TableColumn<>("ID Venta");

        colEstado.setPrefWidth(200.0);
        colCantidad.setPrefWidth(160);
        colIDpastel.setPrefWidth(160.0);
        colIDventa.setPrefWidth(160.0);

        //para alinear texto
        tablaPedidosP.setId("my-table");

        colEstado.setCellValueFactory(new PropertyValueFactory<TieneEntidad, String>("estado"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<TieneEntidad, Integer>("cantidad"));
        colIDpastel.setCellValueFactory(new PropertyValueFactory<TieneEntidad, Integer>("pastelIdPastel"));
        colIDventa.setCellValueFactory(new PropertyValueFactory<TieneEntidad, Integer>("ventaIdVenta"));

        this.tablaPedidosP.getColumns().addAll(colEstado, colCantidad, colIDpastel, colIDventa);
    }

    public TableView<TieneEntidad> getTablaPedidosP() {
        return this.tablaPedidosP;
    }

    public ObservableList<TieneEntidad> getListaPedidosP() {
        return this.pedidosP;}
}
