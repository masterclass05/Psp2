package boletin3_2;

import java.util.Random;

public class CalculaMaximoSecuencia {
	public static void main(String[] args) {
		
		long t_comienzo = System.currentTimeMillis();
		CalculaMaximoSecuencia calcula = new CalculaMaximoSecuencia();
		int tamTabla= 10000000;
		int[] tabla = calcula.construyeTablaNotas(tamTabla);
		int maximo = calcula.calculaMax(tabla);
		
		 long t_fin = System.currentTimeMillis();
        long tiempototal = t_fin-t_comienzo;
        System.out.println("El proceso total ha tardado= "+tiempototal+"mseg");
	}
	int calculaMax(int[] tabla) {
		int maxTotal = tabla[0];
		for (int i = 0; i < tabla.length; i++) {
			if (tabla[i] > maxTotal) {
				maxTotal = tabla[i];
			}
		}
		return maxTotal;
	}
	int[] construyeTablaNotas(int tamanyo)
	{
		int [] tabla =  new int [tamanyo];
		Random r = new Random();
		for (int i = 0; i < tabla.length; i++) {
			try {
				int aleatorio = r.nextInt(500)+1;
				tabla[i] = aleatorio;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return tabla;
		
	}
}
