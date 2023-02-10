package com.pasteleria.gui.Cliente;

import com.itextpdf.kernel.colors.Lab;
import com.pasteleria.CONT.ClientesController;
import com.pasteleria.CONT.DireccionesController;
import com.pasteleria.CONT.TrabajadoresController;
import com.pasteleria.MOD.ClienteEntidad;
import com.pasteleria.MOD.DireccionEntidad;
import com.pasteleria.MOD.VentaEntidad;
import com.pasteleria.MOD2.MOD.EntidadDireccion;
import com.pasteleria.gui.VentanaAlert;
import com.pasteleria.gui.VentanaWaring;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static java.lang.Integer.parseInt;

public class PantallaDatosCliente extends BorderPane {
    private Stage stage;
    private int seleccion;
    private ClienteEntidad cliente;
    private DireccionEntidad direccion;

    private Button btnAceptar;
    private Button btnEliminar;

    private Label lNombre;
    private Label lApellidoP;
    private Label lApellidoM;
    private Label lTel1;
    private Label lTitulo;
    private Label lDatosCliente;

    private TextField tNombre;
    private TextField tApellidoP;
    private TextField tApellidoM;
    private TextField tTel1;

    private ImageView imgEliminar;
    private ImageView imgConfirmar;
    private Stage stage1;

    private TablaCliente tablaCliente;
    private GridPane datosCliente;
    private GridPane datosDireccion;

    private Label titulo;

    //DIRECCION
    private Label lEstado;
    private Label lCiudad;
    private Label lColonia;
    private Label lCalle;
    private Label lNoCasa;
    private Label lDescripcion;
    private TextField tEstado;
    private TextField tCiudad;
    private TextField tColonia;
    private TextField tCalle;
    private TextField tNoCasa;
    private TextArea tDetalles;
    private DireccionEntidad lDireccion;
    private Button agregar;

    public PantallaDatosCliente(Stage stage, ClienteEntidad cliente, int seleccion, TablaCliente tablaCliente) {
        this.stage = stage;
        this.cliente = cliente;
        this.seleccion = seleccion;
        this.tablaCliente = tablaCliente;
        getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        imgEliminar = new ImageView("contenedor-de-basura.png");
        imgEliminar.setFitWidth(50);
        imgEliminar.setFitHeight(50);

        btnEliminar = new Button();
        btnEliminar.setGraphic(imgEliminar);
        btnEliminar.setTooltip(new Tooltip("Eliminar cliente"));
        btnEliminar.getStyleClass().add("cssBoton");

        imgConfirmar = new ImageView("correcto.png");
        imgConfirmar.setFitHeight(40);
        imgConfirmar.setFitWidth(40);

        btnAceptar = new Button();
        btnAceptar.setShape(new Circle(1.0));
        btnAceptar.setGraphic(imgConfirmar);
        btnAceptar.setTooltip(new Tooltip("Modificar cliente"));

        btnAceptar.setOnAction(evtm->{
            VentanaWaring ventanaWaring = new VentanaWaring("¿Desear guardar los cambios realizados?","Guardar Cliente");
            if(ventanaWaring.resultado()){
                new ClientesController().ModificarCliente(modificarCliente());
                tablaCliente.agregarCliente(cliente, seleccion);
                new DireccionesController().ModificarDireccion(modificarDireccion());
                stage.close();
            }
        });

        btnEliminar.setOnAction(evtm->{
            VentanaWaring ventanaWaring = new VentanaWaring("Estas seguro de borrar este cliente", "Importante");
            if(ventanaWaring.resultado()){
                ClientesController helper =new ClientesController();
                helper.EliminarCliente(cliente);
                new DireccionesController().EliminarDireccion(direccion);
                tablaCliente.eliminarCliente(seleccion);
                stage.close();
            }
        });

        inicializarComponentes();
        llenarComponenetes();
        //llenarComponenetes2(direccion);
    }



    private ClienteEntidad modificarCliente(){
        cliente.setNombreCliente(tNombre.getText());
        cliente.setPrimerApellido(tApellidoP.getText());
        cliente.setSegundoApellido(tApellidoM.getText());
        cliente.setTelefono(tTel1.getText());
        return cliente;
    }
    private DireccionEntidad modificarDireccion() {
        direccion.setCiudad(tCiudad.getText());
        direccion.setColonia(tColonia.getText());
        direccion.setCalle(tCalle.getText());
        direccion.setDetalles(tDetalles.getText());
        return direccion;
    }

