package Ejercicios;

import java.util.LinkedHashSet;
import java.util.Scanner;

//@author Manuel Bote Zabala
public class Ejercicio35 {

	public static void main(String[] args) {

		/*
		 * Crea una aplicación con un menú de operaciones que nos permita trabajar uno
		 * de los ejercicios anteriores. 
		 * 1.- Añadir elemento 
		 * 2.- Borrar elemento 
		 * 3.- Listar 
		 * 4.- Salir
		 */
		
		Scanner teclado = new Scanner (System.in);
		LinkedHashSet<String> grupos = new LinkedHashSet<String>();
		int num = 0;
		
		try {
			do {
				
				System.out.println("\n===========================");
				System.out.println("1.- Anadir elemento");
				System.out.println("2,- Borrar elemento");
				System.out.println("3.- Listar");
				System.out.println("4.- Salir");
				System.out.printf("Seleccione la opcion: ");
				num = Integer.parseInt(teclado.nextLine());
				System.out.println("===========================\n");
				
				switch(num) {
				case 1:
					System.out.printf("Introduzca el nombre a añadir: ");
					grupos.add(teclado.nextLine());
					break;
					
				case 2:
					System.out.printf("Introduzca el nombre a eliminar: ");
					String p = teclado.nextLine();
					if (grupos.contains(p)) {
						grupos.remove(p);
					} else {
						System.out.println("No se encuentra el elemento a borrar");
					}
					break;
					
				case 3:
					for (String i : grupos) {
						System.out.println(i);
					}
					break;
					
				case 4:
					break;
					
				default:
					System.out.println("Numero incorrecto");
				}
				
			}while(num != 4);
		
		}catch (Exception e) {
			System.out.println("A ocurrido una excepcion: " + e.getMessage());
		}
		
		

	}

}
