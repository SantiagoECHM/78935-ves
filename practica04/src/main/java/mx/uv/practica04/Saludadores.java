package mx.uv.practica04;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Saludadores{
@Id/* //indicamos que es la llave primaria */ 
@GeneratedValue(strategy = GenerationType.AUTO)//Define la estrategia de la notacion de los indices/
	private Integer id;
	private String nombre;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
} 



