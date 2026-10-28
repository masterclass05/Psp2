package Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Boletin1_2 {
	private static final Logger logger = LogManager.getLogger(Boletin1_2.class);


	public static void main(String[] args){
		// TODO Auto-generated method stub
		try {
		Process proceso = Runtime.getRuntime().exec("tasklist");
		
		BufferedReader lector = new BufferedReader(
                new InputStreamReader(proceso.getInputStream())
            );

		String linea;
        // Muestra el resultado línea a línea
        while ((linea = lector.readLine()) != null) {
            System.out.println(linea);
        }

        // Cierra el lector y espera a que el proceso termine
        lector.close();
        proceso.waitFor();

    } catch (Exception e) {
    	logger.error("Ha habido un fallo");
    }
		
	}

}