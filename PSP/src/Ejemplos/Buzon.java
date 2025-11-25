package Ejemplos;

public class Buzon {

	private int mensaje;
	private boolean disp = false;
	
	public synchronized void enviar(int valor) {
		// Esperar si ya hay un mensaje disponible
		while(disp) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
				Thread.currentThread().interrupt();
			}
		}
		mensaje = valor;
		disp = true;
		notify();
	}
	
	public synchronized int recibir() {
		// Esperar si no hay mensaje disponible
		while(!disp) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
				Thread.currentThread().interrupt();
			}
		}
		disp = false;
		notify();
		return mensaje;
	}
}
