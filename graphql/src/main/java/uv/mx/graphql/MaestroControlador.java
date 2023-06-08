package uv.mx.graphql;

import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MaestroControlador {
    private final MaestroRepository maestroRepository;
    private final SalonRepository salonRepository;

    public MaestroControlador(MaestroRepository maestroRepository, SalonRepository salonRepository) {
        this.maestroRepository = maestroRepository;
        this.salonRepository = salonRepository;
    }

    // Tiene que coincidir con nuestro esquema
    @QueryMapping
    Iterable<Maestro> maestros() {
        return this.maestroRepository.findAll();
    }

    @QueryMapping
    Optional<Maestro> maestroById(@Argument Integer id) {
        return this.maestroRepository.findById(id);
    }

    @MutationMapping
    Salon agregarSalon(@Argument SalonInput salon) {
        Maestro maestro = maestroRepository.findById(salon.idMaestro())
                .orElseThrow(() -> new IllegalArgumentException("Maestro no encontrado"));
        Salon salon2 = new Salon(salon.nombre(), salon.edificio(), maestro);
        return salonRepository.save(salon2);
    }

    /**
     * InnerMaestroControlador
     */
    record SalonInput(String nombre, String edificio, Integer idMaestro) {
    }
}
