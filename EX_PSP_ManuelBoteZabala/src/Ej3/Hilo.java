package Ej3;

import java.util.Random;

public class Hilo{
	
	private int saldo = 1000;
	
	public synchronized void retirarDinero(int retiro) {
		//Comprobar que el retiro no de menos de 0
		if(saldo - retiro >= 0) {
			saldo = saldo - retiro;
			System.out.println("Se retira: " + retiro + "\tSaldo restante: " + saldo);			
		}
				
	}
	
	//Obtener saldo
	public int getSaldo() {
		return saldo;
	}

}
