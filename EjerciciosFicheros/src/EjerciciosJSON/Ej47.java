package EjerciciosJSON;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Ej47 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * Realiza un programa para gestionar recetas y la persistencia se realizará
		 * utilizando un fichero Json. (Opciones básicas: Nueva receta, Mostrar recetas
		 * y Borrar receta)
		 */
		
		Scanner sc = new Scanner(System.in);
		RecetasContenedor receta = new RecetasContenedor();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			RecetasContenedor recetas = mapper.readValue(new File("json/ej46recetas.json"), RecetasContenedor.class);
			receta.setRecetas(recetas.getRecetas());
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		

	}

}
