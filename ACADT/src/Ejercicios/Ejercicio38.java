package Ejercicios;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import Ejercicios.Alumno;

public class Ejercicio38 {

	public static void main(String[] args) {

		/*
		 * Crea una lista de expediente-alumno sin duplicados y ordenados por orden de
		 * inserción, añade cinco niveles y muestra su contenido. Debes utilizar la
		 * clase Alumno ya definida en un ejercicio anterior.
		 */
		
		//Creamos los alumnos
		Alumno al1 = new Alumno(87542391, "Juan", "Juan");
		Alumno al2 = new Alumno(34542312, "Paco", "Paco");
		Alumno al3 = new Alumno(93041235, "Miguel", "Miguel");
		Alumno al4 = new Alumno(58294012, "Pepe", "Pepe");
		Alumno al5 = new Alumno(17390403, "Jose", "Jose");
		
		LinkedHashMap<Integer, Alumno> alumnos = new LinkedHashMap<Integer, Alumno>();
		
		//La clave se saca de la propia clase alumno
		alumnos.put(al1.getExpediente(), al1);
		alumnos.put(al2.getExpediente(), al2);
		alumnos.put(al3.getExpediente(), al3);
		alumnos.put(al4.getExpediente(), al4);
		alumnos.put(al5.getExpediente(), al5);
		
		
		for (Alumno a : alumnos.values()) {
			System.out.println(a.toString());
		}

	}

}
