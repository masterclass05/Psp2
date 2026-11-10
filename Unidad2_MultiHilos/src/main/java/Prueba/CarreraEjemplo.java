package Prueba;

public class CarreraEjemplo {
   public static void main(String[] args) throws InterruptedException {
       Contador contador = new Contador();
       Thread t1 = new Thread(new TareaIncremento(contador));
       Thread t2 = new Thread(new TareaIncremento(contador));
       t1.start();
       t2.start();
       t1.join();
       t2.join();
       System.out.println("Valor final: " + contador.getValor()); // esperado: 200000
   }
}



