package Temperaturas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class AnalizadorTemperaturas {
	
	private static String rutaResources = "src//main//resources";
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String rutaFichero = args[0];
		int temperatura = Integer.parseInt(args[1]);
		AnalizadorTemperaturas at = new AnalizadorTemperaturas();
		at.diasTemperaturaSuperada(rutaFichero, temperatura);
	}
	
	
	public void diasTemperaturaSuperada(String ruta, int umbral) throws FileNotFoundException {
		try {
		FileReader fr = new FileReader(ruta);
		Scanner in = new Scanner(fr);
		int contador = 0;
		int contador2=0;
		String[] dias;
		while (in.hasNext()) {
			contador2++;
			if (in.nextInt()>umbral) {
				contador++;
				dias.add(in);
			}
		}
		System.out.println(contador);
		System.out.println(contador2);
		String temperatura = String.valueOf(umbral);
		String rutaFinal= (rutaResources+temperatura);
		
		FileWriter fw = new FileWriter(rutaFinal);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
