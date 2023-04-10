package mx.uv.proyecto.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.empleados.BuscarRequest;
import https.t4is_uv_mx.empleados.BuscarResponse;
import https.t4is_uv_mx.empleados.EditarRequest;
import https.t4is_uv_mx.empleados.EditarResponse;
import https.t4is_uv_mx.empleados.EliminarRequest;
import https.t4is_uv_mx.empleados.EliminarResponse;
import https.t4is_uv_mx.empleados.SaludarRequest;
import https.t4is_uv_mx.empleados.SaludarResponse;
import mx.uv.proyecto.Modelos.Empleado;
import mx.uv.proyecto.Repository.EmpleadoRepositorio;

@Endpoint
public class EndPoint {
    private static final String nameSpace_uri = "https://t4is.uv.mx/empleados";
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @PayloadRoot(localPart = "SaludarRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion) {
        SaludarResponse respuesta = new SaludarResponse();
        Empleado empleado = new Empleado();

        respuesta.setRespuesta("Hola " + peticion.getNombre() + " bienvenido, tienes " + peticion.getEdad() + " años");

        // Guardar base de datos
        empleado.setName(peticion.getNombre());
        empleado.setAge(peticion.getEdad());
        empleadoRepositorio.save(empleado);

        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public BuscarResponse Buscar(@RequestPayload BuscarRequest peticion) {
        BuscarResponse respuesta = new BuscarResponse();
        Empleado empleado = empleadoRepositorio.findById(peticion.getBuscadorId()).get();

        respuesta.setRespuestaBuscar("El empleado No." + empleado.getId() + " " + empleado.getName() + " tiene "
                + empleado.getAge() + " años");

        return respuesta;
    }

    @PayloadRoot(localPart = "EditarRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public EditarResponse Editar(@RequestPayload EditarRequest peticion) {
        EditarResponse respuesta = new EditarResponse();
        Empleado empleado = empleadoRepositorio.findById(peticion.getEditarId()).get();

        empleado.setName(peticion.getEditarNombre());
        empleado.setAge(peticion.getEditarEdad());

        empleadoRepositorio.save(empleado);

        respuesta.setRespuestaEditar("Se han actualizados los datos del empleado " + empleado.getName());

        return respuesta;
    }

    @PayloadRoot(localPart = "EliminarRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public EliminarResponse Eliminar(@RequestPayload EliminarRequest peticion) {
        EliminarResponse respuesta = new EliminarResponse();

        Empleado empleado = empleadoRepositorio.findById(peticion.getEliminarId()).get();

        respuesta.setRespuestaEliminar("Se ha eliminado el empleado No." + empleado.getId() + " " + empleado.getName());

        empleadoRepositorio.delete(empleado);

        // Nota: también se puede eliminar por el id deleteById

        return respuesta;
    }

    public ArrayList<Empleado> getEmpleados() {
        return (ArrayList<Empleado>) empleadoRepositorio.findAll();
    }
}
