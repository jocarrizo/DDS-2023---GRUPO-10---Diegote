import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CargarDatos {
    public static void leerArchivo(){
        try {
            // archivo
            Reader reader = Files.newBufferedReader(Paths.get("src/main/java/Organismos De Control.csv"));
            // reader
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : records) {
                String columnOne = record.get(0);
                String columnTwo = record.get(1);
                // Agregar columnas en caso de necesitarlas
                // Realizar lo que se necesite aca.
                System.out.println(columnOne + " ; " + columnTwo);
            }

            reader = Files.newBufferedReader(Paths.get("src/main/java/Entidades.csv"));

            records = CSVFormat.DEFAULT.parse(reader);

            for (CSVRecord record : records) {
                String columnOne = record.get(0);
                String columnTwo = record.get(1);
                // Agregar columnas en caso de necesitarlas
                System.out.println(columnOne + " ; " + columnTwo);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
