package AnalizadorTemperaturas4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LanzadorAnalisisTemperaturas {

    private static final String CARPETA_CLASSES = "bin";
    private static final String RUTA_CLASE_JAVA =
            "src/AnalizadorTemperaturas/AnalizadorTemperaturas.java";

    private static final String FICHERO_QUE_NO_EXISTE =
            "src/main/resources/noExiste.txt";

    private static final int[] UMBRALES = {10, 20, 25, 30, 35};

    public static void main(String[] args) {

        LanzadorAnalisisTemperaturas lanzador =
                new LanzadorAnalisisTemperaturas();

        int codigoCompilacion = lanzador.compila();

        if (codigoCompilacion != 0) {
            System.out.println("Error al compilar AnalizadorTemperaturas");
            return;
        }

        List<Process> procesos = new ArrayList<>();

        for (int umbral : UMBRALES) {

            Process proceso = lanzador.ejecuta(
                    FICHERO_QUE_NO_EXISTE,
                    umbral
            );

            procesos.add(proceso);
        }

        for (int i = 0; i < procesos.size(); i++) {

            Process proceso = procesos.get(i);
            int umbral = UMBRALES[i];

            try {
                StringBuilder errores = new StringBuilder();

                try (BufferedReader lectorError = new BufferedReader(
                        new InputStreamReader(proceso.getErrorStream()))) {

                    String lineaError;

                    while ((lineaError = lectorError.readLine()) != null) {
                        errores.append(lineaError).append("\n");
                    }
                }

                int codigoSalida = proceso.waitFor();

                if (codigoSalida != 0) {
                    System.out.println(
                        "Error: El proceso con umbral " + umbral
                        + " ha fallado. Motivo: fichero no encontrado."
                    );
                } else {
                    System.out.println("Umbral " + umbral
                            + " finalizado correctamente.");
                }

            } catch (IOException | InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
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

    public Process ejecuta(
            String ficheroEntrada,
            int umbral) {

        String clase = "AnalizadorTemperaturas.AnalizadorTemperaturas";

        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                CARPETA_CLASSES,
                clase,
                ficheroEntrada,
                String.valueOf(umbral)
        );

        try {
            return pb.start();

        } catch (IOException e) {
            System.out.println("Error ejecutando: " + e.getMessage());
            return null;
        }
    }
}