package EjerciciosJSON;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Ej47 {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * Realiza un programa para gestionar recetas y la persistencia se realizará
		 * utilizando un fichero Json. (Opciones básicas: Nueva receta, Mostrar recetas
		 * y Borrar receta)
		 */
		
		
		RecetasContenedor receta = new RecetasContenedor();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			RecetasContenedor recetas = mapper.readValue(new File("json/ej46recetas.json"), RecetasContenedor.class);
			receta.setRecetas(recetas.getRecetas());
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		int cond = 0;
		
		do {
			
			System.out.println("[1] Nueva receta");
			System.out.println("[2] Mostrar recetas");
			System.out.println("[3] Borrar receta");
			System.out.println("[0] Salir");
			System.out.print("Selecciona una opcion: ");
			cond = Integer.parseInt(sc.nextLine());
			
			switch(cond) {
			
			case 0:
				break;

			case 1:
				aniadirReceta(receta);
				break;
			
			case 2:
				break;
			
			case 3:
				break;
			}
			
		}while(cond != 0);
		

	}
	
	public static void aniadirReceta(RecetasContenedor receta) {
		
		System.out.print("Introduce el nombre de la receta: ");
		String nombre = sc.nextLine();
		
		System.out.print("Introduce el tipo de la receta: ");
		String tipo = sc.nextLine();
		
		System.out.print("Introduce el pais de origen de la receta: ");
		String pais = sc.nextLine();
		
		System.out.print("Introduce la region de origen de la receta: ");
		String region = sc.nextLine();
		
		int c = 0;
		
		do {
			
			System.out.print("Introduce el nombre de ingrediente: ");
			String nombreI = sc.nextLine();
			System.out.print("Introduce la cantidad de ingrediente: ");
			String cantidad = sc.nextLine();
			
			System.out.print("¿Desea añadir otro ingrediente?: ");
			c = Integer.parseInt(sc.nextLine());
		}while(c != 0);
		
	}

}
