package Controlador;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Boletin1_1 {
	private static final Logger logger = LogManager.getLogger(MainPrueba.class);

	public static void main(String[] args) {
		Runtime rt = Runtime.getRuntime();

		

		String[] argumentos = {"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe\\", "https://www.marca.com/"};

		

		Process proceso;
		try {
			proceso = rt.exec(argumentos);
			int codigoRetorno = proceso.waitFor(); //Espero a que termine
			logger.info(codigoRetorno);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

		
	

