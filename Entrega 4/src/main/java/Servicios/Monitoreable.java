package Servicios;


import Entidades.Tramo;
import Usuarios.Perfil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Monitoreable {

    @Id
    @GeneratedValue
    private long id_monitoreable;

    @Column(name = "NOMBRE")
    private String nombre;

    @ManyToOne
    @JoinColumn(name="ID_GRUPO_MONITOREABLE")
    private GrupoMonitoreables grupoAsociado;

    @OneToOne(mappedBy = "monitoreable")
    private Perfil suscripcionMonitoreable;

    @OneToOne(mappedBy = "monitoreable")
    private Tramo tramoAsociado;
    public boolean estaHabilitado(){
        return true;
    }
    public void agregarMonitoreable(){

    }
    public void removerMonitoreable(){

    }
    public Monitoreable getMonitoreable(int index){

        return null;
    }
}
