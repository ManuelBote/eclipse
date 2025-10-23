package Ejercicios;

import java.util.Scanner;
import java.io.File;

public class Ejercicio3 {

	public static void main(String[] args) {
		
		/*
		 * Mostrar el contenido de una carpeta cuya ruta se pide por teclado comprobando
		 * que existe y que es una carpeta. Del contenido se debe mostrar el nombre, si
		 * es fichero o carpeta, y las propiedades rwx.
		 */
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduce la ruta de la carpeta");
		String ruta = teclado.nextLine();
		
		File directorio = new File(ruta);

		if (directorio.exists() && directorio.isDirectory()) {
			File[] archivos = directorio.listFiles();
			System.out.println("Archivos de la ruta " + ruta);
			for (File e : archivos) {
				System.out.println(e.getName());
				System.out.println("Permisos de lectura  : " + e.canRead());
				System.out.println("Permisos de escritura: " + e.canWrite());
				System.out.println("Permisos de ejecucion: " + e.canExecute());
			}
		}
	}

}
