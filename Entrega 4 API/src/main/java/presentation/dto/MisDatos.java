package presentation.dto;
import domain.Confianza;

import java.util.List;

public class MisDatos {

    private Long id;
    private Confianza categoria;
    private Double puntaje;
    private String tipoDato;

    public Long getId() {
        return id;
    }

    public void setId(Long ID) {
        this.id = ID;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntos) {
        this.puntaje = puntos;
    }
    public String getTipo() {
        return tipoDato;
    }
    public void setTipo(String tipo) {
        this.tipoDato = tipo;
    }

    public Confianza getCategoria() {
        return categoria;
    }

    public void setCategoria(Confianza confianza) {
        this.categoria = confianza;
    }
}