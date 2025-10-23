package Ejercicios;

import java.io.File;
import java.util.Scanner;

public class Ejercicio4 {

	public static void main(String[] args) {

		/*
		 * Crear una carpeta cuyo nombre se pide por teclado en la ruta por defecto.
		 * Comprobar antes de crear comprobar que la carpeta no existe
		 */

		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduce el nombre de la carpeta a crear: ");
		String nombre = teclado.nextLine();
		
		File carpeta = new File(nombre);
		
		if(!carpeta.exists()) {
			if(carpeta.mkdir()) {
				System.out.println("Carpeta creada en " + carpeta.getAbsolutePath());
			} else {
				System.out.println("La carpeta no se a creado");
			}
			
		} else {
			System.out.println("El directorio ya existe");
		}

		
	}

}
