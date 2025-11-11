package Thread;

public class Hilo extends Thread implements Runnable{
	
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("Prueba " + (i+1));			
		}
	}

}
