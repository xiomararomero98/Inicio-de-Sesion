package InicioDeSesion.Inicio_De_Sesion.Controller;

import InicioDeSesion.Inicio_De_Sesion.Service.InicioSesionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InicioSesionController.class)
public class InicioSesionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InicioSesionService inicioSesionService;

    @Test
    public void testLoginExitoso() throws Exception {
        String correo = "usuario@correo.com";
        String clave = "123456";
        String rolEsperado = "CLIENTE";

        when(inicioSesionService.iniciarSesion(correo, clave)).thenReturn(rolEsperado);

        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"" + correo + "\", \"clave\":\"" + clave + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rol").value(rolEsperado));
    }

    @Test
    public void testLoginFaltanDatos() throws Exception {
        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"\", \"clave\":null}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Debe enviar correo y clave"));
    }

    @Test
    public void testLoginUsuarioNoEncontrado() throws Exception {
        String correo = "inexistente@correo.com";
        String clave = "123456";

        when(inicioSesionService.iniciarSesion(correo, clave))
                .thenThrow(new RuntimeException("Usuario no encontrado"));

        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"" + correo + "\", \"clave\":\"" + clave + "\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Usuario no encontrado"));
    }

    @Test
    public void testLoginClaveIncorrecta() throws Exception {
        String correo = "usuario@correo.com";
        String clave = "claveMala";

        when(inicioSesionService.iniciarSesion(correo, clave))
                .thenThrow(new RuntimeException("Contraseña incorrecta"));

        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"" + correo + "\", \"clave\":\"" + clave + "\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Contraseña incorrecta"));
    }
}