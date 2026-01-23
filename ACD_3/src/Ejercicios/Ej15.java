package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ej15 {
	
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

		int cont = 0;
		boolean condW = true;
		
		do {
			
			System.out.println("\n======= Menu =======");
			System.out.println("[1] Listar mascotas");
			System.out.println("[2] Mostrar mascotas del propietario (dni)");
			System.out.println("[3] Mostrar perrors vacunados de 'Moquillo'");
			System.out.println("[0] Salir");
			System.out.print("Seleccione una opcion: ");
			cont = ts.nextInt(); ts.nextLine();
			
			switch(cont) {
		
			case 1 -> metodo1();
			case 2 -> System.out.println("Opcion2");	
			case 3 -> System.out.println("Opcion3");
			case 0 -> condW = false;
			
			}
			
		}while(condW);
		System.out.println("Saliendo..");

	}
	
	
	//Metodo 1
	public static void metodo1() {
		Connection conexion = conectar();
		
		String sql = "SELECT * FROM public.mascota";
		
		try {
			PreparedStatement select = conexion.prepareStatement(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	

}
