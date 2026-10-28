package Controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanzaComandosJava {
	private static final Logger logger = LogManager.getLogger(MainPrueba.class);

	private final static String directorioGenerarClasses = "target\\classes";
	private final static String rutaSourceJava = "src\\main\\java\\unidad1\\ProcesoHijo.java";

	public int lanzaCompilador(String rutaFicheroFuente, String rutaParaGenerarClass) {
		ProcessBuilder pb;
		int exit = 0;
		try {
			String[] comando = { "javac", "-d", directorioGenerarClasses, rutaSourceJava};
			pb = new ProcessBuilder(comando);
			pb.inheritIO(); // La salidas y los errores se ven por consola
			Process proceso = pb.start();
			 exit = proceso.waitFor(); //esperamos a que javac termine 
			logger.debug("javac terminó con código {}", exit); 

		} catch (Exception e) { 			
			logger.error(e.getMessage()); 		
			}
		return exit;
	}
	public static void main(String[] args) {
		LanzaComandosJava l = new LanzaComandosJava();
		int codigo= l.lanzaCompilador(rutaSourceJava, directorioGenerarClasses);                 
	}
	}
