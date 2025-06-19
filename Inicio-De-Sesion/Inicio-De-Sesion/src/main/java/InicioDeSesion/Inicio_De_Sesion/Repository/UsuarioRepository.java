package InicioDeSesion.Inicio_De_Sesion.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import InicioDeSesion.Inicio_De_Sesion.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
        // Buscar por correo (para verificar existencia o login)
    Optional<Usuario> findByCorreo(String correo);

    // Buscar por correo y contraseña (para autenticación segura)
    Optional<Usuario> findByCorreoAndClave(String correo, String clave);
 
}
