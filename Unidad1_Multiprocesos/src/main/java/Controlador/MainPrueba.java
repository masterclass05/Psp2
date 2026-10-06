package Controlador;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MainPrueba {
	private static final Logger logger = LogManager.getLogger(MainPrueba.class);


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			Runtime rt = Runtime.getRuntime();
			String[] informacionProceso = {"notePad.exe", "miFichero.txt"};
			Process proceso;
			try {
				proceso = rt.exec(informacionProceso);
				int codigoRetorno = proceso.waitFor(); //Espero a que termine
				logger.debug(codigoRetorno);
			} catch (IOException e) {
				logger.error(e.getMessage());
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}

	
	}

}
