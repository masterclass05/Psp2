package boletin3;

public class CalculaMaxHilo implements Runnable{
	private int[] tabla;
	private int inicio;
	private int fin;
	private int maximoTramo;
	
	private int calculaMax( ) {
		int maxTotal = tabla[inicio];
		for (int i = inicio; i < fin; i++) {
			if (tabla[i] > maxTotal) {
				maxTotal = tabla[i];
			}
		}
		System.out.println("Maximo del rango "+inicio+" - "+fin+":"+maxTotal);
		return maxTotal;
	}
	@Override
	public void run() {
		maximoTramo = calculaMax();
		
	}
	/**
	 * @return the tabla
	 */
	public int[] getTabla() {
		return tabla;
	}
	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(int[] tabla) {
		this.tabla = tabla;
	}
	/**
	 * @return the inicio
	 */
	public int getInicio() {
		return inicio;
	}
	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	/**
	 * @return the fin
	 */
	public int getFin() {
		return fin;
	}
	/**
	 * @param fin the fin to set
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}
	/**
	 * @return the maximoTramo
	 */
	public int getMaximoTramo() {
		return maximoTramo;
	}
	/**
	 * @param maximoTramo the maximoTramo to set
	 */
	public void setMaximoTramo(int maximoTramo) {
		this.maximoTramo = maximoTramo;
	}
	
}
