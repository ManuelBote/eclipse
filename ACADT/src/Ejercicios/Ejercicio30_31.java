package Ejercicios;

import java.util.ArrayList;
import java.util.HashSet;

public class Ejercicio30_31 {

	public static void main(String[] args) {

		/*
		 * Crea una lista de nombres de los alumnos con duplicados, añade cinco nombres
		 * y muestra su contenido.
		 */

		ArrayList<String> alumnos = new ArrayList<String>();

		alumnos.add("Juan");
		alumnos.add("Paco");
		alumnos.add("Raul");
		alumnos.add("Juan");
		alumnos.add("Jorge");

		for (String i : alumnos) {
			System.out.println("Nombre del alumno: " + i);
		}
		
		

		/*
		 * Crea una lista de nombres de los profesores sin duplicados, añade cinco
		 * nombres y muestra su contenido.
		 */
		
		HashSet<String> alumnos2 = new HashSet<String>();
		
		alumnos2.add("Paco");
		alumnos2.add("Juan");
		alumnos2.add("Pepe");
		alumnos2.add("Julia");
		alumnos2.add("Juanjo");
		
		System.out.println("Nombres con HashSet");
		for (String i : alumnos2) {
			System.out.println("Nombre del alumno: " + i);
		}
		
	}

}
