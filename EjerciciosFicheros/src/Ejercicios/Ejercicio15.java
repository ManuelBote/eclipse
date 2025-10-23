package Ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio15 {

	public static void main(String[] args) throws IOException {

		/*
		 * Realiza un programa que lea y muestre un fichero de texto que contiene el
		 * nombre, los apellidos y número de teléfono de personas, separados por el
		 * carácter ;.También, deberá mostrar el número de personas que hay en el
		 * fichero. El nombre del fichero se pedirá por teclado.
		 */

		Scanner teclado = new Scanner(System.in);

		BufferedReader fichero = null;
		
		System.out.print("Introduce la ruta del archivo: ");
		String ruta = teclado.nextLine();
		System.out.println("");
		
		try {
			fichero = new BufferedReader(new FileReader(ruta));
			
			String linea;
			int cont = 0;
			
			while((linea = fichero.readLine()) != null) {
				String[] datos = linea.split(";");
				System.out.println("Nombre: " + datos[0] + "\t\tApellidos: " + datos[1] + "\t\tTelefono: " + datos[2]);
				cont ++;
			}
			
			System.out.println("\nNumero total de personas en el fichero: " + cont);
			
		}catch(Exception e) {
			System.out.println("Error al abrir el fichero");
			e.getMessage();
			e.printStackTrace();
		}
		finally {
			fichero.close();
		}

	}

}
