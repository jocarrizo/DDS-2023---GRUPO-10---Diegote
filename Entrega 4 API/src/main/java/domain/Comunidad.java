package domain;

import java.util.List;

public class Comunidad {
    private long id_comunidad;

    private List<Perfil> perfiles;
    private Float puntaje;
    private Confianza confianza;
    public void actualizarPuntaje() {
        Double puntaje =  perfiles.stream()
                .mapToDouble(Perfil::getPuntaje)
                .average()
                .orElse(0.0); // Manejo de caso especial para evitar NaN
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

    public Float getPuntaje(){
        return puntaje;
    }
    public Long getId(){
        return id_comunidad;
    }

    public Confianza getCategoria(){
        return confianza;
    }
}
