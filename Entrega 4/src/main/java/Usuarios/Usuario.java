package Usuarios;
import Locaciones.Locacion;
import Servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id_usuario;

    @OneToMany(mappedBy ="usuario_asoc" )
    private List<Perfil> perfiles = new ArrayList<>();

    @Column(name = "PASSWORD")
    private String contrasenia;
    @Column(name = "TELEFONO")
    private int nro_telefono;
    @Column(name = "CORREO")
    private String correo;

    public Usuario() {}

    public void addPerfil(Perfil p){
        if(!perfiles.contains(p)){
            perfiles.add(p);
            p.setUsuario_asoc(this);
        }
    }
}
