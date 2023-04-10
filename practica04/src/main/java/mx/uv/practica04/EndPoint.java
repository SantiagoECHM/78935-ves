package mx.uv.practica04;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.BuscarRequest;
import https.t4is_uv_mx.saludos.BuscarResponse;
import https.t4is_uv_mx.saludos.EditarRequest;
import https.t4is_uv_mx.saludos.EditarResponse;
import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;
import https.t4is_uv_mx.saludos.ListarResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;
import jakarta.xml.bind.JAXBElement;

@Endpoint
public class EndPoint {
    @Autowired // Cuando haga los procesos de inyeccion vincula las otras clases que tenemos
               // ahíi
    private ISaludadores iSaludadores;

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion) {
        SaludarResponse i = new SaludarResponse();
        i.setRespuesta("Hola " + peticion.getNombre());
        // Usar el repositorio para efectuar el crud
        Saludadores saludador = new Saludadores();

        saludador.setNombre(peticion.getNombre());
        iSaludadores.save(saludador);
        return i;
    }

    @PayloadRoot(localPart = "BuscarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarResponse Buscar(@RequestPayload BuscarRequest peticion) {
        BuscarResponse respuesta = new BuscarResponse();
        Saludadores saludador = iSaludadores.findById(peticion.getNumero()).get();

        /*
         * if (peticion.getId()> ) {
         * respuesta.setRespuesta("El Id ingresado no existe");
         * } else {
         * 
         * }
         */
        respuesta.setRespuesta(saludador.getNombre());

        return respuesta;
    }

    @PayloadRoot(localPart = "EditarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EditarResponse Editar(@RequestPayload EditarRequest peticion) {
        EditarResponse i = new EditarResponse();
        Saludadores saludador = new Saludadores();

        saludador.setId(peticion.getId());
        saludador.setNombre(peticion.getNombre());
        iSaludadores.save(saludador);
        return i;
    }

    @PayloadRoot(localPart = "EliminarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EliminarResponse Eliminar(@RequestPayload EliminarRequest peticion) {
        EliminarResponse i = new EliminarResponse();
        iSaludadores.deleteById(peticion.getId());
        i.setRespuesta("Se ha eliminado ");
        return i;
    }

    /*
     * @PayloadRoot(localPart = "ListarRequest", namespace =
     * "https://t4is.uv.mx/saludos")
     * 
     * @ResponsePayload
     * public ListarResponse Listar() {
     * ListarResponse i = new ListarResponse();
     * String lista = "";
     * for (int x = 0; x < saludos.size(); x++) {
     * lista += saludos.get(x) + ", ";
     * }
     * i.setRespuesta("Lista: " + lista);
     * return i;
     * }
     */
}
