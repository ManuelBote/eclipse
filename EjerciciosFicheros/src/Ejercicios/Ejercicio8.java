package Ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio8 {

	public static void main(String[] args) {

		/*
		 * Realiza un programa que pida por teclado una ruta de carpeta y si existe
		 * muestre su contenido y el contenido de todos sus hijos tantos directos como
		 * indirectos. Tal y como muestra la imagen.
		 */
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduzca la ruta del directorio: ");
		String ruta = teclado.nextLine();
		
		File directorio = new File(ruta);
		
		if (directorio.exists() && directorio.isDirectory()) {
			listarArchivos(directorio, 0);
			
		} else {
			System.out.println("La ruta proporcionada no existe o no es un directorio");
		}


	}
	
	public static void listarArchivos (File carpeta, int nivel) {
		
		File[] archivos = carpeta.listFiles();
		
		if(archivos == null) {
			return;
		}
		
		for (File a : archivos) {
			for (int i = 0; i < nivel; i++) {
				System.out.print("   ");
			}
			
			if (a.isDirectory()) {
				System.out.println("[C] " + a.getName());
				listarArchivos(a, nivel + 1);
			} else {
				System.out.println("[F]" + a.getName());
			}
		}
		
	}
	

}
