package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Ej10 {


	static Scanner ts = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insertarPropietario();
	}
	

	public static void insertarPropietario() {
		Connection conexion = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mascota", "postgres", "toor");
			
			System.out.print("DNI del propietario: ");
			String dni = ts.nextLine();
			System.out.print("Nombre del propietario: ");
			String nombre = ts.nextLine();
			System.out.print("Calle del propietario: ");
			String calle = ts.nextLine();
			System.out.print("Nuemero del domicilio del propietario: ");
			int num = ts.nextInt(); ts.nextLine();
			System.out.print("Ciudad del propietario: ");
			String ciudad = ts.nextLine();
			System.out.print("Codigo postal: ");
			int cp = ts.nextInt(); ts.nextLine();
			
			String sql = "INSERT INTO public.propietario(dni, nombre, direccion) VALUES (?, ?, (?, ?, ?, ?))";
			
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);	
			stmt.setString(2, nombre);
			stmt.setString(3, calle);
			stmt.setInt(4, num);
			stmt.setString(5, ciudad);
			stmt.setInt(6, cp);
			
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
