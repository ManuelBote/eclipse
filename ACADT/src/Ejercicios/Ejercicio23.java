package Ejercicios;

import Ejercicios.Persona;
public class Ejercicio23 {

	public static void main(String[] args) {
		
		Persona persona[] = new Persona[5];
		
		
		persona[0] = new Persona("Paco", "Martin", "43591255L", 22, false);
		persona[1] = new Persona("Juan", "Jose", "65347612F", 10, false);
		persona[2] = new Persona("Alex", "Garcia", "65234673F", 27, true);
		persona[3] = new Persona("Julia", "a", "92473812H", 33, false);
		persona[4] = new Persona("Pepe", "a", "12749324N", 56, true);
		
		
		
		System.out.println("Utilizando 'for'");
		for (int i = 0; i< persona.length; i++) {
			System.out.println(persona[i].toString());
		}
		
		System.out.println("Utilizando 'for-each'");
		for (Persona i : persona) {
			System.out.println(i.toString());
		}
		

	}

}
