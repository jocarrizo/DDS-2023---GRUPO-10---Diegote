package presentation;

import com.opencsv.CSVReader;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public class CargaMasivaHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String[] nombresArchivos = {"EntidadescsvFile", "OrganismoscsvFile"};
        boolean archivoEncontrado = false;

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
            ctx.result("No se encontró ningún archivo CSV");
        }
    }

        public void procesarEntidad_csv(List<String[]> csvData) {
            for (int i = 1; i < csvData.size(); i++) {
                String[] row = csvData.get(i);

                // Suponiendo que el orden de las columnas es: nombre, precio, cantidad
                String att1 = row[0];
                double att2 = Double.parseDouble(row[1]);
                int att3 = Integer.parseInt(row[2]);

                Entidad entidad = new Entidad(att1, att2, att3);

            }
        }

        public void procesarOrganismo_csv(List<String[]> csvData){}

}
