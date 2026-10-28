package ContadorPalabras2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Clase Hija
public class ContadorPalabras {
/*
 * Apartado 2
Modifica para que el programa padre se ocupe de:
 Esperar a que terminen todos los subprocesos. 
Una vez que todos terminen, leerá cada uno de los ficheros y recuperarla el resultado de cada subproceso. 
Deberá mostrar: “Todas las palabras aparecen un total de X veces” Y luego para cada palabra:  
“Aparecen X veces la palabra servicio, Y veces la palabra hilo…. 

 * */
    public static void main(String[] args)
            throws FileNotFoundException, IOException {

        String nombreFicheroEntrada = args[0];
        String palabra = args[1];
        String nombreFicheroResultado = args[2];

        contarPalabras(nombreFicheroEntrada, palabra, nombreFicheroResultado);
    }

    public static void contarPalabras(
            String nombreFicheroEntrada,
            String palabra,
            String nombreFicheroResultado)
            throws FileNotFoundException, IOException {

        int contador = 0;

        try (BufferedReader lector = new BufferedReader(
                new FileReader(nombreFicheroEntrada))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                // Divide la línea por espacios, comas, puntos, etc.
                String[] palabras = linea.split("\\W+");

                for (String palabraActual : palabras) {

                    if (palabraActual.equalsIgnoreCase(palabra)) {
                        contador++;
                    }
                }
            }
        }

        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(nombreFicheroResultado))) {

            escritor.write("La palabra '" + palabra
                    + "' aparece " + contador + " veces.");
        }

        System.out.println("Resultado guardado en: "
                + nombreFicheroResultado);
    }
}