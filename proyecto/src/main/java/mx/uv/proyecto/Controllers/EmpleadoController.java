package mx.uv.proyecto.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uv.proyecto.Modelos.Empleado;
import mx.uv.proyecto.Services.EndPoint;

@RestController

public class EmpleadoController {
    @Autowired
    private EndPoint endPoint;

    @GetMapping("/empleado")
    public ArrayList<Empleado> getEmpleados() {
        return this.endPoint.getEmpleados();
    }
}
