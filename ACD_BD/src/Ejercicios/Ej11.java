package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//@author Manuel Bote Zabala
public class Ej11 {
	

	private static final String URL = "jdbc:mysql://localhost:3306/navavinted";
	private static final String USER = "root"; // Usuario
	private static final String PASSWORD = ""; // Contraseña
	
	static final Scanner tc = new Scanner(System.in);
	
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
		
		int cond = 0;
		
		do {
			
			System.out.println("\n ==== Menu ==== ");
			System.out.println("[1] Productos por Categoria");
			System.out.println("[2] Productos por Talla");
			System.out.println("[3] Nuevo producto");
			System.out.println("[4] Calcular precio Final");
			System.out.println("[5] Vender un producto");
			System.out.println("[0] Salir");
			System.out.print("Selecciona una opcion: ");
			cond = tc.nextInt(); tc.nextLine();
			
			switch(cond) {
			case 0 -> System.out.println("Saliendo...");
			case 1 -> productoCategoria();
			case 2 -> productoTalla();
			case 3 -> nuevoProducto();
			case 4 -> calcularPrecioFinal();
			case 5 -> venderProducto();
			}
			
		}while(cond != 0);

	}
	
	public static void productoCategoria() {
		Connection conexion = conectar();
		
		System.out.print("Indica la categoria: ");
		String categoria = tc.nextLine();
		
		String sql = "SELECT p.id_Producto, p.nombre_Producto, p.estado, p.precio "
				+ "FROM PRODUCTO p "
				+ "JOIN CATEGORIA c ON p.id_categoria = c.id_categoria "
				+ "WHERE c.categoria = ?";
		
		try {
			
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, categoria);
			
			ResultSet resultado = sentencia.executeQuery();
			
			System.out.println("Categoria / id_producto / nombre_producto / estado / precio");
			while(resultado.next()) {
				System.out.println(categoria + " -> " + resultado.getString(1) + " / " + resultado.getString(2) + " / " + resultado.getString(3) + " / " + resultado.getDouble(4));
			}
			
			resultado.close();
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static void productoTalla() {
		Connection conexion = conectar();
		
		System.out.print("Indica la talla: ");
		String talla = tc.nextLine();
		
		String sql = "SELECT p.id_Producto, p.nombre_Producto, p.estado, p.precio "
				+ "FROM PRODUCTO p "
				+ "JOIN TALLA t ON p.id_talla = t.id_talla "
				+ "WHERE t.talla = ?";
		
		try {
			
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, talla);
			
			ResultSet resultado = sentencia.executeQuery();
			
			System.out.println("Talla / id_producto / nombre_producto / estado / precio");
			while(resultado.next()) {
				System.out.println(talla + " -> " + resultado.getString(1) + " / " + resultado.getString(2) + " / " + resultado.getString(3) + " / " + resultado.getDouble(4));
			}
			
			resultado.close();
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
	}
	
	
	
	
	public static void nuevoProducto() {
		Connection conexion = conectar();
		
		System.out.print("Introduce el nombre del producto: ");
		String nombre = tc.nextLine();
		System.out.print("Introduce el id de la categoria: ");
		int idCategoria = tc.nextInt(); tc.nextLine();
		System.out.print("Introduce el id de la talla: ");
		int idTalla = tc.nextInt(); tc.nextLine();
		System.out.print("Introduce el id del color: ");
		int idColor = tc.nextInt(); tc.nextLine();
		System.out.print("Introduce el id del material: ");
		int idMaterial = tc.nextInt(); tc.nextLine();
		System.out.print("Introduce el stock del producto: ");
		int stock = tc.nextInt(); tc.nextLine();
		System.out.print("Introduce el precio del producto: ");
		double precio = tc.nextDouble(); tc.nextLine();
		System.out.print("Introduce el costo del producto: ");
		double costo = tc.nextDouble(); tc.nextLine();
		System.out.print("Introduce el estado del producto: ");
		String estado = tc.nextLine();
		System.out.print("Introduce el descuento del producto: ");
		int descuento = tc.nextInt(); tc.nextLine();
		
		String sql = "INSERT INTO producto (nombre_Producto, id_Categoria, id_Talla, id_Color, id_Material, stock, precio, costo, estado, descuento)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, nombre);
			sentencia.setInt(2, idCategoria);
			sentencia.setInt(3, idTalla);
			sentencia.setInt(4, idColor);
			sentencia.setInt(5, idMaterial);
			sentencia.setInt(6, stock);
			sentencia.setDouble(7, precio);
			sentencia.setDouble(8, costo);
			sentencia.setString(9, estado);
			sentencia.setInt(10, descuento);

			
			int resultado = sentencia.executeUpdate();
			
			if(resultado != -1) {
				System.out.println("Se ha añadido el nuevo producto");
			}
			
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static void calcularPrecioFinal() {
		Connection conexion = conectar();
		
		System.out.print("Indica el id del producto: ");
		int id = tc.nextInt();
		
		String sql = "SELECT precio, descuento FROM producto WHERE id_Producto = ?";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, id);
			
			ResultSet resultado = sentencia.executeQuery();
			
			while(resultado.next()) {
				double precio = resultado.getDouble(1);
				int descuento = resultado.getInt(2);
				double preciof = precio - (precio*descuento/100);
				
				System.out.println("El precio final del producto es: " + preciof);				
			}
			
			resultado.close();
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public static void venderProducto() {	
		Connection conexion = conectar();
		
		System.out.print("Indica el id del producto: ");
		int id = tc.nextInt();
		
		System.out.print("Indica la cantidad a comprar: ");
		int compra = tc.nextInt();
		
		String sql = "UPDATE producto SET stock = stock - ? WHERE id_Producto = ?";
		
		try {
			
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, id);
			sentencia.setInt(2, compra);
			int resultado = sentencia.executeUpdate();
			
			if(resultado != -1) {
				System.out.println("Se ha realizado la compra");
			}
			
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
