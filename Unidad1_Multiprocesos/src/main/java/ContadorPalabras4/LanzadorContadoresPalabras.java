package ContadorPalabras4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LanzadorContadoresPalabras {

    private static final String FICHERO_ENTRADA =
            "src/main/resources/fichero.txt";

    private static final String FICHERO_QUE_NO_EXISTE =
            "src/main/resources/noExiste.txt";

    private static final String[] PALABRAS = {
        "servicio",
        "hilo",
        "proceso",
        "multihilo",
        "concurrencia"
    };

    public static void main(String[] args) {

        LanzadorContadoresPalabras lanzador =
                new LanzadorContadoresPalabras();

        List<Process> procesos = new ArrayList<>();

        for (String palabra : PALABRAS) {

            Process proceso = lanzador.ejecuta(
                    FICHERO_QUE_NO_EXISTE,
                    palabra
            );

            procesos.add(proceso);
        }

        for (int i = 0; i < procesos.size(); i++) {

            Process proceso = procesos.get(i);

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
                    throw new RuntimeException(
                            "Error en el subproceso de la palabra "
                            + PALABRAS[i] + ".\n"
                            + errores.toString());
                }

                try (BufferedReader lectorSalida = new BufferedReader(
                        new InputStreamReader(proceso.getInputStream()))) {

                    String resultado = lectorSalida.readLine();
                    System.out.println(PALABRAS[i] + ": " + resultado);
                }

            } catch (IOException | InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public Process ejecuta(
            String ficheroEntrada,
            String palabra) {

        String clase = "ContadorPalabras4.ContadorPalabras";

        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                "bin",
                clase,
                ficheroEntrada,
                palabra
        );

        try {
            return pb.start();

        } catch (IOException e) {
            System.out.println("Error al ejecutar: " + e.getMessage());
            return null;
        }
    }
}