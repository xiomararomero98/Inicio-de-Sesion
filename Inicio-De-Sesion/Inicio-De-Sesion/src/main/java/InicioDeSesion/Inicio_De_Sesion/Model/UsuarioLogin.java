package InicioDeSesion.Inicio_De_Sesion.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios_login")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de Usuario para Inicio de Sesión")
public class UsuarioLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del usuario", example = "1")
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Correo electrónico del usuario", example = "xio@duoc.cl")
    private String correo;

    @Column(nullable = false)
    @Schema(description = "Contraseña del usuario", example = "claveSegura123")
    private String clave;  
}