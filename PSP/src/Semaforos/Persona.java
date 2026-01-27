package Semaforos;

import java.util.concurrent.Semaphore;

public class Persona extends Thread{

	private static Semaphore llave = new Semaphore(1);
	
	private int id;
	
	public Persona(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Persona " + id + " quiere entrar al baño");
			
			llave.acquire(); //coger la llave
			System.out.println("Persona " + id + " entra al baño");
			
			Thread.sleep(1000); //Usa el recurso
			
			System.out.println("Persona " + id + " sale del baño");
			llave.release(); //devolver la llave

			
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		for(int i = 1; i < 11; i++) {
			Persona p = new Persona(i);
			p.start();
		}
	}
	

}
