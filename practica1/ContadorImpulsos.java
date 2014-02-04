package practica1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContadorImpulsos {

	public static int impulsos = 0;
	
	public static synchronized void nuevoImpulso(){impulsos++;}
	public static synchronized int getImpulsos(){return impulsos;}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Aquí creamos los hilos a lanzar
		LectorImpulsos li = new LectorImpulsos();
		GeneradorImpulsos gi1 = new GeneradorImpulsos("GENERADOR 1");
		GeneradorImpulsos gi2 = new GeneradorImpulsos("GENERADOR 2");
		GeneradorImpulsos gi3 = new GeneradorImpulsos("GENERADOR 3");
		GeneradorImpulsos gi4 = new GeneradorImpulsos("GENERADOR 4");
		//Creación del manejador de hilos
		ExecutorService threadExecutor = Executors.newCachedThreadPool();
		//inicialización de hilos, se ponen en estado "Runnable"
		threadExecutor.execute( li ); // inicio del lector de impulsos
		threadExecutor.execute( gi1 ); // Inicio del Generador de impulsos 1
		threadExecutor.execute( gi2 ); // Inicio del Generador de impulsos 2
		threadExecutor.execute( gi3 ); // Inicio del Generador de impulsos 3
		threadExecutor.execute( gi4 ); // Inicio del Generador de impulsos 4
		
		//Cierre de todos los hilos cuando terminen
		threadExecutor.shutdown();
		
		System.out.println("CONTADOR DE IMPULSOS: Todas las tareas iniciadas, finaliza el hilo principal...");
	}//end main

}//end_class
