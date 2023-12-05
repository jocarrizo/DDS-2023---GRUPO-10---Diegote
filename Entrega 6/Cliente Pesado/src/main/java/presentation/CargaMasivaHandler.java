package presentation;

import com.opencsv.CSVReader;

import example.hibernate.utils.BDUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.*;
import java.util.Arrays;
import java.util.List;


public class CargaMasivaHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        List<String> nombresArchivos = Arrays.asList("EntidadescsvFile", "OrganismoscsvFile");
        boolean archivoEncontrado = false;

        String a = ctx.body();
        for (String fieldName : nombresArchivos) {
            UploadedFile uploadedFile = ctx.uploadedFile(fieldName);

            if (uploadedFile != null) {
                InputStream data = uploadedFile.content();
                Reader reader = new InputStreamReader(data);
                CSVReader csvReader = new CSVReader(reader);
                List<String[]> csvData = csvReader.readAll();

                switch (fieldName) {
                    case "EntidadescsvFile":
                        procesarEntidad_csv(csvData);
                        ctx.result("Procesando archivo EntidadescsvFile");
                        break;
                    case "OrganismoscsvFile":
                        procesarOrganismo_csv(csvData);
                        ctx.result("Procesando archivo OrganismoscsvFile");
                        break;
                }

                archivoEncontrado = true;
                break; // Salir del bucle una vez que se haya encontrado y procesado un archivo
            }
        }

        if (!archivoEncontrado) {
            ctx.result("No se pudo guardar el csv.");
        }
    }

    public void procesarEntidad_csv(List<String[]> csvData) {
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        try {

            for (int i = 1; i < csvData.size(); i++) {
                String[] row = csvData.get(i);

                String sql = "INSERT INTO Entidad (id_entidad, nombre, establecimiento, tipo, locacion, incidentes, id_perfil) VALUES (?, ?, ?, ?, ?, ?, ?)";

                Query query = em.createNativeQuery(sql);

                query.setParameter(1, Long.valueOf(row[0]));
                query.setParameter(2, row[1]);
                query.setParameter(3, Long.valueOf(row[2]));
                query.setParameter(4, String.valueOf(row[3]));
                query.setParameter(5, Long.valueOf(row[4]));
                query.setParameter(6, Long.valueOf(row[5]));
                query.setParameter(7, Long.valueOf(row[6]));
                query.executeUpdate();

            }

            BDUtils.commit(em);
        } catch (Exception e) {
            BDUtils.rollback(em);
            e.printStackTrace();
        }
        em.close();
    }
        public void procesarOrganismo_csv(List<String[]> csvData){}

}
