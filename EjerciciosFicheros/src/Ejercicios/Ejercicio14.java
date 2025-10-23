package Ejercicios;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Ejercicio14 {

	public static void main(String[] args)  throws IOException{

		/*
		 * Modifica el ejercicio anterior para leer por línea.
		 */
		
		Scanner teclado = new Scanner(System.in);
		BufferedReader fichero = null;

		System.out.println("Introduce la ruta del archivo");
		String ruta = teclado.nextLine();
		
		try {
			fichero = new BufferedReader(new FileReader(ruta));
			
			String linea;
			System.out.println("Contenido del fichero: ");
			
			while((linea = fichero.readLine()) != null) {
				System.out.println(linea);
			}
			
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
