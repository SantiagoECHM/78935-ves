package com.pasteleria.gui.Graficas;

import com.pasteleria.MOD.TrabajadorEntidad;
import com.pasteleria.MOD.VentaEntidad;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//El paquete entero ha sido realizado por Brandon Aguilera 25-11-22
public class DaoLenguaje extends Conexion{
    public List<TrabajadorEntidad> mostrarTrabajadores(){
        List listaTrabajadores = new ArrayList();
        ResultSet res;
        try{
            this.Conectar();
            String sql = "select * from trabajador";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()){
                TrabajadorEntidad leng = new TrabajadorEntidad();
                //leng.setIdTrabajador(res.getInt("IDTrabajador"));
                leng.setNombre(res.getString("Nombre"));

                listaTrabajadores.add(leng);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al extraer datos: "+ e.getMessage(), "Error", 0);

        }finally {
            this.desconectar();
        }
        return listaTrabajadores;
    }

    public List<VentaEntidad> mostrarIngresos(){
        List listaTrabajadores = new ArrayList();
        ResultSet res;
        try{
            this.Conectar();
            String sql = "select * from venta";
            PreparedStatement pre = this.getConexion().prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()){
                VentaEntidad leng = new VentaEntidad();
                //leng.setIdTrabajador(res.getInt("IDTrabajador"));
                leng.setTotal(res.getInt("Total"));

                listaTrabajadores.add(leng);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al extraer datos: "+ e.getMessage(), "Error", 0);

        }finally {
            this.desconectar();
        }
        return listaTrabajadores;
    }
}
