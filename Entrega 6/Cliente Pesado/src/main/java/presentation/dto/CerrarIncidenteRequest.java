package presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CerrarIncidenteRequest {
    private String idSesion;
    private long idIncidente;
    private long idPerfil;

    public CerrarIncidenteRequest(){}

    public CerrarIncidenteRequest(String idSesion, long idIncidente, long idPerfil) {
        this.idSesion = idSesion;
        this.idIncidente = idIncidente;
        this.idPerfil = idPerfil;
    }
}
