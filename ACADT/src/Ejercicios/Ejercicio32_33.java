package Ejercicios;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Ejercicio32_33 {

	public static void main(String[] args) {

		/*
		 * Crea una lista de niveles educativos que se imparten en el centro sin
		 * duplicados y ordenados por el orden de inserción, añade cinco niveles y
		 * muestra su contenido.
		 */

		LinkedHashSet<String> niveles = new LinkedHashSet<String>();

		niveles.add("ESO");
		niveles.add("GM-SMR");
		niveles.add("GS-DAM");
		niveles.add("GS-ASIR");
		niveles.add("BACH");

		System.out.println("Ejercicio 32");
		for (String i : niveles) {
			System.out.println(i);
		}

		
		System.out.println("");
		/*
		 * Crea una lista de grupos que hay en el centro sin duplicados y ordenados por
		 * el grupo, añade cinco niveles y muestra su contenido.
		 */
		
		TreeSet<String> grupos = new TreeSet<String>();
		
		grupos.add("1ESOA");
		grupos.add("4ESOC");
		grupos.add("2DAM");
		grupos.add("1FPB");
		grupos.add("2BACHB");
		
		System.out.println("Ejercicio 33");
		for (String i : grupos) {
			System.out.println(i);
		}

	}

}
