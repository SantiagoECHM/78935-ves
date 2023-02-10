package com.pasteleria.gui.Cliente;

import com.pasteleria.MOD.DireccionEntidad;
import com.pasteleria.gui.VentanaAlert;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class EditarDireccion extends GridPane {
    private Stage stage;
    private Label lDireccion;
    private DireccionEntidad direccion;

    private Label lEstado;
    private Label lCiudad;
    private Label lColonia;
    private Label lCalle;
    private Label lNoCasa;
    private Label lDescripcion;

    private Button agregar;

    private TextField tEstado;
    private TextField tCiudad;
    private TextField tColonia;
    private TextField tCalle;
    private TextField tNoCasa;
    private TextArea tDetalles;
    public EditarDireccion(Stage stage, Label lDireccion, DireccionEntidad direccion) {
        this.stage = stage;
        this.lDireccion = lDireccion;
        this.direccion = direccion;
        setStyle("-fx-background-color: #dfe6e9");
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        lEstado = new Label("Estado: ");
        tEstado = new TextField();
        tEstado.setPrefWidth(200);
        tEstado.setPromptText("Estado");

        lCiudad = new Label("Ciudad: ");
        tCiudad = new TextField();
        tCiudad.setPrefWidth(200);
        tCiudad.setPromptText("Ciudad");

        lColonia = new Label("Colonia: ");
        tColonia=new TextField();
        tColonia.setPrefWidth(200);
        tColonia.setPromptText("Colonia");

        lCalle = new Label("Calle: ");
        tCalle = new TextField();
        tCalle.setPromptText("Calle");
        tCalle.setMaxWidth(280);

        lNoCasa = new Label("No. de la casa: ");
        tNoCasa = new TextField();
        tNoCasa.setPromptText("No. de la casa");
        tNoCasa.setMaxWidth(200);

        lDescripcion = new Label("Detalles de casa: ");
        tDetalles = new TextArea();
        tDetalles.setPromptText("Ingresa detalles de la casa");
        tDetalles.getStyleClass().add("ios-field");
        tDetalles.setMaxWidth(280);
        tDetalles.setMaxHeight(100);

        agregar = new Button("Agregar");
        agregar.getStyleClass().add("cssBoton");

        agregar.setOnAction(evt -> {
            if(comprobar()){
                crearDireccion();
                lDireccion.setText(direccion.toString());
                stage.close();
            }
        });
        setHgap(7);
        setVgap(7);
        setAlignment(Pos.CENTER);
        add(lEstado, 2, 0);
        add(tEstado, 3, 0);
        add(lCiudad, 2, 2);
        add(tCiudad, 3, 2);
        add(lColonia, 2, 4);
        add(tColonia, 3, 4);
        add(lCalle, 2, 6);
        add(tCalle, 3, 6);
        add(lNoCasa, 2, 8);
        add(tNoCasa, 3, 8);
        add(lDescripcion, 2, 10);
        add(tDetalles, 3, 10);
        add(agregar, 3, 11);
    }

    private void crearDireccion() {
        direccion.setCiudad(tCiudad.getText());
        direccion.setColonia(tColonia.getText());
        direccion.setCalle(tCalle.getText());
        direccion.setNumExterior(parseInt(tNoCasa.getText()));
        direccion.setNumInterior(0);
        direccion.setDetalles(tDetalles.getText());
    }

    private boolean comprobar() {
        String str="";
        if(tColonia.getText().isEmpty())
            str+="* No se agrego la colonia\n";
        if(tCiudad.getText().isEmpty())
            str+="* No se agrego la ciudad\n";
        if(tEstado.getText().isEmpty())
            str+="* No se agrego el estado\n";
        if(tCalle.getText().isEmpty())
            str+="* No se agrego una calle\n";
        if(tDetalles.getText().isEmpty())
            str+="* No se agregaron detalles\n";
        char[] array= tNoCasa.getText().toCharArray();
        for(char letra : array){
            if(!Character.isDigit(letra)){
                str+="* El numero contiene letras\n";
                break;
            }
        }
        if(str.isEmpty()) {
            return true;
        }
        else {
            VentanaAlert ventanaAlert = new VentanaAlert(str, "Error al agregar direccion");
            return false;
        }
    }
}
