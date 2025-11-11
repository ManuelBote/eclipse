package Thread;

public class Principal {
	
public static void main(String[] arg) {
		
		Thread t1 = new Thread(new Hilo());
		Thread t2 = new Thread(new Hilo());
		
		t1.start();
		t2.start();
		
	}

}
