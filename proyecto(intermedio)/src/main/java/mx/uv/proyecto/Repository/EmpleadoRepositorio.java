package mx.uv.proyecto.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import mx.uv.proyecto.Modelos.Empleado;

public interface EmpleadoRepositorio extends CrudRepository<Empleado, Integer> {
    Optional<Empleado> findByName(String name);

    Iterable<Empleado> findAllByName(String name);
}
