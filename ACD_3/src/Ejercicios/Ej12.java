package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class Ej12 {

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

			System.out.print("Cantidad de vacunas de la mascota: ");
			int cant = ts.nextInt(); ts.nextLine();
			
			StringBuilder arrayVacunas = new StringBuilder("ARRAY[");
			
			for (int i = 0; i < cant; i++) {
				System.out.println("Vacuna " + (i+1));
				
				System.out.print("Nombre de la vacuna: ");
				String vacuna = ts.nextLine();
				System.out.print("Numero de colegiado del veterinario: ");
				int veterinario = ts.nextInt(); ts.nextLine();
				System.out.print("Fecha de vacunacion(YYYY-MM-DD): ");
				LocalDate fecha = LocalDate.parse(ts.nextLine());
				
				if(i > 0) {
					arrayVacunas.append(", ");
				}
				arrayVacunas.append("('" + vacuna + "', '" + veterinario + "', '" + fecha + "')");	
			}
			arrayVacunas.append("]::cartilla_vacunacion[]");
			
			
			String sql = "INSERT INTO public.mascota (nombre, especie, raza, vacuna)"
					+ "VALUES (?, ?, ?, "+ arrayVacunas.toString() +")";
			
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, nombre);	
			stmt.setString(2, especie);
			stmt.setString(3, raza);

			
			// Ejecutar la consulta
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
			System.out.println("¡Datos insertados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
