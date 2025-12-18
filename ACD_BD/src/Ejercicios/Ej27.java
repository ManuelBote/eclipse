package Ejercicios;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class Ej27 {
	
	private static final String URL = "jdbc:mysql://localhost:3306/alumnos";
	private static final String USER = "root"; // Usuario
	private static final String PASSWORD = ""; // Contraseña

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
		
		Alumnos a = null;
		
		try {
			JAXBContext contexto = JAXBContext.newInstance(Alumnos.class);
			Unmarshaller um = contexto.createUnmarshaller();
			a = (Alumnos) um.unmarshal(new File("ficheros/ampliarAlumnos.xml"));
			System.out.println("XML cargado: " + a.getListaAlumnos().size() + " alumnos");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Connection conexion = conectar();
		String consulta = "INSERT INTO alumno (nombre, curso, telefono) VALUES (?,?,?)";
		
		for(Alumno al: a.getListaAlumnos()) {
			try {
				PreparedStatement sentencia = conexion.prepareStatement(consulta);
				sentencia.setString(1, al.getNombre());
				sentencia.setString(2, al.getCurso());
				sentencia.setInt(3, al.getTelefono());
				
				int result = sentencia.executeUpdate();
				
				if(result != -1) {
					System.out.println("Alumno aniadido");
				} else {
					System.out.println("Error");
				}
				
				sentencia.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		

	}

}
