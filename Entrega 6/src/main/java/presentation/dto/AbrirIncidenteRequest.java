package presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbrirIncidenteRequest {
    private String idSesion;
    private long idServicio;
    private long idPerfil;
    private long idEntidad;
    private String observaciones;

    public AbrirIncidenteRequest(){}

    public AbrirIncidenteRequest(String idSesion, long idServicio,long idPerfil, long idEntidad, String observaciones) {
        this.idSesion = idSesion;
        this.idServicio = idServicio;
        this.idPerfil = idPerfil;
        this.idEntidad = idEntidad;
        this.observaciones = observaciones;
    }
}
