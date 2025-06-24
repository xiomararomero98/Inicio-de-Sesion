package InicioDeSesion.Inicio_De_Sesion.Service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import InicioDeSesion.Inicio_De_Sesion.WebClient.UsuarioClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.HashMap;

public class InicioSesionServiceTest {

    private UsuarioClient usuarioClientMock;
    private InicioSesionService inicioSesionService;

    @BeforeEach
    void setUp() {
        usuarioClientMock = mock(UsuarioClient.class);
        inicioSesionService = new InicioSesionService(usuarioClientMock);
    }

    @Test
    void iniciarSesion_DeberiaRetornarRolSiCredencialesSonValidas() {
        String correo = "usuario@ejemplo.com";
        String claveIngresada = "clave123";
        String claveEncriptada = EncripatadorDeClaves.encriptar(claveIngresada);

        Map<String, Object> rolMap = Map.of("nombre", "ADMIN");
        Map<String, Object> usuarioData = new HashMap<>();
        usuarioData.put("clave", claveEncriptada);
        usuarioData.put("rol", rolMap);

        when(usuarioClientMock.getUsuarioPorCorreo(correo)).thenReturn(usuarioData);

        String rol = inicioSesionService.iniciarSesion(correo, claveIngresada);

        assertEquals("ADMIN", rol);
    }

    @Test
    void iniciarSesion_DeberiaLanzarExcepcion_SiUsuarioNoExiste() {
        String correo = "inexistente@correo.com";
        when(usuarioClientMock.getUsuarioPorCorreo(correo)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            inicioSesionService.iniciarSesion(correo, "clave123");
        });

        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    @Test
    void iniciarSesion_DeberiaLanzarExcepcion_SiClaveIncorrecta() {
        String correo = "usuario@ejemplo.com";
        String claveIncorrecta = "malaclave";

        Map<String, Object> usuarioData = new HashMap<>();
        usuarioData.put("clave", EncripatadorDeClaves.encriptar("claveCorrecta"));
        usuarioData.put("rol", Map.of("nombre", "CLIENTE"));

        when(usuarioClientMock.getUsuarioPorCorreo(correo)).thenReturn(usuarioData);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            inicioSesionService.iniciarSesion(correo, claveIncorrecta);
        });

        assertEquals("Contraseña incorrecta", exception.getMessage());
    }

    @Test
    void iniciarSesion_DeberiaLanzarExcepcion_SiRolNoPresente() {
        String correo = "usuario@ejemplo.com";

        Map<String, Object> usuarioData = new HashMap<>();
        usuarioData.put("clave", EncripatadorDeClaves.encriptar("clave123"));
        usuarioData.put("rol", null); // rol nulo

        when(usuarioClientMock.getUsuarioPorCorreo(correo)).thenReturn(usuarioData);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            inicioSesionService.iniciarSesion(correo, "clave123");
        });

        assertEquals("No se pudo obtener el rol del usuario", exception.getMessage());
    }
}
