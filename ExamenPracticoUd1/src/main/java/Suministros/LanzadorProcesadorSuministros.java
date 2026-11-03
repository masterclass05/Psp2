package Suministros;

import java.util.ArrayList;
import java.util.List;

public class LanzadorProcesadorSuministros {

	public static void main(String[] args) {

		ProcesadorSuministrosPorPunto p = new ProcesadorSuministrosPorPunto();
		
		System.out.println("--- Resumen de facturación ---");
		p.leeFichero();
		
	}

}
