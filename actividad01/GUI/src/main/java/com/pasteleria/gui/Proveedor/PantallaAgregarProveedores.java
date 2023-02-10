package com.pasteleria.gui.Proveedor;


import com.pasteleria.MOD2.CONT.ProveedorController;
import com.pasteleria.MOD2.MOD.EntidadProveedor;
import com.pasteleria.gui.Menu.LanzadorSecciones;

import com.pasteleria.gui.VentanaAlert;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PantallaAgregarProveedores extends GridPane{

    Button btnVolver;

    Label etiCod;
    Label etiEmpresa;
    Label etiCP;
    Label etiCorr;
    Label etiNumTel;

    TextField txtEmpresa;
    TextField txtCP;
    TextField txtCorr;

    TextField txtNum;
    Button btnElim;
    Button btnAdd;
    ImageView imgEliminar;
    ImageView imgConfirmar;
    private  Stage stage;

    public PantallaAgregarProveedores(Stage ventanaProveedor, TablaProveedor tablaHelper){
        this.stage = ventanaProveedor;
        getStylesheets().add(getClass().getResource("gui/styles.css").toExternalForm());
        setStyle("-fx-background-color: #ECF0F1;");



        btnElim = new Button();
        //btnElim.setGraphic(imgEliminar);
        btnElim.setTooltip(new Tooltip("Eliminar proveedor"));
        btnElim.getStyleClass().add("cssBoton");

        btnAdd = new Button();
        btnAdd.setShape(new Circle(1.0));
        //btnAdd.setGraphic(imgConfirmar);
        btnAdd.setTooltip(new Tooltip("Agregar proveedor"));

        btnAdd.setOnAction(evtm->{
//            new ProveedorController().AgregarProveedor(new EntidadProveedor(agregarProveedor()));
            tablaHelper.limpiarLista();
            tablaHelper.agregarProveedores(new ProveedorController().ListarTodos());
            stage.close();
        });

        inicializarComponentes();

    }

//    private EntidadProveedor agregarProveedor() {
//        if(comprobar()){
//            return new EntidadProveedor(txtEmpresa.getText(), txtCorr.getText(), Integer.parseInt(txtCP.getText()));
//        }
//        return null;
//    }

    private boolean comprobar() {
            String errores="";
            if(txtEmpresa.getText().isEmpty()){
                errores+="No se agrego un nomnbre de Empresa\r\n";
            }
            if(txtCP.getText().isEmpty()) {
                errores += "No se agrego el codigo postal\r\n";
            }
            if(txtCorr.getText().isEmpty()){
                errores+="No se agrego el primer apellido\r\n";
            }

            if(errores.equals("")){
                return true;
            }else {
                VentanaAlert alerta = new VentanaAlert(errores, "Error el agregar proveedor");
                return false;
            }
        }



    private void inicializarComponentes(){
        this.setVgap(30);
        this.setHgap(10);
        this.setAlignment(Pos.TOP_CENTER);

        etiCod = new Label("Código: ");

        etiEmpresa = new Label("Emppresa: ");
        txtEmpresa = new TextField();

        etiCorr = new Label("Correo Electrónico: ");
        txtCorr = new TextField();

        etiCP = new Label("Codigo Postal: ");
        txtCP = new TextField();

        imgEliminar = new ImageView("contenedor-de-basura.png");
        imgEliminar.setFitWidth(50);
        imgEliminar.setFitHeight(50);

        btnElim = new Button();
        btnElim.setGraphic(imgEliminar);
        btnElim.setTooltip(new Tooltip("Eliminar proveedor"));

        imgConfirmar = new ImageView("correcto.png");
        imgConfirmar.setFitHeight(40);
        imgConfirmar.setFitWidth(40);

        btnAdd = new Button();
        btnAdd.setShape(new Circle(1.0));
        btnAdd.setGraphic(imgConfirmar);
        btnAdd.setTooltip(new Tooltip("Agregar proveedor"));

       //btnVolver.setOnAction(e-> {
        //  LanzadorSecciones pantallaAnterior = new LanzadorSecciones(tabPane, tab);
        // tab.setContent(pantallaAnterior);
        //});

        this.add(etiCod, 2,0);

        this.add(etiEmpresa, 1,1);
        this.add(txtEmpresa, 2, 1);

        this.add(etiCorr,1,2);
        this.add(txtCorr, 2, 2);

        this.add(etiCP, 1, 3);
        this.add(txtCP, 2, 3);

        this.add(btnElim, 1, 4);
        this.add(btnAdd, 3, 4);

        //this.add(btnVolver, 6, 6);

    }
}
