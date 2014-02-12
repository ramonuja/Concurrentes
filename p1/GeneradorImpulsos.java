package p1;

import java.util.Random;
/**
 * Se encarga de generar los impulsos eléctricos en el sistema.
 * @author Ramón Díaz Valenzuela
 *
 */
public class GeneradorImpulsos implements Runnable {
	
	private String name;
	/**
	 * 
	 * @param name Nombre del generador de Impulsos
	 */
	public GeneradorImpulsos(String name) {
		this.name = name;
	}
	
	private final static Random generadorAl = new Random();
	
	@Override
	public void run() {
		//iteramos 10 veces para cada uno de los pulsos
		for (int y = 0; y < 10 ; y++){
			try {
				//Antes de enviar un impulso mandamos el hilo a "dormir" entre 0 y 10 segundos
				Thread.sleep(generadorAl.nextInt(20000));
				ContadorImpulsos.nuevoImpulso();
				System.out.println(name + ": Nuevo impulso emitido.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + ": Fin de emisión de pulsos, termina mi hilo de ejecución.");
	}//end run

}//end_class
