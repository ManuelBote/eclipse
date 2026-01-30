package EjercicioExamen;

import java.awt.Taskbar.State;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Scanner;

public class Ej2_ManuelBote {
	
	
	static Scanner ts = new Scanner(System.in);

	//Metodo para hacer la conexion a la BD
	public static Connection conectar() {
		Connection conexion = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BDVehiculosManuel", "postgres", "toor");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conexion;
	}
	
	
	//Menu opcion conductores
	public static void menuConductores() {
		int menu = 0;
		
		System.out.println("\n======= Menu Conductores =======");
		System.out.println("[1] Agregar conductor");
		System.out.println("[2] Eliminar conductor");
		System.out.println("[3] Mostrar conductores");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> agregarConductor();
			case 2 -> elimianrConductor();
			case 3 -> monstrarConductores();
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}
	
	//Menu opcion vehiculos
	public static void menuVehivulos() {
		int menu = 0;
		
		System.out.println("\n======= Menu Vehiculos =======");
		System.out.println("[1] Agregar vehiculo");
		System.out.println("[2] Eliminar vehiculo");
		System.out.println("[3] Mostrar vehiculos");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> agregarVehiculo();
			case 2 -> eliminarVehiculo();
			case 3 -> mostrarVehiculos();
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}
	
	//Menu opcion itv
	public static void menuITV() {
		int menu = 0;
		
		System.out.println("\n======= Menu ITV =======");
		System.out.println("[1] Registrar ITV pasada en un vehiculo");
		System.out.println("[2] Mostrar historial de ITV de un vehiculo");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> registrarItv();
			case 2 -> mostrarItv();
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}
	
	//Menu opcion consultas conductores
	public static void menuConsultas() {
		int menu = 0;
		
		System.out.println("\n======= Menu Consultas Conductores =======");
		System.out.println("[1] Total de conductores");
		System.out.println("[2] Subir el irpf a todos los conductores");
		System.out.println("[0] Volver");
		System.out.print("Seleccione una opcion: ");
		menu = ts.nextInt(); ts.nextLine();
		
		switch(menu) {
			case 1 -> totalConductores();
			//case 2 -> subirIrpf();
			case 0 -> System.out.println("Volviendo../n");
			default -> System.out.println("Opcion no valida\n");
		}
	}

