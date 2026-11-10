package Prueba;

public class Hilo2 implements Runnable {
	private final String nombreHilo;
	private final int milisegundos;
	
	public Hilo2(String nombre, int milisegundos) {
		this.nombreHilo = nombre;
		this.milisegundos = 0;
	}
	
	@Override
	public void run() {
		Thread.currentThread().setName(nombreHilo);
		System.out.println("Ejecutando Hilo:"+Thread.currentThread().getName());		
		try {
			Thread.sleep(milisegundos);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error");
		}
	}
}