    /*private ClienteEntidad crearClienteEditar(ClienteEntidad cliente){
        if(comprobar()){
            return new ClienteEntidad(cliente.getIdCliente(), tNombre.getText(), tApellidoP.getText(), tApellidoM.getText(), tTel1.getText(), tTel2.getText());
        }
        return null;
    }*/
    private DireccionEntidad crearDireccionEditar(DireccionEntidad direccion){
        if(comprobar2()){
            return new DireccionEntidad(direccion.getIdDireccion(), tCiudad.getText(), tColonia.getText(), tCalle.getText(), tDetalles.getText());
        }
        return null;

    }
    private void inicializarComponentes() {
        datosDireccion = new GridPane();

        lCiudad = new Label("Selecciona la ciudad: ");
        tCiudad = new TextField();
        tCiudad.setPrefWidth(200);
        tCiudad.setPromptText("Ciudad");

        lColonia = new Label("Selecciona la colonia: ");
        tColonia=new TextField();
        tColonia.setPrefWidth(200);
        tColonia.setPromptText("Colonia");

        lCalle = new Label("Ingresa la calle: ");
        tCalle = new TextField();
        tCalle.setPromptText("Calle");
        tCalle.setMaxWidth(280);

        lDescripcion = new Label("Descripcion/detalles de la casa: ");
        tDetalles = new TextArea();
        tDetalles.setPromptText("Ingresa detalles de la casa");
        tDetalles.getStyleClass().add("ios-field");
        tDetalles.setMaxWidth(280);
        tDetalles.setMaxHeight(100);


        datosDireccion.setHgap(10);
        datosDireccion.setVgap(20);
        datosDireccion.setAlignment(Pos.CENTER);
        datosDireccion.add(lCiudad, 2, 0);
        datosDireccion.add(tCiudad, 3, 0);
        datosDireccion.add(lColonia, 2, 2);
        datosDireccion.add(tColonia, 3, 2);
        datosDireccion.add(lCalle, 2, 4);
        datosDireccion.add(tCalle, 3, 4);
        datosDireccion.add(lDescripcion, 2, 6);
        datosDireccion.add(tDetalles, 3, 6);

        //CLIENTES
        datosCliente=new GridPane();

        lNombre = new Label("Nombre: ");
        tNombre = new TextField();

        lApellidoP = new Label("Apellido Paterno: ");
        tApellidoP = new TextField();

        lApellidoM = new Label("Apellido Materno: ");
        tApellidoM = new TextField();

        lTel1 = new Label("Teléfono: ");
        tTel1 = new TextField();

        titulo=new Label("Clientes");
        titulo.getStyleClass().add("label-titulo-Grande");

        datosCliente.setStyle("-fx-background-color: #dfe6e9");
        datosCliente.setVgap(30);
        datosCliente.setHgap(10);
        datosCliente.setPrefWidth(500);

        datosCliente.add(lNombre, 1,1);
        datosCliente.add(tNombre, 2, 1);

        datosCliente.add(lApellidoP, 1,2);
        datosCliente.add(tApellidoP, 2, 2);

        datosCliente.add(lApellidoM, 1,3);
        datosCliente.add(tApellidoM, 2, 3);

        datosCliente.add(lTel1,1,4);
        datosCliente.add(tTel1, 2, 4);


        datosCliente.add(btnEliminar, 1, 5);
        datosCliente.add(btnAceptar, 3, 5);

        setRight(datosDireccion);
        setLeft(datosCliente);
        setTop(titulo);
        setAlignment(titulo, Pos.CENTER);
    }
    private void llenarComponenetes() {
        tNombre.setText(cliente.getNombreCliente());
        tApellidoP.setText(cliente.getPrimerApellido());
        tApellidoM.setText(cliente.getSegundoApellido());
        tTel1.setText(cliente.getTelefono());
        direccion = new DireccionesController().BuscarID(cliente.getDireccionIdDireccion());
        tCiudad.setText(direccion.getCiudad());
        tColonia.setText(direccion.getColonia());
        tCalle.setText(direccion.getCalle());
        tDetalles.setText(direccion.getDetalles());
    }


    private boolean comprobar() {
        String errores="";
        if(tNombre.getText().isEmpty()){
            errores+="No se agregó nombre\r\n";
        }
        if(tApellidoP.getText().isEmpty()) {
            errores += "No se agregó apellido paterno\r\n";
        }
        if(tApellidoM.getText().isEmpty()){
            errores+="No se agregó apellido materno\r\n";
        }
        if(tTel1.getText().isEmpty()){
            errores+="No se agregó teléfono\r\n";
        }
        if(errores.equals("")){
            return true;
        }else {
            VentanaAlert alerta = new VentanaAlert(errores, "Error el agregar trabajdor");
            return false;
        }
    }

    private boolean comprobar2() {
        String str="";
        if(tColonia.getText().isEmpty())
            str+="* No se agrego la colonia\n";
        if(tCiudad.getText().isEmpty())
            str+="* No se agrego la ciudad\n";
        if(tCalle.getText().isEmpty())
            str+="* No se agrego una calle\n";
        if(tDetalles.getText().isEmpty())
            str+="* No se agregaron detalles\n";
        /*char[] array= tNoCasa.getText().toCharArray();
        for(char letra : array){
            if(!Character.isDigit(letra)){
                str+="* El numero contiene letras\n";
                break;
            }
        }*/
        if(str.isEmpty()) {
            return true;
        }
        else {
            VentanaAlert ventanaAlert = new VentanaAlert(str, "Error al agregar direccion");
            return false;
        }
    }
}
