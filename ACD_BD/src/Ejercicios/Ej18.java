package Ejercicios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Ej18 {
	
	private static final String URL = "jdbc:mysql://localhost:3306/bdalumnos";
	private static final String USER = "root"; // Usuario
	private static final String PASSWORD = ""; // Contraseña
	private static Scanner tc = new Scanner(System.in);

	public static Connection conectar() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establecer la conexión
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexión exitosa a la base de datos");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: No se encontró el driver de MySQL.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error: No se pudo conectar a la base de datos.");
			e.printStackTrace();
		}
		return conexion;
	}

	public static void main(String[] args) {
		Connection conexion = conectar();
		
		String consulta = "{?=Call chequearAlumno(?)}";
		
		System.out.print("Introduce el id a buscar: ");
		int id = tc.nextInt(); tc.nextLine();
		
		try {
			
			CallableStatement stmt = conexion.prepareCall(consulta);
			
			stmt.registerOutParameter(1, java.sql.Types.BOOLEAN);
			stmt.setInt(2, id);
			
			stmt.execute();
			
			boolean resultado = stmt.getBoolean(1);
			
			if(resultado) {
				System.out.println("Se ha encontrado el alumno");
			} else {
				System.out.println("No existe un alumno con ese id");
			}
			
			stmt.close();
			conexion.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
