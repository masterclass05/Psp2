/*package Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Modelo.GeneraNSSFichero;

public class GeneradorHacienda {
	public static String  rutaResources = "src\\main\\resources\\";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Introduce el nombre del fichero");
		String fichero = scanner.nextLine();
		
		String rutaFinal = rutaResources+fichero;
		
		GeneraNSSFichero gn = new GeneraNSSFichero();
		try {
			gn.escribeNSS(rutaFinal);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
*/