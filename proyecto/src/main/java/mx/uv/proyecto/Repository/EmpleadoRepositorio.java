package mx.uv.proyecto.Repository;

import org.springframework.data.repository.CrudRepository;

import mx.uv.proyecto.Modelos.Empleado;

public interface EmpleadoRepositorio extends CrudRepository<Empleado, Integer> {

}
