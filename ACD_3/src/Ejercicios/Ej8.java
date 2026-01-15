package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Ej8 {
	
	static Scanner ts = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insertarVeterinario();
	}
	

	public static void insertarVeterinario() {
		Connection conexion = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mascota", "postgres", "toor");
			
			System.out.print("DNI del veterinario: ");
			String dni = ts.nextLine();
			System.out.print("Nombre del veterinario: ");
			String nombre = ts.nextLine();
			System.out.print("Nº de colegioalo del veterinario: ");
			int numCol = ts.nextInt(); ts.nextLine();
			
			String sql = "INSERT INTO public.veterinario(dni, nombre, num_colegiado) VALUES (?, ?, ?)";
			
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);	
			stmt.setString(2, nombre);
			stmt.setInt(3, numCol);
			
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
