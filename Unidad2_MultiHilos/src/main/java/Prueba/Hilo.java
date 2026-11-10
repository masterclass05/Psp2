package Prueba;

public class Hilo extends Thread{

	public Hilo (String str) {
		super(str);
	}
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i + " " + getName());
			try {
				sleep((long)(Math.random() * 1000));
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("FIN! "+ getName());
		}
	}
}
