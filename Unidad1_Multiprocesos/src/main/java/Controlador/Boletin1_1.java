package Controlador;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Boletin1_1 {
	private static final Logger logger = LogManager.getLogger(MainPrueba.class);

	public static void main(String[] args) {
		Runtime rt = Runtime.getRuntime();

		String[] argumentos = {"C:\\Users\\alumno\\AppData\\Local\\Google\\Chrome", "https://www.marca.com/"};

		Process proceso;
		try {
			proceso = rt.exec(argumentos);
			int codigoRetorno = proceso.waitFor(); //Espero a que termine
			logger.info(codigoRetorno);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}

}