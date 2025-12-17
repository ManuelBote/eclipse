package Ejercicios;

import java.util.Scanner;

public class Ej21 {

	public static void main(String[] args) {

		Scanner tc = new Scanner(System.in);
		
		int cond = 0;
		
		do {
			
			System.out.println("\n ==== Menu ==== ");
			System.out.println("[1] Comprobar si existe el id");
			System.out.println("[2] Agregar alumno");
			System.out.println("[3] Contar total alumnos");
			System.out.println("[0] Salir");
			System.out.print("Selecciona una opcion: ");
			cond = tc.nextInt(); tc.nextLine();
			
			switch(cond) {
			case 0 -> System.out.println("Saliendo...");
			case 1 -> Ej18.main(args);
			case 2 -> Ej19.main(args);
			case 3 -> Ej20.main(args);
			}
			
		}while(cond != 0);

	}

}
