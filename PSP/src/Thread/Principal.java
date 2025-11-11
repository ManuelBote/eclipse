package Thread;

public class Principal {
	
public static void main(String[] arg) {
		
		Hilo h = new Hilo();
		Hilo h2 = new Hilo();
		
		h.start();
		h2.start();
		
	}

}
