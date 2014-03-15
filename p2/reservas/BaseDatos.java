package p2.reservas;

import java.util.ArrayList;

public class BaseDatos {
	private boolean asientos[][];
	private final int N_FILAS,N_COLUMNAS;
	/**
	 * 
	 * @param nFilas número de filas
	 * @param nColumnas número de columnas
	 */
	public BaseDatos(int nFilas, int nColumnas){
		N_FILAS = nFilas;
		N_COLUMNAS = nColumnas;
		asientos = new boolean[nFilas][nColumnas];
		//inicializamos todos los asientos para que estén disponibles
		for (int i = 0; i < asientos.length; i++) {
			for (int j = 0; j < asientos[i].length; j++) {
				asientos[i][j] = true;
			}
		}
	}//end_constructor
	
	public boolean disponibilidadAsiento(int fila, int columna){
		if (fila > N_FILAS || columna > N_COLUMNAS
				|| fila < 0 || columna < 0) return false;
		return asientos[fila][columna];
	}
	
	public boolean reservaAsiento(int fila, int columna){
		if(asientos[fila][columna]){
			asientos[fila][columna] = false;
			return true;//la reserva ha sido hecha
		}else{
			return false; // fallo en la reserva
		}
	}//end reservaAsiento
	
	public ArrayList<PairXY> verDisponibles(){
		ArrayList<PairXY> a = new ArrayList<BaseDatos.PairXY>();
		for (int i = 0; i < asientos.length; i++) {
			for (int j = 0; j < asientos[i].length; j++) {
				if (asientos[i][j] == true) a.add(new PairXY(i, j));
			}
		}
		return a;
	}//end method verDisponibles
	
	class PairXY {
		final public int x,y;
		public PairXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}//end inner class PairXY
	
}//end_class
