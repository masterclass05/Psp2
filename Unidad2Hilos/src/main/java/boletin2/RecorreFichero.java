
package boletin2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RecorreFichero implements Runnable {

    private File fichero;

    public RecorreFichero(File fichero) {
        this.fichero = fichero;
    }

    @Override
    public void run() {

        int numCaracteres = 0;

        try (FileReader fr = new FileReader(fichero)) {

            while (fr.read() != -1) {
                numCaracteres++;
            }

            System.out.println("Fichero: " + fichero.getName()
                    + " - Número de caracteres: " + numCaracteres);

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: "
                    + fichero.getName());
        }
    }
}

