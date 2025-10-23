package Ejercicios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio11 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		

		System.out.println("Indica el nombre del fichero");
		String nombreFichero = teclado.nextLine();
		File fichero = new File(nombreFichero);
		
		if (fichero.exists())
			System.out.println("El fichero " + nombreFichero + " ya existe");
		
		else {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero));
				
				System.out.println("Texto a añadir: ");
				bw.write(teclado.nextLine());
				
				bw.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}

}
