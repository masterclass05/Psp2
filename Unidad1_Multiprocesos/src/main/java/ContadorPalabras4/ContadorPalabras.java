package ContadorPalabras4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContadorPalabras {
/*
 * Apartado 4
Modifica el programa anterior para que se propague la excepción de Hijo a Padre. 
Simula este caso enviando un fichero que no existe.

 * */
    public static void main(String[] args)
            throws FileNotFoundException, IOException {

        String ficheroEntrada = args[0];
        String palabra = args[1];

        contarPalabras(ficheroEntrada, palabra);
    }

    public static void contarPalabras(
            String ficheroEntrada,
            String palabra)
            throws FileNotFoundException, IOException {

        int contador = 0;

        // Si el fichero no existe, se lanza FileNotFoundException
        try (BufferedReader lector = new BufferedReader(
                new FileReader(ficheroEntrada))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] palabras = linea.split("\\W+");

                for (String palabraActual : palabras) {

                    if (palabraActual.equalsIgnoreCase(palabra)) {
                        contador++;
                    }
                }
            }
        }

        System.out.println(contador);
    }
}