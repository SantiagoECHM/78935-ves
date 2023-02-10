package com.pasteleria.gui.Cliente;

import com.pasteleria.MOD.ClienteEntidad;
import com.pasteleria.MOD.DireccionEntidad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TablaCliente {

    private TableView<ClienteEntidad> tablaClientes;
    private ObservableList<ClienteEntidad> clientes = FXCollections.observableArrayList();
    private DireccionEntidad direccion;
    private ClienteEntidad cliente;
    public TablaCliente(List<ClienteEntidad> listaClientes){
        this.clientes.addAll(listaClientes);
        this.tablaClientes = new TableView<>();
        this.tablaClientes.getStyleClass().add("table-cell");
        this.tablaClientes.setPrefWidth(700.0);
        this.crearTabla();
    }
//eliminar, buscar, editar
    private void crearTabla() {
        this.tablaClientes.setItems(clientes);

        TableColumn<ClienteEntidad, Integer> colId = new TableColumn<ClienteEntidad, Integer>("ID");
        TableColumn<ClienteEntidad, String> colNombre = new TableColumn<ClienteEntidad, String>("Nombre");
        TableColumn<ClienteEntidad, String> colPrimerApellido = new TableColumn<ClienteEntidad, String>("Primer Apellido");
        TableColumn<ClienteEntidad, String> colSegundoApellido = new TableColumn<ClienteEntidad, String>("Segundo Apellido");
        TableColumn<ClienteEntidad, String> colTelefono = new TableColumn<ClienteEntidad, String>("Telefono");

        colId.setPrefWidth(120.0);
        colNombre.setPrefWidth(180.0);
        colPrimerApellido.setPrefWidth(210.0);
        colSegundoApellido.setPrefWidth(210.0);
        colTelefono.setPrefWidth(100.0);


        colId.setCellValueFactory(new PropertyValueFactory<ClienteEntidad, Integer>("idCliente"));
        colNombre.setCellValueFactory(new PropertyValueFactory<ClienteEntidad, String>("NombreCliente"));
        colPrimerApellido.setCellValueFactory(new PropertyValueFactory<ClienteEntidad, String>("PrimerApellido"));
        colSegundoApellido.setCellValueFactory(new PropertyValueFactory<ClienteEntidad, String>("SegundoApellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<ClienteEntidad, String>("Telefono"));


        this.tablaClientes.getColumns().addAll(colId, colNombre, colPrimerApellido, colSegundoApellido, colTelefono);


    }

    public void agregarCliente(ClienteEntidad cliente, int seleccion){
        clientes.remove(seleccion);
        clientes.add(seleccion, cliente);
        tablaClientes.refresh();
    }

    public void eliminarCliente(int seleccion){
        clientes.remove(seleccion);
        tablaClientes.refresh();
    }

    public TableView<ClienteEntidad> getTablaClientes() {
        return this.tablaClientes;
    }

    public ObservableList<ClienteEntidad> getListaClientes() {
        return this.clientes;}

    public void agregarClientes(List<ClienteEntidad> listaClientes){
        clientes.addAll(listaClientes);
    }

    public void limpiarLista(){this.clientes.clear();}
}
