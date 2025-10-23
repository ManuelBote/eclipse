package Ejercicios;

import Ejercicios.Persona;
public class Ejercicio25_26 {

	
	public static void main(String[] args) {
		
		Persona p1 = new Persona("Juan", "Ramon", "54372332H", 25, false);
		Persona p2 = new Persona("Marta", "Gimenez", "42338693Y", 30, true);
		
		if(p1.getEdad() > p2.getEdad()) {
			System.out.println(p1.getNombre() + " es mayor que " + p2.getNombre());
		}else {
			System.out.println(p2.getNombre() + " es mayor que " + p1.getNombre());
		}

	}

}
