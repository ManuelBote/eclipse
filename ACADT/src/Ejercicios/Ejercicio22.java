package Ejercicios;

// @author Manuel Bote Zabala
public class Ejercicio22 {

	public static void main(String[] args) {
		
		int[] num = {42, 12, 8, 59, 6};
		
		System.out.println("Utilizando for");
		for(int i = 0; i < num.length; i++) {
			System.out.println(num [i]);
		}
		
		System.out.println("Utilizando for-each");
		for (int i : num) {
			System.out.println(i);
		}
		
		
	}
	
	
}
