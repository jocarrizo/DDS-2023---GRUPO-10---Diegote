package presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DatosIncidente {

    private long id_incidente;
    private long id_servicio;
    private String nombre_servicio;

    private long id_entidad;
    private String nombre_entidad;

    private Date apertura;
    private String apertura_aux;

    public DatosIncidente(long id_incidente, long id_servicio,  long id_entidad, String nombre_servicio, String nombre_entidad, Date apertura) {
        this.id_incidente = id_incidente;
        this.id_servicio = id_servicio;
        this.nombre_servicio = nombre_servicio;
        this.id_entidad = id_entidad;
        this.nombre_entidad = nombre_entidad;
        this.apertura = apertura;
    }
}
