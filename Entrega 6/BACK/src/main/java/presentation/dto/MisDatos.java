package presentation.dto;

import domain.Usuarios.Confianza;

public class MisDatos {
    private Long id;
    private Confianza categoria;
    private Double puntaje;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Confianza getCategoria() {
        return categoria;
    }

    public void setCategoria(Confianza categoria) {
        this.categoria = categoria;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }
}