package domain.Servicios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class GrupoMonitoreables extends Monitoreable {

    @OneToMany(mappedBy = "grupoAsociado")
    private List<Monitoreable> monitoreables = new ArrayList<>();

    public boolean estaHabilitado(){
        return true;
    }

    public void agregarMonitoreable(){}

    public void removerMonitoreable(){}

    public Monitoreable getMonitoreable(int index){
        return null;
    }

}
