package InicioDeSesion.Inicio_De_Sesion.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name="Rol")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;
    


    @Column
    private String nombre;


    //identificar relacion con usuarios
    @OneToMany(mappedBy = "rol", cascade =CascadeType.ALL)
    @JsonIgnore
    private List <Usuario> users;

    //identificar relacion con permisos

    @OneToMany(mappedBy = "rol", cascade =CascadeType.ALL)
    @JsonIgnore
    private List <Permisos> permisos;

    //carga de datos

    public Rol(Long id, String nombre){
        this.id =id;
        this.nombre= nombre;

}
}
