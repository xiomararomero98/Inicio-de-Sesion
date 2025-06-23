package InicioDeSesion.Inicio_De_Sesion.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import InicioDeSesion.Inicio_De_Sesion.Model.UsuarioLogin;

public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Long> {
    Optional<UsuarioLogin> findByCorreo(String correo);
}