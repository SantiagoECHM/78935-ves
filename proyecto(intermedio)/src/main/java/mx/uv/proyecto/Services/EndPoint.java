package mx.uv.proyecto.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.empleados.BuscarEmpleadoRequest;
import https.t4is_uv_mx.empleados.BuscarEmpleadoResponse;
import https.t4is_uv_mx.empleados.BuscarTodosResponse;
import https.t4is_uv_mx.empleados.EditarEmpleadoRequest;
import https.t4is_uv_mx.empleados.EditarEmpleadoResponse;
import https.t4is_uv_mx.empleados.EliminarEmpleadoRequest;
import https.t4is_uv_mx.empleados.EliminarEmpleadoResponse;
import https.t4is_uv_mx.empleados.RegistrarEmpleadoRequest;
import https.t4is_uv_mx.empleados.RegistrarEmpleadoResponse;
import mx.uv.proyecto.Modelos.Empleado;
import mx.uv.proyecto.Repository.EmpleadoRepositorio;

@Endpoint
public class EndPoint {
    private static final String nameSpace_uri = "https://t4is.uv.mx/empleados";
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @PayloadRoot(localPart = "RegistrarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public RegistrarEmpleadoResponse Saludar(@RequestPayload RegistrarEmpleadoRequest peticion) {
        RegistrarEmpleadoResponse respuesta = new RegistrarEmpleadoResponse();
        Empleado empleado = new Empleado();

        // Guardar base de datos
        empleado.setName(peticion.getNombre());
        empleado.setApellido(peticion.getApellido());
        empleado.setAge(peticion.getEdad());
        empleado.setOficio(peticion.getOficio());
        empleadoRepositorio.save(empleado);

        respuesta.setRespuesta("Se ha registrado el empleado " + peticion.getNombre() + " " + peticion.getApellido());

        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public BuscarEmpleadoResponse Buscar(@RequestPayload BuscarEmpleadoRequest peticion) {
        BuscarEmpleadoResponse respuesta = new BuscarEmpleadoResponse();
        Empleado empleado = empleadoRepositorio.findById(peticion.getBuscadorId()).get();

        respuesta.setRespuestaBuscar("ID: " + empleado.getId() + " Nombre: "
                + empleado.getName() + " " + empleado.getApellido() + " Oficio: "
                + empleado.getOficio());

        return respuesta;
    }

    @PayloadRoot(localPart = "EditarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public EditarEmpleadoResponse Editar(@RequestPayload EditarEmpleadoRequest peticion) {
        EditarEmpleadoResponse respuesta = new EditarEmpleadoResponse();
        Empleado empleado = empleadoRepositorio.findById(peticion.getEditarId()).get();

        empleado.setName(peticion.getEditarNombre());
        empleado.setApellido(peticion.getEditarApellido());
        empleado.setAge(peticion.getEditarEdad());
        empleado.setOficio(peticion.getEditarOficio());

        empleadoRepositorio.save(empleado);

        respuesta.setRespuestaEditar("Se han actualizados los datos del empleado " + empleado.getName());

        return respuesta;
    }

    @PayloadRoot(localPart = "EliminarEmpleadoRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public EliminarEmpleadoResponse Eliminar(@RequestPayload EliminarEmpleadoRequest peticion) {
        EliminarEmpleadoResponse respuesta = new EliminarEmpleadoResponse();

        Empleado empleado = empleadoRepositorio.findById(peticion.getEliminarId()).get();

        respuesta.setRespuestaEliminar("Se ha eliminado el empleado No." + empleado.getId() + " " + empleado.getName());

        empleadoRepositorio.delete(empleado);

        // Nota: también se puede eliminar por el id deleteById

        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarTodosRequest", namespace = nameSpace_uri)
    @ResponsePayload
    public BuscarTodosResponse BuscarTodos() {
        BuscarTodosResponse respuesta = new BuscarTodosResponse();
        ArrayList<Empleado> empleado;
        empleado = (ArrayList<Empleado>) empleadoRepositorio.findAll();

        for (int i = 0; i < empleado.size(); i++) {
            respuesta.getRespuestaTodos().add("ID: " + empleado.get(i).getId() + " Nombre: "
                    + empleado.get(i).getName() + " " + empleado.get(i).getApellido() + " Oficio: "
                    + empleado.get(i).getOficio());
        }

        return respuesta;
    }

    public ArrayList<Empleado> getEmpleados() {
        return (ArrayList<Empleado>) empleadoRepositorio.findAll();
    }
}
