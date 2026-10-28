package AnalizadorTemperaturas2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

            String ficheroResultado = umbral + ".txt";

            Process proceso = lanzador.ejecuta(
                    FICHERO_TEMPERATURAS,
                    umbral,
                    ficheroResultado
            );

            procesos.add(proceso);
        }

        for (Process proceso : procesos) {

            try {
                proceso.waitFor();

            } catch (InterruptedException e) {
                System.out.println("Error esperando un subproceso");
            }
        }

        System.out.println("Se han analizado todas las temperaturas.");
        System.out.println("Resultados:");

        int sumaTotal = 0;

        for (int umbral : UMBRALES) {

            String ficheroResultado = umbral + ".txt";
            int dias = lanzador.leerResultado(ficheroResultado);

            sumaTotal += dias;

            System.out.println("Umbral " + umbral
                    + " → " + dias + " días");
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
            return pb.start();

        } catch (IOException e) {
            System.out.println("Error ejecutando: " + e.getMessage());
            return null;
        }
    }

    public int leerResultado(String ficheroResultado) {

        try (BufferedReader lector = new BufferedReader(
                new FileReader(ficheroResultado))) {

            String linea = lector.readLine();

            if (linea != null) {
                return Integer.parseInt(linea.trim());
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error leyendo " + ficheroResultado);
        }

        return 0;
    }
}