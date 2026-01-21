package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Ej13 {
	
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
	
	
	//Menu opcion Alta
	public static void menuAlta() {
		int menu = 0;
		
		System.out.println("\n======= Menu Alta =======");
		System.out.println("[1] Alta Veterinario");
		System.out.println("[2] Alta Mascota");
		System.out.println("[3] Alta Propietario");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> System.out.println("Opcion 1/n");
			case 2 -> System.out.println("Opcion 2/n");
			case 3 -> System.out.println("Opcion 3/n");
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}
	
	//Menu opcion Modificacion
	public static void menuMod() {
		int menu = 0;
		
		System.out.println("======= Menu Modificacion =======");
		System.out.println("[1] Modificar Propietario");
		System.out.println("[2] Modificar Mascota");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> System.out.println("Opcion 1/n");
			case 2 -> System.out.println("Opcion 2/n");
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}
	
	//Menu opcion Baja
	public static void menuBaja() {
		int menu = 0;
		
		System.out.println("======= Menu Baja =======");
		System.out.println("[1] Baja Mascota");
		System.out.println("[2] Baja Propietario");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> System.out.println("Opcion 1/n");
			case 2 -> System.out.println("Opcion 2/n");
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}

	public static void main(String[] args) {
		
		int cont = 0;
		boolean condW = true;
		
		do {
			
			System.out.println("======= Menu Veterinaria =======");
			System.out.println("[1] Alta");
			System.out.println("[2] Modificar");
			System.out.println("[3] Baja");
			System.out.println("[4] Aplicar Vacuna");
			System.out.println("[0] Salir");
			System.out.print("Seleccione una opcion: ");
			cont = ts.nextInt(); ts.nextLine();
			
			switch(cont) {
		
			case 1 -> menuAlta();	
			case 2 -> menuMod();	
			case 3 -> menuBaja();	
			case 4 -> System.out.println("Opcion 4\n");
			case 0 -> condW = false;
			
			}
			
		}while(condW);
		System.out.println("Saliendo..");
		
	}
	
	//========================	
	//===== METODOS ALTA =====
	//========================
	
	//Alta Veterinario
	public static void altaVeterinario() {
		Connection conexion = conectar();
		
		try {
			System.out.print("DNI del veterinario: ");
			String dni = ts.nextLine();
			System.out.print("Nombre del veterinario: ");
			String nombre = ts.nextLine();
			System.out.print("Nº de colegioalo del veterinario: ");
			int numCol = ts.nextInt(); ts.nextLine();
			
			String sql = "INSERT INTO public.veterinario(dni, nombre, num_colegiado) VALUES (?, ?, ?)";
			
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);	
			stmt.setString(2, nombre);
			stmt.setInt(3, numCol);
			
			// Ejecutar la consulta
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
			System.out.println("¡Datos insertados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Alta Masctota
	public static void altaMascota() {
		Connection conexion = conectar();
		
		try {		
			System.out.print("Nombre de la mascota: ");
			String nombre = ts.nextLine();
			System.out.print("Especie de la mascota: ");
			String especie = ts.nextLine();
			System.out.print("Raza de la mascota: ");
			String raza = ts.nextLine();

			System.out.print("Cantidad de vacunas de la mascota: ");
			int cant = ts.nextInt(); ts.nextLine();
			
			//Formacion del array
			StringBuilder arrayVacunas = new StringBuilder("ARRAY[");
			
			for (int i = 0; i < cant; i++) {
				System.out.println("Vacuna " + (i+1));
				
				System.out.print("Nombre de la vacuna: ");
				String vacuna = ts.nextLine();
				System.out.print("Numero de colegiado del veterinario: ");
				int veterinario = ts.nextInt(); ts.nextLine();
				System.out.print("Fecha de vacunacion(YYYY-MM-DD): ");
				LocalDate fecha = LocalDate.parse(ts.nextLine());
				
				if(i > 0) {
					arrayVacunas.append(", ");
				}
				arrayVacunas.append("('" + vacuna + "', '" + veterinario + "', '" + fecha + "')");	
			}
			arrayVacunas.append("]::cartilla_vacunacion[]");
			
			
			String sql = "INSERT INTO public.mascota (nombre, especie, raza, vacuna)"
					+ "VALUES (?, ?, ?, "+ arrayVacunas.toString() +")";
			
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, nombre);	
			stmt.setString(2, especie);
			stmt.setString(3, raza);

			
			// Ejecutar la consulta
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
			System.out.println("¡Datos insertados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//AltaPropietario
	public static void insertarPropietario() {
		Connection conexion = conectar();
		
		try {
			System.out.print("DNI del propietario: ");
			String dni = ts.nextLine();
			System.out.print("Nombre del propietario: ");
			String nombre = ts.nextLine();
			System.out.print("Calle del propietario: ");
			String calle = ts.nextLine();
			System.out.print("Nuemero del domicilio del propietario: ");
			int num = ts.nextInt(); ts.nextLine();
			System.out.print("Ciudad del propietario: ");
			String ciudad = ts.nextLine();
			System.out.print("Codigo postal: ");
			int cp = ts.nextInt(); ts.nextLine();
			
			String sql = "INSERT INTO public.propietario(dni, nombre, direccion) VALUES (?, ?, (?, ?, ?, ?))";
			
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);	
			stmt.setString(2, nombre);
			stmt.setString(3, calle);
			stmt.setInt(4, num);
			stmt.setString(5, ciudad);
			stmt.setInt(6, cp);
			
			// Ejecutar la consulta
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
			System.out.println("¡Datos insertados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//=============================	
	//===== METODOS MODIFICAR =====
	//=============================
	
	//Modificar Propietario
	

}
