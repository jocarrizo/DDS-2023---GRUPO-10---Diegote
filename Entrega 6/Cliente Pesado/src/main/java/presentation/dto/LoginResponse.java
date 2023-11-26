package presentation.dto;

import domain.Usuarios.Perfil;
import domain.Usuarios.Usuario;
import example.hibernate.utils.BDUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LoginResponse {

    private String idSesion;
    private List<DatoPerfil> listadoPerfiles = new ArrayList<>();

    public LoginResponse(String idSesion, Usuario usuario) {
        EntityManager em = BDUtils.getEntityManager();

        List<Perfil> perfiles  =  em.createQuery("SELECT t FROM Perfil t where t.usuario_asoc.id_usuario = ?1", Perfil.class)
                .setParameter(1,usuario.getId_usuario())
                .getResultList();

        em.close();

        this.idSesion = idSesion;

        for(Perfil perfil: perfiles){
            listadoPerfiles.add(new DatoPerfil(perfil.getId_perfil(), perfil.getNombre()));
        }

    }
}
