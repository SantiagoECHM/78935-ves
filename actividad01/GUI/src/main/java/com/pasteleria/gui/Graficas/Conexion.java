package com.pasteleria.gui.Graficas;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static String bd = "bdpasteleriav4";
    static String login = "UserPasteleria";
    static String pass = "Pl0p-Pass.test?";
    static String url = "jdbc:mysql://localhost:3306/"+bd;

    Connection con = null;

    public boolean Conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, login, pass);
            return true;
        }catch (SQLException | ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Error al conectar: "+ex.getMessage(), "Error", 0);
            return false;
        }

    }
    public Connection getConexion(){
        return con;
    }
    public boolean desconectar(){
        try {
            if (con != null){
                if (con.isClosed()==false) {
                    con.close();
                }
            }
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al desconectar: "+ e.getMessage(), "Error", 0);
            return false;
        }
    }

}
