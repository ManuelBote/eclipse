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
	
	
	
	
	
	
	
	

}
