package Suministros;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcesadorSuministrosPorPunto {
	public static double precioEnergia = 0.30;
	public static String rutaYNombre = "src\\main\\resources\\recargas.txt";
	
	private String nombreP;
	private List<String> datos;
	
	
	
	
	public ProcesadorSuministrosPorPunto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public static double getPrecioEnergia() {
		return precioEnergia;
	}


	public static void setPrecioEnergia(double precioEnergia) {
		ProcesadorSuministrosPorPunto.precioEnergia = precioEnergia;
	}


	public static String getRutaYNombre() {
		return rutaYNombre;
	}


	public static void setRutaYNombre(String rutaYNombre) {
		ProcesadorSuministrosPorPunto.rutaYNombre = rutaYNombre;
	}


	public String getNombreP() {
		return nombreP;
	}


	public void setNombreP(String nombreP) {
		this.nombreP = nombreP;
	}


	public List<String> getDatos() {
		return datos;
	}


	public void setDatos(List<String> datos) {
		this.datos = datos;
	}


	public ProcesadorSuministrosPorPunto(String nombreP, List<String> datos) {
		super();
		this.nombreP = nombreP;
		this.datos = datos;
	}


	public void leeFichero() {
		Scanner in = null;		
		Double total = 0.0;
		List<String> lista = new ArrayList<String>();
		try {
			FileReader fichero = new FileReader(rutaYNombre);
			in = new Scanner(fichero);
			while (in.hasNext()) { 
				
				lista.clear();
				String frase = in.nextLine();
				String[] conjunto = frase.split(";");
				for (int i = 1; i < conjunto.length; i++) {
					lista.add(conjunto[i]);
					Double nuevo = Double.parseDouble(conjunto[i]);
					total+= nuevo;

				}
				
				ProcesadorSuministrosPorPunto p1 = new ProcesadorSuministrosPorPunto(conjunto[0], lista);
				p1.importeTotal(conjunto[0], lista);
			}
			total = total*precioEnergia;
			System.out.println("TOTAL facturado: " + total);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Override
	public String toString() {
		return "ProcesadorSuministrosPorPunto [nombreP=" + nombreP + ", datos=" + datos + "]";
	}


	public void importeTotal(String nombre, List<String> energia) {
		int total = 0;
		List<Integer> lista = new ArrayList<Integer>();
		for (String s : energia) {
			Integer nuevo = Integer.parseInt(s);
			lista.add(nuevo);
			total += nuevo;
		}

		Double totalPrecio = total*precioEnergia;
		System.out.println(nombre+";"+total+";"+totalPrecio);
	}
	

	
}

