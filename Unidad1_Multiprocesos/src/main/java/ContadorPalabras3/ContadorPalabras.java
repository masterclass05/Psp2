package ContadorPalabras3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContadorPalabras {
/*
 * Apartado 3
Modifica la clase ContadorPalabras para que en lugar de escribir el número de palabras que contiene en un 
fichero txt,  lo haga por consola (System.out.println) y el proceso Padre lo lea del InputStream.

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

        // El padre leerá esta línea desde getInputStream()
        System.out.println(contador);
    }
}