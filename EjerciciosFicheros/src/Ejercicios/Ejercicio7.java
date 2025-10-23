package Ejercicios;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

//@author Manuel Bote Zabala
public class Ejercicio7 {

	public static void main(String[] args) {

		/*
		 * Realiza un programa en java que pida por teclado nombres de ficheros que
		 * iremos guardando en una lista. Finalizada la lista, para cada uno de esos
		 * ficheros devolverá la siguiente información: 
		 *  Si existe 
		 *  Si es un fichero o una carpeta 
		 *  La ruta absoluta 
		 *  Propiedades de lectura, escritura y ejecutar
		 *  El tamaño
		 */
		
		Scanner teclado = new Scanner(System.in);
		ArrayList<String> nombres = new ArrayList<String>();
		
		String cond = "si";
		
		//Menu donde se escribe el nombre del fichero y se pregunta si desea continuar
		do {
			System.out.println("Introduce el nombre del fichero: ");
			nombres.add(teclado.nextLine());
			System.out.println("¿Desea agregar otro archivo? (si/no)");
			cond = teclado.nextLine().toLowerCase();
			
		}while (cond.equals("si"));
		
		System.out.println("======================================");
		
		for (String i : nombres) {
			System.out.println(i+"\n");
			//Le paso la carpeta Ejercicio4 que es donde tengo guardado los archivos de ejemplo
			File fichero = new File("Ejercicio4/" + i);
			
			//Existe
			if(fichero.exists()) {
				System.out.println("Existe");
			} else {
				System.out.println("No existe");
			}
			
			//Es un fichero o un directorio
			if(fichero.isDirectory()) {
				System.out.println("Es un directorio");
			}
			if(fichero.isFile()) {
				System.out.println("Es un fichero");
			}
			
			//Ruta absoluta
			System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
			
			//Permisos de lectura
			if(fichero.canRead()) {
				System.out.println("Tiene permiso de lectura");
			} else {
				System.out.println("No tiene permiso de lectura");
			}
			
			//Permisos de escritura
			if(fichero.canWrite()) {
				System.out.println("Tiene permiso de escritura");
			} else {
				System.out.println("No tiene permiso de escritura");
			}
			
			//Permisos de ejecuccion
			if(fichero.canExecute()) {
				System.out.println("Tiene permiso de ejecucion");
			} else {
				System.out.println("No tiene permiso de ejecucion");
			}
			
			//Tamaño 
			System.out.println("Peso: " + fichero.length());
			
			System.out.println("======================================\n");
		}

	}

}
