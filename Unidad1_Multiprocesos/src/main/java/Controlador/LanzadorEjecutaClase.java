package Controlador;

import java.io.IOException;

public class LanzadorEjecutaClase {

/*	private final static String directorioGenerarClasses = "target\\classes";
	private final static String rutaClase = "Controlador.MainPhyton";
	public int ejecutaClase (String clase) {
		ProcessBuilder pb;
	    int resultado = -1;
	    String claseCompleta =  clase;
	   try {
			String[] comando = {"java", "-cp", directorioGenerarClasses, claseCompleta };
			pb = new ProcessBuilder(comando);
			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process proceso = pb.start();
			int exit = proceso.waitFor();
			System.out.println(exit);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	   return resultado;
}

	public static void main(String[] args) {
		LanzadorEjecutaClase l = new LanzadorEjecutaClase();
		int codigo= l.ejecutaClase(rutaClase);
		if (args.length>1) {
			System.out.println("Recibe como argumentos: "+args[0]+ " y "+args[1]);
		}else {
			System.out.println("Recibo un argumento");
		}
	}*/

}
