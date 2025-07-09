package InicioDeSesion.Inicio_De_Sesion.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de Usuario para Inicio de Sesión")
public class UsuarioLogin {

    @Schema(description = "Identificador único del usuario", example = "1")
    private Long id;

    @Schema(description = "Correo electrónico del usuario", example = "xio@duoc.cl")
    private String correo;

    @Schema(description = "Contraseña del usuario", example = "claveSegura123")
    private String clave;  
}