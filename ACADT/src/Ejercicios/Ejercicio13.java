package Ejercicios;

import java.util.Scanner;

//Manuel Bote Zabala
public class Ejercicio13 {

	public static void main(String[] args) {

		/*
		 * Pedir la calificación numérica y decir si es un sobresaliente, notable, bien,
		 * suficiente o insuficiente (usando el swicth)
		 */

		Scanner teclado = new Scanner(System.in);

		System.out.printf("Introduzca la nota: ");
		int num = Integer.parseInt(teclado.nextLine());

		switch (num) {

		case 0, 1, 2, 3, 4:
			System.out.println("Insuficiente");
			break;

		case 5:
			System.out.println("Suficiente");
			break;

		case 6:
			System.out.println("Bien");
			break;

		case 7, 8:
			System.out.println("Notable");
			break;

		case 9, 10:
			System.out.println("Sobresaliente");
			break;

		default:
			System.out.println("Nota no valida");

		}
	}

}