	public static void main(String[] args) {
		
		int cont = 0;
		boolean condW = true;
		
		do {
			
			System.out.println("\n======= Menu Vehiculos =======");
			System.out.println("[1] Gestion Conductor");
			System.out.println("[2] Gestion Vehiculo");
			System.out.println("[3] Gestion ITV");
			System.out.println("[4] Consultas conductores");
			System.out.println("[5] Salir");
			System.out.print("Seleccione una opcion: ");
			cont = ts.nextInt(); ts.nextLine();
			
			switch(cont) {
		
			case 1 -> menuConductores();	
			case 2 -> menuVehivulos();	
			case 3 -> menuITV();	
			case 4 -> menuConsultas();
			case 5 -> condW = false;
			default -> System.out.println("Opcion no valida\n");
			
			}
			
		}while(condW);
		System.out.println("Saliendo..");
		
	}
	
	
	public static void agregarConductor() {
		Connection conexion = conectar();
		
		System.out.print("\nDNI: ");
		String dni = ts.nextLine();
		System.out.print("Nombre: ");
		String nombre = ts.nextLine();
		System.out.print("Apellidos: ");
		String apellidos = ts.nextLine();
		System.out.print("Salario bruto: ");
		int bruto = ts.nextInt(); ts.nextLine();
		System.out.print("IRPF: ");
		double irpf = ts.nextDouble(); ts.nextLine();
		System.out.print("Numero de licencia: ");
		int numerolicencia = ts.nextInt(); ts.nextLine();
		
		String sql = "INSERT INTO public.conductor	(dni, nombre, apellidos, sueldo, numero_licencia)	VALUES (?, ?, ?, (?, ?), ?)";
		
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);
			stmt.setString(2, nombre);
			stmt.setString(3, apellidos);
			stmt.setInt(4, bruto);
			stmt.setDouble(5, irpf);
			stmt.setInt(6, numerolicencia);
			
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
			System.out.println("¡Datos insertados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void elimianrConductor() {
		Connection conexion = conectar();
		
		System.out.print("\nDNI del conductor a eliminar: ");
		String dni = ts.nextLine();
		
		String sql = "DELETE FROM public.conductor WHERE dni = ?";
		
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, dni);
		
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
			System.out.println("¡Datos eliminados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void monstrarConductores() {
		Connection conexion = conectar();
		
		String sql = "SELECT * FROM public.conductor ORDER BY dni";
		
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("\n" + rs.getString(1) + ", "
						+ rs.getString(2) + " " + rs.getString(3)
						+"\nNumero de licencia: " + rs.getInt(5));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void agregarVehiculo() {
		Connection conexion = conectar();
		
		try {		
			System.out.print("\nMatricula: ");
			String nombre = ts.nextLine();
			System.out.print("Marca: ");
			String especie = ts.nextLine();
			System.out.print("Modelo: ");
			String raza = ts.nextLine();
			System.out.print("anio: ");
			int anio = ts.nextInt(); ts.nextLine();

			System.out.print("Cantidad de itvs pasadas:");
			int cant = ts.nextInt(); ts.nextLine();
			
			LocalDate[] itv = new LocalDate[cant];
			for(int i = 0; i < cant; i++) {
				System.out.print("Fecha de itv(YYYY-MM-DD): ");
				LocalDate fecha = LocalDate.parse(ts.nextLine());
				itv[i] = fecha;
			}
			Array itvs = conexion.createArrayOf("date", itv);
			
			
			String sql = "INSERT INTO public.vehiculo (matricula, marca, modelo, anio, itv)"
					+ "VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, nombre);	
			stmt.setString(2, especie);
			stmt.setString(3, raza);
			stmt.setInt(4, anio);
			stmt.setArray(5, itvs);

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
	
	public static void eliminarVehiculo() {
		Connection conexion = conectar();
		
		System.out.print("\nMatricula del vehiculo a eliminar: ");
		String matricula = ts.nextLine();
		
		String sql = "DELETE FROM public.vehiculo WHERE matricula = ?";
		
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, matricula);
		
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
			System.out.println("¡Datos eliminados con éxito!");
			} else {
			System.out.println("No se insertaron datos.");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void mostrarVehiculos() {
		Connection conexion = conectar();
		
		String sql = "SELECT * FROM public.vehiculo";
		
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("\n" + rs.getString(1) + "\n"
						+ rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getInt(4)
						+"\nITVs: " + rs.getArray(5));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public static void registrarItv() {
		Connection conexion = conectar();
		
		System.out.print("\nMatricula del vehiculo a agregar itv: ");
		String matricula = ts.nextLine();
		System.out.print("Fecha de itv(YYYY-MM-DD): ");
		Date fecha = Date.valueOf(LocalDate.parse(ts.nextLine()));
		
		String sql = "UPDATE public.vehiculo SET itv = array_append(itv, ?) WHERE matricula = ?";
		
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setDate(1, fecha);
			stmt.setString(2, matricula);
			
			int resultado = stmt.executeUpdate();
			if (resultado > 0) {
				System.out.println("¡Datos insertados con éxito!");
			} else {
				System.out.println("No se insertaron datos.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	public static void mostrarItv() {
		Connection conexion = conectar();
		
		System.out.print("\nMatricula del vehiculo: ");
		String matricula = ts.nextLine();
		
		String sql = "SELECT * FROM public.vehiculo WHERE matricula = ?";
		
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, matricula);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
					System.out.println(rs.getArray(5));
				}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

	
	
	
	
	public static void totalConductores() {
		Connection conexion = conectar();
		
		String sql = "{ ? = call contar_conductores() }";
		
		try {
			 CallableStatement stmt = conexion.prepareCall(sql);
			 stmt.registerOutParameter(1, Types.INTEGER);
			 
			 stmt.execute();
			 System.out.println("Total de conductores: " + stmt.getInt(1));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}
