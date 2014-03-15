package p2.reservas;

public class ControlReservas {
	private boolean escribiendo;
	private int nLectores;
	private int nEscritoresEsperando;
	
	public synchronized void abrirConsulta(){
		while(escribiendo || nEscritoresEsperando >0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//end_while
		nLectores++;
		notifyAll();
	}
	
	public synchronized void cerrarConsulta(){
		nLectores--;
		notifyAll();//despertar a todos
	}
	
	public synchronized void abrirReserva(){
		while (escribiendo || nLectores>0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end_while
		nEscritoresEsperando--;
		escribiendo = true;
	}//end method abrirReserva
	
	public synchronized void solicitarReserva(){nEscritoresEsperando++;}
	
	public synchronized void finReserva(){
		//if(nEscritoresEsperando == 0) 
		escribiendo = false;
		notifyAll();//despertar todos
	}//end method finReserva
}//end_class ControlReservas
