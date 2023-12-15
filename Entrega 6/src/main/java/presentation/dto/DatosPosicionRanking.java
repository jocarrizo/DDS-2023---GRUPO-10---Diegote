package presentation.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosPosicionRanking {
    private String nombreEntidad;
    private float puntaje;

    public DatosPosicionRanking() {}
    public DatosPosicionRanking(String nombreEntidad, float puntaje) {
        this.nombreEntidad = nombreEntidad;
        this.puntaje = puntaje;
    }
}
