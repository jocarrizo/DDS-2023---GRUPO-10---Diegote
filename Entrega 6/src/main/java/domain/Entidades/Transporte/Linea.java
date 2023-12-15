package domain.Entidades.Transporte;


import domain.Entidades.Entidad;
import domain.Entidades.Establecimiento;

public class Linea extends Entidad {


    private Establecimiento estacionOrigen;

    private  Establecimiento estacionDestino;

    private ServicioPublicodeTransporte servicioPublicodeTransporte;

    public Linea() {
    }

    public Linea(Establecimiento estacionOrigen, Establecimiento estacionDestino, ServicioPublicodeTransporte servicioPublicodeTransporte) {
        this.estacionOrigen = estacionOrigen;
        this.estacionDestino = estacionDestino;
        this.servicioPublicodeTransporte = servicioPublicodeTransporte;
    }
}
