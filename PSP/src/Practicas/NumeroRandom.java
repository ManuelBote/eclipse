package Practicas;

import java.util.Random;

public class NumeroRandom {

	public static void main(String[] args) {
		
		Random rand = new Random();
		int num = rand.nextInt(100);
		
		System.out.println(num);
		
		
	}
}
