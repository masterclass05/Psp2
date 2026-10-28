package Controlador;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Boletin1_1 {
	private static final Logger logger = LogManager.getLogger(Boletin1_1.class);


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runtime rt = Runtime.getRuntime();
		

		String[] argumentos = {"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe\\", "https://www.marca.com/"};

		try {
			rt.exec(argumentos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
