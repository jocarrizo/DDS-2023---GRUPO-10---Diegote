package presentation.dto;
import domain.Confianza;

import java.util.List;

public class MisDatos {

    private Long id;
    private Confianza categoria;
    private Float puntaje;

    public Long getId() {
        return id;
    }

    public void setId(Long ID) {
        this.id = ID;
    }

    public Float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Float puntos) {
        this.puntaje = puntos;
    }

    public Confianza getCategoria() {
        return categoria;
    }

    public void setCategoria(Confianza confianza) {
        this.categoria = confianza;
    }
}