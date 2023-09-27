package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Perfil {
    private Long id_perfil;
    private Confianza confianza;
    private String nombre;
    private String apellido;
    private List<Incidente> monitoreables;
    private Double puntaje;

    public List<Incidente> getIncidentesFraudulentos() {
        return monitoreables.stream()
                .filter(Incidente::incidenteFraudulento) // Filtra por incidenteFraudulento() == true
                .collect(Collectors.toList()); // Convierte el resultado en una lista
    }

    public void actualizarPuntaje(){
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

    public boolean esConReserva(){
        return this.confianza == Confianza.ConReservas;
    }

    private Double puntosASumar(){
        if (monitoreables.size() - this.getIncidentesFraudulentos().size() > 0){
            return 0.5;
        } else {return 0.0;}
    }

    private Double puntosARestar(){
        return this.getIncidentesFraudulentos().size() * 0.2;
    }


}
