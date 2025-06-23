package InicioDeSesion.Inicio_De_Sesion.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios_login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String clave;  
}