package ContadorPalabras3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LanzadorContadoresPalabras {

    private static final String FICHERO_ENTRADA =
            "src/main/resources/fichero.txt";

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
                    FICHERO_ENTRADA,
                    palabra
            );

            procesos.add(proceso);
        }

        int total = 0;
        int[] resultados = new int[PALABRAS.length];

        // Espera y lee la salida de cada hijo
        for (int i = 0; i < procesos.size(); i++) {

            Process proceso = procesos.get(i);

            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {

                String linea = lector.readLine();

                if (linea != null) {
                    resultados[i] = Integer.parseInt(linea.trim());
                }

                int codigo = proceso.waitFor();

                if (codigo != 0) {
                    System.out.println("El proceso "
                            + PALABRAS[i] + " terminó con error.");
                }

            } catch (IOException | InterruptedException e) {
                System.out.println("Error leyendo resultado: "
                        + e.getMessage());
            }
        }

        for (int resultado : resultados) {
            total += resultado;
        }

        System.out.println("Todas las palabras aparecen un total de "
                + total + " veces");

        StringBuilder detalle = new StringBuilder();

        for (int i = 0; i < PALABRAS.length; i++) {

            detalle.append(resultados[i])
                   .append(" veces la palabra ")
                   .append(PALABRAS[i]);

            if (i < PALABRAS.length - 1) {
                detalle.append(", ");
            }
        }

        System.out.println("Aparecen " + detalle + ".");
    }

    public Process ejecuta(
            String ficheroEntrada,
            String palabra) {

        String clase = "ContadorPalabras3.ContadorPalabras";

        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                "bin",
                clase,
                ficheroEntrada,
                palabra
        );

        // Importante: NO usar pb.inheritIO()
        // Si lo usas, el padre no podrá leer la salida del hijo.

        try {
            return pb.start();

        } catch (IOException e) {
            System.out.println("Error al ejecutar: " + e.getMessage());
            return null;
        }
    }
}