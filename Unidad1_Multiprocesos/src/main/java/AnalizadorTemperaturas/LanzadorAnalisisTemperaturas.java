package AnalizadorTemperaturas;

import java.io.File;
import java.io.IOException;

public class LanzadorAnalisisTemperaturas {

    private static final String CARPETA_CLASSES = "target/classes";
    private static final String RUTA_CLASE_JAVA = "src/AnalizadorTemperaturas/AnalizadorTemperaturas.java";

    private static final String FICHERO_TEMPERATURAS = "src/main/resources/temperaturas.txt";

    private static final int[] UMBRALES = {10, 20, 25, 30, 35};

    public static void main(String[] args) {

        LanzadorAnalisisTemperaturas lanzador =
                new LanzadorAnalisisTemperaturas();

        int codigoCompilacion = lanzador.compila();

        if (codigoCompilacion != 0) {
            System.out.println("Error al compilar AnalizadorTemperaturas");
            return;
        }

        for (int umbral : UMBRALES) {

            String ficheroResultado = umbral + ".txt";

            int codigo = lanzador.ejecuta(
                    FICHERO_TEMPERATURAS,
                    umbral,
                    ficheroResultado
            );

            System.out.println("Proceso con umbral " + umbral
                    + " terminado con código: " + codigo);
        }
    }

    public int compila() {

        ProcessBuilder pb = new ProcessBuilder(
                "javac",
                "-d",
                CARPETA_CLASSES,
                RUTA_CLASE_JAVA
        );

        pb.directory(new File(System.getProperty("user.dir")));
        pb.inheritIO();

        try {
            Process proceso = pb.start();
            return proceso.waitFor();

        } catch (IOException | InterruptedException e) {
            System.out.println("Error compilando: " + e.getMessage());
            return -1;
        }
    }

    public int ejecuta(
            String ficheroEntrada,
            int umbral,
            String ficheroResultado) {

        String clase = "AnalizadorTemperaturas.AnalizadorTemperaturas";

        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                CARPETA_CLASSES,
                clase,
                ficheroEntrada,
                String.valueOf(umbral),
                ficheroResultado
        );

        pb.directory(new File(System.getProperty("user.dir")));
        pb.inheritIO();

        try {
            Process proceso = pb.start();
            return proceso.waitFor();

        } catch (IOException | InterruptedException e) {
            System.out.println("Error ejecutando: " + e.getMessage());
            return -1;
        }
    }
}