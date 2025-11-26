package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej3 {

	private static final String URL = "jdbc:mysql://localhost:3306/bdalumnos";
	private static final String USER = "root"; // Usuario
	private static final String PASSWORD = ""; // Contraseña

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
		// Llamar al método conectar para establecer la conexión
		Connection conexion = conectar();
		// Cerrar la conexión después de usarla
		if (conexion != null) {
			try {
				Statement sentencia = conexion.createStatement();
				ResultSet resultado = sentencia.executeQuery("Select * from alumno");
				
				while(resultado.next()) {
					System.out.println(resultado.getInt(1) + ", " + resultado.getString(2) + ", " + resultado.getString(3));
				}
				
				resultado.close();
				sentencia.close();
				conexion.close();
				System.out.println("Conexión cerrada.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}}

}
