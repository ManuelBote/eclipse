package Ejercicios;

//Manuel Bote Zabala
import java.util.Scanner;

public class Ejercicio21 {

	public static void main(String[] args) {

		/*
		 * 21. Escribe un programa que permita al usuario ingresar un número y muestre
		 * su tabla de multiplicar del 1 al 10. Luego, pregunta si el usuario quiere
		 * calcular otra tabla y repite el proceso si responde "sí".
		 */
		
		Scanner teclado = new Scanner(System.in);
		String resp = "si";
		
		do {
			
			try {
				System.out.printf("Introduzca un numero: ");
				int num = Integer.parseInt(teclado.nextLine());
				
				for(int i = 0; i <= 10; i++) {
					System.out.println(num + "x" + i + ": " + (num*i));
				}
				
			} catch(Exception e) {
				System.out.println("A ocurrido una excepcion: " + e.getMessage());
			}
			
			
			do {
				System.out.println("¿Desea realizar otra tabla de multiplicacion? (si/no)");
				resp = teclado.nextLine().toLowerCase();
				
			}while(!resp.equals("si") && !resp.equals("no"));
			
		}while(resp.equals("si"));

	}

}
