package presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CambiarRolRequest {
    private String idSesion;
    private long idPerfil;
    private long idComunidad;
    private boolean esObservador;

    public CambiarRolRequest() {}

    public CambiarRolRequest(String idSesion, long idPerfil, long idComunidad, boolean rol) {
        this.idSesion = idSesion;
        this.idPerfil = idPerfil;
        this.idComunidad = idComunidad;
        this.esObservador = rol;
    }
}
