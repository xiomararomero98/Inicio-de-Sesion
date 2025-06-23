package InicioDeSesion.Inicio_De_Sesion.WebClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UsuarioClient {

  private final WebClient webClient;

    public UsuarioClient(@Value("${usuario-service.url}") String usuarioServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(usuarioServiceUrl).build();
    }

    public Map<String, Object> getUsuarioPorCorreo(String correo) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/correo/{correo}").build(correo))
            .retrieve()
            .bodyToMono(Map.class)
            .block();
    }
}