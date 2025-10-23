package Ejercicios;

import java.io.File;
import java.util.*;

public class Ejercicio10 {

	public static void main(String[] args) {

		/*
		 * Crea un programa que solicita por teclado una ruta, verifica que la ruta es
		 * válida y nos mostrará los archivos que contiene dicho directorio ordenador
		 * por tamaño.
		 */
		
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Indica la ruta: ");
		String ruta = teclado.nextLine();
		
		File carpeta = new File(ruta);
		
		if(carpeta.exists() && carpeta.isDirectory()) {
			
			File[] arch = carpeta.listFiles();
			
			Arrays.sort(arch,Comparator.comparingLong(File::length));
			for(File a : arch) {
				System.out.println(a.getName() + "\t" + a.length() + " bytes");
			}
			
			
			
			
			
		}


	}

}
