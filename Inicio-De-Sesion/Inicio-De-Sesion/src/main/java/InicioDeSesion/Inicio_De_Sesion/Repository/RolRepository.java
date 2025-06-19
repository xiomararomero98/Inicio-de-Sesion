package InicioDeSesion.Inicio_De_Sesion.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import InicioDeSesion.Inicio_De_Sesion.Model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    // Buscar por rol por id
    Optional<Rol> findById(Long id);

}
