package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ej9 {
	
	private static final String URL = "jdbc:mysql://localhost:3306/bdalumnos";
	private static final String USER = "root"; // Usuario
	private static final String PASSWORD = ""; // Contraseña
	
	static final Scanner tc = new Scanner(System.in);
	
	public static Connection conectar() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establecer la conexión
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexión exitosa a la base de datos 'test'");
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
		
		int cond = 0;
		
		do {
			
			System.out.println("\n ==== Menu ==== ");
			System.out.println("[1] Mostar alumnos Asignaturas");
			System.out.println("[2] Mostar notas Alumnos");
			System.out.println("[3] Nuevo alumno");
			System.out.println("[4] Aniadir campo telefono");
			System.out.println("[5] Rellenar telefono por id");
			System.out.println("[0] Salir");
			System.out.print("Selecciona una opcion: ");
			cond = tc.nextInt(); tc.nextLine();
			
			switch(cond) {
			case 0 -> System.out.println("Saliendo...");
			case 1 -> mostrarAlumnosasignaturas();
			case 2 -> mostrarNotasAlumnos();
			case 3 -> nuevoAlumno();
			case 4 -> aniadirTelefono();
			case 5 -> rellenarTelefonoId();
			}
			
		}while(cond != 0);

	}

	private static void rellenarTelefonoId() {
		Connection conexion = conectar();
		
		System.out.print("Introduce el id del alumno: ");
		int id = tc.nextInt(); tc.nextLine();
		System.out.print("Introduce el telefono del alumno: ");
		String telefono = tc.nextLine();
		
		String sql = "UPDATE alumno SET telefono = ? WHERE id = ?";
		
		try {
			
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, telefono);
			sentencia.setInt(2, id);
			
			int resultado = sentencia.executeUpdate();
			
			if (resultado != -1) {
				System.out.println("Se a agregado el telefono");
			}
			
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
		

	private static void aniadirTelefono() {
		Connection conexion = conectar();
		
		try {
			
			Statement sentencia = conexion.createStatement();
			int resultado = sentencia.executeUpdate("ALTER TABLE alumno ADD COLUMN IF NOT EXISTS telefono VARCHAR(15)");
			
			if(resultado != -1) {
				System.out.println("Se ha añadido el campo telefono");
			}
			
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
		

	private static void nuevoAlumno() {
		Connection conexion = conectar();
		
		System.out.print("Introduce el nombre del alumno: ");
		String nombreA = tc.nextLine();
		
		try {
			
			Statement sentencia = conexion.createStatement();
			int resultado = sentencia.executeUpdate("INSERT INTO alumno(nombre, curso) VALUES ('" + nombreA + "', '2DAM')");
			
			if(resultado != -1) {
				System.out.println("Se ha añadido el nuevo alumno");
			}
			
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	private static void mostrarNotasAlumnos() {
		Connection conexion = conectar();
		
		System.out.print("Introduce el nombre del alumno: ");
		String nombreA = tc.nextLine();
		
		try {
			
			Statement sentencia = conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery("SELECT a.nombre      AS alumno, "
														+ "       m.nombre      AS modulo, "
														+ "       n.nota		AS nota "
														+ "FROM ALUMNO a "
														+ "JOIN NOTA n   ON n.alumno = a.id "
														+ "JOIN MODULO m ON n.modulo = m.codigo "
														+ "WHERE a.nombre = '"+ nombreA +"' "
														+ "ORDER BY m.nombre, n.nota");
			
			System.out.println("Notas del alumno " + nombreA);
			while(resultado.next()) {
				System.out.println(resultado.getString(2) + " -> " + resultado.getInt(3));
			}
			
			resultado.close();
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void mostrarAlumnosasignaturas() {
	Connection conexion = conectar();
			
			try {
				
				Statement sentencia = conexion.createStatement();
				ResultSet resultado = sentencia.executeQuery("SELECT a.nombre      AS alumno, "
															+ "       m.nombre      AS modulo "
															+ "FROM ALUMNO a "
															+ "JOIN NOTA n   ON n.alumno = a.id "
															+ "JOIN MODULO m ON n.modulo = m.codigo "
															+ "ORDER BY a.nombre, m.nombre");
				
				System.out.println("Nombre / Asignaturas");
				while(resultado.next()) {
					System.out.println(resultado.getString(1) + " / " + resultado.getString(2));
				}
				
				resultado.close();
				sentencia.close();
				conexion.close();
				
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
		

}
