package AnalizadorTemperaturas4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AnalizadorTemperaturas {

    public static void main(String[] args)
            throws FileNotFoundException, IOException {

        String ficheroEntrada = args[0];
        int umbral = Integer.parseInt(args[1]);

        contarTemperaturas(ficheroEntrada, umbral);
    }

    public static void contarTemperaturas(
            String ficheroEntrada,
            int umbral)
            throws FileNotFoundException, IOException {

        int dias = 0;

        // Si no existe, se propaga FileNotFoundException
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

        System.out.println(dias);
    }
}