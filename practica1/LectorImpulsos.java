package practica1;

public class LectorImpulsos implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(20000);//espera 20 segundos antes de mostrar los impulsos acumulados
				System.out.println("LECTOR DE IMPULSOS: Se han emitido " + ContadorImpulsos.getImpulsos() + " impulsos hasta el momento.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("LECTOR DE IMPULSOS: Fin de la lectura de pulsos, se cierra mi hilo de ejecución.");
	}//end run

}//end_class
//comment
