package presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ComunidadAux {
    private Long IDUSUARIO;
    private String FILTRO;
    private Long COMUNIDAD;

    public ComunidadAux (Long IDUSUARIO, String FILTRO, Long COMUNIDAD){
        this.IDUSUARIO = IDUSUARIO;
        this.FILTRO = FILTRO;
        this.COMUNIDAD = COMUNIDAD;
    }
}
