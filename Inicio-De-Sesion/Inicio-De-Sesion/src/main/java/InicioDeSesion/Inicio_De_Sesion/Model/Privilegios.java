package InicioDeSesion.Inicio_De_Sesion.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "privilegios")
public class Privilegios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_privilegios")
    private Long id;


    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "privilegio")
    private List <Permisos> permisos;

}
