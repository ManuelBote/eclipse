package Ejercicios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio12 {

	public static void main(String[] args) {

		/*
		 * Realiza un programa que añada información a un fichero como el que se muestra
		 * en la imagen. El programa deberá pedir el nombre de fichero y los datos de
		 * varias personas. Si el fichero no existe, se creará. Si existe se añadirán
		 * registros.
		 */

		Scanner teclado = new Scanner(System.in);

		System.out.println("Indica el nombre del fichero");
		String nombreFichero = teclado.nextLine();
		File fichero = new File(nombreFichero);

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero, true));

			for (int i = 0; i < 3; i++) {
				System.out.println("Persona " + (i + 1));
				System.out.println("Nombre: ");
				String nombre = teclado.nextLine();
				System.out.println("Apellido: ");
				String apl = teclado.nextLine();
				System.out.println("Numero: ");
				String numero = teclado.nextLine();

				bw.append("\n" + nombre + ";" + apl + ";" + numero);
			}
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
