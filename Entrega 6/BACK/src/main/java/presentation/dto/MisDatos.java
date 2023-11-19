package presentation.dto;

import domain.Usuarios.Confianza;

public class MisDatos {
    private Long id;
    private String nombre;
    private Confianza categoria;
    private Double puntaje;

    public MisDatos(){}
    public MisDatos(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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