
package boletin2;

import java.io.File;

public class GestionaFicheros {

    public static void main(String[] args) {

   
        File carpeta = new File("src/main/resources");

  
        File[] ficheros = carpeta.listFiles();

        if (ficheros == null) {
            System.out.println("No se ha podido acceder a la carpeta");
        }
        for (File fichero : ficheros) {
            if (fichero.isFile()) {             
                RecorreFichero tarea = new RecorreFichero(fichero);              
                Thread hilo = new Thread(tarea);                
                hilo.start();
            }
        }
    }
}
