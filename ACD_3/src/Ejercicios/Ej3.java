package Ejercicios;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Ej3 {
	
	static Scanner ts = new Scanner(System.in);

	public static void main(String[] args) {	
		insertarMascota();
	}
	
	
	
	public static void insertarMascota() {
		Connection conexion = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mascota", "postgres", "toor");
			
			System.out.print("Nombre de la mascota: ");
			String nombre = ts.nextLine();
			System.out.print("Especie de la mascota: ");
			String especie = ts.nextLine();
			System.out.print("Raza de la mascota: ");
			String raza = ts.nextLine();
			
			String sql = "INSERT INTO public.mascota (nombre, especie, raza)"
					+ "VALUES ('" + nombre + "', '" + especie + "', '" + raza + "')";
			
			Statement stmt = conexion.createStatement();
			
			// Ejecutar la consulta
			int filasInsertadas = stmt.executeUpdate(sql);
			if (filasInsertadas > 0) {
			System.out.println("¡Datos insertados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
