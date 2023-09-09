package Usuarios;
import Locaciones.Locacion;
import Servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class Usuario
{
    @Id
    @GeneratedValue
    private long id_usuario;

    @OneToMany(mappedBy ="id_usuario_asoc" )
    private List<Perfil> perfiles;

    @Column(name = "PASSWORD")
    private String contrasenia;
    @Column(name = "TELEFONO")
    private int nro_telefono;
    @Column(name = "CORREO")
    private String correo;

    public Usuario() {}

}
