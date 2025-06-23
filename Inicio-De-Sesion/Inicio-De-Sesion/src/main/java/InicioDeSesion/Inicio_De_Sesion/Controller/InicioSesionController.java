package InicioDeSesion.Inicio_De_Sesion.Controller;
import InicioDeSesion.Inicio_De_Sesion.Service.InicioSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
public class InicioSesionController {

    @Autowired
    private InicioSesionService inicioSesionService;

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