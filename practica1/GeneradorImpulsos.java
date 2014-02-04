package practica1;

import java.util.Random;

public class GeneradorImpulsos implements Runnable {
	
	private String name;

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
				Thread.sleep(generadorAl.nextInt(10000));
				ContadorImpulsos.nuevoImpulso();
				System.out.println(name + ": Nuevo impulso emitido.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + ": Fin de emisión de pulsos, termina mi hilo de ejecución.");
	}//end run

}//end_class
