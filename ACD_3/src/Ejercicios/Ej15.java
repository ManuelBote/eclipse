package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
			System.out.println("[3] Mostrar perros vacunados de 'Moquillo'");
			System.out.println("[0] Salir");
			System.out.print("Seleccione una opcion: ");
			cont = ts.nextInt(); ts.nextLine();
			
			switch(cont) {
		
			case 1 -> metodo1();
			case 2 -> metodo2();	
			case 3 -> metodo3();
			case 0 -> condW = false;
			
			}
			
		}while(condW);
		System.out.println("Saliendo..");

	}
	
	
	//Metodo 1
	public static void metodo1() {
		Connection conexion = conectar();
		
		String sql = " SELECT  m.id, m.nombre, m.especie, m.raza, m.dni_propietario, "
							+ " v.nombre_vacuna, v.numero_colegiado_veterinario, v.fecha_aplicacion "
							+ "FROM public.mascota m  LEFT JOIN LATERAL unnest(m.vacuna) v("
									+ "nombre_vacuna, numero_colegiado_veterinario, fecha_aplicacion "
							+ ") ON true "
							+ "ORDER BY m.id";
		
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			int idCont = -1;
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
		        String nombre = rs.getString(1);
		        String especie = rs.getString(2);
		        String raza = rs.getString(3);
		        String dniProp = rs.getString(4);

		        String nombreVacuna = rs.getString(5);
		        Integer numVet = (Integer) rs.getObject(6);
		        Date fechaVac = rs.getDate(7);
			
		        
		        if (id != idCont) {
		            System.out.println("\nMascota ID: " + id);
		            System.out.println("  Nombre: " + nombre);
		            System.out.println("  Especie: " + especie);
		            System.out.println("  Raza: " + raza);
		            System.out.println("  DNI propietario: " + dniProp);
		            System.out.println("  Vacunas:");
		            idCont = id;
		        }
		        
		        if (nombreVacuna != null) {
		            System.out.println("\t- " + nombreVacuna + "(Vet: " + numVet + ", Fecha: " + fechaVac + ")");
		        } else {
		            System.out.println("\t(Sin vacunas registradas)");
		        }
		        
			}
			
			 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	//Metodo 2
	public static void metodo2() {
		Connection conexion = conectar();
		
		System.out.print("Introduce el dni del propietario: ");
		String dni = ts.nextLine();
		
		
		String sql = "SELECT nombre, especie FROM public.mascota WHERE dni_propietario = ?";
		
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("\nNombre: " + rs.getString(1));
				System.out.println("Especie: " + rs.getString(2));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	//Metodo 3
	public static void metodo3() {
		Connection conexion = conectar();
		
		String sql = "SELECT DISTINCT m.nombre, m.especie, m.raza, m.dni_propietario"
						+ "FROM public.mascota m WHERE m.especie = 'perro' "
						+ "AND EXISTS (SELECT 1 FROM unnest(m.vacuna) AS v(nombre_vacuna, numero_colegiado_veterinario, fecha_aplicacion) "
										+ "WHERE v.nombre_vacuna = 'moquillo')";
		
		try {
			
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("\nNombre: " + rs.getString(1));
				System.out.println("Especie: " + rs.getString(2));
				System.out.println("Raza: " + rs.getString(3));
				System.out.println("DNI del Propietario: " + rs.getString(4));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
