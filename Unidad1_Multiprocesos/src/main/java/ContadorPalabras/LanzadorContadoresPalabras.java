package ContadorPalabras;

import java.io.File;
import java.io.IOException;

//Clase Padre
public class LanzadorContadoresPalabras {

    private static final String CARPETA_CLASSES = "target/classes";
    private static final String RUTA_FICHERO_JAVA =
            "src/main/java/ContadorPalabras/ContadorPalabras.java";

    private static final String FICHERO_ENTRADA = "src/main/resources/texto.txt";

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

        int codigoCompilacion = lanzador.compila();

        if (codigoCompilacion != 0) {
            System.out.println("Error al compilar ContadorPalabras");
            return;
        }

        for (String palabra : PALABRAS) {

            String ficheroResultado = palabra + ".txt";

            int codigoEjecucion = lanzador.ejecuta(
                    FICHERO_ENTRADA,
                    palabra,
                    ficheroResultado
            );

            System.out.println("Proceso para '" + palabra
                    + "' terminado con código: " + codigoEjecucion);
        }
    }

    public int compila() {

        ProcessBuilder pb = new ProcessBuilder(
                "javac",
                "-d",
                CARPETA_CLASSES,
                RUTA_FICHERO_JAVA
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
            String palabra,
            String ficheroResultado) {

        String clase = "ContadorPalabras.ContadorPalabras";

        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                CARPETA_CLASSES,
                clase,
                ficheroEntrada,
                palabra,
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