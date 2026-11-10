package Prueba;

public class GeneraHilos {
	public static void main(String[] args) throws InterruptedException {
		Hilo2 runnable1 = new Hilo2("Hilo1", 3000);
		Hilo2 runnable2 = new Hilo2("Hilo2", 5000);
		
		Thread hilo1 = new Thread(runnable1);
		Thread hilo2 = new Thread(runnable2);
		
		
		hilo1.start();
		hilo2.start();
		
		
		hilo1.join();
		hilo2.join();
		
		
		System.out.println("Hilo principal terminado");
		
		
		
		
		
	}
}




