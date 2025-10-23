package Ejercicios;

import java.util.*;

//@author Manuel Bote Zabala

public class EjercicioExcepciones {

	public static void main(String[] args) {

		// 1. Conversión inválida de String a int
		try {
			String invalido = "abc";
			int numero = Integer.parseInt(invalido);

		} catch (IllegalArgumentException e) {
			System.out.println("No se puede parsear. " + e.getMessage());
		}

		
		// 2. División por cero
		try {
			int resultado = 10 / 0;

		} catch (ArithmeticException e) {
			System.out.println("No se puede dividir por cero. " + e.getMessage());
		}

		
		// 3. Operación sobre variable null
		try {
			String texto = null;
			int longitud = texto.length();

		} catch (NullPointerException e) {
			System.out.println("No se puede operar con un valor null. " + e.getMessage());
		}

		
		// 4. Acceso a índice inválido en lista
		try {
			List<String> lista = new ArrayList<>();
			lista.add("A");
			String valor = lista.get(2);
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No se puede acceder a esa posicion. " + e.getMessage());
		}

		
		// 5. Modificación de colección durante iteración
		try {
			List<String> nombres = new ArrayList<>();
			nombres.add("Ana");
			nombres.add("Luis");

			for (String nombre : nombres) {
				System.out.println(nombre);
				if (nombre.equals("Ana")) {
					nombres.remove(nombre);
				}

			}
			
		} catch (ConcurrentModificationException e) {
			System.out.println("No se puede modificar la coleccion durante la iteracion. " + e.getMessage());
		}

	}

}
