package InicioDeSesion.Inicio_De_Sesion.Controller;
import InicioDeSesion.Inicio_De_Sesion.Service.InicioSesionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
public class InicioSesionController {

    @Autowired
    private InicioSesionService inicioSesionService;

    @Operation(
        summary = "Iniciar sesión",
        description = "Valida las credenciales del usuario (correo y clave) y devuelve el nombre del rol si son correctas."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(example = "{ \"rol\": \"CLIENTE\" }")
            )
        ),
        @ApiResponse(responseCode = "400", description = "Faltan datos en la solicitud",
            content = @Content(
                mediaType = "text/plain",
                schema = @Schema(example = "Debe enviar correo y clave")
            )
        ),
        @ApiResponse(responseCode = "401", description = "Credenciales inválidas",
            content = @Content(
                mediaType = "text/plain",
                schema = @Schema(example = "Contraseña incorrecta")
            )
        ),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
            content = @Content(
                mediaType = "text/plain",
                schema = @Schema(example = "Error inesperado en el servidor")
            )
        )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        required = true,
        description = "Credenciales del usuario para iniciar sesión",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(example = "{ \"correo\": \"usuario@correo.com\", \"clave\": \"123456\" }")
        )
    )
    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        String correo = datos.get("correo");
        String clave = datos.get("clave");

        if (correo == null || clave == null) {
            return ResponseEntity.badRequest().body("Debe enviar correo y clave");
        }

        try {
            String rol = inicioSesionService.iniciarSesion(correo, clave);
            return ResponseEntity.ok(Map.of("rol", rol));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}