import Servicios.*;
import Usuarios.Comunidades.*;
import Usuarios.*;
import Entidades.*;
import Locaciones.*;

import com.example.hibernate.utils.BDUtils;
import com.github.javafaker.Faker;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.*;

class PersistenciaTest {
    @Test
    @DisplayName("Se levanta la base")
    public void cargaBASE(){
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);


        Usuario usuario = new Usuario();
        usuario.setContrasenia("contraseña123");
        usuario.setNro_telefono(123456789);
        usuario.setCorreo("usuario@gmail.com");

        Perfil perfil = new Perfil();
        perfil.setNombre("Juan");
        perfil.setApellido("Pérez");
        perfil.setUsuario_asoc(usuario);

        usuario.addPerfil(perfil);

        Monitoreable monitoreable = new Monitoreable();
        monitoreable.setNombre("Monitoreable1");

        Comunidad comunidad = new Comunidad();
        comunidad.addAdministrador(perfil);

        Locacion locacion = new Locacion();  
        locacion.setCalle("Mozart");
        locacion.setAltura(1245);
        locacion.setNombre_barrio("Villa Lugano");
        locacion.setNombre_departamento("deptoNombre");
        locacion.setNombre_municipio("muncNombre");
        locacion.setNombre_provincia("Buenos Aires");

        Entidad entidad = new Entidad();
        entidad.setNombre("Entidad1");
        entidad.setTipo(TipoEntidad.BANCO);
        entidad.setLocacion(locacion);

        Incidente incidente = new Incidente();
        incidente.setMonitoreable(monitoreable);
        incidente.setEntidad(entidad);
        incidente.setComunidad(comunidad);
        incidente.setObservaciones("observaciones");
        incidente.setAbierto(true);

        Establecimiento establecimiento = new Establecimiento();
        establecimiento.setNombre("Establecimiento1");
        establecimiento.setLocacion(locacion);
        establecimiento.setEntidad(entidad);

        Tramo tramo = new Tramo();
        tramo.setDescripcion("Calle-Molinete");
        tramo.setEstablecimiento(establecimiento);
        tramo.setMonitoreable(monitoreable);


        em.persist(usuario);
        em.persist(perfil);
        em.persist(monitoreable);
        em.persist(locacion);
        em.persist(entidad);
        em.persist(incidente);
        em.persist(establecimiento);
        em.persist(tramo);

        Faker faker = new Faker();

        Perfil perfil2 = new Perfil();
        perfil2.setNombre(faker.name().firstName());
        perfil2.setApellido(faker.name().lastName());
        perfil2.setUsuario_asoc(usuario);
        perfil2.setLocacion(locacion);
        comunidad.addObservador(perfil2);

        // Agregar usuarios con datos aleatorios
        for (int i = 0; i < 5; i++) {
            perfil2 = new Perfil();
            perfil2.setNombre(faker.name().firstName());
            perfil2.setApellido(faker.name().lastName());
            perfil2.setUsuario_asoc(usuario);
            perfil2.setLocacion(locacion);

            comunidad.addMiembro(perfil2);
            em.persist(perfil2);
        }
        em.persist(comunidad);

        BDUtils.commit(em);
        assertTrue(true);
    }
}