package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import domain.Perfil;

@Getter
@Setter
@Entity
public class Comunidad {
    @Id
    @GeneratedValue
    private Long id_comunidad;

    @Transient
    private List<Perfil> perfiles;

    @Column
    private Double puntaje;

    @Enumerated(EnumType.STRING)
    private Confianza confianza;

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

}
