package Ejercicios;

import java.util.Scanner;

//Manuel Bote Zabala
public class Ejercicio12 {

	public static void main(String[] args) {

		/*
		 * 12. Pedir la calificación numérica y decir si es un sobresaliente, notable,
		 * bien, suficiente o insuficiente (usando el if)
		 */

		Scanner teclado = new Scanner(System.in);

		System.out.printf("Introduzca la nota: ");
		double num = Double.parseDouble(teclado.nextLine());

		if (num <= 10 && num >= 9) {
			System.out.println("Sobresaliente");

		} else if (num < 9 && num >= 7) {
			System.out.println("Notable");

		} else if (num < 7 && num >= 6) {
			System.out.println("Bien");

		} else if (num < 6 && num >=5) {
			System.out.println("Suficiente");

		} else if (num < 5 && num >= 0) {
			System.out.println("Insuficiente");

		} else {
			System.out.println("Numero no valido");
		}

	}

}
