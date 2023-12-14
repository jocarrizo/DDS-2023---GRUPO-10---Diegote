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
    @Column(name="TIPO")
    private TipoEntidad tipo;

    @ManyToOne
    @JoinColumn (name = "ID_LOCALIZACION")
    private Locacion locacion;

    @OneToMany(mappedBy = "entidad")
    private List<Incidente> incidentes;

    //TODO
    @Transient
    private List<Perfil> suscripciones;

    @ManyToOne
    @JoinColumn(name = "ID_ENTIDAD_PRESTADORA")
    private EntidadPrestadora entidad_prestadora;

    public void agregar_incidente(Incidente incidente){};
    public void agregar_incidente_oficial(Incidente incidente){};
    public void agregar_suscriptor(Perfil perfil){};
    public void remover_suscriptor(Perfil perfil){};

}

