package Ej3;

import java.util.Random;

public class Padre {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hilo h = new Hilo();
		System.out.println("Saldo restante: " + h.getSaldo());
		
		//Hilo1
		Thread cliente1 = new Thread(() -> {
			while(h.getSaldo() > 0) {
				Random rand = new Random();
				int num = rand.nextInt(50);
				h.retirarDinero(num);				
			}
		});
		
		//Hilo 2
		Thread cliente2 = new Thread(() -> {
			while(h.getSaldo() > 0) {
				Random rand = new Random();
				int num = rand.nextInt(50);
				h.retirarDinero(num);				
			}
		});
		
		//Hilo 3
		Thread cliente3 = new Thread(() -> {
			while(h.getSaldo() > 0) {
				Random rand = new Random();
				int num = rand.nextInt(50);
				h.retirarDinero(num);				
			}
		});
		
		cliente1.start();
		cliente2.start();
		cliente3.start();
		

		
		
	}

}
