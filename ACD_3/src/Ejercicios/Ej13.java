package Ejercicios;

import java.util.Scanner;

public class Ej13 {
	
	static Scanner ts = new Scanner(System.in);
	
	//Menu opcion Alta
	public static void menuAlta() {
		int menu = 0;
		
		System.out.println("\n======= Menu Alta =======");
		System.out.println("[1] Alta Veterinario");
		System.out.println("[2] Alta Mascota");
		System.out.println("[3] Alta Propietario");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> System.out.println("Opcion 1/n");
			case 2 -> System.out.println("Opcion 2/n");
			case 3 -> System.out.println("Opcion 3/n");
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}
	
	//Menu opcion Modificacion
	public static void menuMod() {
		int menu = 0;
		
		System.out.println("======= Menu Modificacion =======");
		System.out.println("[1] Modificar Propietario");
		System.out.println("[2] Modificar Mascota");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> System.out.println("Opcion 1/n");
			case 2 -> System.out.println("Opcion 2/n");
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}
	
	//Menu opcion Baja
	public static void menuBaja() {
		int menu = 0;
		
		System.out.println("======= Menu Baja =======");
		System.out.println("[1] Baja Mascota");
		System.out.println("[2] Baja Propietario");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> System.out.println("Opcion 1/n");
			case 2 -> System.out.println("Opcion 2/n");
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}

	public static void main(String[] args) {
		
		int cont = 0;
		boolean condW = true;
		
		do {
			
			System.out.println("======= Menu Veterinaria =======");
			System.out.println("[1] Alta");
			System.out.println("[2] Modificar");
			System.out.println("[3] Baja");
			System.out.println("[4] Aplicar Vacuna");
			System.out.println("[0] Salir");
			System.out.print("Seleccione una opcion: ");
			cont = ts.nextInt(); ts.nextLine();
			
			switch(cont) {
		
			case 1 -> menuAlta();	
			case 2 -> menuMod();	
			case 3 -> menuBaja();	
			case 4 -> System.out.println("Opcion 4\n");
			case 0 -> condW = false;
			
			}
			
		}while(condW);
		System.out.println("Saliendo..");
		
		
	
	}

}
