package Ejercicios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Ej19 {
	
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
		
		System.out.print("Introduce el nombre del alumno: ");
		String nombre = tc.nextLine();
		System.out.print("Introduce el curso del alumno: ");
		String curso = tc.nextLine();
		System.out.print("Introduce el telefono del alumno: ");
		int tlf = tc.nextInt(); tc.nextLine();
		
		String consulta = "{ CALL guardarAlumno(?,?,?)}";
		
		try {
			
			CallableStatement stmt = conexion.prepareCall(consulta);
			stmt.setString(1, nombre);
			stmt.setString(2, curso);
			stmt.setInt(3, tlf);
			
			stmt.execute();
			
			System.out.println("Alumno agregado");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		

	}

}
