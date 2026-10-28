package ContadorPalabras2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        List<Integer> codigosSalida = new ArrayList<>();

        // Lanza los cinco procesos hijos
        for (String palabra : PALABRAS) {

            String ficheroResultado = palabra + ".txt";

            Process proceso = lanzador.ejecuta(
                    FICHERO_ENTRADA,
                    palabra,
                    ficheroResultado
            );

            procesos.add(proceso);
        }

        // Espera a que terminen todos
        for (Process proceso : procesos) {

            try {
                int codigo = proceso.waitFor();
                codigosSalida.add(codigo);

            } catch (InterruptedException e) {
                System.out.println("Error esperando proceso");
                codigosSalida.add(-1);
            }
        }

        // Cuando todos han terminado, lee los resultados
        int total = 0;

        System.out.println("Todas las palabras aparecen un total de:");

        StringBuilder detalle = new StringBuilder();

        for (int i = 0; i < PALABRAS.length; i++) {

            String palabra = PALABRAS[i];
            String ficheroResultado = palabra + ".txt";

            int veces = lanzador.leerResultado(ficheroResultado);

            total += veces;

            detalle.append(veces)
                   .append(" veces la palabra ")
                   .append(palabra);

            if (i < PALABRAS.length - 1) {
                detalle.append(", ");
            }
        }

        System.out.println(total + " veces");

        System.out.println("Aparecen " + detalle.toString() + ".");
    }

    public Process ejecuta(
            String ficheroEntrada,
            String palabra,
            String ficheroResultado) {

        String clase = "ContadorPalabras2.ContadorPalabras";

        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                "bin",
                clase,
                ficheroEntrada,
                palabra,
                ficheroResultado
        );

        pb.inheritIO();

        try {
            return pb.start();

        } catch (IOException e) {
            System.out.println("Error al ejecutar: " + e.getMessage());
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