package InicioDeSesion.Inicio_De_Sesion.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Long id;

    @Column
    private String calle;

    @Column
    private Integer numeracion;

    @Column
    private Integer numeroDepartamento;

    @Column
    private String torre;

    @ManyToOne
    @JoinColumn(name = "Usuarios_id_usuarios", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "Comuna_id_comuna", nullable = false)
    @JsonBackReference
    private Comuna comuna;

}
