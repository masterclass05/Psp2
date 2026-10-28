package AnalizadorTemperaturas3;

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

    private static final String FICHERO_TEMPERATURAS =
            "src/main/resources/temperaturas.txt";

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
                    FICHERO_TEMPERATURAS,
                    umbral
            );

            procesos.add(proceso);
        }

        int[] resultados = new int[UMBRALES.length];
        int sumaTotal = 0;

        for (int i = 0; i < procesos.size(); i++) {

            Process proceso = procesos.get(i);

            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {

                String linea = lector.readLine();

                if (linea != null) {
                    resultados[i] = Integer.parseInt(linea.trim());
                }

                proceso.waitFor();

            } catch (IOException | InterruptedException e) {
                System.out.println("Error leyendo resultado: "
                        + e.getMessage());
            }
        }

        System.out.println("Se han analizado todas las temperaturas.");
        System.out.println("Resultados:");

        for (int i = 0; i < UMBRALES.length; i++) {

            sumaTotal += resultados[i];

            System.out.println("Umbral " + UMBRALES[i]
                    + " → " + resultados[i] + " días");
        }

        System.out.println("Suma total de días contados: " + sumaTotal);
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

        // Sin inheritIO(): el padre necesita leer la salida del hijo
        try {
            return pb.start();

        } catch (IOException e) {
            System.out.println("Error ejecutando: " + e.getMessage());
            return null;
        }
    }
}