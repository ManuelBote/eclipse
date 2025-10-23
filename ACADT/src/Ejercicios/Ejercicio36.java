package Ejercicios;

import java.util.TreeMap;

public class Ejercicio36 {

	public static void main(String[] args) {

		/*
		 * Crea una lista de vehiculos sin duplicados y ordenados por la matricula,
		 * añade cinco vehículos y muestra su contenido.
		 */
		
		
		TreeMap<String, String> vehiculo = new TreeMap<String, String>();
		
		vehiculo.put("5463MBN", "Coche1");
		vehiculo.put("1923CTK", "Coche2");
		vehiculo.put("9235BDS", "Coche3");
		vehiculo.put("3650HJK", "Coche4");
		vehiculo.put("8234FQL", "Coche5");
		
		for (String i : vehiculo.keySet()) {
			System.out.println("Matricula: " + i + ", Coche: " + vehiculo.get(i));
		}
		
		

	}

}
