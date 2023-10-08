package domain;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Perfil {
    @Id
    @GeneratedValue
    private Long id_perfil;

    @Enumerated(EnumType.STRING)
    private Confianza confianza;

    @OneToMany(mappedBy = "id_perfil_apertura", cascade = CascadeType.ALL)
    private List<Incidente> incidentes;

    @Column
    private Double puntaje = 5.0;

    public Perfil() {}

    public Long getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(Long id_perfil) {
        this.id_perfil = id_perfil;
    }

    public Confianza getConfianza() {
        return confianza;
    }

    public void setConfianza(Confianza confianza) {
        this.confianza = confianza;
    }

    public List<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public Perfil(Confianza confianza, List<Incidente> incidentes, Double puntaje) {
        this.confianza = confianza;
        this.incidentes = incidentes;
        this.puntaje = puntaje;
    }

    public List<Incidente> getIncidentesFraudulentos() {
        return incidentes.stream()
                .filter(Incidente::incidenteFraudulento)
                .collect(Collectors.toList());
    }

    public void actualizarPuntaje() {
        puntaje = puntaje - this.puntosARestar() + this.puntosASumar();
        this.actualizarConfianza();
    }

    private void actualizarConfianza() {
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

    public boolean esConReserva() {
        return this.confianza == Confianza.ConReservas;
    }

    private Double puntosASumar() {
        if (incidentes.size() - this.getIncidentesFraudulentos().size() > 0) {
            return 0.5;
        } else {
            return 0.0;
        }
    }

    private Double puntosARestar() {
        return this.getIncidentesFraudulentos().size() * 0.2;
    }
}
