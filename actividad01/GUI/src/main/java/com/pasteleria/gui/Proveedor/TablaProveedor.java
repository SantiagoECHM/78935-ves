package com.pasteleria.gui.Proveedor;

;
import com.pasteleria.MOD2.MOD.EntidadProveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TablaProveedor {
    private TableView<EntidadProveedor> tablaProveedores;
    private ObservableList<EntidadProveedor> proveedores = FXCollections.observableArrayList();

    public TablaProveedor(List<EntidadProveedor> listaProveedor) {
        this.proveedores.addAll((EntidadProveedor) listaProveedor);
        this.tablaProveedores = new TableView<>();
        this.tablaProveedores.getStyleClass().add("table-cell");
        this.tablaProveedores.setPrefWidth(700.0);
        this.crearTabla();
    }
    //crear la tabla con los atributos del usuario

    public void crearTabla() {
        this.tablaProveedores.setItems(this.proveedores);
        TableColumn<EntidadProveedor, Integer> colId = new TableColumn<EntidadProveedor, Integer>("ID");
        TableColumn<EntidadProveedor, String> colEmpresa = new TableColumn<EntidadProveedor, String>("Empresa");
        TableColumn<EntidadProveedor, String> colCorreo = new TableColumn<EntidadProveedor, String>("Correo");
        TableColumn<EntidadProveedor, String> colTelefono = new TableColumn<EntidadProveedor, String>("Codigo Postal");
        TableColumn<EntidadProveedor, String> colCP = new TableColumn<EntidadProveedor, String>("Telefono");
        TableColumn<EntidadProveedor, Integer> colIDDireccion = new TableColumn<EntidadProveedor, Integer>("ID direccion");

        colEmpresa.setPrefWidth(120.0);
        colCorreo.setPrefWidth(120.0);
        colCP.setPrefWidth(120.0);


        colId.setCellValueFactory(new PropertyValueFactory<EntidadProveedor, Integer>("Id"));
        colEmpresa.setCellValueFactory(new PropertyValueFactory<EntidadProveedor, String>("Empresa"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<EntidadProveedor, String>("Correo"));
        colCP.setCellValueFactory(new PropertyValueFactory<EntidadProveedor, String>("Telefono"));
        colCP.setCellValueFactory(new PropertyValueFactory<EntidadProveedor, String>("Codigo Postal"));
        colIDDireccion.setCellValueFactory(new PropertyValueFactory<EntidadProveedor, Integer>("ID Direccion"));

//            colEliminar.setCellFactory(cellFactory1);
        this.tablaProveedores.getColumns().addAll(colId, colEmpresa, colCorreo, colCP, colTelefono, colIDDireccion);
    }
    //Metodos de la tabla


    public void agregarProveedor() {
        this.proveedores.add((EntidadProveedor) proveedores);
        tablaProveedores.refresh();
    }

    public TableView<EntidadProveedor> getTablaProveedor() {
        return this.tablaProveedores;
    }



    public void eliminarProveedor(int seleccion){
        proveedores.remove(seleccion);
        tablaProveedores.refresh();
    }

    public void agregarProveedores(List<EntidadProveedor> listaProveedores) {
        this.proveedores.addAll(listaProveedores);
    }

    public void limpiarLista() {
        this.proveedores.clear();
    }

    public ObservableList<EntidadProveedor> getListaProveedores() {
        return this.proveedores;
    }


}

