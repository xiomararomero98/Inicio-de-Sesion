package InicioDeSesion.Inicio_De_Sesion.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Comuna")
public class Comuna {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comuna")
    private Long id;

    @Column(name = "nombre_comuna", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "Region_id_region", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "comuna")
    @JsonManagedReference
    private List<Direccion> direcciones;

}
