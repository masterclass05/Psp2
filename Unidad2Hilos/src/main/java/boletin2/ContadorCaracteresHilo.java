package boletin2;

import java.io.FileReader;
import java.io.IOException;

public class ContadorCaracteresHilo implements Runnable {

    private String archivo;
    private int contador;

    public ContadorCaracteresHilo(String archivo) {
        this.archivo = archivo;
        this.contador = 0;
    }

    @Override
    public void run() {

        try (FileReader lector = new FileReader(archivo)) {

            while (lector.read() != -1) {
                contador++;
            }

            System.out.println("Fichero: " + archivo + " - Caracteres: " + contador);

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + archivo);
        }
    }

    public int getContador() {
        return contador;
    }
}
