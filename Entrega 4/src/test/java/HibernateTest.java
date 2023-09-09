import Usuarios.Usuario;
import com.example.hibernate.utils.BDUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;

class HibernateTest {
    @Test
    @DisplayName("Se levanta la base")
    public void cargaBASE(){
    EntityManager em = BDUtils.getEntityManager();
    BDUtils.comenzarTransaccion(em);

    Usuario maxi;
    try {
        maxi = new Usuario();
        maxi.setContrasenia("123456789");
        maxi.setCorreo("maxipiola123@gmail.com");
        maxi.setNro_telefono(1156789123);
        //Insert
        em.persist(maxi);
        assertTrue(true);

    } catch (Exception e) {
        BDUtils.rollback(em);
        fail();
    }

/*
        //Update
        maxi.setNombre("Maximo"); //Entidad administrada -> detecta cambios
        maxi.setApellido("Dabru");
        maxi.setPeso(80);
        maxi.setFecha(new Date());
        maxi.setDocumento(20387419);
        maxi.setEquipoDeCompetencia(dinamita);

        nico.setNombre("Nico"); //Entidad administrada -> detecta cambios
        nico.setApellido("Garcia");
        nico.setPeso(300);
        nico.setFecha(new Date());
        nico.setDocumento(34567891);
        nico.setEquipoDeCompetencia(dinamita);

        caro.setNombre("Caro"); //Entidad administrada -> detecta cambios
        caro.setApellido("Boschini");
        caro.setPeso(45);
        caro.setFecha(new Date());
        caro.setDocumento(12345678);
        caro.setCertificaciones("sarasa");
        caro.setCertificadosEst("mas sarasa");
        caro.setEquipoDeCompetencia(dinamita);

        joaco.setNombre("Joaquin"); //Entidad administrada -> detecta cambios
        joaco.setApellido("Carrizo");
        joaco.setPeso(70);
        joaco.setFecha(new Date());
        joaco.setDocumento(23847419);
        joaco.setEquipoDeCompetencia(dinamita);

        List<Corredor> corredores = new ArrayList<Corredor>();
        corredores.add(maxi);
        corredores.add(nico);

        List<Tecnico> tecnicos = new ArrayList<Tecnico>();
        tecnicos.add(caro);
        tecnicos.add(joaco);

        dinamita.setNombre("DINAMITA");
        dinamita.setCorredores(corredores);
        dinamita.setTecnicos(tecnicos);


*/
        BDUtils.commit(em);
    }
}