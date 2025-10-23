package Ejercicios;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

//@author Manuel Bote Zabala

public class Ejercicio39 {

	public static void main(String[] args) {

		/*
		 * Crea una aplicación con un menú de operaciones que nos permita trabajar con
		 * el ejercicio anterior. 1.- Añadir elemento 2.- Buscar elemento 3.- Borrar
		 * elemento 4.- Listar 5.- Salir
		 */

		// Creamos datos para tomar ejemplos, el scanner para recibir datos por teclado
		// y el mapa
		Alumno al1 = new Alumno(87542391, "Juan", "Juan");
		Alumno al2 = new Alumno(34542312, "Paco", "Paco");
		Alumno al3 = new Alumno(93041235, "Miguel", "Miguel");
		Alumno al4 = new Alumno(58294012, "Pepe", "Pepe");
		Alumno al5 = new Alumno(17390403, "Jose", "Jose");

		Scanner teclado = new Scanner(System.in);
		LinkedHashMap<Integer, Alumno> alumnos = new LinkedHashMap<Integer, Alumno>();

		alumnos.put(al1.getExpediente(), al1);
		alumnos.put(al2.getExpediente(), al2);
		alumnos.put(al3.getExpediente(), al3);
		alumnos.put(al4.getExpediente(), al4);
		alumnos.put(al5.getExpediente(), al5);

		int num = 0;

		try {
			do {

				//Menu
				System.out.println("\n===========================");
				System.out.println("1.- Anadir elemento");
				System.out.println("2,- Buscar elemento");
				System.out.println("3,- Eliminar elemento");
				System.out.println("4.- Listar");
				System.out.println("5.- Salir");
				System.out.printf("Seleccione la opcion: ");
				num = Integer.parseInt(teclado.nextLine());
				System.out.println("===========================\n");

				
				switch (num) {
				//Caso 1, añadir elemento. Primero comprueba que el alumno no este repetido y luego lo agrega
				case 1:
					System.out.printf("Introduzca el numero de expediente del alumno a añadir: ");
					int numEx = Integer.parseInt(teclado.nextLine());
					
					if (alumnos.containsKey(numEx)) {
						System.out.println("El alumno ya esta añadido");
					} else {
						System.out.printf("Introduzca el nombre del alumno: ");
						String nom = teclado.nextLine();
						System.out.printf("Introduzaca los apellidos del alumno: ");
						String apl = teclado.nextLine();
						
						alumnos.put(numEx, new Alumno(numEx, nom, apl));
					}
					break;

				//Caso 2, buscar elemento. Se busca el elemento por expediente y si existe se pregunta que desea realizar
				case 2:
					System.out.printf("Introduzca el expediente del alumno que desee buscar: ");
					int ex = Integer.parseInt(teclado.nextLine());
					if (alumnos.containsKey(ex)) {
						int cond = 0;
						
						//Menu para poder añadir notas o calcular la media de un solo alumno
						do {
							System.out.println("\nQue desea realizar:");
							System.out.println("(1). Añadir una nota");
							System.out.println("(2). Calcular nota media");
							System.out.println("(3). Mostrar");
							System.out.println("(4). Salir");
							cond = Integer.parseInt(teclado.nextLine());
							
							//Añadir nota
							switch(cond) {
							case 1:
								System.out.printf("Introduzca la nota para añadir: ");
								alumnos.get(ex).anadirNota(Double.parseDouble(teclado.nextLine()));
								break;
								
							//Calcular la media, primero comprueba que tenga notas el alumno
							case 2:
								if (alumnos.get(ex).getNotas().isEmpty()) {
									System.out.println("El alumno no tiene notas agregadas");
								} else {
									System.out.println(alumnos.get(ex).calcularNota());
								}
								break;
								
							//Lista solo al alumno seleccionado
							case 3:
								System.out.println(alumnos.get(ex).toString());
								break;

							//Salir
							case 4:
								break;
								
							default:
								System.out.println("Numero incorrecto");
							}
							
						} while(cond != 4);
						
						
					} else {
						System.out.println("No se encuentra el alumnos");
					}
					break;

				//Caso 3, eliminar alumno.
				case 3:
					System.out.printf("Introduzca el expediente del alumno que desee eliminar: ");
					int exp = Integer.parseInt(teclado.nextLine());
					if (alumnos.containsKey(exp)) {
						alumnos.remove(exp);
					} else {
						System.out.println("No se encuentra el alumnos");
					}
					
					break;

				//Caso 4, mostrar alumnos
				case 4:
					for (int e : alumnos.keySet()) {
						System.out.println(alumnos.get(e).toString());
					}
					break;
					
				//Salir
				case 5:
					break;

				default:
					System.out.println("Numero incorrecto");
				}

			} while (num != 5);

		} catch (Exception e) {
			System.out.println("A ocurrido una excepcion: " + e.getMessage());
		}

	}

}
