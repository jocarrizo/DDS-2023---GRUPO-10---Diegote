package domain.Entidades;

import domain.Locaciones.Locacion;
import java.util.List;

import domain.Servicios.Incidente;
import domain.Usuarios.Perfil;
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

    @Column(name="NOMBRE")
    private String nombre;

    @OneToMany ( mappedBy = "entidad")
    private List<Establecimiento> establecimientos;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoEntidad tipo;

    @ManyToOne
    @JoinColumn (name = "id_localizacion")
    private Locacion locacion;

    @OneToMany(mappedBy = "entidad")
    private List<Incidente> incidentes;

    @Transient
    private List<Perfil> suscripciones;

    public void agregar_incidente(Incidente incidente){};
    public void agregar_incidente_oficial(Incidente incidente){};
    public void agregar_suscriptor(Perfil perfil){};
    public void remover_suscriptor(Perfil perfil){};

}

