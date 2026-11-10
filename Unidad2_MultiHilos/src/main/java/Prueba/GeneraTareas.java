package Prueba;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GeneraTareas {
	   public static void main(String[] args) {
	       ExecutorService pool = Executors.newFixedThreadPool(2);
	       try {
	           pool.submit(new Hilo("Hilo1"));
	           pool.submit(new Hilo("Hilo2"));
	           pool.shutdown(); // no acepta más tareas pero sí se ejecutan las en curso
	           if (!pool.awaitTermination(10, TimeUnit.SECONDS)) { 
	//bloquea al hilo main hasta que terminen las tareas o pase el tiempo indicado.
	               pool.shutdownNow(); // interrumpe las que sigan en marcha
	           }
	       } catch (InterruptedException e) {
	           pool.shutdownNow();
	           Thread.currentThread().interrupt(); // restaura la marca
	       }
	       System.out.println("Hilo principal terminado");
	   }
	}
