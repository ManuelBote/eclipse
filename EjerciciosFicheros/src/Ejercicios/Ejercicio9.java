package Ejercicios;

import java.io.File;
import java.util.Scanner;

public class Ejercicio9 {

	public static void main(String[] args) {

		/*
		 * Realiza un programa que borre un fichero que se pasa por parámetro. Antes de
		 * borrar se debe chequear que el fichero existe y pedir una confirmación de si
		 * realmente se quiere borrar.
		 */
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduzca la ruta del fichero: ");
		String ruta = teclado.nextLine();
		
		File fichero = new File(ruta);
		
		if (fichero.exists() && fichero.isFile()) {
			System.out.println("Estas seguro que quieres borrar el archivo " + fichero.getName() + " (si/no)");
			String cond = teclado.nextLine().toLowerCase();
			if (cond.equals("si")) {
				fichero.delete();
				System.out.println("Fichero borrado");
			} else {
				System.out.println("Fichero no borrado");
			}
			
		} else {
			System.out.println("El archivo no existe o no es un fichero");
		}


	}

}
