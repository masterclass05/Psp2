package AnalizadorTemperaturas2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AnalizadorTemperaturas {

    public static void main(String[] args)
            throws FileNotFoundException, IOException {

        String ficheroEntrada = args[0];
        int umbral = Integer.parseInt(args[1]);
        String ficheroResultado = args[2];

        contarTemperaturas(ficheroEntrada, umbral, ficheroResultado);
    }

    public static void contarTemperaturas(
            String ficheroEntrada,
            int umbral,
            String ficheroResultado)
            throws FileNotFoundException, IOException {

        int dias = 0;

        try (BufferedReader lector = new BufferedReader(
                new FileReader(ficheroEntrada))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                linea = linea.trim();

                if (!linea.isEmpty()) {

                    double temperatura = Double.parseDouble(linea);

                    if (temperatura >= umbral) {
                        dias++;
                    }
                }
            }
        }

        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(ficheroResultado))) {

            escritor.write(String.valueOf(dias));
        }
    }
}