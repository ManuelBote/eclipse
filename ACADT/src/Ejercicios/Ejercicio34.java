package Ejercicios;

import java.util.TreeMap;

public class Ejercicio34 {

	public static void main(String[] args) {

		/*
		 * Crea una lista de clave-profesor sin duplicados y ordenados por clave, añade
		 * cinco niveles y muestra su contenido.
		 */
		
		TreeMap<String, String> clases = new TreeMap<String, String>();
		
		clases.put("IFC02", "Paco");
		clases.put("FOL01", "Juana");
		clases.put("IFC08", "Jose");
		clases.put("ING06", "Pepe");
		clases.put("FPIFC01", "Juan");
		
		for ( String i : clases.keySet()) {
			System.out.println("Clase: " + i + ", Profesor: " + clases.get(i));
		}
		
		

	}

}
