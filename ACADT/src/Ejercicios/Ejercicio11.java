package Ejercicios;

import java.util.Scanner;

//Manuel Bote Zabala
public class Ejercicio11 {

	public static void main(String[] args) {

		// 11.Pedir un número del 1 al 7 e indicar el día de la semana

		Scanner teclado = new Scanner(System.in);

		System.out.printf("Introduzca un numero del 1 al 7: ");
		int num = Integer.parseInt(teclado.nextLine());

		switch (num) {
		case 1:
			System.out.println("Lunes");
			break;

		case 2:
			System.out.println("Martes");
			break;

		case 3:
			System.out.println("Miercoles");
			break;

		case 4:
			System.out.println("Jueves");
			break;

		case 5:
			System.out.println("Viernes");
			break;

		case 6:
			System.out.println("Sabado");
			break;

		case 7:
			System.out.println("Domingo");
			break;

		default:
			System.out.println("Numero no valido");
		}

	}

}
