package Prueba;

public class PrimerHilo extends Thread{
	
	
	
	public PrimerHilo(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void run() {
		System.out.println("Entro en hilo: "+ this.getName());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Salgo del Hilo");
	}
}
