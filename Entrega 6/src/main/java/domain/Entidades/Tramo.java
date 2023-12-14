package domain.Entidades;

import domain.Servicios.GrupoMonitoreables;
import domain.Servicios.Monitoreable;
import domain.Servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Entity
public class Tramo {
    @Id
    @GeneratedValue
    private long id_tramo;

    @OneToOne
    @JoinColumn(name="ID_MONITOREABLE")
    private Monitoreable monitoreable;

    @ManyToOne
    @JoinColumn(name="ID_ESTABLECIMIENTO")
    private Establecimiento establecimiento;

    @Column
    private String descripcion;

    public List<Servicio> obtenerServicios() {
        List<Servicio> servicios = new ArrayList<>();



        // Si el monitoreable asociado al tramo es una instancia de GrupoMonitoreables
        // Agrega los servicios contenidos en ese grupo
        if (monitoreable instanceof GrupoMonitoreables) {

            GrupoMonitoreables grupoMonitoreables = (GrupoMonitoreables) monitoreable;
            servicios.addAll(obtenerServiciosDelGrupo(grupoMonitoreables));

        }else servicios.add((Servicio) monitoreable);

        return servicios;
    }

    private List<Servicio> obtenerServiciosDelGrupo(GrupoMonitoreables grupo) {
        List<Servicio> servicios = new ArrayList<>();

        for (Monitoreable monitoreable : grupo.getMonitoreables()) {

            // Si el monitoreable en el grupo es una instancia de GrupoMonitoreables, realiza la llamada recursiva
            if (monitoreable instanceof GrupoMonitoreables) {
                GrupoMonitoreables subGrupo = (GrupoMonitoreables) monitoreable;
                servicios.addAll(obtenerServiciosDelGrupo(subGrupo));
            }else servicios.add((Servicio) monitoreable);

        }

        return servicios;
    }
}
