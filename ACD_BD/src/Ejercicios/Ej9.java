package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
			System.out.println("Selecciona una opcion");
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

	private static Object rellenarTelefonoId() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object aniadirTelefono() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object nuevoAlumno() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object mostrarNotasAlumnos() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object mostrarAlumnosasignaturas() {
		// TODO Auto-generated method stub
		return null;
	}

}
