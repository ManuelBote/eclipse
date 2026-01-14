package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ej4 {
	
	public static void main(String[] args) {	
		mostrarMascota();
	}
	
	
	public static void mostrarMascota() {
		Connection conexion = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mascota", "postgres", "toor");
			
			String sql = "SELECT id, nombre, especie, raza FROM public.mascota";
			
			Statement stmt = conexion.createStatement();
			
			// Ejecutar la consulta
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            String especie = rs.getString("especie");
	            String raza = rs.getString("raza");

	            System.out.println(
	                "ID: " + id +
	                ", Nombre: " + nombre +
	                ", Especie: " + especie +
	                ", Raza: " + raza
	            );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
