package Prueba;

public class Demo {
    public static void main(String[] args) {
    	
    	Hilo uno, dos;
    	uno = new Hilo("Jamaica");
    	dos = new Hilo("Fiji");
    	
    	uno.start();
    	dos.start();
    	
    	System.out.println("main no hace nada");
    }

}
