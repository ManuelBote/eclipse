package Ejercicios;

// Manuel Bote Zabala
import java.util.Scanner;

public class Ejercicio10 {

	public static void main(String[] args) {
		/*
		 * 10. Recuerda las mates nº naturales: 1, 2, 3… nº enteros ..-2,-1,0,1,2..
		 * Pedir un número entero y ver primero si es positivo. Si es positivo, se
		 * verifica si es par o impar dentro del primer if. Si el número es negativo, se
		 * imprime "El número es negativo".
		 */

		Scanner teclado = new Scanner(System.in);

		System.out.printf("Introduzca un numero entero: ");
		int num = Integer.parseInt(teclado.nextLine());

		if (num >= 0) {
			if (num % 2 == 0) {
				System.out.println("El numero " + num + " es par");
			} else {
				System.out.println("El numero " + num + " es impar");
			}
		} else {
			System.out.println("El numero " + num + " es negativo");
		}

	}

}
