package Ejercicios;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio5 {

	public static void main(String[] args) {

		/*
		 * Crear un fichero cuyo nombre se pide por teclado en la ruta por defecto.
		 * Comprobar antes de crear que el fichero no existe.
		 */

		Scanner teclado = new Scanner(System.in);

		System.out.println("Introduce el nombre del fichero a crear: ");
		String nombre = teclado.nextLine();

		File fichero = new File("Ejercicio4/" + nombre);

		if (!fichero.exists()) {
			try {
				if (fichero.createNewFile()) {
					System.out.println("Fichero creada en " + fichero.getAbsolutePath());
					
				} else {
					System.out.println("La Fichero no se a creado");
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("El fichero ya existe");
		}

	}

}
