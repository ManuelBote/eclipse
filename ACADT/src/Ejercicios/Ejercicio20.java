package Ejercicios;

import java.util.Scanner;

//Manuel Bote Zabala
public class Ejercicio20 {

	public static void main(String[] args) {

		/*
		 * 20. Escribe un programa que permita al usuario adivinar un número entre 1 y
		 * 100. El programa debe guiar al usuario diciéndole si el número es mayor o
		 * menor que el que ingresó.
		 */

		Scanner teclado = new Scanner(System.in);

		int numAdivinar = (int) (Math.random() * 100 + 1);
		int num = 0;

		do {
			try {

				System.out.printf("Adivna el numero del 1 al 100: ");
				num = Integer.parseInt(teclado.nextLine());

				if (num > numAdivinar) {
					System.out.println("El numero es menor");
					
				} else if (num < numAdivinar) {
					System.out.println("El numero es mayor");
					
				} else {
					System.out.println("¡Adivinastes el numero!");
					
				}

			} catch (Exception e) {
				System.out.println("A ocurrido una excepcion: " + e.getMessage());

			}
		} while (num != numAdivinar);

	}

}
