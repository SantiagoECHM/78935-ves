package com.pasteleria.gui.BD;

import com.pasteleria.CONT.TrabajadoresController;
import com.pasteleria.MOD.TrabajadorEntidad;
import com.pasteleria.gui.VentanaExito;
import com.pasteleria.gui.VentanaWaring;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PantallaExportacion extends BorderPane {
    TilePane contenedorIconos = null;
    VBox vbxCajaElementos;
    Label lblDescripcion;
    Button btnExportar;
    TabPane tabPane;
    Tab tab;

    public PantallaExportacion(TabPane tabPane, Tab tab){
        this.tabPane = tabPane;
        this.tab = tab;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        contenedorIconos = new TilePane();
        contenedorIconos.setVgap(12);
        contenedorIconos.setHgap(12);
        contenedorIconos.setPadding(new Insets(10));
        contenedorIconos.setPrefColumns(5);

        lblDescripcion = new Label("Exportar la base de datos");
        btnExportar = new Button("Respaldar");

        btnExportar.setOnAction( e-> {
            //Este codigo de abajo deberia servir para JPA pero no aun asi puede ser util
            /*StatelessSession session = ((Session) entityManager.getDelegate()).getSessionFactory().openStatelessSession();

            Query query = session
                    .createQuery("SELECT a FROM Address a WHERE .... ORDER BY a.id");
            query.setFetchSize(Integer.valueOf(1000));
            query.setReadOnly(true);
            query.setLockMode("a", LockMode.NONE);
            ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
            while (results.next()) {
                Address addr = (Address) results.get(0);
                // Do stuff
            }
            results.close();
            session.close();*/
            try{
                //JPA no posee de buenos metodos para manejar un resultlist XD JPA: Java Pendejo Akesi
                List<TrabajadorEntidad> listaTrabajadores = new ArrayList<>();
                TrabajadoresController trabajadoresController = new TrabajadoresController();
                listaTrabajadores = trabajadoresController.ListarTodos();

                //Proceder a crear la hoja de trabajo nueva.
                XSSFWorkbook wb = new XSSFWorkbook(); //Esta linea sirve para Excel 2007 en adelante, para anteriores usar HSSF
                //Crear la hoja
                XSSFSheet hoja = wb.createSheet("Lista Trabajadores");
                //Comenzar con los encabezados de la tabla
                XSSFRow cabecera = hoja.createRow(0);
                //Añadir las columnas. Obviamente hacerlas acorde a las que estan en la BD de la pasteleria
                cabecera.createCell(0).setCellValue("IDTrabajador");
                cabecera.createCell(1).setCellValue("Nombre");
                cabecera.createCell(2).setCellValue("PrimerApellido");
                cabecera.createCell(3).setCellValue("SegundoApellido");
                cabecera.createCell(4).setCellValue("Telefono");
                cabecera.createCell(5).setCellValue("Pass");
                cabecera.createCell(6).setCellValue("Tipo");

                for (int i=0;i<listaTrabajadores.size();i++){ //Este es equivalente al while(rs.next()) que se usa en JDBC but mejor porque tambien deberia llevar manejo de indices aparte
                    XSSFRow fila = hoja.createRow(i); //Le puse fila para diferenciar o imaginar pero es lo mismo de arriba
                    fila.createCell(0).setCellValue(listaTrabajadores.get(i).getIdTrabajador());
                    System.out.println(listaTrabajadores.get(i).getIdTrabajador()); //Linea de depuracion XDD
                    fila.createCell(1).setCellValue(listaTrabajadores.get(i).getNombre());
                    fila.createCell(2).setCellValue(listaTrabajadores.get(i).getPrimerApellido());
                    fila.createCell(3).setCellValue(listaTrabajadores.get(i).getSegundoApellido());
                    fila.createCell(4).setCellValue(listaTrabajadores.get(i).getTelefono());
                    fila.createCell(5).setCellValue(listaTrabajadores.get(i).getPass());
                    fila.createCell(6).setCellValue(listaTrabajadores.get(i).getTipo());
                }

                FileOutputStream archivoExport = new FileOutputStream("ListaTrabajadores.xsx"); //Recordar que antes del 2007 es xls
                wb.write(archivoExport);
                archivoExport.close();
                VentanaExito exito = new VentanaExito("Se ha exportado la BD gracias al cielo", "Finalizado");
            }catch (Exception ex){
                VentanaWaring error = new VentanaWaring("Lo sentimos, no se ha podido exportar la BD","Error");
            }
        });

        vbxCajaElementos = new VBox();
        vbxCajaElementos.setAlignment(Pos.TOP_CENTER);
        vbxCajaElementos.getChildren().addAll(btnExportar, lblDescripcion);

        contenedorIconos.setAlignment(Pos.CENTER);
        contenedorIconos.getChildren().addAll(vbxCajaElementos);
        setCenter(contenedorIconos);
    }
}
