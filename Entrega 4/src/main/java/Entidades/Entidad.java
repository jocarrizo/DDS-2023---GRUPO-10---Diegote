package Entidades;

import Locaciones.Locacion;
import Servicios.*;
import Usuarios.*;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Entidad {

    @Id
    @GeneratedValue
    private long id_entidad;

    private String nombre;

    @OneToMany (mappedBy = "id_establecimiento")
    private List<Establecimiento> establecimientos;

    private TipoEntidad tipo;

    @ManyToOne
    @JoinColumn (name = "id_localizacion")
    private Locacion locacion;

    @Transient
    private List<Incidente> incidentes;

    @Transient
    private List<Perfil> suscripciones;

    public void agregar_incidente(Incidente incidente){};
    public void agregar_incidente_oficial(Incidente incidente){};
    public void agregar_suscriptor(Perfil perfil){};
    public void remover_suscriptor(Perfil perfil){};

}

