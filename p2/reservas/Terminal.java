package p2.reservas;

import java.util.List;
import java.util.Random;

public class Terminal implements Runnable {

	private int ID;
	private BaseDatos bd;
	private ControlReservas controlAcceso;
	private boolean disp;
	private boolean ok;
	
	
	
	public Terminal(int iD, BaseDatos bd, ControlReservas controlAcceso) {
		super();
		ID = iD;
		this.bd = bd;
		this.controlAcceso = controlAcceso;
		System.out.printf("Hola soy el Terminal %d.\n", iD);
	}



	@Override
	public void run() {
		//El terminal realizará un número aleatorio de reservas entre 1 y 5.
		Random r = new Random();		
		int nReservas = (r.nextInt(5))+1;
		while(nReservas > 0 && !bd.verDisponibles().isEmpty()){
			do{
				//obtenemos el listado de disponibles en este momento
				List<BaseDatos.PairXY> l = bd.verDisponibles();
				if(l.isEmpty()) break;
				//Esperamos un tiempo aleatorio para simular que el usuario está eligiendo sus sitios
				System.out.printf("Terminal%d: Me estoy pensando que asiento elegir...\n",ID);
				try {
					Thread.sleep(r.nextInt(10000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//de los disponibles cogemos uno al azar
				BaseDatos.PairXY asiento =l.get(r.nextInt(l.size()));
				int fila = asiento.x;
				int columna = asiento.y;			
				System.out.printf("Terminal%d: Elijo el asiento en la fila %d y columna %d\n", ID,fila,columna);
				controlAcceso.abrirConsulta();
				disp = bd.disponibilidadAsiento(fila, columna);
				controlAcceso.cerrarConsulta();
				
				if (disp){
					System.out.printf("Terminal%d: Mi asiento está disponible, voy a reservarlo.\n",ID);
					controlAcceso.solicitarReserva();
					controlAcceso.abrirReserva();
					ok = bd.reservaAsiento(fila, columna);
					controlAcceso.finReserva();
					System.out.printf("Terminal%d: Asiento Reservado.\n",ID);
					nReservas--;
				}else {
					System.out.printf("Terminal%d: Vaya! parece que me han quitado el sitio mientras lo escogía!\n",ID);
				}
			}while(!ok);
		}//end while
	}

}//end class terminal
