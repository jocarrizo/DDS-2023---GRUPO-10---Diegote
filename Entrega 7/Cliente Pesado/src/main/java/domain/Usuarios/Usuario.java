package domain.Usuarios;
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
    private long id_usuario
    @OneToMany(mappedBy ="usuario_asoc" )
    private List<Perfil> perfiles = new ArrayList<>();

    @Column(name = "PASSWORD")
    private String contrasenia;
    @Column(name = "TELEFONO")
    private int nro_telefono;
    @Column(name = "CORREO")
    private String correo;

    public Usuario() {}
    public Usuario(String contrasenia, Integer nro_telefono, String correo) {
        this.contrasenia = contrasenia;
        this.nro_telefono = nro_telefono;
        this.correo = correo;
    }
    public void addPerfil(Perfil p){
        if(!perfiles.contains(p)){
            perfiles.add(p);
            p.setUsuario_asoc(this);
        }
    }
}
