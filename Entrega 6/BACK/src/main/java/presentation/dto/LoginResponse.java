package presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String idSesion;
    private long idUsuario;

    public LoginResponse(String idSesion, long id_usuario) {
        this.idSesion = idSesion;
        this.idUsuario = id_usuario;
    }
}
