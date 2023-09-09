package Servicios;


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
