package Ejercicios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ej3 {

	private static final String URL = "jdbc:mysql://localhost:3306/bdempleados";
	private static final String USER = "root"; // Usuario
	private static final String PASSWORD = ""; // Contraseña
	
	
	public static Connection conectar() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establecer la conexión
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexión exitosa a la base de datos\n");
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
		String sql = "SELECT * FROM empleados";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("bdempleados.txt")))){
			
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String dni = rs.getString(1);
				String nombre = rs.getString(2);
				int edad = rs.getInt(3);
				String departamento = rs.getString(4);
				int sueldo = rs.getInt(5);
				double prima = rs.getDouble(6);
				bw.write(dni + ";" + nombre + ";" + edad + ";" + departamento + ";" + sueldo + ";" + prima);
				bw.newLine();
			}
			
			System.out.println("Se ha generado el fichero");
			rs.close();
			stmt.close();
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
