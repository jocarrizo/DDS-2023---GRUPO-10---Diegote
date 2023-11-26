package presentation.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DatoComunidades_x_Perfil {
    private long idComunidad;
//    private String nombreComunidad;
    private boolean rolComunidad;
    private boolean adminDeComunidad;

    public DatoComunidades_x_Perfil() {}

    //TODO: AGREGAR NOMBRE COMUNIDAD
    public DatoComunidades_x_Perfil(long idComunidad, boolean rolComunidad, boolean adminDeComunidad) {
        this.idComunidad = idComunidad;
//        this.nombreComunidad = nombreComunidad;
        this.rolComunidad = rolComunidad;
        this.adminDeComunidad = adminDeComunidad;
    }
}
