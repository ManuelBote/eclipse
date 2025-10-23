package Ejercicios;

import java.io.File;
import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {
		

		/*
		 * Pedir por teclado una ruta de fichero o carpeta y mostrar si lo que se ha introducido existe,
		 * si es un fichero o una carpeta y el tamaño.
		 */
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduce el archivo o carpeta a buscar: ");
		String ruta2 = teclado.nextLine();
		
		File fichero = new File(ruta2);
		if(fichero.exists()) {
			if(fichero.isFile()) {
				System.out.println("Es un archivo");
				
			} else if(fichero.isDirectory()) {
				System.out.println("Es un directorio");
				
			}
			System.out.println(fichero.length());
			
		} else {
			System.out.println("El archivo o directorio no existe");
			
		}

	}

}
