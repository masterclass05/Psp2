package Modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class GeneraNSSFichero {
	private String nss;
	private String nombre;
	
	public GeneraNSSFichero() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeneraNSSFichero(String nss, String nombre) {
		super();
		this.nss = nss;
		this.nombre = nombre;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	
	public void escribeNSS(String rutaYNombre) throws FileNotFoundException, IOException {
		Scanner in = null;
		List<GeneraNSSFichero> listaNss = null;
		PrintWriter out = null;
		FileWriter ficheroSalida = new FileWriter("src\\main\\resources\\NSSs.txt");
		out = new PrintWriter(ficheroSalida);
		try {
			FileReader fichero = new FileReader(rutaYNombre);
			in = new Scanner(fichero);
			while (in.hasNext()) { 
				String frase = in.nextLine();
				String[] conjunto = frase.split(",");
				for (int i = 0; i < conjunto.length; i++) {
					if (frase.startsWith("AN")) {
						GeneraNSSFichero g1 = new GeneraNSSFichero(conjunto[0],conjunto[1]);
						out.print(g1);
						}
				}

				}
			

		}catch (FileNotFoundException e) { 
			e.printStackTrace(); 
			
		}catch (IOException e) { 
			e.printStackTrace(); 
			
		} finally {
			if (in != null) {
				in.close();
			}
			}
		

			
		}

	@Override
	public String toString() {
		return nss + ", " + nombre + "\n";
	}
	
}
