package Ejercicios;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio13 {

	public static void main(String[] args) {

		/*
		 * Muestra en pantalla el contenido de un fichero de texto cuya ruta se pasa por
		 * consola. Leeremos por carácter.
		 */
		
		Scanner teclado = new Scanner(System.in);
		FileReader fichero = null;

		System.out.println("Introduce la ruta del archivo");
		String ruta = teclado.nextLine();
		
		try {
			
			fichero = new FileReader(ruta);
			char letra;
			int caracter;

			while ((caracter = fichero.read()) != -1) {
				letra = (char) caracter;
				System.out.print(letra);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer");
			e.printStackTrace();
		} finally {
			try {
				fichero.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
