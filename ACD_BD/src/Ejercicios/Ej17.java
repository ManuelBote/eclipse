package Ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ej17 {

	private static final String URL = "jdbc:mysql://localhost:3306/navavinted";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static Scanner tc = new Scanner(System.in);
	
	public static Connection conectar() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
		
		System.out.println("¿Desea agregar los nuevos datos? (s/n)");
		String cond = tc.nextLine();
		
		if(cond.toLowerCase().equals("s")) {
			
			File archivo = new File("ficheros/navaVinted.csv");
			
			if(archivo.exists()) {				
				try(BufferedReader bf = new BufferedReader(new FileReader(archivo))) {									
					Connection conexion = conectar();
					String linea = "";
					
					while((linea = bf.readLine()) != null) {
						String[] datos = linea.split(";");
						
						String sql = "INSERT INTO producto (nombre_Producto, id_Categoria, id_Talla, id_Color, id_Material, stock, precio, costo, estado, descuento)"
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						
						PreparedStatement sentencia = conexion.prepareStatement(sql);
						sentencia.setString(1, datos[1]);
						sentencia.setInt(2, Integer.parseInt(datos[2]));
						sentencia.setInt(3, Integer.parseInt(datos[3]));
						sentencia.setInt(4, Integer.parseInt(datos[4]));
						sentencia.setInt(5, Integer.parseInt(datos[5]));
						sentencia.setInt(6, Integer.parseInt(datos[6]));
						sentencia.setDouble(7, Double.parseDouble(datos[7]));
						sentencia.setDouble(8, Double.parseDouble(datos[8]));
						sentencia.setString(9, datos[9]);
						sentencia.setInt(10, Integer.parseInt(datos[10]));
						
						int resultado = sentencia.executeUpdate();
						
						if(resultado != -1) {
							System.out.println("Se ha aniadido un nuevo producto");
						}
						
					}
					
					System.out.println("Se han aniadido todos los productos");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			} else {
				System.out.println("El fichero no existe");
			}
			
			
		}else {
			System.out.println("Saliendo..");
		}
		

	}

}
