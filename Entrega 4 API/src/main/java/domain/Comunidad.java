package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Comunidad {
    @Id
    @GeneratedValue
    private Long id_comunidad;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Comunidad_x_Perfil",
            joinColumns = { @JoinColumn(name = "comunidad_id") },
            inverseJoinColumns = { @JoinColumn(name = "perfil_id") }
    )
    private List<Perfil> perfiles = new ArrayList<>();

    @Column
    private Double puntaje;

    @Enumerated(EnumType.STRING)
    private Confianza confianza;

    public Comunidad(){}

    public Comunidad(List<Perfil> perfiles, Double puntaje, Confianza confianza) {
        this.perfiles = perfiles;
        this.puntaje = puntaje;
        this.confianza = confianza;
    }

    public Long getId_comunidad() {
        return id_comunidad;
    }

    public void setId_comunidad(Long id_comunidad) {
        this.id_comunidad = id_comunidad;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public Confianza getConfianza() {
        return confianza;
    }

    public void setConfianza(Confianza confianza) {
        this.confianza = confianza;
    }

    public void actualizarPuntaje() {
        double sumaPuntajes = 0.0;
        for (Perfil perfil : perfiles) {
            sumaPuntajes += perfil.getPuntaje();
        }
        if (!perfiles.isEmpty()) {
            puntaje = sumaPuntajes / perfiles.size();
        } else {
            puntaje = 0.0; // Manejo de caso especial para evitar NaN
        }
    }

    public int usuariosConReserva() {
        // Filtra por incidenteFraudulento() == true
        return (int) perfiles.stream()
                .filter(Perfil::esConReserva).count(); // Convierte el resultado en una lista
    }

    public void actualizarConfianza() {
        if (puntaje > 5) {
            confianza = Confianza.ConfiableNivel2;
        } else if (puntaje > 3) {
            confianza = Confianza.ConfiableNivel1;
        } else if (puntaje >= 2) {
            confianza = Confianza.ConReservas;
        } else {
            confianza = Confianza.NoConfiable;
        }
    }

}
