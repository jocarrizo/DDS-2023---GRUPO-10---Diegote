import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CargarDatos {
    private static CargarDatos instance = null;
    private static String[][] organismos_matriz;
    private static String[][] entidades_matriz;

    private CargarDatos() {
        leerCSVs();
    }

    public static CargarDatos getInstance() {
        if (instance == null) {
            instance = new CargarDatos();
        }
        return instance;
    }

    public static void leerCSVs() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/java/Organismos De Control.csv"));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

            List<String[]> organismosList = new ArrayList<>();
            for (CSVRecord record : records) {
                String[] row = {record.get(0)};
                organismosList.add(row);
            }
            organismos_matriz = organismosList.toArray(new String[0][1]);
            reader.close();

            reader = Files.newBufferedReader(Paths.get("src/main/java/Entidades.csv"));
            records = CSVFormat.DEFAULT.parse(reader);

            List<String[]> entidadesList = new ArrayList<>();
            for (CSVRecord record : records) {
                String[] row = {record.get(0), record.get(1)};
                entidadesList.add(row);
            }
            entidades_matriz = entidadesList.toArray(new String[0][2]);
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static String[][] getOrganismosData() {
        return organismos_matriz;
    }

    public static String[][] getEntidadesData() {
        return entidades_matriz;
    }
}
