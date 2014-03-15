package p2.reservas;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrdenadorCentral {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Iniciando aplicación...");
		int filas =10;
		int columnas = 20;
		System.out.println("Creando la base de datos y el sistema de control de reservas...");
		BaseDatos bd = new BaseDatos(filas, columnas);
		ControlReservas cr = new ControlReservas();
		//vamos a crear un número aleatorio de terminales comprendido entre 1 y M/3+1, siendo M el número total de asientos. 
		Random r = new Random();
		int nTerminales = r.nextInt(filas*columnas/3)+1;
		System.out.printf("Se van a crear %d terminales\n",nTerminales);
		ExecutorService threadExecutor = Executors.newCachedThreadPool();
		for (int i = 1; i <= nTerminales; i++) {
			Terminal t = new Terminal(i, bd, cr);
			threadExecutor.execute(t);
		}//end_for
		threadExecutor.shutdown();
	}//end_main
}
