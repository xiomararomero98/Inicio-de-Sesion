package InicioDeSesion.Inicio_De_Sesion.Service;

import InicioDeSesion.Inicio_De_Sesion.WebClient.UsuarioClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InicioSesionService {

    private final UsuarioClient usuarioClient;

    public InicioSesionService(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    public String iniciarSesion(String correo, String clavePlain) {
        // Consultar usuario desde microservicio control de usuarios
        Map<String, Object> usuarioData = usuarioClient.getUsuarioPorCorreo(correo);

        if (usuarioData == null || usuarioData.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Obtener clave encriptada del usuario
        String claveHash = (String) usuarioData.get("clave");

        // Validar contraseña usando tu clase EncripatadorDeClaves
        boolean passwordValido = EncripatadorDeClaves.comparar(clavePlain, claveHash);

        if (!passwordValido) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Extraer el rol del usuario (suponiendo que el JSON trae un objeto "rol" con campo "nombre")
        Map<String, Object> rol = (Map<String, Object>) usuarioData.get("rol");
        if (rol == null || !rol.containsKey("nombre")) {
            throw new RuntimeException("No se pudo obtener el rol del usuario");
        }

        String nombreRol = rol.get("nombre").toString();

        return nombreRol;
    }
}