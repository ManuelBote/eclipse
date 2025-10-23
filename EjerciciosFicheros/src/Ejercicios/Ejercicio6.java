package Ejercicios;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio6 {

	public static void main(String[] args) {

		/*
		 * Pedir por teclado una ruta de fichero y un nuevo nombre de fichero. El
		 * programa deberá renombrar el fichero original con el nuevo nombre en la
		 * carpeta original. Comprobar que el fichero original existe y que el nuevo no
		 * existe.
		 */
		
		Scanner teclado = new Scanner(System.in);

		System.out.println("Introduce el nombre del fichero : ");
		String nombre = teclado.nextLine();
		System.out.println("Introduce el nuevo nombre del fichero : ");
		String nombre_nuevo = teclado.nextLine();

		File fichero = new File("Ejercicio4/" + nombre);
		File fichero_nuevo = new File("Ejercicio4/" + nombre_nuevo);

		if (fichero.exists() && !fichero_nuevo.exists()) {
			fichero.renameTo(fichero_nuevo);
			System.out.println("Fichero renombrado");

		} else {
			System.out.println("El fichero no existe o el nuevo nombre ya esta en uso");
		}

	


	}

}
