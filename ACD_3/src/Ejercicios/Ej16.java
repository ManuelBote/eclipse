package Ejercicios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ej16 {
	
	static Scanner ts = new Scanner(System.in);
	
	//Metodo para hacer la conexion a la BD
	public static Connection conectar() {
		Connection conexion = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mascota", "postgres", "toor");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conexion;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cont = 0;
		boolean condW = true;
		
		do {
			
			System.out.println("\n======= Menu =======");
			System.out.println("[1] Listado de mascotas");
			System.out.println("[2] Poner vacuna a mascota");
			System.out.println("[0] Salir");
			System.out.print("Seleccione una opcion: ");
			cont = ts.nextInt(); ts.nextLine();
			
			switch(cont) {
		
			case 1 -> listadoMascota();
			case 2 -> vacunaMascota();	
			case 0 -> condW = false;
			
			}
			
		}while(condW);
		System.out.println("Saliendo..");

	}
	
	
	public static void listadoMascota() {
		Connection conexion = conectar();
		
		String sql = "SELECT public.contar_mascotas()";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int cantidad = rs.getInt(1);
				System.out.println("Cantidad de mascotas en la base de datos: " + cantidad);				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void vacunaMascota() {
		
	}
	
	/*
	private static void contarMascotasTotal(Connection conn) {
		try (CallableStatement stmt = conn.prepareCall("{ ? = call total_mascotas() }")) {
			stmt.registerOutParameter(1, Types.INTEGER);

			stmt.execute();

			int total = stmt.getInt(1);
			System.out.println("\nTOTAL MASCOTAS REGISTRADAS: " + total);

		} catch (SQLException e) {
			System.out.println("Error función: " + e.getMessage());
		}
	}

	private static void vacunar(Connection conn, Scanner sc) {
		try (CallableStatement stmt = conn.prepareCall("{ ? = call vacunar_mascota_existente(?, ?, ?, ?) }")) {
			stmt.registerOutParameter(1, Types.VARCHAR);

			System.out.print("Nombre mascota: ");
			stmt.setString(2, sc.nextLine());
			System.out.print("Vacuna: ");
			stmt.setString(3, sc.nextLine());
			System.out.print("Número de colegiado: ");
			stmt.setInt(4, Integer.parseInt(sc.nextLine()));
			System.out.print("Fecha (YYYY-MM-DD): ");
			stmt.setDate(5, java.sql.Date.valueOf(sc.nextLine()));

			stmt.execute();
			System.out.println("\n" + stmt.getString(1));

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	*/
	
	
	
	

}
