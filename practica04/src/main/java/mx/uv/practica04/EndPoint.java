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

    /*
     * public List<Book> list() {
     * return bookRepository.findAll();
     * }
     */

    private List<String> saludos = new ArrayList<>();

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload JAXBElement<SaludarRequest> peticion) {
        SaludarResponse i = new SaludarResponse();
        SaludarRequest peticion1 = peticion.getValue();
        i.setRespuesta("Hola " + peticion1.getNombre());
        saludos.add(peticion1.getNombre());

        // Usar el repositorio para efectuar el crud
        Saludadores saludador = new Saludadores();
        /*
         * iSaludadores.findAll();
         * saludador = iSaludadores.findById(peticion1.getId()).get;
         */
        saludador.setNombre(peticion1.getNombre());
        iSaludadores.save(saludador);
        return i;
    }

    @PayloadRoot(localPart = "BuscarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarResponse Buscar(@RequestPayload BuscarRequest peticion) {
        BuscarResponse i = new BuscarResponse();
        if (peticion.getId() > saludos.size())
            i.setRespuesta("El id ingresado es mas grande que la lista");
        else
            i.setRespuesta(saludos.get(peticion.getId()));
        return i;
    }

    @PayloadRoot(localPart = "EditarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EditarResponse Editar(@RequestPayload EditarRequest peticion) {
        EditarResponse i = new EditarResponse();
        if (peticion.getId() > saludos.size()) {
            i.setRespuesta("El id ingresado es mas grande que la lista");
        } else {
            saludos.set(peticion.getId(), peticion.getNombre());
            i.setRespuesta("Se ha cambiando el nombre, hola " + peticion.getNombre());
        }
        return i;
    }

    @PayloadRoot(localPart = "EliminarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public EliminarResponse Eliminar(@RequestPayload EliminarRequest peticion) {
        EliminarResponse i = new EliminarResponse();
        if (peticion.getId() > saludos.size()) {
            i.setRespuesta("El id ingresado es mas grande que la lista");
        } else {
            i.setRespuesta("Se ha eliminado " + saludos.get(peticion.getId()) + " correctamente");
            saludos.remove(peticion.getId());
        }
        return i;
    }

    @PayloadRoot(localPart = "ListarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ListarResponse Listar() {
        ListarResponse i = new ListarResponse();
        String lista = "";
        for (int x = 0; x < saludos.size(); x++) {
            lista += saludos.get(x) + ", ";
        }
        i.setRespuesta("Lista: " + lista);
        return i;
    }
}
